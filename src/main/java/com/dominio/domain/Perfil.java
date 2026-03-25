package com.dominio.domain;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name="Perfil")

public class Perfil {
    
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name="id_perfil")
private Integer id_perfil;

private String nombre;
}
