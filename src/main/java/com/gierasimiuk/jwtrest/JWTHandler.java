package com.gierasimiuk.jwtrest;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.UUID;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

import com.gierasimiuk.jwtrest.model.User;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.JwtHandler;

/**
 * {@link JWTHandler} class. 
 * 
 * @author Michael Gierasimiuk
 */
public class JWTHandler {
    
	public static final int ACCESS_TOKEN_EXPIRY = 24 * 60 * 60 * 1000;         // 24 hours
    
    public static final int REFRESH_TOKEN_EXPIRY = ACCESS_TOKEN_EXPIRY * 7;    // 7 days
    
	private static final String SECRET_KEY = "oeRaYY7Wo24sDqKSX3IM9ASGmdGPmkTd9jo1QTy4b7P9Ze5_9hKolVX8xNrQDcNRfVEdTZNOuOyqEGhXEbdJI-ZQ19k_o9MI0y3eZN2lp9jow55FfXMiINEdt1XR85VipRLSOkT6kSpzs2x-jbLDiz9iFVzkd81YKxMgPA7VfZeQUm4n-mOmnWMaVX30zGFU4L3oPBctYKkl4dYfqYWqRNfrgPJVi5DGFjywgxx0ASEiJHtV72paI3fDR2XwlSkyhhmY-ICjCRmsJN4fX1pdoL8a18-aQrvyu4j0Os6dVPYIoPvvY0SAZtWYKHfM15g7A3HD4cVREf9cUsprCRK93w";
 
    private static final String ISSUER = "JwtRestApp";

    // private static final String TYPE_ACCESS = "access_token";
    
    // private static final String TYPE_REFRESH = "refresh_token";

    // private final HashMap<String, String> users = new HashMap<String, String>();

	/**
	 * Creates a new {@link JWTHandler}.
	 */
	public JWTHandler() {
    }

    public String create(String username, long ttl) {
        String access_token = this.createJWT(
            UUID.randomUUID().toString(), 
            JWTHandler.ISSUER,
            username, 
            ttl
        );
        return access_token;
    }

    public boolean verify(String jwt) {
        Claims claims = parseJWT(jwt);
        Date now = new Date(System.currentTimeMillis());
        if (claims.getExpiration().getTime() >= now.getTime()) {
            return true;
        }
        return false;
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
