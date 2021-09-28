package com.pj.offer.domain.form;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

@Getter
@Setter
public class LoginForm {

    private String username;
    private String password;

    public UsernamePasswordAuthenticationToken toConvert() {
        return new UsernamePasswordAuthenticationToken(username, password);
    }
}
