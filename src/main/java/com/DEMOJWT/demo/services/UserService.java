package com.DEMOJWT.demo.services;

import com.DEMOJWT.demo.dto.User;
import com.DEMOJWT.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class UserService {
    @Autowired
    private UserRepository repository;

    public Flux<User> list() {
        return repository.findAll();
    }

    public Mono<User> obtenerUsuario(String user, String pwd){
        return repository.findByUserAndPwd(user,pwd);
    }



}
