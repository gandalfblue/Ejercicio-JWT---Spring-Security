package com.DEMOJWT.demo.controller;

import com.DEMOJWT.demo.dto.User;
import com.DEMOJWT.demo.services.UserService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class UserController {

    @Autowired
    UserService servicioUsuario;

    @GetMapping("/user")
    public Mono<ResponseEntity<User>> login(@RequestParam("user") String username, @RequestParam("pwd") String pwd) {
        String token = getJWTToken(username);

        return servicioUsuario.obtenerUsuario(username, pwd)
                .filter(elemento -> {
                    if (elemento.getUser().equalsIgnoreCase(username) && elemento.getPwd().equalsIgnoreCase(pwd)) {
                        elemento.setToken(token);
                        return true;
                    }
                   return false;
                })
                .map(element -> ResponseEntity.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(element))
                .switchIfEmpty(Mono.error(new RuntimeException("El usuario no existe vuelva mas tarde")));
    }

    private String getJWTToken(String username) {
        String secretKey = "mySecretKey";
        List<GrantedAuthority> grantedAuthorities = AuthorityUtils
                .commaSeparatedStringToAuthorityList("ROLE_USER");

        String token = Jwts
                .builder()
                .setId("sofkaJWT")
                .setSubject(username)
                .claim("authorities",
                        grantedAuthorities.stream()
                                .map(GrantedAuthority::getAuthority)
                                .collect(Collectors.toList()))
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 600000))
                .signWith(SignatureAlgorithm.HS512,
                        secretKey.getBytes()).compact();

        return "Valido " + token;
    }
}
