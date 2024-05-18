package com.provodromo.provodromo.service;

import com.provodromo.provodromo.dto.LoginDTO;
import com.provodromo.provodromo.error.exception.CredenciaisInvalidasException;
import com.provodromo.provodromo.error.exception.RegraNegocioException;
import com.provodromo.provodromo.model.Usuario;
import com.provodromo.provodromo.repository.UsuarioRepository;
import com.provodromo.provodromo.security.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AutenticacaoService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;

    public String login(LoginDTO loginDTO) {
        Usuario usuario = usuarioRepository.findByEmail(loginDTO.getEmail());
        validarCredenciais(usuario, loginDTO.getSenha());
        return tokenService.generateToken(usuario);
    }

    public String register(LoginDTO loginDTO) {
        validarUsuarioExistente(loginDTO.getEmail());
        Usuario newUser = criarNovoUsuario(loginDTO);
        return tokenService.generateToken(newUser);
    }


    private void validarCredenciais(Usuario usuario, String senha) {
        if (usuario == null || !passwordEncoder.matches(senha, usuario.getSenha())) {
            throw new CredenciaisInvalidasException("Credenciais inválidas");
        }
    }

    private void validarUsuarioExistente(String email) {
        if (usuarioRepository.existsByEmail(email)) {
            throw new RegraNegocioException("Já existe um usuário com o e-mail fornecido");
        }
    }

    private Usuario criarNovoUsuario(LoginDTO loginDTO) {
        Usuario newUser = new Usuario();
        newUser.setSenha(passwordEncoder.encode(loginDTO.getSenha()));
        newUser.setEmail(loginDTO.getEmail());
        newUser.setNome(loginDTO.getNome());
        return usuarioRepository.save(newUser);
    }
}