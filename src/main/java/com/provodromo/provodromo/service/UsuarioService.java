package com.provodromo.provodromo.service;

import com.provodromo.provodromo.dto.TipoUsuarioDTO;
import com.provodromo.provodromo.dto.UsuarioDTO;
import com.provodromo.provodromo.error.exception.RegraNegocioException;
import com.provodromo.provodromo.model.TipoUsuario;
import com.provodromo.provodromo.model.Usuario;

import java.util.Set;
import java.util.stream.Collectors;

import com.provodromo.provodromo.repository.TipoUsuarioRepository;
import com.provodromo.provodromo.repository.UsuarioRepository;
import com.provodromo.provodromo.service.base.BaseService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UsuarioService implements BaseService<UsuarioDTO> {
    private final UsuarioRepository usuarioRepository;
    private final TipoUsuarioRepository tipoUsuarioRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UsuarioDTO findById(Long id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RegraNegocioException("Usuário não encontrado com o ID: " + id));
        return entityToDTO(usuario);
    }

    @Override
    public Set<UsuarioDTO> findAll() {
        return usuarioRepository.findAll()
                .stream()
                .map(this::entityToDTO)
                .collect(Collectors.toSet());
    }

    @Override
    public UsuarioDTO save(UsuarioDTO dto) {
        if (dto == null || dto.getEmail() == null || dto.getSenha() == null) {
            throw new IllegalArgumentException("Dados de usuário inválidos");
        }

        if (usuarioRepository.existsByEmail(dto.getEmail())) {
            throw new RegraNegocioException("Já existe um usuário com o e-mail " + dto.getEmail());
        }

        return createOrUpdate(dto);
    }

    @Override
    public void deleteById(Long id) {
        if (!usuarioRepository.existsById(id)) {
            throw new RegraNegocioException("Usuário não encontrado com o ID: " + id);
        }
        usuarioRepository.deleteById(id);
    }

    public UsuarioDTO update(Long id, UsuarioDTO dto) {
        if (dto == null || dto.getEmail() == null || dto.getSenha() == null) {
            throw new RegraNegocioException("Dados de usuário inválidos");
        }

        if (!usuarioRepository.existsById(id)) {
            throw new RegraNegocioException("Usuário não encontrado com o ID: " + id);
        }

        dto.setId(id);
        return createOrUpdate(dto);
    }

    @Transactional
    public UsuarioDTO associarTipoUsuario(Long usuarioId, Long tipoUsuarioId) {
        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new RegraNegocioException("Usuário não encontrado com o ID: " + usuarioId));

        TipoUsuario tipoUsuario = tipoUsuarioRepository.findById(tipoUsuarioId)
                .orElseThrow(() -> new RegraNegocioException("Tipo de usuário não encontrado com o ID: " + tipoUsuarioId));

        usuario.setTipoUsuario(tipoUsuario);

        var usuarioSaved = usuarioRepository.save(usuario);
        return entityToDTO(usuarioSaved);
    }

    public UsuarioDTO buscarPorEmail(String email) {
        if (email == null) {
            throw new IllegalArgumentException("E-mail não pode ser nulo");
        }

        Usuario usuario = usuarioRepository.findByEmail(email);
        if (usuario == null) {
            throw new RegraNegocioException("Usuário não encontrado com o e-mail: " + email);
        }
        return entityToDTO(usuario);
    }

    private UsuarioDTO entityToDTO(Usuario usuario) {
        if (usuario == null) {
            return null;
        }

        TipoUsuarioDTO tipoUsuarioDTO = convertToTipoUsuarioDTO(usuario.getTipoUsuario());
        return new UsuarioDTO(
                usuario.getId(),
                usuario.getNome(),
                usuario.getEmail(),
                null,
                tipoUsuarioDTO
        );
    }

    private Usuario dtoToEntity(UsuarioDTO dto) {
        if (dto == null) {
            return null;
        }

        Usuario usuario = new Usuario();
        usuario.setId(dto.getId());
        usuario.setNome(dto.getNome());
        usuario.setEmail(dto.getEmail());
        usuario.setSenha(dto.getSenha());
        usuario.setTipoUsuario(convertToTipoUsuario(dto.getTipoUsuario()));

        return usuario;
    }

    private TipoUsuarioDTO convertToTipoUsuarioDTO(TipoUsuario tipoUsuario) {
        if (tipoUsuario == null) {
            return null;
        }
        return new TipoUsuarioDTO(tipoUsuario.getNome());
    }

    private TipoUsuario convertToTipoUsuario(TipoUsuarioDTO tipoUsuarioDTO) {
        if (tipoUsuarioDTO == null) {
            return null;
        }
        TipoUsuario tipoUsuario = new TipoUsuario();
        tipoUsuario.setNome(tipoUsuarioDTO.getNome());
        return tipoUsuario;
    }


    private UsuarioDTO createOrUpdate(UsuarioDTO dto) {
        try {
            Usuario usuario = dtoToEntity(dto);

            if (dto.getSenha() != null && !dto.getSenha().isEmpty()) {
                String senhaCodificada = passwordEncoder.encode(dto.getSenha());
                usuario.setSenha(senhaCodificada);
            }

            if (dto.getTipoUsuario() != null && !dto.getTipoUsuario().getNome().isEmpty()) {
                TipoUsuario tipoUsuario = tipoUsuarioRepository.findByNome(dto.getTipoUsuario().getNome());
                if (tipoUsuario == null) {
                    throw new RegraNegocioException("Tipo de usuário não encontrado: " + dto.getTipoUsuario().getNome());
                }
                usuario.setTipoUsuario(tipoUsuario);
            }

            usuario = usuarioRepository.save(usuario);
            return entityToDTO(usuario);
        } catch (Exception e) {
            throw new RegraNegocioException("Erro ao salvar/atualizar o usuário: " + e.getMessage());
        }
    }
}