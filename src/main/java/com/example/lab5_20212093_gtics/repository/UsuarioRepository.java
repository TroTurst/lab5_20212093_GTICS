package com.example.lab5_20212093_gtics.repository;

import com.example.lab5_20212093_gtics.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    // Para obtener la lista de usuario por el numero de regalos
    @Query(value = "SELECT u.id, u.nombre, COUNT(m.destinatario_id) AS total_regalos " +
            "FROM usuarios u LEFT JOIN mensajes m ON u.id = m.destinatario_id " +
            "GROUP BY u.id, u.nombre ORDER BY total_regalos DESC", nativeQuery = true)
    List<Object[]> findRankingUsuarios();
}