package com.dominio;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Clase principal que arranca la aplicacion Spring Boot.
 * La anotacion @SpringBootApplication habilita la configuracion
 * automatica, el escaneo de componentes y la configuracion de Spring.
 *
 * Autor: Juan Manuel Lagos - Ficha 3114733 - ADSO SENA
 */
@SpringBootApplication
public class DominioClaseApplication {

	public static void main(String[] args) {
		SpringApplication.run(DominioClaseApplication.class, args);
	}

}
