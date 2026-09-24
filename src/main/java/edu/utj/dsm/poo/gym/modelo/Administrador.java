package edu.utj.dsm.poo.gym.modelo;

public final class Administrador extends Persona {
    private String rol;
    private String usuario;
    private String contraseña;

    public Administrador() {
        super();
        this.rol = "";
        this.usuario = "";
        this.contraseña = "";
    }

    public String getRol() { return rol; }
    public void setRol(String rol) { this.rol = rol; }

    public String getUsuario() { return usuario; }
    public void setUsuario(String usuario) { this.usuario = usuario; }

    public String getContraseña() { return contraseña; }
    public void setContraseña(String contraseña) { this.contraseña = contraseña; }
}