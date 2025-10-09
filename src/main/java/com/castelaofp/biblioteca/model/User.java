package com.castelaofp.biblioteca.model;

import jakarta.persistence.*;

import java.util.Objects;


@Entity
public class User {

    @Id
    private String email;
    @Column(name = "password")
    private String password;
    @Column(name="rol")
    private String rol;

    public String getEmail() {
        return email;
    }

    public void setEmail(String username) {
        this.email = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(email, user.email) && Objects.equals(password, user.password) && Objects.equals(rol, user.rol);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email, password, rol);
    }

    @Override
    public String toString() {
        return "User{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", rol='" + rol + '\'' +
                '}';
    }
}
