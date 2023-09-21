package br.senaisc.lab365.itacorubi.semana07.labschool.controllers;

import br.senaisc.lab365.itacorubi.semana07.labschool.dtos.AcompanhamentoPedagogicoDto;
import br.senaisc.lab365.itacorubi.semana07.labschool.dtos.AcompanhamentoPedagogicoResponseGetAllDto;
import br.senaisc.lab365.itacorubi.semana07.labschool.models.AcompanhamentoPedagogicoModel;
import br.senaisc.lab365.itacorubi.semana07.labschool.repositories.AcompanhamentosPedagogicosRepository;

import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/")
public class AcompanhamentosPedagogicosController {
    @Autowired
    private AcompanhamentosPedagogicosRepository acompanhamentosPedagogicosRepository;
    
    @PostMapping("acompanhamento")
    public ResponseEntity<AcompanhamentoPedagogicoModel> saveAcompanhamentoPedagogico(
            @RequestBody @Valid AcompanhamentoPedagogicoDto acompanhamentoPedagogicoDto
    ) {
        var acompanhamentoPedagogicoModel = new AcompanhamentoPedagogicoModel();

        BeanUtils.copyProperties(acompanhamentoPedagogicoDto, acompanhamentoPedagogicoModel);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(
                        acompanhamentosPedagogicosRepository.save(acompanhamentoPedagogicoModel)
                );
    }

    @GetMapping("acompanhamentos")
    /*public ResponseEntity<List<AcompanhamentoPedagogicoDto>> getAllAcompanhamentoPedagogicos() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(
                        acompanhamentosPedagogicosRepository
                                .findAll()
                                .stream()
                                .map(AcompanhamentoPedagogicoDto::new)
                                .toList()
                );
    }*/
    public ResponseEntity<List<AcompanhamentoPedagogicoResponseGetAllDto>> getAllAcompanhamentoPedagogicos() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(
                        acompanhamentosPedagogicosRepository
                                .findAllAcompanhamentos()
                );
    }

    @GetMapping("acompanhamento/{id}")
    public ResponseEntity<Object> getOneAcompanhamentoPedagogico(@PathVariable(value = "id") UUID id) {
        Optional<AcompanhamentoPedagogicoModel> acompanhamentoPedagogico = acompanhamentosPedagogicosRepository
                .findById(id);
        System.out.println("acompanhamentoPedagogico = " + acompanhamentoPedagogico.get().toString());

        if (acompanhamentoPedagogico.isEmpty()) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("AcompanhamentoPedagogico não encontrado!");
        }

        /*Optional<AcompanhamentoPedagogicoDto> acompanhamentoPedagogicoDto = acompanhamentoPedagogico
                .map(AcompanhamentoPedagogicoDto::new);
        System.out.println("acompanhamentoPedagogicoDto = " + acompanhamentoPedagogicoDto.get().toString());*/
        Optional<AcompanhamentoPedagogicoResponseGetAllDto> acompanhamentoPedagogicoDto = acompanhamentoPedagogico
                .map(AcompanhamentoPedagogicoResponseGetAllDto::new);
        System.out.println("acompanhamentoPedagogicoDto = " + acompanhamentoPedagogicoDto.get().toString());

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(acompanhamentoPedagogicoDto.get());
    }

    @PutMapping("acompanhamento/{id}")
    public ResponseEntity<Object> updateAcompanhamentoPedagogico(
            @PathVariable(value = "id") UUID id,
            @RequestBody @Valid AcompanhamentoPedagogicoDto acompanhamentoPedagogicoDto
    ) {
        Optional<AcompanhamentoPedagogicoModel> acompanhamentoPedagogicoFound = acompanhamentosPedagogicosRepository
                .findById(id);

        if (acompanhamentoPedagogicoFound.isEmpty()) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("AcompanhamentoPedagogico não encontrado!");
        }

        var acompanhamentoPedagogicoModel = acompanhamentoPedagogicoFound.get();

        BeanUtils.copyProperties(acompanhamentoPedagogicoDto, acompanhamentoPedagogicoModel);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(acompanhamentosPedagogicosRepository.save(acompanhamentoPedagogicoModel));
    }

    @DeleteMapping("acompanhamento/{id}")
    public ResponseEntity<Object> deleteAcompanhamentoPedagogico(
            @PathVariable(value = "id") UUID id
    ) {
        Optional<AcompanhamentoPedagogicoModel> acompanhamentoPedagogicoModel = acompanhamentosPedagogicosRepository
                .findById(id);

        if (acompanhamentoPedagogicoModel.isEmpty()) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("AcompanhamentoPedagogico não encontrado!");
        }

        acompanhamentosPedagogicosRepository.deleteById(id);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body("O acompanhamentoPedagogico foi excluído com sucesso!");
    }
}
