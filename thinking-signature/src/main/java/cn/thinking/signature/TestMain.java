package cn.thinking.signature;

import cn.thinking.signature.utils.SignatureUtils;

import java.io.File;
import java.nio.file.Path;
import java.security.PrivateKey;

public class TestMain {

    public static void main(String[] args) throws Exception {
        Path path = new File("C:\\WorkSpace\\thinking\\thinking-conf\\src\\main\\resources\\conf\\client-private.pem").toPath();
        PrivateKey privateKey = SignatureUtils.exportPrivateKey(path);
        String content = "POST/thinking/v1/applications[{\"user_name\":\"张三\"}]";
        String sign = SignatureUtils.generateSignature(privateKey, content);
        System.out.println(sign);
    }
}
