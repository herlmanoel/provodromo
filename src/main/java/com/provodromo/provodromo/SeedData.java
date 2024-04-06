package com.provodromo.provodromo;

import com.provodromo.provodromo.model.TipoUsuario;
import com.provodromo.provodromo.model.Usuario;
import com.provodromo.provodromo.repository.TipoUsuarioRepository;
import com.provodromo.provodromo.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class SeedData implements CommandLineRunner {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private TipoUsuarioRepository tipoUsuarioRepository;

    @Override
    public void run(String... args) throws Exception {
        seedTiposUsuario();
        seedUsuarios();
    }

    private void seedUsuarios() {
        if (usuarioRepository.findAll().isEmpty()) {
            TipoUsuario tipoAdmin = tipoUsuarioRepository.findByName("Administrador");
            TipoUsuario tipoComum = tipoUsuarioRepository.findByName("Comum");
            TipoUsuario tipoModerador = tipoUsuarioRepository.findByName("Moderador");
            TipoUsuario tipoConvidado = tipoUsuarioRepository.findByName("Convidado");
            TipoUsuario tipoVisitante = tipoUsuarioRepository.findByName("Visitante");

            Usuario admin = new Usuario();
            admin.setNome("John Doe");
            admin.setEmail("john.doe@example.com");
            admin.setSenha("admin123");
            admin.setTipoUsuario(tipoAdmin);
            usuarioRepository.save(admin);

            Usuario comum = new Usuario();
            comum.setNome("Jane Smith");
            comum.setEmail("jane.smith@example.com");
            comum.setSenha("comum123");
            comum.setTipoUsuario(tipoComum);
            usuarioRepository.save(comum);

            Usuario moderador = new Usuario();
            moderador.setNome("Alice Johnson");
            moderador.setEmail("alice.johnson@example.com");
            moderador.setSenha("moderador123");
            moderador.setTipoUsuario(tipoModerador);
            usuarioRepository.save(moderador);

            Usuario convidado = new Usuario();
            convidado.setNome("Bob Brown");
            convidado.setEmail("bob.brown@example.com");
            convidado.setSenha("convidado123");
            convidado.setTipoUsuario(tipoConvidado);
            usuarioRepository.save(convidado);

            Usuario visitante = new Usuario();
            visitante.setNome("Eva Wilson");
            visitante.setEmail("eva.wilson@example.com");
            visitante.setSenha("visitante123");
            visitante.setTipoUsuario(tipoVisitante);
            usuarioRepository.save(visitante);

        }
    }

    private void seedTiposUsuario() {
        if (tipoUsuarioRepository.findAll().isEmpty()) {
            TipoUsuario tipoAdmin = new TipoUsuario();
            tipoAdmin.setNome("Administrador");
            tipoUsuarioRepository.save(tipoAdmin);

            TipoUsuario tipoComum = new TipoUsuario();
            tipoComum.setNome("Comum");
            tipoUsuarioRepository.save(tipoComum);

            TipoUsuario tipoModerador = new TipoUsuario();
            tipoModerador.setNome("Moderador");
            tipoUsuarioRepository.save(tipoModerador);

            TipoUsuario tipoConvidado = new TipoUsuario();
            tipoConvidado.setNome("Convidado");
            tipoUsuarioRepository.save(tipoConvidado);

            TipoUsuario tipoVisitante = new TipoUsuario();
            tipoVisitante.setNome("Visitante");
            tipoUsuarioRepository.save(tipoVisitante);

        }
    }
}