package Modelo;

public class    Orden
{
    int idOrden;
    String fecha_orden;
    String estado_del_orden;
    double total_orden;
    int idClientes;
    int idMesas;
    int idEmpleados;


    public Orden (int idOrden, String fecha_orden, String estado_del_orden, double total_orden, int idClientes, int idMesas, int idEmpleados) {
        this.idOrden = idOrden;
        this.fecha_orden = fecha_orden;
        this.estado_del_orden = estado_del_orden;
        this.total_orden = total_orden;
        this.idClientes = idClientes;
        this.idMesas = idMesas;
        this.idEmpleados = idEmpleados;
    }

    public int getIdOrden() {
        return idOrden;
    }

    public void setIdOrden(int IdOrden) {
        this.idOrden = idOrden;
    }

    public String getFecha_orden() {
        return fecha_orden;
    }

    public void setFecha_orden(String fecha_orden) {
        this.fecha_orden = fecha_orden;
    }

    public String getEstado_del_orden() {
        return estado_del_orden;
    }

    public void setEstado_del_orden(String estado_del_orden) {
        this.estado_del_orden = estado_del_orden;
    }

    public double getTotal_orden() {
        return total_orden;
    }

    public void setTotal_orden(double total_orden) {
        this.total_orden = total_orden;
    }

    public int getIdClientes() {
        return idClientes;
    }

    public void setIdClientes(int idClientes) {
        this.idClientes = idClientes;
    }
    public int getIdMesas() {
        return idMesas;
    }

    public void setIdMesas(int idMesas) {
        this.idMesas = idMesas;
    }
    public int getIdEmpleados() {
        return idEmpleados;
    }

    public void setIdEmpleados(int idEmpleados) {
        this.idEmpleados = idEmpleados;
    }
}

