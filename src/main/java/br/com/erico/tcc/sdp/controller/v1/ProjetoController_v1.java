package br.com.erico.tcc.sdp.controller.v1;

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
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/projetos")
@Tag(name = "Projetos | Nível 1", description = "Controller com URIs ajustadas")
public class ProjetoController_v1 {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProjetoController_v1.class);
    private final ProjetoService projetoService;

    public ProjetoController_v1(ProjetoService projetoService) {
        this.projetoService = projetoService;
    }

    @GetMapping("/usuario/{usuarioId}")
    @Operation(summary = "Busca os projetos pelo UUID do usuário")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Lista de projetos do usuário", content = {
                    @Content(array = @ArraySchema(schema = @Schema(implementation = GetProjetoUsuarioResponse.class)), mediaType = MediaType.APPLICATION_JSON_VALUE)
            })
    })
    public List<GetProjetoUsuarioResponse> getProjetosByUsuario(@PathVariable("usuarioId") UUID usuarioId) {
        LOGGER.info("Buscando projetos do usuário {}", usuarioId);

        try {
            return projetoService.getProjetosByUsuario(usuarioId);
        } catch (Exception e) {
            throw e;
        }
    }

    @GetMapping("/{projetoId}")
    @Operation(summary = "Busca o projeto pelo UUID do projeto")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Projeto encontrado", content = {
                    @Content(schema = @Schema(implementation = GetProjetoByIdResponse.class), mediaType = MediaType.APPLICATION_JSON_VALUE)
            })
    })
    public GetProjetoByIdResponse getProjetoById(@PathVariable("projetoId") UUID projetoId) {
        LOGGER.info("Buscando projeto {}", projetoId);
        try {
            return projetoService.getProjetoById(projetoId);
        } catch (Exception e) {
            throw e;
        }
    }

    @PostMapping
    @Operation(summary = "Cria um projeto")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Projeto criado com sucesso", content = {
                    @Content(schema = @Schema(implementation = CreateProjetoResponse.class), mediaType = MediaType.APPLICATION_JSON_VALUE)
            })
    })
    public CreateProjetoResponse addProjeto(@RequestBody CreateProjetoRequest createProjetoRequest) {
        LOGGER.info("Adicionando projeto {}", createProjetoRequest.toString());

        try {
            return projetoService.addProjeto(createProjetoRequest);
        } catch (Exception e) {
            throw e;
        }
    }

    @PutMapping("/{projetoId}")
    @Operation(summary = "Atualiza as informações de um projeto")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Projeto atualizado", content = {
                    @Content(array = @ArraySchema(schema = @Schema(implementation = UpdateProjetoResponse.class)), mediaType = MediaType.APPLICATION_JSON_VALUE)
            })
    })
    public UpdateProjetoResponse updateProjeto(@RequestBody UpdateProjetoRequest updateProjetoRequest, @PathVariable("projetoId") UUID projetoId) {
        LOGGER.info("Atualizando dados do projeto {}", projetoId);
        try {
            return projetoService.updateProjeto(updateProjetoRequest, projetoId);
        } catch (Exception e) {
            throw e;
        }
    }

    @DeleteMapping("/{projetoId}")
    @Operation(summary = "Deleta um projeto")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Projeto deletado! Sem informações adicionais"),
    })
    public void deleteProjeto(@PathVariable("projetoId") UUID projetoId) {
        LOGGER.info("Deletando projeto {}", projetoId);

        try {
            projetoService.deleteProjeto(projetoId);
        } catch (Exception e) {
            throw e;
        }
    }

}
