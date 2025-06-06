package internship.intern.Authentication.AuthServices;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {

    private static final String SECRECT_KEY="BF7FD11ACE545745B7BA1AF98B6F156D127BC7BB544BAB6A4FD74E4FC7";
    
    public String extractUsername(String token){
        return extractClaim(token, Claims::getSubject);
    }

    public <T> T extractClaim(String token, Function<Claims,T> claimsResolver){
        final Claims claims= extractAllClaims(token);
        return claimsResolver.apply(claims);
    }


    private Claims extractAllClaims(String token){
        System.out.println("Token received for parsing: " + token); // ✅ Add this line
        return Jwts
        .parserBuilder()
        .setSigningKey(getSignInKey())
        .build()
        .parseClaimsJws(token)
        .getBody();

    }

    //decode and get key 
    private Key getSignInKey(){
        byte[] keyBytes= Decoders.BASE64.decode(SECRECT_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }
    
public String generateToken(UserDetails userDetails){
    return generateToken(new HashMap<>(), userDetails);
}

public String generateToken(Map<String, Object>extraClaims,
                         UserDetails userDetails){
    extraClaims= new HashMap<>(extraClaims);
    extraClaims.put("role", userDetails.getAuthorities());

    return  Jwts
    .builder()
    .setClaims(extraClaims)
    .setSubject(userDetails.getUsername())
    .setIssuedAt(new Date(System.currentTimeMillis()))
    .setExpiration(new Date(System.currentTimeMillis()+25*100000))
    .signWith(getSignInKey(),SignatureAlgorithm.HS256)
    .compact();

}

//if token is valid  by checking

public boolean isTokenValid(String token, UserDetails userDetails){
    final String username= extractUsername(token);
    return (username.equals(userDetails.getUsername()) && !isToeknExpired(token));
}
public boolean isToeknExpired(String token){
    return extractExpiretion(token).before(new Date());
}

private Date extractExpiretion(String token){
    return extractClaim(token,Claims::getExpiration);
}

}
