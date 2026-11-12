package edu.utj.dsm.poo.gym.modelo;

public class Administrador {
    private int id;
    private String nombre;
    private String email;
    private String rol;
    private String estado;
    private String rfc;
    private String primerApellido;

    // Constructor vacío
    public Administrador() {
        this.id = 0;
        this.nombre = "";
        this.email = "";
        this.rol = "";
        this.estado = "Activo";
        this.rfc = "";
        this.primerApellido = "";
    }

    // Constructor completo con 5 parámetros (incluyendo estado)
    public Administrador(int id, String nombre, String email, String rol, String estado) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
        this.rol = rol;
        this.estado = estado;
        this.rfc = "";
        this.primerApellido = "";
    }

    // Getters y Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getRol() { return rol; }
    public void setRol(String rol) { this.rol = rol; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

    public String getRfc() { return rfc; }
    public void setRfc(String rfc) { this.rfc = rfc; }

    public String getPrimerApellido() { return primerApellido; }
    public void setPrimerApellido(String primerApellido) { this.primerApellido = primerApellido; }
}