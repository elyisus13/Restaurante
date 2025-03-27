package Modelo;

public class Clientes
{
    int idClientes;
    String nombre;
    String telefono;
    String correo;


    public Clientes(int idClientes, String nombre, String telefono, String correo) {
        this.idClientes = idClientes;
        this.nombre = nombre;
        this.telefono = telefono;
        this.correo = correo;
    }

    public int getIdClientes() {
        return idClientes;
    }

    public void setIdClientes(int Idclientes) {
        this.idClientes = idClientes;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getDirecion() {
        return direcion;
    }

    public void setDirecion(String direcion) {
        this.direcion = direcion;
    }

    String direcion;
}
