package com.rimfire.controllers;

import com.rimfire.entities.Users;
import com.rimfire.repositories.UsersRepository;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.*;

import jakarta.inject.Inject;
import java.util.List;
import java.util.Optional;
//import java.util.UUID;

import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;


@Secured(SecurityRule.IS_ANONYMOUS)
@Controller("/users")
public class UsersController {

    @Inject
    UsersRepository usersRepository;

    @Get
    @Produces(MediaType.APPLICATION_JSON)
    public List<Users> getAllUsers() {
        return usersRepository.getAllUsers();
    }

    @Get("/{username}")
    @Produces(MediaType.APPLICATION_JSON)
    public Optional<Users> findByUsername(String username) {
        return Optional.ofNullable(usersRepository.findByUsername(username));
    }

    // @Post
    // @Consumes(MediaType.APPLICATION_JSON)
    // public void createUser(@Body Users user) {
    //     usersRepository.save(user);
    // }

    // @Put("/{id}")
    // @Consumes(MediaType.APPLICATION_JSON)
    // public void updateUser(@PathVariable UUID id, @Body Users user) {
    //     if(usersRepository.existsById(id)) {
    //         user.setUserId(id);
    //         usersRepository.update(user);
    //     }
    // }

    // @Delete("/{id}")
    // public void deleteUser(@PathVariable UUID id) {
    //     usersRepository.deleteById(id);
    // }
}
