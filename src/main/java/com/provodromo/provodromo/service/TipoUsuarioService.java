package com.provodromo.provodromo.service;

import com.provodromo.provodromo.dto.request.TipoUsuarioRequestDTO;
import com.provodromo.provodromo.dto.response.TipoUsuarioResponseDTO;
import com.provodromo.provodromo.error.exception.RegraNegocioException;
import com.provodromo.provodromo.model.TipoUsuario;
import com.provodromo.provodromo.model.Usuario;
import com.provodromo.provodromo.repository.TipoUsuarioRepository;
import com.provodromo.provodromo.repository.UsuarioRepository;
import com.provodromo.provodromo.service.base.BaseServiceNew;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

import java.util.stream.Collectors;


@Service
@AllArgsConstructor
public class TipoUsuarioService implements BaseServiceNew<TipoUsuarioRequestDTO, TipoUsuarioResponseDTO, Long> {

    private final TipoUsuarioRepository repository;
    private final UsuarioRepository usuarioRepository;

    @Override
    public TipoUsuarioResponseDTO findById(Long id) {
        TipoUsuario tipoUsuario = repository.findById(id)
                .orElseThrow(() -> new RegraNegocioException("Tipo de usuário não encontrado com o id: " + id));
        return convertToTipoUsuarioResponseDTO(tipoUsuario);
    }

    @Override
    public Set<TipoUsuarioResponseDTO> findAll() {
        return repository.findAll().stream()
                .map(this::convertToTipoUsuarioResponseDTO)
                .collect(Collectors.toSet());
    }

    @Override
    public TipoUsuarioResponseDTO update(Long id, TipoUsuarioRequestDTO dto) {
        TipoUsuario tipoUsuario = convertToTipoUsuario(dto);
        if (!repository.existsById(id)) {
            throw new RegraNegocioException("Tipo de usuário não encontrado com o id: " + id);
        }
        tipoUsuario.setId(id);
        return convertToTipoUsuarioResponseDTO(repository.save(tipoUsuario));
    }

    @Override
    public TipoUsuarioResponseDTO create(TipoUsuarioRequestDTO dto) {
        if (repository.findByNome(dto.getNome()) != null) {
            throw new RegraNegocioException("Já existe um tipo de usuário com o nome: " + dto.getNome());
        }
        TipoUsuario tipoUsuario = convertToTipoUsuario(dto);
        return convertToTipoUsuarioResponseDTO(repository.save(tipoUsuario));
    }

    @Override
    public void deleteById(Long id) {
        TipoUsuario tipoUsuario = repository.findById(id)
                .orElseThrow(() -> new RegraNegocioException("Tipo de usuário não encontrado com o id: " + id));

        if (!tipoUsuario.getNome().equals("Comum")) {
            List<Usuario> usuarios = usuarioRepository.findByTipoUsuarioId(id);
            TipoUsuario tipoComum = repository.findByNome("Comum");

            for (Usuario usuario : usuarios) {
                usuario.setTipoUsuario(tipoComum);
                usuarioRepository.save(usuario);
            }

            repository.deleteById(id);
        }
    }

    public TipoUsuarioResponseDTO findByName(String name) {
        TipoUsuario tipoUsuario = repository.findByNome(name);
        if (tipoUsuario == null) {
            throw new RegraNegocioException("Tipo de usuário não encontrado com o nome: " + name);
        }
        return convertToTipoUsuarioResponseDTO(tipoUsuario);
    }

    private TipoUsuario convertToTipoUsuario(TipoUsuarioRequestDTO dto) {
        TipoUsuario entity = new TipoUsuario();
        entity.setNome(dto.getNome());
        return entity;
    }

    private TipoUsuarioResponseDTO convertToTipoUsuarioResponseDTO(TipoUsuario entity) {
        TipoUsuarioResponseDTO dto = new TipoUsuarioResponseDTO();
        dto.setNome(entity.getNome());
        return dto;
    }
}

