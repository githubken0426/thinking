package cn.thinking.signature.utils;

import cn.thinking.signature.aop.SignatureAnnotationAspect;
import cn.thinking.signature.constans.Constant;
import cn.thinking.signature.manager.ClientManager;
import cn.thinking.signature.pojo.Client;
import cn.thinking.signature.service.SignatureService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.nio.file.AccessDeniedException;

@Component
public class SignatureFacade {
    private static final Logger logger = LoggerFactory.getLogger(SignatureAnnotationAspect.class);

    @Autowired
    private ClientManager clientManager;
    @Autowired
    SignatureService signatureService;

    /**
     * 请求vendor加签方法
     * @param userName
     * @param data
     * @return
     * @throws Exception
     */
    public String signData(String userName, String data) throws Exception {
        Client client = clientManager.getClientByUsername(userName);
        if (client == null) {
            logger.warn(Constant.LOG_PREFIX + " --  not found user according to Authorization --- {}", userName);
            throw new AccessDeniedException(Constant.SIGN_VERIFICATION_FAIL);
        }
        return SignatureUtils.generateSignature(client.getServerPrivateKey(), data);
    }

    /**
     * 请求vendor返回验签
     * @param auth
     * @param unSignData
     * @param signData
     * @return
     * @throws Exception
     */
    public boolean checkSign(String auth, String unSignData, String signData) throws Exception {
        String token = "basic " + auth;
        Client client = clientManager.getClientByToken(token);
        if (client == null) {
            throw new AccessDeniedException(Constant.SIGN_VERIFICATION_FAIL);
        }
        return SignatureUtils.isSignatureValidated(client.getClientPublicKey(), unSignData, signData);
    }

}
