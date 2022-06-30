package com.ugarte.users;

import com.ugarte.users.modelo.Usuario;
import com.ugarte.users.repositorio.Repositorio;
import com.ugarte.users.repositorio.RepositorioUsuarios;

import javax.swing.*;
import java.util.HashMap;
import java.util.Map;

public class AdministradorUsuarios {
    public static void main(String[] args) {
        Repositorio<Usuario> repositorio = new RepositorioUsuarios();

        int opcionIndice = 0;
        Map<String, Integer> operaciones = new HashMap();
        operaciones.put("Actualizar", 1);
        operaciones.put("Eliminar", 2);
        operaciones.put("Agregar", 3);
        operaciones.put("Listar", 4);
        operaciones.put("Salir", 5);

        Object[] opArreglo = operaciones.keySet().toArray();

        Object opcion = JOptionPane.showInputDialog(null,
                "Seleccione un Operación",
                "Mantenedor de Usuarios",
                JOptionPane.INFORMATION_MESSAGE, null, opArreglo, opArreglo[0]);

        int indiceId;

        if (opcion == null) {
            JOptionPane.showMessageDialog(null, "Debe seleccionar una operación");
        } else {
            opcionIndice = operaciones.get(opcion.toString());

            switch (opcionIndice){

                case 1: Usuario userActualizar = new Usuario();
                userActualizar.setId(Integer.valueOf(JOptionPane.showInputDialog("Escribe el id del usuario a actualizar")));
                userActualizar.setNombre(JOptionPane.showInputDialog("Escribe el nuevo nombre del usuario:"));
                userActualizar.setPassword(JOptionPane.showInputDialog("Escribe el nuevo password del usuario:"));
                userActualizar.setEmail(JOptionPane.showInputDialog("Escribe el nuevo email del usuario:"));
                repositorio.guardar(userActualizar);
                JOptionPane.showMessageDialog(null, "El usuario se actualizo correctamente");
                main(args);

                case 2: Integer idEliminar = Integer.valueOf(JOptionPane.showInputDialog("Escribe el id del usuario que quieres eliminar"));
                repositorio.eliminar(idEliminar);
                JOptionPane.showMessageDialog(null,"El usuario se ha eliminado correctamente");
                main(args);

                case 3: Usuario userAgregar = new Usuario();
                    userAgregar.setNombre(JOptionPane.showInputDialog("Escribe el nombre del usuario:"));
                    userAgregar.setPassword(JOptionPane.showInputDialog("Escribe el password del usuario:"));
                    userAgregar.setEmail(JOptionPane.showInputDialog("Escribe el email del usuario:"));
                    repositorio.guardar(userAgregar);
                    JOptionPane.showMessageDialog(null, "El usuario se agrego correctamente");
                    main(args);

                case 4: repositorio.listar().forEach(user -> JOptionPane.showMessageDialog(null, user));
                main(args);

                case 5: JOptionPane.showMessageDialog(null, "Usted ha salido correctamente del sistema");
                System.exit(0);
            }
        }
    }
}
