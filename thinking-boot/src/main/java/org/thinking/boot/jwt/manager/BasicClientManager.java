package org.thinking.boot.jwt.manager;

import java.util.Base64;
import java.util.List;
import java.util.function.Predicate;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.thinking.boot.jwt.Constants;
import org.thinking.boot.jwt.pojo.JwtClient;

/**
 * ignoreUnknownFields 在有属性不能匹配到声明的域的时候是否抛出异常(默认为 true) ignoreInvalidFields
 * 当我们为属性配置错误的值时，而又不希望 Spring Boot 应用启动失败， 设置ignoreInvalidFields 属性为 true (默认为
 * false) 比如字段类型为Integer,yml中设置为字符串，默认会抛出异常
 */
@Component
@ConfigurationProperties(prefix = Constants.JWT_YML_PREFIX, ignoreInvalidFields = false, ignoreUnknownFields = true)
public class BasicClientManager implements ClientManager {
	List<JwtClient> jwtClients;

	public void setClients(List<JwtClient> jwtClients) {
		this.jwtClients = jwtClients;
	}

	@Override
	public JwtClient getClientByToken(String token) {
		try {
			String[] split = new String(Base64.getDecoder().decode(token.substring(6))).split(":");
			for (int i = 0; i < jwtClients.size(); i++) {
				if (split[0].equals(jwtClients.get(i).getIssuer())) {
					return jwtClients.get(i);
				}
			}
		} catch (SecurityException e) {
			throw new SecurityException("Signature verification failed");
		}
		return null;
	}

	@Override
	public JwtClient getClientByIssuer(String issuer) {
		Predicate<JwtClient> predicate = item -> issuer.equals(item.getIssuer());
		return jwtClients.stream().filter(predicate).findFirst().orElse(null);
	}
}
