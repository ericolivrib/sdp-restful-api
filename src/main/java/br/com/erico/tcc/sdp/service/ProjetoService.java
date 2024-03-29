package br.com.erico.tcc.sdp.service;

import br.com.erico.tcc.sdp.dto.*;
import br.com.erico.tcc.sdp.enumeration.PeriodoEnum;
import br.com.erico.tcc.sdp.enumeration.StatusEnum;
import br.com.erico.tcc.sdp.model.EixoTecnologico;
import br.com.erico.tcc.sdp.model.Projeto;
import br.com.erico.tcc.sdp.model.Status;
import br.com.erico.tcc.sdp.model.Usuario;
import br.com.erico.tcc.sdp.repository.PeriodoRepository;
import br.com.erico.tcc.sdp.repository.ProjetoRepository;
import br.com.erico.tcc.sdp.repository.UsuarioRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
public class ProjetoService {

    private final ProjetoRepository projetoRepository;
    private final UsuarioRepository usuarioRepository;
    private final PeriodoRepository periodoRepository;

    public ProjetoService(ProjetoRepository projetoRepository, UsuarioRepository usuarioRepository, PeriodoRepository periodoRepository) {
        this.projetoRepository = projetoRepository;
        this.usuarioRepository = usuarioRepository;
        this.periodoRepository = periodoRepository;
    }

    public List<ProjetoUsuarioResponseDto> getProjetosByUsuario(UUID usuarioId) throws HttpClientErrorException {
        usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new HttpClientErrorException(HttpStatus.UNAUTHORIZED, "Usuário não encontrado"));

        var projetos = projetoRepository.findByUsuarioId(usuarioId);

        return projetos.stream()
                .map(p -> new ProjetoUsuarioResponseDto(p.getId(), p.getNumero(), p.getNome(), p.getModalidade(),
                        p.getDataCriacao(), p.getAno(), p.getStatus().getId(), p.isFinalizado(), p.getDataFinalizacao()))
                .toList();
    }

    public ProjetoResponseDto getProjetoById(UUID projetoId) throws HttpClientErrorException {
        var projeto = projetoRepository.findById(projetoId);

        return projeto.map(p -> new ProjetoResponseDto(p.getId(), p.getNumero(), p.getNome(), p.getModalidade(),
                        p.getJustificativa(), p.getDataCriacao(), p.getAno(), p.getStatus().getId(),
                        p.isPortalProjetos(), p.getDataFinalizacao(), p.getImpactosAmbientais()))
                .orElseThrow(() -> new HttpClientErrorException(HttpStatus.NOT_FOUND, "Não foram encontrados projetos com ID " + projetoId));
    }

    public ProjetoAdicionadoResponseDto addProjeto(AdicionarProjetoDto adicionarProjetoDto) throws HttpClientErrorException, HttpServerErrorException {
        usuarioRepository.findById(adicionarProjetoDto.usuarioId())
                .orElseThrow(() -> new HttpClientErrorException(HttpStatus.UNAUTHORIZED, "Usuário não encontrado"));

        var periodoSubmissaoProjetos = periodoRepository.findById(PeriodoEnum.SUBMISSAO_PROJETOS.getId())
                .orElseThrow(() -> new HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR, "Falha ao consultar período de submissão de projetos"));

        if (periodoSubmissaoProjetos.getDataInicio().isAfter(LocalDate.now()) ||
                periodoSubmissaoProjetos.getDataFim().isBefore(LocalDate.now())) {
            throw new HttpClientErrorException(HttpStatus.FORBIDDEN, "Fora do prazo de submissão de projetos");
        }

        if (projetoRepository.existsByNumero(adicionarProjetoDto.numero())) {
            throw new HttpClientErrorException(HttpStatus.CONFLICT, "Número de projeto já existente");
        }

        var usuario = new Usuario();
        usuario.setId(adicionarProjetoDto.usuarioId());

        var eixoTecnologico = new EixoTecnologico();
        eixoTecnologico.setId(adicionarProjetoDto.eixoTecnologicoId());

        var status = new Status(StatusEnum.NAO_FINALIZADO);

        var projeto = new Projeto();
        projeto.setNumero(adicionarProjetoDto.numero());
        projeto.setNome(adicionarProjetoDto.nome().toUpperCase());
        projeto.setModalidade(adicionarProjetoDto.modalidade());
        projeto.setJustificativa(adicionarProjetoDto.justificativa());
        projeto.setImpactosAmbientais(adicionarProjetoDto.impactosAmbientais());
        projeto.setDataCriacao(LocalDate.now());
        projeto.setAno((short) LocalDate.now().getYear());
        projeto.setUsuario(usuario);
        projeto.setEixoTecnologico(eixoTecnologico);
        projeto.setStatus(status);

        var savedProjeto = projetoRepository.save(projeto);

        return new ProjetoAdicionadoResponseDto(savedProjeto);
    }

    public ProjetoAtualizadoResponseDto updateProjeto(AtualizarProjetoDto atualizarProjetoDto, UUID projetoId) throws HttpClientErrorException {
        var projeto = projetoRepository.findById(projetoId)
                .orElseThrow(() -> new HttpClientErrorException(HttpStatus.NOT_FOUND, "Não foram encontrados projetos com ID " + projetoId));

        projeto.setNumero(atualizarProjetoDto.numero() != null ? atualizarProjetoDto.numero() : projeto.getNumero());
        projeto.setNome(atualizarProjetoDto.nome() != null ? atualizarProjetoDto.nome().toUpperCase() : projeto.getNome());
        projeto.setModalidade(atualizarProjetoDto.modalidade() != null ? atualizarProjetoDto.modalidade() : projeto.getModalidade());
        projeto.setJustificativa(atualizarProjetoDto.justificativa() != null ? atualizarProjetoDto.justificativa() : projeto.getJustificativa());
        projeto.setImpactosAmbientais(atualizarProjetoDto.impactosAmbientais() != null ? atualizarProjetoDto.impactosAmbientais() : projeto.getImpactosAmbientais());

        projetoRepository.save(projeto);

        return new ProjetoAtualizadoResponseDto(projeto);
    }

    public void deleteProjeto(UUID projetoId) throws HttpClientErrorException {
        var projeto = projetoRepository.findById(projetoId)
                .orElseThrow(() -> new HttpClientErrorException(HttpStatus.NOT_FOUND, "Não foram encontrados projetos com ID " + projetoId));

        if (!Objects.equals(projeto.getStatus().getId(), StatusEnum.NAO_FINALIZADO.getId()) &&
                !Objects.equals(projeto.getStatus().getId(), StatusEnum.NAO_APROVADO.getId())) {
            throw new HttpClientErrorException(HttpStatus.FORBIDDEN,
                    "Projetos com status de valor " + projeto.getStatus().getId() + " (" + projeto.getStatus().getNome() + ") não podem ser deletados");
        }

        projetoRepository.delete(projeto);
    }

}
