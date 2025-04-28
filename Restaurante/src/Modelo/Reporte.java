package Modelo;

import Controlador.ReporteDAO;
import java.util.List;

public class Reporte
{

    private ReporteDAO dao;

    public Reporte() {
        dao = new ReporteDAO();
    }

    public double obtenerTotalVentas(String desde, String hasta) {
        return dao.totalVentas(desde, hasta);
    }

    public List<String[]> obtenerProductosMasVendidos() {
        return dao.productosMasVendidos();
    }

    public List<String[]> obtenerClientesConMasCompras() {
        return dao.clientesConMasCompras();
    }
}
