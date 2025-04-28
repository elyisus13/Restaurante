package Modelo;

public class Chat
{
    int id_chat;
    String remitente;
    String receptor;
    String mensaje;
    String fecha;

    public Chat(int id_chat, String remitente, String receptor, String mensaje, String fecha) {
        this.id_chat = id_chat;
        this.remitente = remitente;
        this.receptor = receptor;
        this.mensaje = mensaje;
        this.fecha = fecha;
    }

    public int getId_chat() {
        return id_chat;
    }

    public void setId_chat(int id_chat) {
        this.id_chat = id_chat;
    }

    public String getRemitente() {
        return remitente;
    }

    public void setRemitente(String remitente) {
        this.remitente = remitente;
    }

    public String getReceptor() {
        return receptor;
    }

    public void setReceptor(String receptor) {
        this.receptor = receptor;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
}
