package com.pj.offer.config.security;

import com.pj.offer.domain.model.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class TokenService {

    @Value("${offer.jwt.expiration}")
    private String expiration;

    @Value("${offer.jwt.secret}")
    private String secret;

    public String gerarToken(Authentication authentication)
    {
        User logado = (User) authentication.getPrincipal();
        Date hoje = new Date();
        Date dateExpiration = new Date(hoje.getTime() + Long.parseLong(expiration));

        return Jwts.builder()
                .setIssuer("Offer").
                setSubject(logado.getIdUser().toString())
                .setIssuedAt(hoje)
                .setExpiration(dateExpiration).
                signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }

    public boolean isValidToken(String token){
        try{
            Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
            return true;
        } catch (Exception exception) {
            return false;
        }
    }

    public long getId(String token) {
        Claims claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
        return Long.parseLong(claims.getSubject());
    }

    public boolean isValid(String token) {
        try{
            Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token);
            return true;
        } catch (Exception exception) {
            return false;
        }
    }
}
