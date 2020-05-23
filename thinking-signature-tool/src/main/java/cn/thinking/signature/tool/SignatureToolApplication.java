package cn.thinking.signature.tool;

import cn.thinking.signature.annotation.EnableAutoSignatureConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@EnableAutoSignatureConfiguration
public class SignatureToolApplication {

    public static void main(String[] args) {
        SpringApplication.run(SignatureToolApplication.class, args);
    }
}

