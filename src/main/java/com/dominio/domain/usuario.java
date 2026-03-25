package com.dominio.domain;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name="usuario")  
public class usuario {
    
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY) 
@Column(name="id_usuario")
private Integer idUsuario;
private String username;
private String password;
private String activo;

@ManyToOne
private Perfil perfil;

}
