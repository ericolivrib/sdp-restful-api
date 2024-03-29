package br.com.erico.tcc.sdp.controller.v3;

import br.com.erico.tcc.sdp.assembler.RootEntryPointModelAssembler;
import br.com.erico.tcc.sdp.dto.RootEntryPointDto;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v3")
public class RootEntryPointController {

    private final RootEntryPointModelAssembler rootEntryPointModelAssembler;

    public RootEntryPointController(RootEntryPointModelAssembler rootEntryPointModelAssembler) {
        this.rootEntryPointModelAssembler = rootEntryPointModelAssembler;
    }

    @GetMapping
    public ResponseEntity<EntityModel<RootEntryPointDto>> root() {
        var rootEntryPoint = rootEntryPointModelAssembler.toModel(new RootEntryPointDto());
        return ResponseEntity.ok(rootEntryPoint);
    }

}
