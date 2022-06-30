package com.ugarte.users.repositorio;

import java.util.List;

public interface Repositorio<R> {

    List<R> listar();
    R buscarId(Integer id);
    void guardar(R r);
    void eliminar(Integer id);
}
