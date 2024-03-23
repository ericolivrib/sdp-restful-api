package br.com.erico.tcc.sdp.controller.v1;

import br.com.erico.tcc.sdp.dto.NovoProjetoDto;
import br.com.erico.tcc.sdp.dto.ProjetoResponseDto;
import br.com.erico.tcc.sdp.service.ProjetoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/projetos")
public class ProjetoController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProjetoController.class);
    private final ProjetoService projetoService;

    public ProjetoController(ProjetoService projetoService) {
        this.projetoService = projetoService;
    }

    @GetMapping("/{projetoId}")
    public ProjetoResponseDto getProjetoById(@PathVariable("projetoId") UUID projetoId) {
        LOGGER.info("Buscando projeto {}", projetoId);

        try {
            return projetoService.getProjetoById(projetoId);
        } catch (Exception e) {
            LOGGER.error("Falha ao buscar por projeto: {}", e.getMessage());
            return null;
        }
    }

    @PostMapping
    public UUID addProjeto(@RequestBody NovoProjetoDto novoProjetoDto) {
        LOGGER.info("Adicionando projeto {}", novoProjetoDto.toString());
        return projetoService.addProjeto(novoProjetoDto);
    }

}
