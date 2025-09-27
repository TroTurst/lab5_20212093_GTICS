package com.example.lab5_20212093_gtics.service;

import com.example.lab5_20212093_gtics.entity.Usuario;
import com.example.lab5_20212093_gtics.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    UsuarioRepository usuarioRepository;

    public List<Usuario> listarTodos() {
        return usuarioRepository.findAll();
    }

    public Usuario guardar(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public Optional<Usuario> buscarPorId(Integer id) {
        return usuarioRepository.findById(id);
    }

    public List<Object[]> obtenerRanking() {
        return usuarioRepository.findRankingUsuarios();
    }
}