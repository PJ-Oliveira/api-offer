package com.pj.offer.config.security;

import com.pj.offer.domain.model.User;
import com.pj.offer.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.NoSuchElementException;

@AllArgsConstructor
public class AuthenticationByTokenFilter extends OncePerRequestFilter {

    private TokenService tokenService;
    private UserRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest,
                                    HttpServletResponse httpServletResponse,
                                    FilterChain filterChain) throws ServletException, IOException {
        String token = toRecoverToken(httpServletRequest);
        boolean valid = tokenService.isValid(token);
        if(valid){
            authenticate(token);

        }
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }

    private void authenticate (String token){
        Long id = tokenService.getId(token);
        try {
            User user = userRepository.findById(id).get();
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken
                    (user.getIdUser(), null, user.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        catch (NoSuchElementException noSuchElementException) {
            System.out.println("Forbid Acess" + "Invalid Token");
            //noSuchElementException.printStackTrace("Forbid Acess" + "Invalid Token");
        }
    }

    private String toRecoverToken(HttpServletRequest httpServletRequest) {
        String token = httpServletRequest.getHeader("Authorization");
        if (token == null || token.isEmpty() || !token.startsWith("Bearer")) {
            return null;
        }
        return token.substring(7, token.length());
    }


}
