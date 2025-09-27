package com.example.lab5_20212093_gtics.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "usuarios")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    // Para la pregunta 2, que no tenga caracteres especiales ni numeros
    @NotBlank(message = "El nombre no puede estar vacío")
    @Pattern(regexp = "^[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]+$", message = "El nombre solo puede contener letras y espacios.")
    @Column(nullable = false)
    private String nombre;

    @NotBlank
    @Column(nullable = false)
    private String apellido;

    // Para la pregunta 2, hay que usar el REGEX
    @NotBlank(message = "El correo no puede estar vacío")
    @Email(message = "Formato de correo inválido")
    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$", message = "Formato de correo inválido.")
    @Column(nullable = false, unique = true)
    private String correo;

    // Verificacion de la edad en años
    @NotNull(message = "La edad no puede estar vacía")
    @Min(value = 18, message = "Debe ser mayor de 18 años.")
    @Column(nullable = false)
    private Integer edad;

    // Verificacion de la descripcion tenga minimo 10 caracteres
    @Size(min = 10, message = "La descripción debe tener al menos 10 caracteres.")
    @Column(nullable = true)
    private String descripcion;

    // Verificacion que la contraseña tenga al menos 6 caracteres y un numero
    @Pattern(regexp = "^(?=.*\\d).{6,}$", message = "La contraseña debe tener al menos 6 caracteres y un número.")
    @Column(nullable = true)
    private String contrasena;

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getApellido() { return apellido; }
    public void setApellido(String apellido) { this.apellido = apellido; }
    public String getCorreo() { return correo; }
    public void setCorreo(String correo) { this.correo = correo; }
    public Integer getEdad() { return edad; }
    public void setEdad(Integer edad) { this.edad = edad; }
    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
    public String getContrasena() { return contrasena; }
    public void setContrasena(String contrasena) { this.contrasena = contrasena; }
}