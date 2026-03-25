package com.dominio.dao;

import com.dominio.domain.Individuo;
import org.springframework.data.repository.CrudRepository;
import java.util.List;

/**
 * Interfaz DAO (Data Access Object) para la entidad Individuo.
 * Extiende CrudRepository que nos da los metodos CRUD basicos:
 * findAll(), findById(), save(), deleteById(), etc.
 *
 * Spring Data JPA genera la implementacion automaticamente.
 *
 * Autor: Juan Manuel Lagos - Ficha 3114733 - ADSO SENA
 */
public interface IndividuoDao extends CrudRepository<Individuo, Integer> {

    // Busca individuos cuyo nombre contenga el texto dado (sin importar mayusculas)
    List<Individuo> findByNombreContainingIgnoreCase(String nombre);
}
