package cn.thinking.signature.tool.controller;

import cn.thinking.signature.tool.enumration.PartnerEnum;
import cn.thinking.signature.tool.service.SignatureToolService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/oauth")
public class OAuthController {
    private static final Logger logger = LogManager.getLogger();
    @Autowired
    private SignatureToolService signatureToolService;

    @GetMapping("/clients")
    public Map<String, String> getClients() {
        Map<String, String> map = new HashMap<>();
        for (PartnerEnum partnerEnum : PartnerEnum.values()) {
            map.put(partnerEnum.getClientId(), partnerEnum.getName());
        }
        return map;
    }

    @GetMapping("/tokens")
    public void generateAccessToken(@RequestParam("clientId") String clientId, HttpServletResponse response) throws Exception {
        String token = signatureToolService.generatorClientToken(clientId);
        logger.info("Generator toke is: {}", token);
        response.getWriter().write(token);
    }
}

