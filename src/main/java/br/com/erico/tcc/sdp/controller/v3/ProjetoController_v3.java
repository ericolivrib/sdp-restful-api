package br.com.erico.tcc.sdp.controller.v3;

import br.com.erico.tcc.sdp.dto.*;
import br.com.erico.tcc.sdp.enumeration.StatusEnum;
import br.com.erico.tcc.sdp.service.ProjetoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;

import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/api/v3/projetos")
public class ProjetoController_v3 {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProjetoController_v3.class);
    private final ProjetoService projetoService;

    public ProjetoController_v3(ProjetoService projetoService) {
        this.projetoService = projetoService;
    }

    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<CollectionModel<EntityModel<ProjetoUsuarioResponseDto>>> getProjetosByUsuario(@PathVariable("usuarioId") UUID usuarioId) {
        LOGGER.info("Buscando projetos do usuário {}", usuarioId);

        try {
            var projetos = CollectionModel.of(projetoService.getProjetosByUsuario(usuarioId).stream()
                    .map(p -> {
                        var em = EntityModel.of(p);
                        em.add(linkTo(methodOn(ProjetoController_v3.class).getProjetoById(p.id())).withRel(IanaLinkRelations.ITEM));
                        em.add(linkTo(methodOn(ProjetoController_v3.class).updateProjeto(null, p.id())).withRel(IanaLinkRelations.EDIT));

                        if (Objects.equals(p.statusId(), StatusEnum.NAO_FINALIZADO.getId()) ||
                                Objects.equals(p.statusId(), StatusEnum.NAO_APROVADO.getId())) {
                            em.add(linkTo(methodOn(ProjetoController_v3.class).deleteProjeto(p.id())).withRel("delete"));
                        }

                        return em;
                    })
                    .collect(Collectors.toList()));

            projetos.add(linkTo(methodOn(ProjetoController_v3.class).getProjetosByUsuario(usuarioId)).withSelfRel());

            if (projetos.getContent().isEmpty()) {
                projetos.add(linkTo(methodOn(ProjetoController_v3.class).addProjeto(null)).withRel("create"));
            }

            return ResponseEntity.ok(projetos);
        } catch (HttpClientErrorException e) {
            LOGGER.error(e.getMessage());
            return ResponseEntity.status(e.getStatusCode()).build();
        }

    }

    @GetMapping("/{projetoId}")
    public ResponseEntity<EntityModel<ProjetoResponseDto>> getProjetoById(@PathVariable("projetoId") UUID projetoId) {
        LOGGER.info("Buscando projeto {}", projetoId);

        try {
            var projeto = EntityModel.of(projetoService.getProjetoById(projetoId));
            projeto.add(linkTo(methodOn(ProjetoController_v3.class).getProjetoById(projetoId)).withSelfRel());

            if (Objects.equals(projeto.getContent().statusId(), StatusEnum.NAO_FINALIZADO.getId()) ||
                    Objects.equals(projeto.getContent().statusId(), StatusEnum.NAO_APROVADO.getId())) {
                projeto.add(linkTo(methodOn(ProjetoController_v3.class).deleteProjeto(projeto.getContent().id())).withRel("delete"));
                projeto.add(linkTo(methodOn(ProjetoController_v3.class).updateProjeto(null, projeto.getContent().id())).withRel(IanaLinkRelations.EDIT));
            }

            return ResponseEntity.ok(projeto);
        } catch (HttpClientErrorException e) {
            LOGGER.error(e.getStatusText());
            return ResponseEntity.status(e.getStatusCode()).build();
        }
    }

    @PostMapping
    public ResponseEntity<EntityModel<NovoProjetoResponseDto>> addProjeto(@RequestBody NovoProjetoDto novoProjetoDto) {
        LOGGER.info("Adicionando projeto {}", novoProjetoDto.toString());

        try {
            var projeto = EntityModel.of(projetoService.addProjeto(novoProjetoDto));

            projeto.add(linkTo(methodOn(ProjetoController_v3.class).addProjeto(novoProjetoDto)).withSelfRel());
            projeto.add(linkTo(methodOn(ProjetoController_v3.class).getProjetoById(projeto.getContent().id())).withRel(IanaLinkRelations.RELATED));
            projeto.add(linkTo(methodOn(ProjetoController_v3.class).updateProjeto(null, projeto.getContent().id())).withRel(IanaLinkRelations.EDIT));
            projeto.add(linkTo(methodOn(ProjetoController_v3.class).deleteProjeto(projeto.getContent().id())).withRel("delete"));

            var createdLocation = linkTo(methodOn(ProjetoController_v3.class).getProjetoById(projeto.getContent().id()))
                    .toUri();

            return ResponseEntity.created(createdLocation).body(projeto);
        } catch (HttpClientErrorException | HttpServerErrorException e) {
            LOGGER.error(e.getStatusText());
            return ResponseEntity.status(e.getStatusCode()).build();
        }
    }

    @PutMapping("/{projetoId}")
    public ResponseEntity<EntityModel<UpdateProjetoResponseDto>> updateProjeto(@RequestBody UpdateProjetoDto updateProjetoDto, @PathVariable("projetoId") UUID projetoId) {
        LOGGER.info("Atualizando dados do projeto {}", projetoId);

        try {
            var projeto = EntityModel.of(projetoService.updateProjeto(updateProjetoDto, projetoId));

            projeto.add(linkTo(methodOn(ProjetoController_v3.class).updateProjeto(updateProjetoDto, projetoId)).withSelfRel());
            projeto.add(linkTo(methodOn(ProjetoController_v3.class).getProjetoById(projeto.getContent().id())).withRel(IanaLinkRelations.RELATED));
            projeto.add(linkTo(methodOn(ProjetoController_v3.class).deleteProjeto(projeto.getContent().id())).withRel("delete"));

            return ResponseEntity.ok(projeto);
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
