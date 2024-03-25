package br.com.erico.tcc.sdp.controller.v2;

import br.com.erico.tcc.sdp.controller.v1.ProjetoController_v1;
import br.com.erico.tcc.sdp.dto.NovoProjetoDto;
import br.com.erico.tcc.sdp.dto.ProjetoResponseDto;
import br.com.erico.tcc.sdp.dto.ProjetoUsuarioResponseDto;
import br.com.erico.tcc.sdp.dto.UpdateProjetoDto;
import br.com.erico.tcc.sdp.service.ProjetoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
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
    public ResponseEntity<List<ProjetoUsuarioResponseDto>> getProjetosByUsuario(@PathVariable("usuarioId") UUID usuarioId) {

        LOGGER.info("Buscando projetos do usuário {}", usuarioId);

        try {
            var projetos = projetoService.getProjetosByUsuario(usuarioId);
            return ResponseEntity.ok(projetos);
        } catch (HttpClientErrorException e) {
            LOGGER.error(e.getMessage());
            return ResponseEntity.status(e.getStatusCode()).build();
        }

    }

    @GetMapping("/{projetoId}")
    public ResponseEntity<ProjetoResponseDto> getProjetoById(@PathVariable("projetoId") UUID projetoId) {
        LOGGER.info("Buscando projeto {}", projetoId);

        try {
            var projeto = projetoService.getProjetoById(projetoId);
            return ResponseEntity.ok(projeto);
        } catch (HttpClientErrorException e) {
            LOGGER.error(e.getStatusText());
            return ResponseEntity.status(e.getStatusCode()).build();
        }
    }

    @PostMapping
    public ResponseEntity<UUID> addProjeto(@RequestBody NovoProjetoDto novoProjetoDto) {
        LOGGER.info("Adicionando projeto {}", novoProjetoDto.toString());

        try {
            var projetoId = projetoService.addProjeto(novoProjetoDto);

            var createdLocation = ServletUriComponentsBuilder.fromCurrentRequest()
                    .path("/{projetoId}")
                    .buildAndExpand(projetoId)
                    .toUri();

            return ResponseEntity.created(createdLocation).build();
        } catch (HttpClientErrorException | HttpServerErrorException e) {
            LOGGER.error(e.getStatusText());
            return ResponseEntity.status(e.getStatusCode()).build();
        }
    }

    @PutMapping("/{projetoId}")
    public ResponseEntity<Void> updateProjeto(@RequestBody UpdateProjetoDto updateProjetoDto, @PathVariable("projetoId") UUID projetoId) {
        LOGGER.info("Atualizando dados do projeto {}", projetoId);

        try {
            projetoService.updateProjeto(updateProjetoDto, projetoId);
            return ResponseEntity.noContent().build();
        } catch (HttpClientErrorException e) {
            LOGGER.error(e.getStatusText());
            return ResponseEntity.status(e.getStatusCode()).build();
        }
    }

    @DeleteMapping("/{projetoId}")
    public ResponseEntity<Void> deleteProjeto(@PathVariable("projetoId") UUID projetoId) {
        LOGGER.info("Deletando projeto {}", projetoId);

        try {
            projetoService.deleteProjeto(projetoId);
            return ResponseEntity.noContent().build();
        } catch (HttpClientErrorException e) {
            LOGGER.error(e.getStatusText());
            return ResponseEntity.status(e.getStatusCode()).build();
        }
    }

}
