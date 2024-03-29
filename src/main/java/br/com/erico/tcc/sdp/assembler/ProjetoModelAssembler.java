package br.com.erico.tcc.sdp.assembler;

import br.com.erico.tcc.sdp.controller.v3.ProjetoController_v3;
import br.com.erico.tcc.sdp.dto.ProjetoResponseDto;
import br.com.erico.tcc.sdp.enumeration.StatusEnum;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import java.util.Objects;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class ProjetoModelAssembler implements RepresentationModelAssembler<ProjetoResponseDto, EntityModel<ProjetoResponseDto>> {

    @Override
    public EntityModel<ProjetoResponseDto> toModel(ProjetoResponseDto entity) {
        var entityModel = EntityModel.of(entity);
        entityModel.add(linkTo(methodOn(ProjetoController_v3.class).getProjetoById(entity.id())).withSelfRel());

        if (Objects.equals(entity.statusId(), StatusEnum.NAO_FINALIZADO.getId()) ||
                Objects.equals(entity.statusId(), StatusEnum.NAO_APROVADO.getId())) {
            entityModel.add(linkTo(methodOn(ProjetoController_v3.class).deleteProjeto(entity.id())).withRel("delete"));
            entityModel.add(linkTo(methodOn(ProjetoController_v3.class).updateProjeto(null, entity.id())).withRel(IanaLinkRelations.EDIT));
        }

        return entityModel;
    }

}
