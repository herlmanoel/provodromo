package com.provodromo.provodromo.service;

import com.provodromo.provodromo.dto.request.DadosPessoaisRequestDTO;
import com.provodromo.provodromo.dto.request.DadosPessoaisUpdateRequestDTO;
import com.provodromo.provodromo.dto.response.DadosPessoaisResponseDTO;
import com.provodromo.provodromo.error.exception.RegraNegocioException;
import com.provodromo.provodromo.model.DadosPessoais;
import com.provodromo.provodromo.model.Usuario;
import com.provodromo.provodromo.repository.DadosPessoaisRepository;
import com.provodromo.provodromo.repository.UsuarioRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DadosPessoaisService {
    private final UsuarioRepository usuarioRepository;
    private final DadosPessoaisRepository dadosPessoaisRepository;

    public void validarDadosPessoais(DadosPessoaisRequestDTO dto) {
        if (dto.getDataNascimento() == null) {
            throw new RegraNegocioException("A data de nascimento não pode estar nula");
        }

        if (dto.getCpf() == null || dto.getCpf().trim().isEmpty()) {
            throw new RegraNegocioException("O CPF não pode estar em branco");
        }

        if (dadosPessoaisRepository.existsByCpf(dto.getCpf())) {
            throw new RegraNegocioException("O CPF já foi cadastrado.");
        }

        if (dto.getTelefone() == null || dto.getTelefone().trim().isEmpty()) {
            throw new RegraNegocioException("O telefone não pode estar em branco");
        }

        if (dto.getUf() == null || dto.getUf().trim().isEmpty()) {
            throw new RegraNegocioException("A UF não pode estar em branco");
        }

        if (dto.getCidade() == null || dto.getCidade().trim().isEmpty()) {
            throw new RegraNegocioException("A cidade não pode estar em branco");
        }

        if (dto.getRua() == null || dto.getRua().trim().isEmpty()) {
            throw new RegraNegocioException("A rua não pode estar em branco");
        }

        if (dto.getNumero() == null) {
            throw new RegraNegocioException("O número não pode estar nulo");
        }
    }

    public DadosPessoaisResponseDTO findById(Long id) {
        DadosPessoais dadosPessoais = dadosPessoaisRepository.findById(id)
                .orElseThrow(() -> new RegraNegocioException("Dados Pessoais não encontrado com o ID: " + id));
        return convertToDadosPessoaisResponseDTO(dadosPessoais);
    }

    public DadosPessoaisResponseDTO findByNomeUsuario(String nome) {
        DadosPessoais dadosPessoais = dadosPessoaisRepository.findByUsuarioNome(nome);
        return convertToDadosPessoaisResponseDTO(dadosPessoais);
    }

    public DadosPessoaisResponseDTO findByCpf(String cpf) {
        DadosPessoais dadosPessoais = dadosPessoaisRepository.getByCpf(cpf);
        return convertToDadosPessoaisResponseDTO(dadosPessoais);
    }

    public DadosPessoaisResponseDTO update(Long idUsuario, DadosPessoaisUpdateRequestDTO dto) {
        try {
            DadosPessoais dadosPessoais = dadosPessoaisRepository.findByUsuarioId(idUsuario);

            if (dto.getDataNascimento() != null) {
                dadosPessoais.setDataNascimento(dto.getDataNascimento());
            }
            if (dto.getTelefone() != null) {
                dadosPessoais.setTelefone(dto.getTelefone());
            }
            if (dto.getUf() != null) {
                dadosPessoais.setUf(dto.getUf());
            }
            if (dto.getCidade() != null) {
                dadosPessoais.setCidade(dto.getCidade());
            }
            if (dto.getRua() != null) {
                dadosPessoais.setRua(dto.getRua());
            }
            if (dto.getNumero() != null) {
                dadosPessoais.setNumero(dto.getNumero());
            }
            if (dto.getComplemento() != null) {
                dadosPessoais.setComplemento(dto.getComplemento());
            }

            dadosPessoais = dadosPessoaisRepository.save(dadosPessoais);

            return convertToDadosPessoaisResponseDTO(dadosPessoais);
        } catch (Exception e) {
            throw new RegraNegocioException("Erro ao atualizar o usuário: " + e.getMessage());
        }
    }

    private DadosPessoaisResponseDTO convertToDadosPessoaisResponseDTO(DadosPessoais dadosPessoais) {
        if (dadosPessoais == null) {
            return null;
        }
        return new DadosPessoaisResponseDTO(
                dadosPessoais.getId(),
                dadosPessoais.getDataNascimento(),
                dadosPessoais.getCpf(),
                dadosPessoais.getTelefone(),
                dadosPessoais.getUf(),
                dadosPessoais.getCidade(),
                dadosPessoais.getRua(),
                dadosPessoais.getNumero(),
                dadosPessoais.getComplemento()
        );
    }

    private DadosPessoais convertToDadosPessoaisResponseDTO(DadosPessoaisRequestDTO dto) {
        if (dto == null) {
            return null;
        }
        DadosPessoais dadosPessoais = new DadosPessoais();
        dadosPessoais.setDataNascimento(dto.getDataNascimento());
        dadosPessoais.setCpf(dto.getCpf());
        dadosPessoais.setTelefone(dto.getTelefone());
        dadosPessoais.setUf(dto.getUf());
        dadosPessoais.setCidade(dto.getCidade());
        dadosPessoais.setRua(dto.getRua());
        dadosPessoais.setNumero(dto.getNumero());
        dadosPessoais.setComplemento(dto.getComplemento());

        return dadosPessoais;
    }

    public DadosPessoaisResponseDTO create(Long idUsuario, DadosPessoaisRequestDTO dto) {
        validarDadosPessoais(dto);
        Usuario usuario = usuarioRepository.findById(idUsuario)
                .orElseThrow(() -> new RegraNegocioException("Usuário não encontrado com o ID: " + idUsuario));

        try {
            DadosPessoais dadosPessoais = convertToDadosPessoaisResponseDTO(dto);
            dadosPessoais.setUsuario(usuario);
            dadosPessoais = dadosPessoaisRepository.save(dadosPessoais);
            return convertToDadosPessoaisResponseDTO(dadosPessoais);
        } catch (Exception e) {
            throw new RegraNegocioException("Erro ao salvar/atualizar o usuário: " + e.getMessage());
        }
    }
}