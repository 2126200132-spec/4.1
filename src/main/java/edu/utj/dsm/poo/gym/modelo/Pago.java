package edu.utj.dsm.poo.gym.modelo;

public class Pago {
    private String idPago;
    private double monto;
    private String fecha;

    public Pago() {}

    public Pago(String idPago, double monto, String fecha) {
        this.idPago = idPago;
        this.monto = monto;
        this.fecha = fecha;
    }

    public String getIdPago() { return idPago; }
    public void setIdPago(String idPago) { this.idPago = idPago; }
    public double getMonto() { return monto; }
    public void setMonto(double monto) { this.monto = monto; }
    public String getFecha() { return fecha; }
    public void setFecha(String fecha) { this.fecha = fecha; }
}