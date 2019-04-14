package com.gierasimiuk.jwtrest.token;

import java.security.Key;
import java.sql.Date;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

import com.gierasimiuk.jwtrest.config.JwtSettings;
import com.gierasimiuk.jwtrest.model.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

/**
 * Factory class to be used to create {link @JwtAccessToken} and 
 * {link @JwRefreshToken} objects.
 * 
 * @author Michael Gierasimiuk
 */
public class JwtTokenFactory {
	
	/**
	 * Builds a new {link @JwtAccessToken} given a {link @User}.
	 * 
	 * @param user the user to build the token with. 
	 * @return the new token object, containing the raw token string.
	 */
    public JwtAccessToken createJwtAccessToken(User user) {
        Claims claims = Jwts.claims().setSubject(user.getUsername());
        claims.put("scopes", "ROLE_ACCESS");

        String token = constructJwt(user.getId(), claims, JwtSettings.ACCESS_TOKEN_EXPIRY);

        return new JwtAccessToken(token);
    }

    /**
     * Builds a new {link @JwtRefreshToken} given a {link @User}.
     * 
     * @param user the user to build the token with. 
	 * @return the new token object, containing the raw token string.
     */
    public JwtRefreshToken createJwtRefreshToken(User user) {
        Claims claims = Jwts.claims().setSubject(user.getUsername());
        claims.put("scopes", "ROLE_REFRESH");

        String token = constructJwt(user.getId(), claims, JwtSettings.REFRESH_TOKEN_EXPIRY);
        
        return new JwtRefreshToken(token);
    }

    // Generic inner function to construct a JWT
    private String constructJwt(String userId, Claims claims, long tokenExpiry) {
        Date now = new Date(System.currentTimeMillis());

        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(JwtSettings.SIGNING_KEY);
        Key singingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

        JwtBuilder builder = Jwts.builder().setId(userId)
                            .setIssuedAt(now)
                            .setIssuer(JwtSettings.ISSUER)
                            .signWith(singingKey)
                            .setHeaderParam("typ", "JWT");

        Date exp = new Date(now.getTime() + tokenExpiry);
        builder.setExpiration(exp);

        return builder.compact();
    }
}
