package br.com.erico.tcc.sdp.service;

import br.com.erico.tcc.sdp.dto.NovoProjetoDto;
import br.com.erico.tcc.sdp.dto.ProjetoResponseDto;
import br.com.erico.tcc.sdp.dto.ProjetoUsuarioResponseDto;
import br.com.erico.tcc.sdp.dto.UpdateProjetoDto;
import br.com.erico.tcc.sdp.enumeration.StatusEnum;
import br.com.erico.tcc.sdp.model.EixoTecnologico;
import br.com.erico.tcc.sdp.model.Projeto;
import br.com.erico.tcc.sdp.model.Status;
import br.com.erico.tcc.sdp.model.Usuario;
import br.com.erico.tcc.sdp.repository.ProjetoRepository;
import br.com.erico.tcc.sdp.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
public class ProjetoService {

    private final ProjetoRepository projetoRepository;
    private final UsuarioRepository usuarioRepository;

    public ProjetoService(ProjetoRepository projetoRepository, UsuarioRepository usuarioRepository) {
        this.projetoRepository = projetoRepository;
        this.usuarioRepository = usuarioRepository;
    }

    public List<ProjetoUsuarioResponseDto> getProjetosByUsuario(UUID usuarioId) throws Exception{
        usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new Exception("Não foram encontrados usuários com ID " + usuarioId));

        var projetos = projetoRepository.findByUsuarioId(usuarioId);

        return projetos.stream()
                .map(p -> new ProjetoUsuarioResponseDto(p.getId(), p.getNumero(), p.getNome(), p.getModalidade(),
                        p.getDataCriacao(), p.getAno(), p.getStatus().getId(), p.isFinalizado(), p.getDataFinalizacao()))
                .toList();
    }

    public ProjetoResponseDto getProjetoById(UUID projetoId) throws Exception {
        var entity = projetoRepository.findById(projetoId);

        return entity.map(p -> new ProjetoResponseDto(p.getId(), p.getNumero(), p.getNome(), p.getModalidade(),
                        p.getJustificativa(), p.getDataCriacao(), p.getAno(), p.getStatus().getId(),
                        p.isPortalProjetos(), p.getDataFinalizacao(), p.getImpactosAmbientais()))
                .orElseThrow(() -> new Exception("Não foram encontrados projetos com ID " + projetoId));
    }

    public UUID addProjeto(NovoProjetoDto novoProjetoDto) throws Exception {
        usuarioRepository.findById(novoProjetoDto.usuarioId())
                .orElseThrow(() -> new Exception("Não foram encontrados usuários com o ID " + novoProjetoDto.usuarioId()));

        var projeto = new Projeto();
        projeto.setNumero(novoProjetoDto.numero());
        projeto.setNome(novoProjetoDto.nome().toUpperCase());
        projeto.setModalidade(novoProjetoDto.modalidade());
        projeto.setJustificativa(novoProjetoDto.justificativa());

        var usuario = new Usuario();
        usuario.setId(novoProjetoDto.usuarioId());
        projeto.setUsuario(usuario);

        var eixoTecnologico = new EixoTecnologico();
        eixoTecnologico.setId(novoProjetoDto.eixoTecnologicoId());
        projeto.setEixoTecnologico(eixoTecnologico);

        projeto.setImpactosAmbientais(novoProjetoDto.impactosAmbientais());
        projeto.setDataCriacao(LocalDate.now());
        projeto.setAno((short) LocalDate.now().getYear());

        var status = new Status();
        status.setId(StatusEnum.NAO_FINALIZADO.ordinal());
        projeto.setStatus(status);

        var savedProjeto = projetoRepository.save(projeto);

        return savedProjeto.getId();
    }

    public void updateProjeto(UpdateProjetoDto updateProjetoDto, UUID projetoId) throws Exception {
        var projeto = projetoRepository.findById(projetoId)
                .orElseThrow(() -> new Exception("Não foram encontrados projetos com ID " + projetoId));

        projeto.setNumero(updateProjetoDto.numero() != null ? updateProjetoDto.numero() : projeto.getNumero());
        projeto.setNome(updateProjetoDto.nome() != null ? updateProjetoDto.nome().toUpperCase() : projeto.getNome());
        projeto.setModalidade(updateProjetoDto.modalidade() != null ? updateProjetoDto.modalidade() : projeto.getModalidade());
        projeto.setJustificativa(updateProjetoDto.justificativa() != null ? updateProjetoDto.justificativa() : projeto.getJustificativa());
        projeto.setImpactosAmbientais(updateProjetoDto.impactosAmbientais() != null ? updateProjetoDto.impactosAmbientais() : projeto.getImpactosAmbientais());

        projetoRepository.save(projeto);
    }

}
