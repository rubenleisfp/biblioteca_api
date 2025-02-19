package com.castelaofp.biblioteca.repository;

import com.castelaofp.biblioteca.model.Admin;
import com.castelaofp.biblioteca.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AdminRepository extends JpaRepository<Admin, Long> {
    @Query("SELECT a FROM Admin a WHERE a.email = :email AND a.password = :password")
    Admin findByEmailAndPassword(@Param("email") String email, @Param("password") String password);
}

