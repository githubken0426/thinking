package cn.thinking.signature;

import cn.thinking.signature.manager.BasicClientManager;
import cn.thinking.signature.manager.ClientManager;
import cn.thinking.signature.service.DefaultSignatureService;
import cn.thinking.signature.service.SignatureService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SignatureConfig {

    @Bean
    @ConditionalOnMissingBean(ClientManager.class)
    ClientManager clientManager() {
        return new BasicClientManager();
    }

    @Bean
    @ConditionalOnMissingBean(SignatureService.class)
    SignatureService signatureService() {
        return new DefaultSignatureService();
    }
}
