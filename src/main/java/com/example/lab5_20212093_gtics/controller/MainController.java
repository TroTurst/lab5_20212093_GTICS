package com.example.lab5_20212093_gtics.controller;

import com.example.lab5_20212093_gtics.entity.Mensaje;
import com.example.lab5_20212093_gtics.entity.Usuario;
import com.example.lab5_20212093_gtics.service.MensajeService;
import com.example.lab5_20212093_gtics.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
public class MainController {

    @Autowired
    UsuarioService usuarioService;

    @Autowired
    MensajeService mensajeService;

    // Pagina principal
    @GetMapping(value = {"/", "/home"})
    public String listaUsuarios(Model model) {
        List<Usuario> usuarios = usuarioService.listarTodos();
        model.addAttribute("listaUsuarios", usuarios);

        return "home";
    }

    // Para el registro de usuarios
    @GetMapping("/registro")
    public String formularioRegistro(@ModelAttribute("usuario") Usuario usuario) {
        return "registro";
    }

    @PostMapping("/registro/guardar")
    public String guardarUsuario(@ModelAttribute("usuario") @Valid Usuario usuario,
                                 BindingResult bindingResult,
                                 RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "registro";
        }
        usuarioService.guardar(usuario);
        redirectAttributes.addFlashAttribute("msg", "Registro exitoso! Ya puedes enviar regalos.");
        return "redirect:/home";
    }

    // Para enviar regalos
    @GetMapping("/enviar")
    public String formularioEnvio(@RequestParam(value = "destinatarioId", required = false) Integer destinatarioId,
                                  @ModelAttribute("mensaje") Mensaje mensaje,
                                  Model model) {
        List<Usuario> destinatarios = usuarioService.listarTodos();
        model.addAttribute("listaDestinatarios", destinatarios);

        // Para ver a quien envias el regalo
        if (destinatarioId != null) {
            usuarioService.buscarPorId(destinatarioId).ifPresent(usuario -> {
                mensaje.setDestinatario(usuario);
            });
        }
        usuarioService.buscarPorId(1).ifPresent(usuario -> {
            mensaje.setRemitente(usuario);
        });

        return "envio";
    }

    @PostMapping("/enviar/guardar")
    public String guardarMensaje(@ModelAttribute("mensaje") @Valid Mensaje mensaje,
                                 BindingResult bindingResult,
                                 RedirectAttributes redirectAttributes) {

        // Validacion de longitud del mensaje
        if (mensaje.getContenido() != null && mensaje.getContenido().length() < 20) {
            bindingResult.rejectValue("contenido", "error.contenido", "El mensaje debe tener al menos 20 caracteres.");
        }

        // Validación de palabras
        if (mensaje.getContenido() != null && mensajeService.tienePalabrasProhibidas(mensaje.getContenido())) {
            bindingResult.rejectValue("contenido", "error.contenido", "El mensaje no puede contener las palabras 'odio' ni 'feo'.");
        }

        if (bindingResult.hasErrors()) {
            List<Usuario> destinatarios = usuarioService.listarTodos();
            redirectAttributes.addFlashAttribute("listaDestinatarios", destinatarios);
            redirectAttributes.addFlashAttribute("error", "Error al enviar el mensaje. Revisa los datos.");
            return "redirect:/enviar";
        }

        try {
            mensaje.setRemitente(usuarioService.buscarPorId(mensaje.getRemitente().getId()).get());
            mensaje.setDestinatario(usuarioService.buscarPorId(mensaje.getDestinatario().getId()).get());

            mensajeService.enviar(mensaje); // Eje 3: Aviso de envío exitoso
            redirectAttributes.addFlashAttribute("msg", "¡Mensaje enviado con éxito!");
        } catch (Exception e) {
            //Mensaje de error en caso de alguna falla
            redirectAttributes.addFlashAttribute("error", "Error interno al procesar el envío.");
        }

        return "redirect:/home";
    }

    // Ver el ranking
    @GetMapping("/ranking")
    public String mostrarRanking(Model model) {
        List<Object[]> ranking = usuarioService.obtenerRanking();
        model.addAttribute("ranking", ranking);
        return "ranking";
    }

    // ensajes Recibidos
    @GetMapping("/recibidos")
    public String listarMensajesRecibidos(Model model) {
        Integer userId = 12;
        Optional<Usuario> userOpt = usuarioService.buscarPorId(userId);

        if (userOpt.isPresent()) {
            List<Mensaje> mensajes = mensajeService.listarMensajesRecibidos(userId);
            model.addAttribute("mensajes", mensajes);
            model.addAttribute("totalMensajes", mensajes.size());
            model.addAttribute("usuario", userOpt.get());
        } else {
            model.addAttribute("error", "Usuario no encontrado.");
        }

        return "recibidos";
    }
}