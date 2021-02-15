package ifce.polo.sippi.security;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtTokenProvider
{
    @Autowired private Environment env;

/* --------------------------------------------------------------------------------------------- */

    public String generateToken(Long userId, String profileName, Long profileId)
    {
    	Map<String, Object> claims = new HashMap<>();

        claims.put("id", userId);

        if (profileId != null) {
            claims.put("pid", profileId);
        }

        if (profileName != null) {
            claims.put("profile", profileName);
        }
        
        return generateToken(userId, Integer.parseInt(env.getProperty("jwt.expiration.time")), claims);
    }

/* --------------------------------------------------------------------------------------------- */
    
    public String generateToken(Long userId, Integer expirationTime, Map<String, Object> claims)
    {
    	if (claims.get("id") == null) {
    		claims.put("id", userId);
    	}
    	
        Date issueDate = new Date();
        Date expirationDate = new Date(issueDate.getTime() + expirationTime);

        return Jwts.builder()
                .setSubject(userId.toString())
                .setClaims(claims)
                .setIssuedAt(issueDate)
                .setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.HS512, SecurityConstants.SECRET)
                .compact();
    }

/* --------------------------------------------------------------------------------------------- */

}
