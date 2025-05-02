package Modelo;

public class Ordenes
{
    int id_orden;
    int idclientes;
    int id_empleado;
    int id_mesa;
    Double total;
    String fecha;
    String estado;


    public Ordenes(int id_orden, int idclientes, int id_empleado, int id_mesa, Double total, String fecha, String estado) {
        this.id_orden = id_orden;
        this.idclientes = idclientes;
        this.id_empleado = id_empleado;
        this.id_mesa = id_mesa;
        this.total = total;
        this.fecha = fecha;
        this.estado = estado;
    }

    public int getId_orden() {
        return id_orden;
    }

    public void setId_orden(int id_orden) {
        this.id_orden = id_orden;
    }

    public int getIdclientes() {
        return idclientes;
    }

    public void setIdclientes(int idclientes) {
        this.idclientes = idclientes;
    }

    public int getId_empleado() {
        return id_empleado;
    }

    public void setId_empleado(int id_empleado) {
        this.id_empleado = id_empleado;
    }

    public int getId_mesa() {
        return id_mesa;
    }

    public void setId_mesa(int id_mesa) {
        this.id_mesa = id_mesa;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
