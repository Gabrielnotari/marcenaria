package br.com.marcenaria.api_marcenaria.infra.seguranca;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class ConfiguracoesSeguranca {

    private final FiltroTokenAcesso filtroTokenAcesso;

    public ConfiguracoesSeguranca(FiltroTokenAcesso filtroTokenAcesso) {
        this.filtroTokenAcesso = filtroTokenAcesso;
    }

    @Bean
    public SecurityFilterChain filtrosSeguranca(HttpSecurity http) throws Exception {
        return http
                .authorizeHttpRequests(req -> {
                    req.requestMatchers("/login", "/atualizar-token", "/registrar", "verificar-conta").permitAll();
                    req.requestMatchers(HttpMethod.GET, "/orcamentos").hasRole("CLIENTE");
                    req.requestMatchers(HttpMethod.POST,"/orcamentos/**").hasRole("CLIENTE");
                    req.requestMatchers(HttpMethod.PUT, "/orcamentos").hasRole("CLIENTE");
                    req.requestMatchers(HttpMethod.DELETE, "/orcamentos/**").hasRole("CLIENTE");
                    req.requestMatchers(HttpMethod.GET, "/orcamentos").hasRole("CONSULTOR");
                    req.requestMatchers(HttpMethod.PATCH, "/adicionar-perfil/**").hasRole("ADMIN");
                    req.requestMatchers(HttpMethod.POST, "/cadastrar-material/**").hasRole("ADMIN");
                    req.anyRequest().authenticated();}
                )
                .sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .csrf(csrf -> csrf.disable())
                .addFilterBefore(filtroTokenAcesso, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public PasswordEncoder encriptador(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public RoleHierarchy hierarquiaPerfis(){
        String hierarquia = "ROLE_ADMIN > ROLE_CLIENTE\n" +
                "ROLE_ADMIN > ROLE_CONSULTOR";
        return RoleHierarchyImpl.fromHierarchy(hierarquia);
    }
}
