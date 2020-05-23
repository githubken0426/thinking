package cn.thinking.signature.manager;

import cn.thinking.signature.constans.Constant;
import cn.thinking.signature.pojo.Client;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Base64;
import java.util.List;

/**
 * ignoreUnknownFields 在有属性不能匹配到声明的域的时候是否抛出异常
 * ignoreInvalidFields 当我们为属性配置错误的值时，而又不希望 Spring Boot 应用启动失败，
 *                      设置ignoreInvalidFields 属性为 true (默认为 false)
 *                      比如字段类型为Integer,yml中设置为字符串，默认会抛出异常
 */
@Component
@ConfigurationProperties(prefix = Constant.CONFIGURE_PREFIX)
public class BasicClientManager implements ClientManager {
    List<Client> clients;

    public void setClients(List<Client> clients) {
        this.clients = clients;
    }

    @Override
    public Client getClientByToken(String token) {
        try {
            String[] split = new String(Base64.getDecoder().decode(token.substring(6))).split(":");
            for (int i = 0; i < clients.size(); i++) {
                if (split[0].equals(clients.get(i).getName())) {
                    return clients.get(i);
                }
            }
        } catch (SecurityException e) {
            throw new SecurityException("Signature verification failed");
        }
        return null;
    }

    @Override
    public Client getClientByUsername(String userName) {
        for (int i = 0; i < clients.size(); i++) {
            if (userName.equals(clients.get(i).getName())) {
                return clients.get(i);
            }
        }
        return null;
    }
}
