package com.example.lab5_20212093_gtics.repository;


import com.example.lab5_20212093_gtics.entity.Mensaje;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MensajeRepository extends JpaRepository<Mensaje, Integer> {

    List<Mensaje> findByDestinatario_Id(Integer destinatarioId);
}