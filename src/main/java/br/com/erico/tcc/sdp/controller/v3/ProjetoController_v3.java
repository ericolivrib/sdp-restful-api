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
import br.com.erico.tcc.sdp.dto.response.exception.*;
import br.com.erico.tcc.sdp.service.ProjetoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.UUID;

@RestController
@RequestMapping("/api/v3/projetos")
@Tag(name = "Projetos | Nível 3", description = "Controller com HATEOAS")
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
    @Operation(summary = "Busca os projetos pelo UUID do usuário")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Lista de projetos do usuário", content = {
                    @Content(array = @ArraySchema(schema = @Schema(implementation = GetProjetoUsuarioResponse.class)), mediaType = MediaType.APPLICATION_JSON_VALUE)
            }),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado", content = {
                    @Content(schema = @Schema(implementation = UsuarioNaoEncontradoExceptionResponse.class), mediaType = MediaType.APPLICATION_JSON_VALUE)
            })
    })
    public ResponseEntity<CollectionModel<EntityModel<GetProjetoUsuarioResponse>>> getProjetosByUsuario(@PathVariable("usuarioId") UUID usuarioId) {
        LOGGER.info("Buscando projetos do usuário {}", usuarioId);

        var projetoList = projetoService.getProjetosByUsuario(usuarioId);
        var projetos = projetoUsuarioModelAssembler.toCollection(projetoList, usuarioId);

        return ResponseEntity.ok(projetos);
    }

    @GetMapping("/{projetoId}")
    @Operation(summary = "Busca o projeto pelo UUID do projeto")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Projeto encontrado", content = {
                    @Content(schema = @Schema(implementation = GetProjetoByIdResponse.class), mediaType = MediaType.APPLICATION_JSON_VALUE)
            }),
            @ApiResponse(responseCode = "404", description = "Projeto não encontrado", content = {
                    @Content(schema = @Schema(implementation = ProjetoNaoEncontradoExceptionResponse.class), mediaType = MediaType.APPLICATION_JSON_VALUE)
            })
    })
    public ResponseEntity<EntityModel<GetProjetoByIdResponse>> getProjetoById(@PathVariable("projetoId") UUID projetoId) {
        LOGGER.info("Buscando projeto {}", projetoId);

        var projetoEncontrado = projetoService.getProjetoById(projetoId);
        var projeto = projetoModelAssembler.toModel(projetoEncontrado);
        return ResponseEntity.ok(projeto);
    }

    @PostMapping
    @Operation(summary = "Cria um projeto")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Projeto criado com sucesso", content = {
                    @Content(schema = @Schema(implementation = CreateProjetoResponse.class), mediaType = MediaType.APPLICATION_JSON_VALUE)
            }),
            @ApiResponse(responseCode = "403", description = "Período de submissão de projetos não está vigente", content = {
                    @Content(schema = @Schema(implementation = PeriodoInvalidoExceptionResponse.class), mediaType = MediaType.APPLICATION_JSON_VALUE)
            }),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado", content = {
                    @Content(schema = @Schema(implementation = UsuarioNaoEncontradoExceptionResponse.class), mediaType = MediaType.APPLICATION_JSON_VALUE)
            }),
            @ApiResponse(responseCode = "409", description = "Já existem projetos cadastrados com este número", content = {
                    @Content(schema = @Schema(implementation = ProjetoExistenteExceptionResponse.class), mediaType = MediaType.APPLICATION_JSON_VALUE)
            })
    })
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
    @Operation(summary = "Atualiza as informações de um projeto")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Projeto atualizado", content = {
                    @Content(array = @ArraySchema(schema = @Schema(implementation = UpdateProjetoResponse.class)), mediaType = MediaType.APPLICATION_JSON_VALUE)
            }),
            @ApiResponse(responseCode = "404", description = "Projeto não encontrado", content = {
                    @Content(schema = @Schema(implementation = ProjetoNaoEncontradoExceptionResponse.class), mediaType = MediaType.APPLICATION_JSON_VALUE)
            })
    })
    public ResponseEntity<EntityModel<UpdateProjetoResponse>> updateProjeto(@RequestBody UpdateProjetoRequest updateProjetoRequest, @PathVariable("projetoId") UUID projetoId) {
        LOGGER.info("Atualizando dados do projeto {}", projetoId);

        var projetoAtualizado = projetoService.updateProjeto(updateProjetoRequest, projetoId);
        var projeto = projetoAtualizadoModelAssembler.toModel(projetoAtualizado);
        return ResponseEntity.ok(projeto);
    }

    @DeleteMapping("/{projetoId}")
    @Operation(summary = "Deleta um projeto")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Projeto deletado! Sem informações adicionais"),
            @ApiResponse(responseCode = "403", description = "Projeto não pode ser deletado", content = {
                    @Content(schema = @Schema(implementation = StatusRemocaoProjetoInvalidoExceptionResponse.class), mediaType = MediaType.APPLICATION_JSON_VALUE)
            }),
            @ApiResponse(responseCode = "404", description = "Projeto não encontrado", content = {
                    @Content(schema = @Schema(implementation = ProjetoNaoEncontradoExceptionResponse.class), mediaType = MediaType.APPLICATION_JSON_VALUE)
            })
    })
    public ResponseEntity<Void> deleteProjeto(@PathVariable("projetoId") UUID projetoId) {
        LOGGER.info("Deletando projeto {}", projetoId);

        projetoService.deleteProjeto(projetoId);
        return ResponseEntity.noContent().build();
    }

}
