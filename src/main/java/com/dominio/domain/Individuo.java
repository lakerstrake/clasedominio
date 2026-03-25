package com.dominio.domain;

import jakarta.persistence.*;

/**
 * Entidad JPA que representa a un individuo en la base de datos.
 * Se mapea a la tabla "individuos" en PostgreSQL.
 * Cada atributo corresponde a una columna de la tabla.
 *
 * Autor: Juan Manuel Lagos - Ficha 3114733 - ADSO SENA
 */
@Entity
@Table(name = "individuos")
public class Individuo {

    // Llave primaria autogenerada por la base de datos
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nombre;
    private String apellido;
    private int edad;
    private String correo;
    private String numero; // Telefono de contacto

    // Constructor vacio requerido por JPA
    public Individuo() {
    }

    // Getters y Setters para acceder a los atributos desde otras clases
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getApellido() { return apellido; }
    public void setApellido(String apellido) { this.apellido = apellido; }

    public Integer getEdad() { return edad; }
    public void setEdad(Integer edad) { this.edad = edad; }

    public String getCorreo() { return correo; }
    public void setCorreo(String correo) { this.correo = correo; }

    public String getNumero() { return numero; }
    public void setNumero(String numero) { this.numero = numero; }
}
