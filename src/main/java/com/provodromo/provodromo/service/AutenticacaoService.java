package com.provodromo.provodromo.service;

import com.provodromo.provodromo.dto.request.LoginRequestDTO;
import com.provodromo.provodromo.dto.response.LoginResponseDTO;
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

    public LoginResponseDTO login(LoginRequestDTO loginRequestDTO) {
        Usuario usuario = usuarioRepository.findByEmail(loginRequestDTO.getEmail());
        validarCredenciais(usuario, loginRequestDTO.getSenha());

        return criarLoginResponseDTO(usuario);
    }


    private void validarCredenciais(Usuario usuario, String senha) {
        if (usuario == null || !passwordEncoder.matches(senha, usuario.getSenha())) {
            throw new CredenciaisInvalidasException("Credenciais inválidas");
        }
    }

    private LoginResponseDTO criarLoginResponseDTO(Usuario usuario) {
        LoginResponseDTO loginResponseDTO = new LoginResponseDTO();
        loginResponseDTO.setToken(tokenService.generateToken(usuario));
        loginResponseDTO.setExpiracao(tokenService.generateExpirationDate());
        return loginResponseDTO;
    }
}