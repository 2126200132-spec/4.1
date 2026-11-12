package edu.utj.dsm.poo.gym.modelo;

public class Propietario {
    private int id;
    private String nombre;
    private String email;
    private String telefono;
    private String estado;

    public Propietario() {
        this.id = 0;
        this.nombre = "";
        this.email = "";
        this.telefono = "";
        this.estado = "Activo";
    }

    public Propietario(int id, String nombre, String email, String telefono, String estado) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
        this.telefono = telefono;
        this.estado = estado;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
}