package com.provodromo.provodromo.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Autowired
    SecurityFilter securityFilter;

    private static final String LOGIN_URL = "/api/auth/login";
    private static final String REGISTER_URL = "/api/auth/register";
    private static final String USER_ID_URL = "/api/usuario/{id}";
    private static final String USER_URL = "/api/usuario";
    private static final String TIPO_USUARIO_URL = "/api/tipoUsuario";

    private static final String ROLE_COMUM = "COMUM";
    private static final String ROLE_CONVIDADO = "CONVIDADO";
    private static final String ROLE_MODERADOR = "MODERADOR";
    private static final String ROLE_PROFESSOR = "PROFESSOR";
    private static final String ROLE_ADMINISTRADOR = "ADMINISTRADOR";

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(
                                "/v3/api-docs/**",
                                "/swagger-resources/**",
                                "/swagger-ui/**")
                        .permitAll()
                        // Permite acesso aos endpoints de login e registro sem autenticação
                        .requestMatchers(HttpMethod.POST, "/api/auth/login").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/auth/register").permitAll()
                        .requestMatchers(HttpMethod.POST, LOGIN_URL).permitAll()
                        .requestMatchers(HttpMethod.POST, REGISTER_URL).permitAll()
                        // Para acessar o endpoint de informações do usuário por ID, requer qualquer um dos seguintes papéis
                        .requestMatchers(HttpMethod.GET, USER_ID_URL).hasAnyRole(ROLE_COMUM, ROLE_CONVIDADO, ROLE_MODERADOR, ROLE_PROFESSOR, ROLE_ADMINISTRADOR)
                        // Para acessar o endpoint de informações de todos os usuários, requer qualquer um dos seguintes papéis
                        .requestMatchers(HttpMethod.GET, USER_URL).hasAnyRole(ROLE_CONVIDADO, ROLE_MODERADOR, ROLE_PROFESSOR, ROLE_ADMINISTRADOR)
                        // Para acessar o endpoint de criação de usuário, requer qualquer um dos seguintes papéis
                        .requestMatchers(HttpMethod.POST, USER_URL).hasAnyRole(ROLE_MODERADOR, ROLE_PROFESSOR, ROLE_ADMINISTRADOR)
                        // Para acessar o endpoint de atualização de informações do usuário por ID, requer qualquer um dos seguintes papéis
                        .requestMatchers(HttpMethod.PUT, USER_ID_URL).hasAnyRole(ROLE_COMUM, ROLE_MODERADOR, ROLE_PROFESSOR, ROLE_ADMINISTRADOR)
                        // Para acessar o endpoint de exclusão de usuário por ID, requer o papel de administrador
                        .requestMatchers(HttpMethod.DELETE, USER_ID_URL).hasRole(ROLE_ADMINISTRADOR)
                        .requestMatchers(HttpMethod.GET, TIPO_USUARIO_URL).hasAnyRole(ROLE_ADMINISTRADOR)
                        // Todas as outras requisições precisam ser autenticadas
                        .anyRequest().authenticated()
                )
                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
}

