package com.example.lab5_20212093_gtics.service;


import com.example.lab5_20212093_gtics.entity.Mensaje;
import com.example.lab5_20212093_gtics.entity.Usuario;
import com.example.lab5_20212093_gtics.repository.MensajeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MensajeService {

    @Autowired
    MensajeRepository mensajeRepository;

    public Mensaje enviar(Mensaje mensaje) {
        return mensajeRepository.save(mensaje);
    }

    public List<Mensaje> listarMensajesRecibidos(Integer destinatarioId) {
        return mensajeRepository.findByDestinatario_Id(destinatarioId);
    }

    //Validamos las palabras phohibidas
    public boolean tienePalabrasProhibidas(String contenido) {
        String contenidoNormalizado = contenido.toLowerCase();
        return contenidoNormalizado.contains("odio") || contenidoNormalizado.contains("feo");
    }
}