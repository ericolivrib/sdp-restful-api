package br.com.erico.tcc.sdp.controller.v1;

import br.com.erico.tcc.sdp.dto.NovoProjetoDto;
import br.com.erico.tcc.sdp.dto.ProjetoResponseDto;
import br.com.erico.tcc.sdp.dto.ProjetoUsuarioResponseDto;
import br.com.erico.tcc.sdp.dto.UpdateProjetoDto;
import br.com.erico.tcc.sdp.service.ProjetoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/projetos")
public class ProjetoController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProjetoController.class);
    private final ProjetoService projetoService;

    public ProjetoController(ProjetoService projetoService) {
        this.projetoService = projetoService;
    }

    @GetMapping("/usuario/{usuarioId}")
    public List<ProjetoUsuarioResponseDto> getProjetosByUsuario(@PathVariable UUID usuarioId) {
        LOGGER.info("Buscando projetos do usuário {}", usuarioId);

        try {
            return projetoService.getProjetosByUsuario(usuarioId);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            return null;
        }
    }

    @GetMapping("/{projetoId}")
    public ProjetoResponseDto getProjetoById(@PathVariable("projetoId") UUID projetoId) {
        LOGGER.info("Buscando projeto {}", projetoId);

        try {
            return projetoService.getProjetoById(projetoId);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            return null;
        }
    }

    @PostMapping
    public UUID addProjeto(@RequestBody NovoProjetoDto novoProjetoDto) {
        LOGGER.info("Adicionando projeto {}", novoProjetoDto.toString());

        try {
            return projetoService.addProjeto(novoProjetoDto);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            return null;
        }
    }

    @PutMapping("/{projetoId}")
    public boolean updateProjeto(@RequestBody UpdateProjetoDto updateProjetoDto, @PathVariable UUID projetoId) {
        LOGGER.info("Atualizando dados do projeto {}", projetoId);

        try {
            projetoService.updateProjeto(updateProjetoDto, projetoId);
            return true;
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            return false;
        }
    }

    @DeleteMapping("/{projetoId}")
    public boolean deleteProjeto(@PathVariable("projetoId") UUID projetoId) {
        LOGGER.info("Deletando projeto {}", projetoId);

        try {
            projetoService.deleteProjeto(projetoId);
            return true;
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            return false;
        }
    }

}
