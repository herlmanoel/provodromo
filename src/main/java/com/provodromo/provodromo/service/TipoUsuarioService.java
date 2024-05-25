package com.provodromo.provodromo.service;

import com.provodromo.provodromo.dto.TipoUsuarioDTO;
import com.provodromo.provodromo.error.exception.RegraNegocioException;
import com.provodromo.provodromo.model.HistoricoTipoUsuario;
import com.provodromo.provodromo.model.TipoUsuario;
import com.provodromo.provodromo.model.Usuario;
import com.provodromo.provodromo.repository.HistoricoTipoUsuarioRepository;
import com.provodromo.provodromo.repository.TipoUsuarioRepository;
import com.provodromo.provodromo.repository.UsuarioRepository;
import com.provodromo.provodromo.service.base.BaseServiceNew;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import java.util.stream.Collectors;


@Service
@AllArgsConstructor
public class TipoUsuarioService implements BaseServiceNew<TipoUsuarioDTO, Long> {

    private final TipoUsuarioRepository repository;
    private final UsuarioRepository usuarioRepository;
    private final HistoricoTipoUsuarioRepository historicoRepository;

    @Override
    public TipoUsuarioDTO findById(Long id) {
        TipoUsuario tipoUsuario = repository.findById(id)
                .orElseThrow(() -> new RegraNegocioException("Tipo de usuário não encontrado com o id: " + id));
        return entityToDTO(tipoUsuario);
    }

    @Override
    public Set<TipoUsuarioDTO> findAll() {
        return repository.findAll().stream()
                .map(this::entityToDTO)
                .collect(Collectors.toSet());
    }

    @Override
    public TipoUsuarioDTO update(Long id, TipoUsuarioDTO dto) {
        TipoUsuario tipoUsuario = dtoToEntity(dto);
        if (!repository.existsById(id)) {
            throw new RegraNegocioException("Tipo de usuário não encontrado com o id: " + id);
        }

        TipoUsuario tipoUsuarioAntigo = repository.findById(id)
                .orElseThrow(() -> new RegraNegocioException("Tipo de usuário não encontrado com o id: " + id));

        salvarHistorico(tipoUsuarioAntigo);

        tipoUsuario.setId(id);
        return entityToDTO(repository.save(tipoUsuario));
    }

    @Override
    public TipoUsuarioDTO create(TipoUsuarioDTO dto) {
        if (repository.findByNome(dto.getNome()) != null) {
            throw new RegraNegocioException("Já existe um tipo de usuário com o nome: " + dto.getNome());
        }
        TipoUsuario tipoUsuario = dtoToEntity(dto);

        TipoUsuario tipoUsuarioCreated = repository.save(tipoUsuario);
        salvarHistorico(tipoUsuarioCreated);

        return entityToDTO(repository.save(tipoUsuarioCreated));
    }

    @Override
    public void deleteById(Long id) {
        TipoUsuario tipoUsuario = repository.findById(id)
                .orElseThrow(() -> new RegraNegocioException("Tipo de usuário não encontrado com o id: " + id));

        salvarHistorico(tipoUsuario);

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

    public TipoUsuarioDTO findByName(String name) {
        TipoUsuario tipoUsuario = repository.findByNome(name);
        if (tipoUsuario == null) {
            throw new RegraNegocioException("Tipo de usuário não encontrado com o nome: " + name);
        }
        return entityToDTO(tipoUsuario);
    }

    private TipoUsuario dtoToEntity(TipoUsuarioDTO dto) {
        TipoUsuario entity = new TipoUsuario();
        entity.setNome(dto.getNome());
        return entity;
    }

    private TipoUsuarioDTO entityToDTO(TipoUsuario entity) {
        TipoUsuarioDTO dto = new TipoUsuarioDTO();
        dto.setNome(entity.getNome());
        return dto;
    }
    private void salvarHistorico(TipoUsuario tipoUsuarioAntigo) {
        HistoricoTipoUsuario historico = new HistoricoTipoUsuario();
        historico.setNome(tipoUsuarioAntigo.getNome());
        historico.setDataAlteracao(LocalDateTime.now());
        historico.setTipoUsuarioId(tipoUsuarioAntigo.getId());
        historicoRepository.save(historico);
    }
}

