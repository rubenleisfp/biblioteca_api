package com.castelaofp.biblioteca.dto;

public class AdminDto {
    private String email;
    private String password;

    public AdminDto() {}

    public AdminDto(String username, String password) {
        this.email = username;
        this.password = password;
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

    @Override
    public int hashCode() {
        return email.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        AdminDto admin = (AdminDto) obj;
        return email.equals(admin.email);
    }

    @Override
    public String toString() {
        return "Admin{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
