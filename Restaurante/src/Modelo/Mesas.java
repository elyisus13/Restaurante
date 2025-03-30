package Modelo;

public class Mesas
{
    int id_mesas;
    int capacidad;
    String estatus_mesa;

    public Mesas(int id_mesas, int capacidad, String estatus_mesa)
    {
        this.id_mesas = id_mesas;
        this.capacidad = capacidad;
        this.estatus_mesa = estatus_mesa;
    }

    public int getId_mesas()
    {
        return id_mesas;
    }

    public void setId_mesas(int id_mesas)
    {
        this.id_mesas = id_mesas;
    }

    public int getCapacidad()
    {
        return capacidad;
    }

    public void setCapacidad(int capacidad)
    {
        this.capacidad = capacidad;
    }

    public String getEstatus_mesa()
    {
        return estatus_mesa;
    }

    public void setEstatus_mesa(String estatus_mesa)
    {
        this.estatus_mesa = estatus_mesa;
    }

}
