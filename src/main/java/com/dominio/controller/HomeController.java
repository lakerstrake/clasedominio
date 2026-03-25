package com.dominio.controller;

import com.dominio.domain.Individuo;
import com.dominio.service.IndividuoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import jakarta.validation.Valid;
import org.springframework.validation.BindingResult;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

/**
 * Controlador principal de la aplicacion.
 * Maneja todas las rutas HTTP y conecta las vistas (templates)
 * con la capa de servicio.
 *
 * Autor: Juan Manuel Lagos - Ficha 3114733 - ADSO SENA
 */
@Controller
public class HomeController {

    // Servicio que contiene la logica de negocio
    private final IndividuoService individuoService;

    // Spring inyecta automaticamente el servicio por el constructor
    @Autowired
    public HomeController(IndividuoService individuoService) {
        this.individuoService = individuoService;
    }

    /**
     * Pagina principal - muestra la lista de individuos.
     * Si se recibe el parametro "q", filtra por nombre.
     * Tambien detecta si el usuario es admin para mostrar
     * o esconder las opciones de administracion.
     */
    @GetMapping("/")
    public String index(@RequestParam(value = "q", required = false) String q, Model model, Authentication authentication) {
        // Si hay texto de busqueda, filtra; si no, trae todos
        var lista = (q != null && !q.isBlank())
                ? individuoService.buscarPorNombre(q)
                : individuoService.listarIndividuos();
        model.addAttribute("lista", lista);
        model.addAttribute("q", q);

        // Verificar si el usuario autenticado tiene rol ADMIN
        boolean esAdmin = false;
        String usuarioActual = "No autenticado";
        String rolesActuales = "Sin roles";

        if (authentication != null) {
            usuarioActual = authentication.getName();
            rolesActuales = authentication.getAuthorities().toString();
            for (GrantedAuthority authority : authentication.getAuthorities()) {
                if (authority.getAuthority().equals("ROLE_ADMIN")) {
                    esAdmin = true;
                    break;
                }
            }
        }
        model.addAttribute("esAdmin", esAdmin);
        model.addAttribute("usuarioActual", usuarioActual);
        model.addAttribute("rolesActuales", rolesActuales);

        return "index";
    }

    /**
     * Muestra el detalle completo de un individuo.
     * Recibe el id por parametro de URL: /detalle?id=1
     */
    @GetMapping("/detalle")
    public String detalle(@RequestParam("id") Integer id, Model model) {
        Individuo individuo = individuoService.buscarIndividuoPorId(id);
        model.addAttribute("individuo", individuo);
        return "detalle";
    }

    /**
     * Endpoint de demo: crea un registro de prueba rapido.
     * Util para verificar que la conexion a BD funciona.
     */
    @GetMapping("/guardarDemo")
    public String guardarDemo() {
        Individuo nuevo = new Individuo();
        nuevo.setNombre("nuevo");
        nuevo.setApellido("registro");
        nuevo.setCorreo("nuevo@example.com");
        nuevo.setEdad(50);
        nuevo.setNumero("50");

        individuoService.guardarIndividuo(nuevo);

        return "redirect:/";
    }

    /**
     * Muestra el formulario vacio para crear un nuevo individuo.
     */
    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("individuo", new Individuo());
        return "form";
    }

    /**
     * Recibe los datos del formulario y guarda el individuo.
     * Si hay errores de validacion, vuelve a mostrar el formulario.
     * Funciona tanto para crear como para editar (si tiene id, actualiza).
     */
    @PostMapping("/guardar")
    public String guardar(@Valid Individuo individuo, BindingResult resultado) {
        if (resultado.hasErrors()) {
            return "form";
        }
        individuoService.guardarIndividuo(individuo);
        return "redirect:/";
    }

    /**
     * Carga el formulario con los datos de un individuo existente
     * para poder editarlos. Recibe el id por URL: /editar?id=1
     */
    @GetMapping("/editar")
    public String editar(@RequestParam("id") Integer id, Model model) {
        Individuo individuo = individuoService.buscarIndividuoPorId(id);
        model.addAttribute("individuo", individuo);
        return "form";
    }

    /**
     * Elimina un individuo de la base de datos y redirige a la lista.
     */
    @GetMapping("/eliminar")
    public String eliminar(@RequestParam("id") Integer id) {
        individuoService.eliminarIndividuo(id);
        return "redirect:/";
    }

    /**
     * Muestra la pagina de login.
     * Spring Security maneja la autenticacion automaticamente.
     */
    @GetMapping("/login")
    public String login() {
        return "login";
    }
}
