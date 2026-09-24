package edu.utj.dsm.poo.gym.modelo;

public final class Entrenador extends Persona {
    private String especialidad;
    private String turno;

    public Entrenador() {
        super();
        this.especialidad = "";
        this.turno = "";
    }

    public String getEspecialidad() { return especialidad; }
    public void setEspecialidad(String especialidad) { this.especialidad = especialidad; }

    public String getTurno() { return turno; }
    public void setTurno(String turno) { this.turno = turno; }
}