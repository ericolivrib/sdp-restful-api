package br.com.erico.tcc.sdp.assembler;

import br.com.erico.tcc.sdp.controller.v3.ProjetoController_v3;
import br.com.erico.tcc.sdp.dto.response.GetProjetoUsuarioResponse;
import br.com.erico.tcc.sdp.enumeration.StatusEnum;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class ProjetoUsuarioModelAssembler implements RepresentationModelAssembler<GetProjetoUsuarioResponse, EntityModel<GetProjetoUsuarioResponse>> {

    @Override
    public EntityModel<GetProjetoUsuarioResponse> toModel(GetProjetoUsuarioResponse entity) {
        var entityModel = EntityModel.of(entity);

        entityModel.add(linkTo(methodOn(ProjetoController_v3.class).getProjetoById(entity.id())).withRel(IanaLinkRelations.ITEM));

        if (Objects.equals(entity.statusId(), StatusEnum.NAO_FINALIZADO.getId()) ||
                Objects.equals(entity.statusId(), StatusEnum.NAO_APROVADO.getId())) {
            entityModel.add(linkTo(methodOn(ProjetoController_v3.class).deleteProjeto(entity.id())).withRel("delete"));
            entityModel.add(linkTo(methodOn(ProjetoController_v3.class).updateProjeto(null, entity.id())).withRel(IanaLinkRelations.EDIT));
        }

        return entityModel;
    }

    @Override
    public CollectionModel<EntityModel<GetProjetoUsuarioResponse>> toCollectionModel(Iterable<? extends GetProjetoUsuarioResponse> entities) {
        return RepresentationModelAssembler.super.toCollectionModel(entities);
    }

    public CollectionModel<EntityModel<GetProjetoUsuarioResponse>> toCollection(List<GetProjetoUsuarioResponse> entities, UUID usuarioId) {
        var collectionModel = toCollectionModel(entities);

        collectionModel.add(linkTo(methodOn(ProjetoController_v3.class).getProjetosByUsuario(usuarioId)).withSelfRel());

        if (entities.isEmpty()) {
            collectionModel.add(linkTo(methodOn(ProjetoController_v3.class).addProjeto(null)).withRel("create"));
        }

        return collectionModel;
    }

}
