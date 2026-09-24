package edu.utj.dsm.poo.gym.modelo;

public final class Cliente extends Persona {
    private String direccion;
    private String token;

    public Cliente() {
        super();
        this.direccion = "";
        this.token = "";
    }

    public String getDireccion() { return direccion; }
    public void setDireccion(String direccion) { this.direccion = direccion; }

    public String getToken() { return token; }
    public void setToken(String token) { this.token = token; }
}