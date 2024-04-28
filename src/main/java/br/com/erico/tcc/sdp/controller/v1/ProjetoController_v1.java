package br.com.erico.tcc.sdp.controller.v1;

import br.com.erico.tcc.sdp.dto.request.CreateProjetoRequest;
import br.com.erico.tcc.sdp.dto.request.UpdateProjetoRequest;
import br.com.erico.tcc.sdp.dto.response.CreateProjetoResponse;
import br.com.erico.tcc.sdp.dto.response.GetProjetoByIdResponse;
import br.com.erico.tcc.sdp.dto.response.GetProjetoUsuarioResponse;
import br.com.erico.tcc.sdp.dto.response.UpdateProjetoResponse;
import br.com.erico.tcc.sdp.service.ProjetoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/projetos")
public class ProjetoController_v1 {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProjetoController_v1.class);
    private final ProjetoService projetoService;

    public ProjetoController_v1(ProjetoService projetoService) {
        this.projetoService = projetoService;
    }

    @GetMapping("/usuario/{usuarioId}")
    public List<GetProjetoUsuarioResponse> getProjetosByUsuario(@PathVariable("usuarioId") UUID usuarioId) {
        LOGGER.info("Buscando projetos do usuário {}", usuarioId);
        return projetoService.getProjetosByUsuario(usuarioId);
    }

    @GetMapping("/{projetoId}")
    public GetProjetoByIdResponse getProjetoById(@PathVariable("projetoId") UUID projetoId) {
        LOGGER.info("Buscando projeto {}", projetoId);
        return projetoService.getProjetoById(projetoId);
    }

    @PostMapping
    public CreateProjetoResponse addProjeto(@RequestBody CreateProjetoRequest createProjetoRequest) {
        LOGGER.info("Adicionando projeto {}", createProjetoRequest.toString());
        return projetoService.addProjeto(createProjetoRequest);
    }

    @PutMapping("/{projetoId}")
    public UpdateProjetoResponse updateProjeto(@RequestBody UpdateProjetoRequest updateProjetoRequest, @PathVariable("projetoId") UUID projetoId) {
        LOGGER.info("Atualizando dados do projeto {}", projetoId);
        return projetoService.updateProjeto(updateProjetoRequest, projetoId);
    }

    @DeleteMapping("/{projetoId}")
    public void deleteProjeto(@PathVariable("projetoId") UUID projetoId) {
        LOGGER.info("Deletando projeto {}", projetoId);
        projetoService.deleteProjeto(projetoId);
    }

}
