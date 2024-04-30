package br.com.erico.tcc.sdp.controller.v3;

import br.com.erico.tcc.sdp.assembler.ProjetoAdicionadoModelAssembler;
import br.com.erico.tcc.sdp.assembler.ProjetoModelAssembler;
import br.com.erico.tcc.sdp.assembler.ProjetoUsuarioModelAssembler;
import br.com.erico.tcc.sdp.assembler.ProjetoAtualizadoModelAssembler;
import br.com.erico.tcc.sdp.dto.request.CreateProjetoRequest;
import br.com.erico.tcc.sdp.dto.request.UpdateProjetoRequest;
import br.com.erico.tcc.sdp.dto.response.CreateProjetoResponse;
import br.com.erico.tcc.sdp.dto.response.GetProjetoByIdResponse;
import br.com.erico.tcc.sdp.dto.response.GetProjetoUsuarioResponse;
import br.com.erico.tcc.sdp.dto.response.UpdateProjetoResponse;
import br.com.erico.tcc.sdp.service.ProjetoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.UUID;

@RestController
@RequestMapping("/api/v3/projetos")
public class ProjetoController_v3 {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProjetoController_v3.class);
    private final ProjetoService projetoService;
    private final ProjetoUsuarioModelAssembler projetoUsuarioModelAssembler;
    private final ProjetoModelAssembler projetoModelAssembler;
    private final ProjetoAdicionadoModelAssembler projetoAdicionadoModelAssembler;
    private final ProjetoAtualizadoModelAssembler projetoAtualizadoModelAssembler;

    public ProjetoController_v3(ProjetoService projetoService, ProjetoUsuarioModelAssembler projetoUsuarioModelAssembler, ProjetoModelAssembler projetoModelAssembler, ProjetoAdicionadoModelAssembler projetoAdicionadoModelAssembler, ProjetoAtualizadoModelAssembler projetoAtualizadoModelAssembler) {
        this.projetoService = projetoService;
        this.projetoUsuarioModelAssembler = projetoUsuarioModelAssembler;
        this.projetoModelAssembler = projetoModelAssembler;
        this.projetoAdicionadoModelAssembler = projetoAdicionadoModelAssembler;
        this.projetoAtualizadoModelAssembler = projetoAtualizadoModelAssembler;
    }

    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<CollectionModel<EntityModel<GetProjetoUsuarioResponse>>> getProjetosByUsuario(@PathVariable("usuarioId") UUID usuarioId) {
        LOGGER.info("Buscando projetos do usuário {}", usuarioId);

        var projetoList = projetoService.getProjetosByUsuario(usuarioId);
        var projetos = projetoUsuarioModelAssembler.toCollection(projetoList, usuarioId);

        return ResponseEntity.ok(projetos);
    }

    @GetMapping("/{projetoId}")
    public ResponseEntity<EntityModel<GetProjetoByIdResponse>> getProjetoById(@PathVariable("projetoId") UUID projetoId) {
        LOGGER.info("Buscando projeto {}", projetoId);

        var projetoEncontrado = projetoService.getProjetoById(projetoId);
        var projeto = projetoModelAssembler.toModel(projetoEncontrado);
        return ResponseEntity.ok(projeto);
    }

    @PostMapping
    public ResponseEntity<EntityModel<CreateProjetoResponse>> addProjeto(@RequestBody CreateProjetoRequest createProjetoRequest) {
        LOGGER.info("Adicionando projeto {}", createProjetoRequest.toString());

        var novoProjeto = projetoService.addProjeto(createProjetoRequest);
        var projeto = projetoAdicionadoModelAssembler.toModel(novoProjeto);

        var createdLocation = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{projetoId}")
                .buildAndExpand(novoProjeto.id())
                .toUri();

        return ResponseEntity.created(createdLocation).body(projeto);
    }

    @PutMapping("/{projetoId}")
    public ResponseEntity<EntityModel<UpdateProjetoResponse>> updateProjeto(@RequestBody UpdateProjetoRequest updateProjetoRequest, @PathVariable("projetoId") UUID projetoId) {
        LOGGER.info("Atualizando dados do projeto {}", projetoId);

        var projetoAtualizado = projetoService.updateProjeto(updateProjetoRequest, projetoId);
        var projeto = projetoAtualizadoModelAssembler.toModel(projetoAtualizado);
        return ResponseEntity.ok(projeto);
    }

    @DeleteMapping("/{projetoId}")
    public ResponseEntity<Void> deleteProjeto(@PathVariable("projetoId") UUID projetoId) {
        LOGGER.info("Deletando projeto {}", projetoId);

        projetoService.deleteProjeto(projetoId);
        return ResponseEntity.noContent().build();
    }

}
