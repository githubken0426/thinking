package org.thinking.boot.jwt.manager;

import org.thinking.boot.jwt.pojo.JwtClient;

public interface ClientManager {
	JwtClient getClientByToken(String token);

	JwtClient getClientByIssuer(String issuer);
}
