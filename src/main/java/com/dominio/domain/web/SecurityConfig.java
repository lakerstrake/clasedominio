package com.dominio.domain.web;

import com.dominio.DominioClaseApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Configuracion de Spring Security.
 * Define los usuarios en memoria, el codificador de contrasenas
 * y las reglas de autorizacion para las rutas de la aplicacion.
 *
 * Autor: Juan Manuel Lagos - Ficha 3114733 - ADSO SENA
 */
@Configuration
public class SecurityConfig {

    private final DominioClaseApplication dominioClaseApplication;

    SecurityConfig(DominioClaseApplication dominioClaseApplication) {
        this.dominioClaseApplication = dominioClaseApplication;
    }

    // Bean para codificar las contrasenas con BCrypt
    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    /**
     * Crea los usuarios en memoria para la autenticacion.
     * - admin/admin: tiene rol ADMIN (puede crear, editar y eliminar)
     * - user/user: tiene rol USER (solo puede ver la lista y detalles)
     */
    @Bean
    public UserDetailsService users(PasswordEncoder passwordEncoder) {
        UserDetails usuario1 = User.builder()
                .username("admin")
                .password(passwordEncoder.encode("admin"))
                .roles("ADMIN")
                .build();

        UserDetails usuario2 = User.builder()
                .username("user")
                .password(passwordEncoder.encode("user"))
                .roles("USER")
                .build();

        return new InMemoryUserDetailsManager(usuario1, usuario2);
    }

    /**
     * Configura las reglas de seguridad:
     * - Las rutas de crear, editar y eliminar requieren rol ADMIN
     * - La lista y los detalles requieren estar autenticado
     * - El login es publico para que cualquiera pueda acceder
     * - Al cerrar sesion se redirige al login con mensaje de exito
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(auth -> auth
                // Solo ADMIN puede crear, guardar, editar y eliminar
                .requestMatchers("/nuevo", "/guardar", "/editar", "/eliminar", "/guardarDemo").hasRole("ADMIN")
                // Cualquier usuario autenticado puede ver la lista y detalles
                .requestMatchers("/", "/detalle").authenticated()
                // Los archivos estaticos (CSS) deben ser publicos
                .anyRequest().authenticated()
            )
            .formLogin(form -> form
                .loginPage("/login")           // Pagina personalizada de login
                .defaultSuccessUrl("/", true)   // Redirige al inicio despues del login
                .permitAll()
            )
            .logout(logout -> logout
                .logoutSuccessUrl("/login?logout") // Al cerrar sesion, va al login
                .permitAll()
            );

        return http.build();
    }
}
