package br.com.erico.tcc.sdp.assembler;

import br.com.erico.tcc.sdp.controller.v3.ProjetoController_v3;
import br.com.erico.tcc.sdp.dto.ProjetoUsuarioResponseDto;
import br.com.erico.tcc.sdp.enumeration.StatusEnum;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.stream.StreamSupport;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class ProjetoUsuarioModelAssembler implements RepresentationModelAssembler<ProjetoUsuarioResponseDto, EntityModel<ProjetoUsuarioResponseDto>> {

    @Override
    public EntityModel<ProjetoUsuarioResponseDto> toModel(ProjetoUsuarioResponseDto entity) {
        var entityModel = EntityModel.of(entity);

        entityModel.add(linkTo(methodOn(ProjetoController_v3.class).getProjetoById(entity.id())).withRel(IanaLinkRelations.ITEM));
        entityModel.add(linkTo(methodOn(ProjetoController_v3.class).updateProjeto(null, entity.id())).withRel(IanaLinkRelations.EDIT));

        if (Objects.equals(entity.statusId(), StatusEnum.NAO_FINALIZADO.getId()) ||
                Objects.equals(entity.statusId(), StatusEnum.NAO_APROVADO.getId())) {
            entityModel.add(linkTo(methodOn(ProjetoController_v3.class).deleteProjeto(entity.id())).withRel("delete"));
        }

        return entityModel;
    }

    @Override
    public CollectionModel<EntityModel<ProjetoUsuarioResponseDto>> toCollectionModel(Iterable<? extends ProjetoUsuarioResponseDto> entities) {
        var entityModelList = StreamSupport.stream(entities.spliterator(), false).map(this::toModel).toList();
        var collectionModel = CollectionModel.of(entityModelList);

        var usuarioId = Objects.requireNonNull(entityModelList.getFirst().getContent()).usuarioId();
        collectionModel.add(linkTo(methodOn(ProjetoController_v3.class).getProjetosByUsuario(usuarioId)).withSelfRel());

        if (collectionModel.getContent().isEmpty()) {
            collectionModel.add(linkTo(methodOn(ProjetoController_v3.class).addProjeto(null)).withRel("create"));
        }

        return collectionModel;
    }

}
