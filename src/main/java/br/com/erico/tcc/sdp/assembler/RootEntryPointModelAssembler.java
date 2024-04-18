package br.com.erico.tcc.sdp.assembler;

import br.com.erico.tcc.sdp.controller.v3.RootEntryPointController;
import br.com.erico.tcc.sdp.dto.response.RootEntryPointResponse;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class RootEntryPointModelAssembler implements RepresentationModelAssembler<RootEntryPointResponse, EntityModel<RootEntryPointResponse>> {

    @Override
    public EntityModel<RootEntryPointResponse> toModel(RootEntryPointResponse entity) {
        var entityModel = EntityModel.of(entity);
        entityModel.add(linkTo(methodOn(RootEntryPointController.class).root()).withSelfRel());
        return entityModel;
    }

}
