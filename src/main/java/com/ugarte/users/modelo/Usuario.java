package com.ugarte.users.modelo;

public class Usuario {

    private Integer id;
    private String nombre;
    private String password;
    private String email;

    public Usuario() {
    }

    @Override
    public String toString() {
        return  id + " | "
                + nombre + " | "
                + password + " | "
                + email;
    }

    public Usuario(Integer id, String nombre, String password, String email) {
        this.id = id;
        this.nombre = nombre;
        this.password = password;
        this.email = email;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
