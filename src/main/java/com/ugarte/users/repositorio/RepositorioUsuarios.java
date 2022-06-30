package com.ugarte.users.repositorio;

import com.ugarte.users.modelo.Usuario;
import com.ugarte.users.util.Conexion;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RepositorioUsuarios implements Repositorio<Usuario>{

    private Connection getConnection() throws SQLException {
        return Conexion.getInstance();
    }

    @Override
    public List<Usuario> listar() {
        List<Usuario> usuarios = new ArrayList<>();
        try(Statement stmt = getConnection().createStatement();
            ResultSet resultSet = stmt.executeQuery("SELECT * FROM  users")) {
            while(resultSet.next()){
                Usuario user = crearUsuario(resultSet);
                usuarios.add(user);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } ;

        return usuarios;
    }

    @Override
    public Usuario buscarId(Integer id) {
        Usuario user = null;
        try(PreparedStatement stmt = getConnection().prepareStatement("SELECT FROM users WHERE id = ?")) {
            stmt.setInt(1, id);
            try(ResultSet resultSet = stmt.executeQuery()){
                if(resultSet.next()){
                        user = crearUsuario(resultSet);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return user;
    }

    @Override
    public void guardar(Usuario usuario) {
        String sql;
        if(usuario.getId() != null && usuario.getId() > 0){
            sql="UPDATE users SET nombre = ?, password = ?, email = ? WHERE id = ?";
        }else{
            sql="INSERT INTO users (nombre, password, email) VALUES (?,?,?)";
        }

        try(PreparedStatement stmt = getConnection().prepareStatement(sql)){
            stmt.setString(1, usuario.getNombre());
            stmt.setString(2, usuario.getPassword());
            stmt.setString(3, usuario.getEmail());

            if(usuario.getId() != null && usuario.getId() > 0){
                stmt.setInt(4, usuario.getId());
            }

            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void eliminar(Integer id) {
        try (PreparedStatement stmt = getConnection().prepareStatement("DELETE FROM users WHERE id = ?")){
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    private Usuario crearUsuario(ResultSet resultSet) throws SQLException {

        Usuario user = new Usuario();
        user.setId(resultSet.getInt("id"));
        user.setNombre(resultSet.getString("nombre"));
        user.setPassword(resultSet.getString("password"));
        user.setEmail(resultSet.getString("email"));
        return user;
    }
}
