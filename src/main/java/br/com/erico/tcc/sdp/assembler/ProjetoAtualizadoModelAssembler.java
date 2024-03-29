package br.com.erico.tcc.sdp.assembler;

import br.com.erico.tcc.sdp.controller.v3.ProjetoController_v3;
import br.com.erico.tcc.sdp.dto.ProjetoAtualizadoResponseDto;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class ProjetoAtualizadoModelAssembler implements RepresentationModelAssembler<ProjetoAtualizadoResponseDto, EntityModel<ProjetoAtualizadoResponseDto>> {

    @Override
    public EntityModel<ProjetoAtualizadoResponseDto> toModel(ProjetoAtualizadoResponseDto entity) {
        var entityModel = EntityModel.of(entity);

        entityModel.add(linkTo(methodOn(ProjetoController_v3.class).updateProjeto(null, entity.id())).withSelfRel());
        entityModel.add(linkTo(methodOn(ProjetoController_v3.class).getProjetoById(entity.id())).withRel(IanaLinkRelations.RELATED));
        entityModel.add(linkTo(methodOn(ProjetoController_v3.class).deleteProjeto(entity.id())).withRel("delete"));

        return entityModel;
    }

}
