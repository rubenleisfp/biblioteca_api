package com.fp.biblioteca.repository;

import com.fp.biblioteca.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Long> {
    @Query("SELECT a FROM User a WHERE a.email = :email AND a.password = :password")
    User findByEmailAndPassword(@Param("email") String email, @Param("password") String password);
}

