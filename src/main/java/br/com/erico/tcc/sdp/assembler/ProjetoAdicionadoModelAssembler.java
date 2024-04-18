package br.com.erico.tcc.sdp.assembler;

import br.com.erico.tcc.sdp.controller.v3.ProjetoController_v3;
import br.com.erico.tcc.sdp.dto.response.CreateProjetoResponse;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class ProjetoAdicionadoModelAssembler implements RepresentationModelAssembler<CreateProjetoResponse, EntityModel<CreateProjetoResponse>> {

    @Override
    public EntityModel<CreateProjetoResponse> toModel(CreateProjetoResponse entity) {
        var entityModel = EntityModel.of(entity);

        entityModel.add(linkTo(methodOn(ProjetoController_v3.class).addProjeto(null)).withSelfRel());
        entityModel.add(linkTo(methodOn(ProjetoController_v3.class).getProjetoById(entity.id())).withRel(IanaLinkRelations.RELATED));
        entityModel.add(linkTo(methodOn(ProjetoController_v3.class).updateProjeto(null, entity.id())).withRel(IanaLinkRelations.EDIT));
        entityModel.add(linkTo(methodOn(ProjetoController_v3.class).deleteProjeto(entity.id())).withRel("delete"));

        return entityModel;
    }

}
