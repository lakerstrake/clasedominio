package com.dominio.service;

import com.dominio.dao.IndividuoDao;
import com.dominio.domain.Individuo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Implementacion del servicio de Individuos.
 * Aqui va la logica de negocio. Esta clase conecta el controlador
 * con el DAO para realizar las operaciones CRUD.
 *
 * La anotacion @Service le dice a Spring que esta clase es un servicio
 * y que debe manejarla como un bean del contenedor.
 *
 * Autor: Juan Manuel Lagos - Ficha 3114733 - ADSO SENA
 */
@Service
public class IndividuoService implements IindividuoService {

    // Se inyecta el DAO para acceder a la base de datos
    @Autowired
    private IndividuoDao individuoDao;

    // Retorna la lista completa de individuos
    @Override
    public List<Individuo> listarIndividuos() {
        return (List<Individuo>) individuoDao.findAll();
    }

    // Busca individuos que contengan el nombre dado
    @Override
    public List<Individuo> buscarPorNombre(String nombre) {
        return individuoDao.findByNombreContainingIgnoreCase(nombre);
    }

    // Busca un solo individuo por su id, retorna null si no existe
    @Override
    public Individuo buscarIndividuoPorId(Integer id) {
        return individuoDao.findById(id).orElse(null);
    }

    // Guarda un individuo nuevo o actualiza uno existente
    @Override
    public void guardarIndividuo(Individuo individuo) {
        individuoDao.save(individuo);
    }

    // Elimina un individuo por su id
    @Override
    public void eliminarIndividuo(Integer id) {
        individuoDao.deleteById(id);
    }
}
