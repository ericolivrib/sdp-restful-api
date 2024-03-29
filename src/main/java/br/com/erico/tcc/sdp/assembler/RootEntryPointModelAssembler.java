package br.com.erico.tcc.sdp.assembler;

import br.com.erico.tcc.sdp.controller.v3.RootEntryPointController;
import br.com.erico.tcc.sdp.dto.RootEntryPointDto;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class RootEntryPointModelAssembler implements RepresentationModelAssembler<RootEntryPointDto, EntityModel<RootEntryPointDto>> {

    @Override
    public EntityModel<RootEntryPointDto> toModel(RootEntryPointDto entity) {
        var entityModel = EntityModel.of(entity);
        entityModel.add(linkTo(methodOn(RootEntryPointController.class).root()).withSelfRel());
        return entityModel;
    }

}
