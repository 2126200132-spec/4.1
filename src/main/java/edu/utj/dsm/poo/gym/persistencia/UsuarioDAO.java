package edu.utj.dsm.poo.gym.persistencia;

import edu.utj.dsm.poo.gym.modelo.SesionUsuario;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {

    public static class UsuarioBD {
        private int id;
        private String nombre;
        private String email;
        private String password;
        private String rol;
        private String estado;

        public UsuarioBD(int id, String nombre, String email, String password, String rol, String estado) {
            this.id = id;
            this.nombre = nombre;
            this.email = email;
            this.password = password;
            this.rol = rol;
            this.estado = estado;
        }

        public int getId() { return id; }
        public String getNombre() { return nombre; }
        public String getEmail() { return email; }
        public String getPassword() { return password; }
        public String getRol() { return rol; }
        public String getEstado() { return estado; }
    }

    private static final List<UsuarioBD> usuariosBD = new ArrayList<>();

    static {
        usuariosBD.add(new UsuarioBD(1, "Admin Principal", "admin@gym.com", "1234", "Administrador", "Activo"));
        usuariosBD.add(new UsuarioBD(2, "Carlos Propietario", "propietario@gym.com", "1234", "Propietario", "Activo"));
        usuariosBD.add(new UsuarioBD(3, "Juan Cliente", "cliente@gym.com", "1234", "Cliente", "Activo"));
        usuariosBD.add(new UsuarioBD(4, "Entrenador Mike", "entrenador@gym.com", "1234", "Entrenador", "Activo"));
        usuariosBD.add(new UsuarioBD(5, "Usuario Inactivo", "inactivo@gym.com", "1234", "Cliente", "Inactivo"));
    }

    public boolean autenticar(String usuarioOrEmail, String password) {
        for (UsuarioBD u : usuariosBD) {
            boolean coincideIdentificador = String.valueOf(u.getId()).equals(usuarioOrEmail.trim())
                                          || u.getEmail().equalsIgnoreCase(usuarioOrEmail.trim());
            boolean coincidePass = u.getPassword().equals(password.trim());

            if (coincideIdentificador && coincidePass) {
                if ("Activo".equalsIgnoreCase(u.getEstado())) {
                    SesionUsuario.iniciarSesion(u.getId(), u.getNombre(), u.getEmail(), u.getRol());
                    return true;
                }
            }
        }
        return false;
    }

    public void cerrarSesion() {
        SesionUsuario.cerrarSesion();
    }
}