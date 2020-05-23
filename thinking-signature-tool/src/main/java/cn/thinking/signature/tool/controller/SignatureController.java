package cn.thinking.signature.tool.controller;

import cn.thinking.signature.pojo.SignatureData;
import cn.thinking.signature.tool.pojo.SignatureRequest;
import cn.thinking.signature.tool.pojo.SignatureResponse;
import cn.thinking.signature.tool.service.SignatureToolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/signature")
public class SignatureController {
    @Autowired
    private SignatureToolService signatureToolService;

    @PostMapping
    public SignatureResponse generateSignature(@RequestBody SignatureRequest signatureRequest) {
        SignatureData signatureData = new SignatureData();
        signatureData.setHttpMethod(signatureRequest.getRequestMethod());
        signatureData.setUri(signatureRequest.getRequestUrl());
        signatureData.addItemToHttpBody(signatureRequest.getRequestBody());
        return signatureToolService.generateSignature(signatureRequest.getClientId(), signatureData);
    }
}
