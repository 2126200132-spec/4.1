package edu.utj.dsm.poo.gym.modelo;

public class Entrenador {
    private int id;
    private String nombre;
    private String especialidad;
    private String telefono;
    private String estado;

    public Entrenador() {
        this.id = 0;
        this.nombre = "";
        this.especialidad = "";
        this.telefono = "";
        this.estado = "Activo";
    }

    public Entrenador(int id, String nombre, String especialidad, String telefono, String estado) {
        this.id = id;
        this.nombre = nombre;
        this.especialidad = especialidad;
        this.telefono = telefono;
        this.estado = estado;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getEspecialidad() { return especialidad; }
    public void setEspecialidad(String especialidad) { this.especialidad = especialidad; }

    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
}