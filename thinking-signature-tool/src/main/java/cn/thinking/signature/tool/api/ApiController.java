package cn.thinking.signature.tool.api;

import cn.thinking.signature.annotation.SignatureRequestRequired;
import cn.thinking.signature.annotation.SignatureResponseRequired;
import cn.thinking.signature.tool.pojo.ApiResponseBody;
import cn.thinking.signature.tool.pojo.RequestPojo;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Auther: ken.wangTJ
 * @Date: 7/18/2019 18:11
 * @Description:
 */
@RestController
@SignatureResponseRequired
public class ApiController {
    private static final Logger logger = LogManager.getLogger();

    
    /**
     * sign:
     * X6oMg2HHI4D5bdr8ZazDvEVO5R+RamnTotqz0ky9ASYE0HuvwoX1s1JBwL0nIdiEWkf9Jihw/h9K1N9+o0X/nzlvY0mmCIc8bQ6FNqOsqC09TdJGt6jk2rkmQTEA19y8Ct0vtTA/boV0CcIFI5mRtVkKbTEwdoJpKE1X98bmlh4=
     * @return
     */
    @ApiOperation(value = "receive information", notes = "receive information")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "receive information success")})
    @PatchMapping(value = "/v1/users", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @SignatureRequestRequired
    public ApiResponseBody<?> receiveContract(@RequestBody List<RequestPojo> list) {
        logger.info("prepare receive info");
        ApiResponseBody<String> response = new ApiResponseBody<String>();
        response.setCode("1");
        response.setMessage("Handler info Success!");
        return response;
    }
    
    @ApiOperation(value = "receive customer", notes = "receive customer")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "receive information success")})
    @GetMapping(value = "/v1/customer", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
//    @SignatureRequestRequired
    public ApiResponseBody<?> customer() {
        logger.info("prepare receive info");
        ApiResponseBody<String> response = new ApiResponseBody<String>();
        response.setCode("1");
        response.setMessage("Handler customer Success!");
        return response;
    }
}
