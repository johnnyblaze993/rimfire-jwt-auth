package com.rimfire.repositories;

import com.rimfire.entities.Users;
import io.micronaut.data.jdbc.annotation.JdbcRepository;
import io.micronaut.data.model.query.builder.sql.Dialect;
import io.micronaut.data.repository.CrudRepository;
import java.util.List;
//import the query
import io.micronaut.data.annotation.Query;
import java.util.UUID;

@JdbcRepository(dialect = Dialect.POSTGRES)
public interface UsersRepository extends CrudRepository<Users, UUID> {

    @Query("SELECT * FROM rimfirede.users WHERE username = :username")
    Users findByUsername(String username); // make sure 'username' matches the case of your entity field
    

    
    @Query("SELECT * FROM rimfirede.users")
    List<Users> getAllUsers();
}
