package br.com.erico.tcc.sdp.service;

import br.com.erico.tcc.sdp.dto.NovoProjetoDto;
import br.com.erico.tcc.sdp.dto.ProjetoResponseDto;
import br.com.erico.tcc.sdp.model.EixoTecnologico;
import br.com.erico.tcc.sdp.model.Projeto;
import br.com.erico.tcc.sdp.repository.ProjetoRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.UUID;

@Service
public class ProjetoService {

    private final ProjetoRepository projetoRepository;

    public ProjetoService(ProjetoRepository projetoRepository) {
        this.projetoRepository = projetoRepository;
    }

    public ProjetoResponseDto getProjetoById(UUID projetoId) throws Exception {
        var entity = projetoRepository.findById(projetoId);

        return entity.map(p -> new ProjetoResponseDto(p.getId(), p.getNumero(), p.getNome(), p.getModalidade(),
                        p.getJustificativa(), p.getDataCriacao(), p.getAno(), p.getStatus().getId(),
                        p.isPortalProjetos(), p.getDataFinalizacao(), p.getImpactosAmbientais()))
                .orElseThrow(() -> new Exception("Projeto " + projetoId + " não encontrado"));
    }

    public UUID addProjeto(NovoProjetoDto novoProjetoDto) {
        var projeto = novoProjetoDto.toProjetoEntity();
        var savedProjeto = projetoRepository.save(projeto);

        return savedProjeto.getId();
    }

}
