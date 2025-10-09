package com.castelaofp.biblioteca.dto;

import java.util.Objects;

public class UserDto {
    private String email;
    private String password;



    private UserRolEnum role;

    public UserDto() {}

    public UserDto(String username, String password, UserRolEnum role) {
        this.email = username;
        this.password = password;
        this.role = role;
    }


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

    public UserRolEnum getRole() {
        return role;
    }

    public void setRole(UserRolEnum role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserDto userDto = (UserDto) o;
        return Objects.equals(email, userDto.email) && Objects.equals(password, userDto.password) && role == userDto.role;
    }

    @Override
    public int hashCode() {
        return Objects.hash(email, password, role);
    }

    @Override
    public String toString() {
        return "UserDto{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                '}';
    }


}
