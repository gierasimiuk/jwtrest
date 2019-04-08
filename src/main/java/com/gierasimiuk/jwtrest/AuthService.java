package com.gierasimiuk.jwtrest;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;

/**
 * {@link AuthService} class. 
 * 
 * @author Michael Gierasimiuk
 */
public class AuthService {
    
	public static final int ACCESS_TOKEN_EXPIRY = 24 * 60 * 60 * 1000;         // 24 hours
    public static final int REFRESH_TOKEN_EXPIRY = ACCESS_TOKEN_EXPIRY * 7;    // 7 days
    
    private static final String SECRET_KEY = "oeRaYY7Wo24sDqKSX3IM9ASGmdGPmkTd9jo1QTy4b7P9Ze5_9hKolVX8xNrQDcNRfVEdTZNOuOyqEGhXEbdJI-ZQ19k_o9MI0y3eZN2lp9jow55FfXMiINEdt1XR85VipRLSOkT6kSpzs2x-jbLDiz9iFVzkd81YKxMgPA7VfZeQUm4n-mOmnWMaVX30zGFU4L3oPBctYKkl4dYfqYWqRNfrgPJVi5DGFjywgxx0ASEiJHtV72paI3fDR2XwlSkyhhmY-ICjCRmsJN4fX1pdoL8a18-aQrvyu4j0Os6dVPYIoPvvY0SAZtWYKHfM15g7A3HD4cVREf9cUsprCRK93w";
    private static final String ISSUER = "JwtRestApp";

    private final Map<String, String> accessTokens = new HashMap<String, String>();
    private final Map<String, String> refreshTokens = new HashMap<String, String>();

	/**
	 * Creates a new {@link AuthService}.
	 */
	public AuthService() {
    }

    public Map<String, String> auth(String username) {
        Map<String, String> tokens = new HashMap<String, String>();
        String accessToken = this.createAccessToken(username);
        String refreshToken = this.createRefreshToken(username);
        tokens.put("access_token", accessToken);
        tokens.put("refresh_token", refreshToken);
        accessTokens.put(username, accessToken);
        refreshTokens.put(username, refreshToken);
        return tokens;
    }

    public String refresh(String username, String refreshToken) {
        String token = createAccessToken(username);
        accessTokens.put(username, token);
        return token;
    }

    public boolean isRefreshTokenValid(String username, String refreshToken) {
        if (refreshTokens.containsKey(username)) {
            String existingToken = refreshTokens.get(username);
            if (refreshToken.equals(existingToken)) {
                return verifyExp(refreshToken) && verifySub(username, refreshToken);
            } 
        } 
        return false;
    }

    public boolean isAccessToken(String token) {
        Claims claims = parseJWT(token);
        String username = claims.getSubject();
        String accessToken = this.accessTokens.get(username);
        if (accessToken.equals(token)) {
            return true;
        }
        return false;
    }
    
    public boolean verifyExp(String token) {
        Claims claims = parseJWT(token);
        Date now = new Date(System.currentTimeMillis());
        if (claims.getExpiration().getTime() >= now.getTime()) {
            return true;
        }
        return false;
    }

    public boolean verifySub(String username, String token) {
        Claims claims = parseJWT(token);
        if (claims.getSubject().equals(username)) {
            return true;
        }
        return false;
    }

    private String createAccessToken(String username) {
        String accessToken = this.createJWT(
            UUID.randomUUID().toString(), 
            AuthService.ISSUER,
            username, 
            AuthService.ACCESS_TOKEN_EXPIRY
        );
        accessTokens.put(username, accessToken);
        return accessToken;
    }

    private String createRefreshToken(String username) {
        String refreshToken = this.createJWT(
            UUID.randomUUID().toString(), 
            AuthService.ISSUER,
            username, 
            AuthService.REFRESH_TOKEN_EXPIRY
        );
        refreshTokens.put(username, refreshToken);
        return refreshToken;
    }

	private String createJWT(String id, String issuer, String subject, long ttl) {
	    Date now = new Date(System.currentTimeMillis());
	    SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
	    byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(SECRET_KEY);
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());
	    JwtBuilder builder = Jwts.builder().setId(id)
	                                .setIssuedAt(now)
	                                .setSubject(subject)
	                                .setIssuer(issuer)
                                    .signWith(signingKey)
                                    .setHeaderParam("typ", "JWT");
        Date exp = new Date(now.getTime() + ttl);
        builder.setExpiration(exp);
	    return builder.compact();
	}

	private Claims parseJWT(String jwt) {
		Claims claims = Jwts.parser()       
		    .setSigningKey(DatatypeConverter.parseBase64Binary(SECRET_KEY))
		    .parseClaimsJws(jwt).getBody();
        return claims;
    }
}
