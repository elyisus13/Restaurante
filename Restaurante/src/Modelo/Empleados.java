package Modelo;

public class    Empleados
{
    int idEmpleados;
    String nombre;
    String cargo;
    String salario;


    public Empleados (int idEmpleados, String nombre, String cargo, String salario) {
        this.idEmpleados = idEmpleados;
        this.nombre = nombre;
        this.cargo = cargo;
        this.salario = salario;
    }

    public int getIdClientes() {
        return idEmpleados;
    }

    public void setIdClientes(int Idclientes) {
        this.idEmpleados = idEmpleados;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String telefono) {
        this.cargo = cargo;
    }

    public String getSalario() {
        return salario;
    }

    public void setSalario(String salario) {
        this.salario = salario;
    }

}
