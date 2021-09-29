package com.pj.offer.controller;

import com.pj.offer.config.security.TokenService;
import com.pj.offer.domain.dto.TokenDto;
import com.pj.offer.domain.form.LoginForm;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/authentication")
@Api(tags = {"Authentication"}, value = "Controller Authentication")
@CrossOrigin(origins = "*")
@Slf4j
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private TokenService tokenService;

    @PostMapping
    @ApiOperation(value="Post in order to generate Token")
    public ResponseEntity<?> authenticate(@RequestBody @Valid LoginForm loginForm){
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = loginForm.toConvert();
        try{
            Authentication authentication = authenticationManager.authenticate(usernamePasswordAuthenticationToken);
            String token = tokenService.gerarToken(authentication);
            log.info("'{}' : was generated!", token);
            log.warn("If data integrity is affected, then an exception will be thrown.");
            return ResponseEntity.ok(new TokenDto(token, "Bearer"));
        }catch(AuthenticationException authenticationException){
            return ResponseEntity.badRequest().build();
        }

    }
}
