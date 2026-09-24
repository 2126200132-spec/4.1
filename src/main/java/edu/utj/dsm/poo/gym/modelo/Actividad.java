package edu.utj.dsm.poo.gym.modelo;

public class Actividad {
    private String idActividad;
    private String nombre;
    private String horario;

    public Actividad() {}

    public Actividad(String idActividad, String nombre, String horario) {
        this.idActividad = idActividad;
        this.nombre = nombre;
        this.horario = horario;
    }

    public String getIdActividad() { return idActividad; }
    public void setIdActividad(String idActividad) { this.idActividad = idActividad; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getHorario() { return horario; }
    public void setHorario(String horario) { this.horario = horario; }
}