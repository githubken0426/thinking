package cn.thinking.signature.aop;

import cn.thinking.signature.constans.Constant;
import cn.thinking.signature.manager.ClientManager;
import cn.thinking.signature.manager.MediaTypeHandleContext;
import cn.thinking.signature.pojo.Client;
import cn.thinking.signature.pojo.SignatureData;
import cn.thinking.signature.service.SignatureService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.nio.file.AccessDeniedException;

@Aspect
@Component
public class SignatureAnnotationAspect {
    private static final Logger logger = LoggerFactory.getLogger(SignatureAnnotationAspect.class);
    @Autowired
    private HttpServletRequest httpRequest;
    @Autowired
    private HttpServletResponse httpResponse;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private ClientManager clientManager;
    @Autowired
    private SignatureService signatureService;
    @Autowired
    private MediaTypeHandleContext mediaTypeHandleContext;

    @Pointcut("@annotation(cn.thinking.signature.annotation.SignatureRequestRequired)")
    private void handleRequest() {
    }

    @Pointcut("execution(public * *(..)) && within(@cn.thinking.signature.annotation.SignatureResponseRequired *)")
    private void handleResponse() {
    }


    @Before(value = "handleRequest()")
    public void interceptor(JoinPoint joinPoint) throws Exception {
        logger.info(Constant.LOG_PREFIX + " -- signature request data start");
        if (StringUtils.isEmpty(httpRequest.getHeader(Constant.HEADER_SIGNATURE))) {
            logger.warn(Constant.LOG_PREFIX + " --  parameter Head-Signature is null");
            throw new AccessDeniedException(Constant.SIGN_VERIFICATION_FAIL);
        }
        if (StringUtils.isEmpty(httpRequest.getHeader(Constant.AUTHORIZATION))) {
            logger.warn(Constant.LOG_PREFIX + " --  parameter Authorization is null");
            throw new AccessDeniedException(Constant.SIGN_VERIFICATION_FAIL);
        }
        Client client = clientManager.getClientByToken(httpRequest.getHeader(Constant.AUTHORIZATION));
        if (client == null) {
            logger.warn(Constant.LOG_PREFIX + " --  not found user according to Authorization" + httpRequest.getHeader(Constant.AUTHORIZATION));
            throw new AccessDeniedException(Constant.SIGN_VERIFICATION_FAIL);
        }
        // build request data:SignatureData
        SignatureData requestData = new SignatureData();
        requestData.setSignature(httpRequest.getHeader(Constant.HEADER_SIGNATURE));
        requestData.setUri(httpRequest.getRequestURI());
        requestData.setHttpMethod(httpRequest.getMethod());
        if (HttpMethod.GET.name().equals(httpRequest.getMethod())) {
            requestData.setHttpQueryString(httpRequest.getQueryString());
        } else {
            if (httpRequest.getContentType() != null) {
                //httpRequest.getContentType().indexOf(MediaType.MULTIPART_FORM_DATA_VALUE) > -1
                MediaType mediaType = MediaType.valueOf(httpRequest.getContentType());
                mediaTypeHandleContext.handle(mediaType, requestData, httpRequest);
            }
        }
        logger.info(Constant.LOG_PREFIX + " --  parameter is：" + requestData.toString());
        boolean isSuccess = signatureService.doCheck(client, requestData);
        logger.info(Constant.LOG_PREFIX + " -- signature result is " + isSuccess);
        if (!isSuccess) {
            throw new AccessDeniedException(Constant.SIGN_VERIFICATION_FAIL);
        }
    }

    /**
     * response加签
     *
     * @param joinPoint
     * @param returnValue
     * @throws Exception
     */
    @AfterReturning(value = "handleRequest() || handleResponse()", returning = "returnValue")
    public void handleReturnData(JoinPoint joinPoint, Object returnValue) throws Exception {
        logger.info(Constant.LOG_PREFIX + "  -- signature response start");
        Client client = clientManager.getClientByToken(httpRequest.getHeader(Constant.AUTHORIZATION));
        if (client == null) {
            logger.warn(Constant.LOG_PREFIX + " --  not found user according to Authorization" + httpRequest.getHeader(Constant.AUTHORIZATION));
            throw new AccessDeniedException(Constant.SIGN_FAIL);
        }
        logger.info(Constant.LOG_PREFIX + " -- According to token get vendor result ：{}", client.getName());
        // response status will have different value, it depends on below logic
        int status = httpResponse.getStatus();

        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        if (method.isAnnotationPresent(ResponseStatus.class)) {
            ResponseStatus responseStatus = method.getAnnotation(ResponseStatus.class);
            HttpStatus httpStatus = responseStatus.value();
            status = httpStatus != null ? httpStatus.value() : status;
        }
        // signature
        SignatureData responseData = new SignatureData();
        if (returnValue != null) {
            String body = null; // Body
            if (returnValue instanceof String) {
                body = (String) returnValue;
            } else if (returnValue instanceof ResponseEntity) {
                ResponseEntity<?> responseEntity = (ResponseEntity<?>) returnValue;
                status = responseEntity.getStatusCodeValue();
                if (responseEntity.getBody() != null) {
                    body = objectMapper.writeValueAsString(responseEntity.getBody());
                }
            } else {
                body = objectMapper.writeValueAsString(returnValue);
            }
            if (body != null) {
                responseData.setHttpBody(body);
            }
        }
        // Headers
        if (status == HttpStatus.CREATED.value()) {
            responseData.setHttpLocation(httpResponse.getHeader(HttpHeaders.LOCATION));
        }
        responseData.setHttpStatus(String.valueOf(status));
         String signature = signatureService.signatureString(client, responseData);
        logger.info(Constant.LOG_PREFIX + " -- ： after signature data is {}", signature);
        httpResponse.addHeader(Constant.HEADER_SIGNATURE, signature);
    }
}
