package edu.utj.dsm.poo.gym.modelo;

public class Equipo {
    private String idEquipo;
    private String nombre;
    private String estado;

    public Equipo() {}

    public Equipo(String idEquipo, String nombre, String estado) {
        this.idEquipo = idEquipo;
        this.nombre = nombre;
        this.estado = estado;
    }

    public String getIdEquipo() { return idEquipo; }
    public void setIdEquipo(String idEquipo) { this.idEquipo = idEquipo; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
}