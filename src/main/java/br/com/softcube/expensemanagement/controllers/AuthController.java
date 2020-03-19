package br.com.softcube.expensemanagement.controllers;

import br.com.softcube.expensemanagement.models.dtos.TokenDTO;
import br.com.softcube.expensemanagement.models.forms.UserCredentialsForm;
import br.com.softcube.expensemanagement.services.security.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService serviceToken;

    @PostMapping
    public ResponseEntity<TokenDTO> authenticate(@RequestBody @Valid UserCredentialsForm userCredentials) {
        UsernamePasswordAuthenticationToken loginData = userCredentials.convert();

        try {
            Authentication authentication = authenticationManager.authenticate(loginData);

            String token = serviceToken.generateTokenFor(authentication);

            return ResponseEntity.ok(new TokenDTO("Bearer", token));
        } catch (AuthenticationException e) {
            return ResponseEntity.badRequest().build();
        }
    }

}
