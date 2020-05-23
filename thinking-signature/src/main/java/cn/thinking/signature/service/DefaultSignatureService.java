package cn.thinking.signature.service;

import cn.thinking.signature.constans.Constant;
import cn.thinking.signature.pojo.Client;
import cn.thinking.signature.pojo.SignatureData;
import cn.thinking.signature.utils.SignatureUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DefaultSignatureService implements SignatureService {
    private static final Logger logger = LoggerFactory.getLogger(DefaultSignatureService.class);

    @Override
    public boolean doCheck(Client client, SignatureData signatureData) {
        StringBuffer sb = new StringBuffer();
        sb.append(signatureData.getHttpMethod());
        sb.append(signatureData.getUri());
        if (StringUtils.isNotEmpty(signatureData.getHttpQueryString())) {
            sb.append(signatureData.getHttpQueryString());
        }
        String httpBody = signatureData.getHttpBody();
        if (StringUtils.isNotEmpty(httpBody)) {
            sb.append(httpBody);
        }
        return SignatureUtils.isSignatureValidated(client.getClientPublicKey(), sb.toString(), signatureData.getSignature());
    }

    @Override
    public String signatureString(Client client, SignatureData signatureData) {
        StringBuilder sb = new StringBuilder();
        sb.append(signatureData.getHttpStatus());
        String location = signatureData.getHttpLocation();
        if (StringUtils.isNotEmpty(location)) {
            sb.append(location);
        }
        String httpBody = signatureData.getHttpBody();
        if (StringUtils.isNotEmpty(httpBody)) {
            sb.append(httpBody);
        }
        logger.info(Constant.LOG_PREFIX + " -- before signature data is {}", sb.toString());
        return SignatureUtils.generateSignature(client.getServerPrivateKey(), sb.toString());
    }
}
