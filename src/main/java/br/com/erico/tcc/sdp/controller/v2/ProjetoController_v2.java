package br.com.erico.tcc.sdp.controller.v2;

import br.com.erico.tcc.sdp.controller.v1.ProjetoController_v1;
import br.com.erico.tcc.sdp.dto.request.CreateProjetoRequest;
import br.com.erico.tcc.sdp.dto.request.UpdateProjetoRequest;
import br.com.erico.tcc.sdp.dto.response.CreateProjetoResponse;
import br.com.erico.tcc.sdp.dto.response.GetProjetoByIdResponse;
import br.com.erico.tcc.sdp.dto.response.GetProjetoUsuarioResponse;
import br.com.erico.tcc.sdp.dto.response.UpdateProjetoResponse;
import br.com.erico.tcc.sdp.service.ProjetoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v2/projetos")
public class ProjetoController_v2 {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProjetoController_v1.class);
    private final ProjetoService projetoService;

    public ProjetoController_v2(ProjetoService projetoService) {
        this.projetoService = projetoService;
    }

    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<GetProjetoUsuarioResponse>> getProjetosByUsuario(@PathVariable("usuarioId") UUID usuarioId) {
        LOGGER.info("Buscando projetos do usuário {}", usuarioId);

        var projetos = projetoService.getProjetosByUsuario(usuarioId);
        return ResponseEntity.ok(projetos);
    }

    @GetMapping("/{projetoId}")
    public ResponseEntity<GetProjetoByIdResponse> getProjetoById(@PathVariable("projetoId") UUID projetoId) {
        LOGGER.info("Buscando projeto {}", projetoId);

        var projeto = projetoService.getProjetoById(projetoId);
        return ResponseEntity.ok(projeto);
    }

    @PostMapping
    public ResponseEntity<CreateProjetoResponse> addProjeto(@RequestBody CreateProjetoRequest createProjetoRequest) {
        LOGGER.info("Adicionando projeto {}", createProjetoRequest.toString());

        var novoProjetoResponseDto = projetoService.addProjeto(createProjetoRequest);

        var createdLocation = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{projetoId}")
                .buildAndExpand(novoProjetoResponseDto.id())
                .toUri();

        return ResponseEntity.created(createdLocation).body(novoProjetoResponseDto);
    }

    @PutMapping("/{projetoId}")
    public ResponseEntity<UpdateProjetoResponse> updateProjeto(@RequestBody UpdateProjetoRequest updateProjetoRequest, @PathVariable("projetoId") UUID projetoId) {
        LOGGER.info("Atualizando dados do projeto {}", projetoId);

        var updateProjetoResponse = projetoService.updateProjeto(updateProjetoRequest, projetoId);
        return ResponseEntity.ok(updateProjetoResponse);
    }

    @DeleteMapping("/{projetoId}")
    public ResponseEntity<Void> deleteProjeto(@PathVariable("projetoId") UUID projetoId) {
        LOGGER.info("Deletando projeto {}", projetoId);

        projetoService.deleteProjeto(projetoId);
        return ResponseEntity.noContent().build();
    }

}
