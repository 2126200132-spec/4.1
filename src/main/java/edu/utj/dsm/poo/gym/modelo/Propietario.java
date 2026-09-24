package edu.utj.dsm.poo.gym.modelo;

public final class Propietario extends Persona {
    private String clave;
    private String estado;
    private int nivel;

    public Propietario() {
        super();
        this.clave = "";
        this.estado = "";
        this.nivel = 0;
    }

    public String getClave() { return clave; }
    public void setClave(String clave) { this.clave = clave; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

    public int getNivel() { return nivel; }
    public void setNivel(int nivel) { this.nivel = nivel; }
}