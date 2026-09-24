package edu.utj.dsm.poo.gym.modelo;

public class Membresia {
    private String idMembresia;
    private String tipo;
    private double costo;

    public Membresia() {}

    public Membresia(String idMembresia, String tipo, double costo) {
        this.idMembresia = idMembresia;
        this.tipo = tipo;
        this.costo = costo;
    }

    public String getIdMembresia() { return idMembresia; }
    public void setIdMembresia(String idMembresia) { this.idMembresia = idMembresia; }
    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }
    public double getCosto() { return costo; }
    public void setCosto(double costo) { this.costo = costo; }
}