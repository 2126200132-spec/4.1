package edu.utj.dsm.poo.gym.modelo;

public class SesionUsuario {
    private static SesionUsuario instancia;
    private int id;
    private String nombre;
    private String email;
    private String rol;

    private SesionUsuario(int id, String nombre, String email, String rol) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
        this.rol = rol;
    }

    public static void iniciarSesion(int id, String nombre, String email, String rol) {
        instancia = new SesionUsuario(id, nombre, email, rol);
    }

    public static SesionUsuario getInstancia() {
        return instancia;
    }

    public static void cerrarSesion() {
        instancia = null;
    }

    public int getId() { return id; }
    public String getNombre() { return nombre; }
    public String getEmail() { return email; }
    public String getRol() { return rol; }
}