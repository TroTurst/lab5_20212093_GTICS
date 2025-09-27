package com.example.lab5_20212093_gtics.entity;

import jakarta.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "mensajes")
public class Mensaje {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "remitente_id", nullable = false)
    private Usuario remitente;

    @ManyToOne
    @JoinColumn(name = "destinatario_id", nullable = false)
    private Usuario destinatario;

    @Column(name = "regalo_tipo", nullable = false)
    private String regaloTipo; // ENUM: Flor, Carrito

    @Column(name = "regalo_color")
    private String regaloColor;

    @Column(nullable = false)
    private String contenido;

    @Column(name = "fecha_envio", nullable = false)
    private Timestamp fechaEnvio;

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public Usuario getRemitente() { return remitente; }
    public void setRemitente(Usuario remitente) { this.remitente = remitente; }
    public Usuario getDestinatario() { return destinatario; }
    public void setDestinatario(Usuario destinatario) { this.destinatario = destinatario; }
    public String getRegaloTipo() { return regaloTipo; }
    public void setRegaloTipo(String regaloTipo) { this.regaloTipo = regaloTipo; }
    public String getRegaloColor() { return regaloColor; }
    public void setRegaloColor(String regaloColor) { this.regaloColor = regaloColor; }
    public String getContenido() { return contenido; }
    public void setContenido(String contenido) { this.contenido = contenido; }
    public Timestamp getFechaEnvio() { return fechaEnvio; }
    public void setFechaEnvio(Timestamp fechaEnvio) { this.fechaEnvio = fechaEnvio; }
}