package com.dominio.service;

import com.dominio.domain.Individuo;
import java.util.List;

/**
 * Interfaz del servicio de Individuos.
 * Define los metodos que debe implementar la capa de servicio.
 * Esto permite separar la logica de negocio del controlador
 * y facilita las pruebas unitarias.
 *
 * Autor: Juan Manuel Lagos - Ficha 3114733 - ADSO SENA
 */
public interface IindividuoService {

    // Obtiene todos los individuos de la BD
    List<Individuo> listarIndividuos();

    // Busca individuos por nombre (busqueda parcial)
    List<Individuo> buscarPorNombre(String nombre);

    // Busca un individuo por su id
    Individuo buscarIndividuoPorId(Integer id);

    // Guarda o actualiza un individuo en la BD
    void guardarIndividuo(Individuo individuo);

    // Elimina un individuo por su id
    void eliminarIndividuo(Integer id);
}
