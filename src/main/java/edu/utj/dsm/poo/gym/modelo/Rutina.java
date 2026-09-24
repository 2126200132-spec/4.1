package edu.utj.dsm.poo.gym.modelo;

public class Rutina {
    private String idRutina;
    private String nombre;
    private String nivel;

    public Rutina() {}

    public Rutina(String idRutina, String nombre, String nivel) {
        this.idRutina = idRutina;
        this.nombre = nombre;
        this.nivel = nivel;
    }

    public String getIdRutina() { return idRutina; }
    public void setIdRutina(String idRutina) { this.idRutina = idRutina; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getNivel() { return nivel; }
    public void setNivel(String nivel) { this.nivel = nivel; }
}