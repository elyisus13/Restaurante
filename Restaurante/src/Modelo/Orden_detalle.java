package Modelo;

public class Orden_detalle
{
    int id_detalle;
    int id_orden;
    int id_producto;
    int cantidad;

    public Orden_detalle(int id_detalle, int id_orden, int id_producto, int cantidad) {
        this.id_detalle = id_detalle;
        this.id_orden = id_orden;
        this.id_producto = id_producto;
        this.cantidad = cantidad;
    }

    public int getId_detalle() {
        return id_detalle;
    }

    public void setId_detalle(int id_detalle) {
        this.id_detalle = id_detalle;
    }

    public int getId_orden() {
        return id_orden;
    }

    public void setId_orden(int id_orden) {
        this.id_orden = id_orden;
    }

    public int getId_producto() {
        return id_producto;
    }

    public void setId_producto(int id_producto) {
        this.id_producto = id_producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
}
