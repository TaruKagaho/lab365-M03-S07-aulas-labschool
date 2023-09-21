package br.senaisc.lab365.itacorubi.semana07.labschool.controllers;

import br.senaisc.lab365.itacorubi.semana07.labschool.dtos.PedagogoDto;
import br.senaisc.lab365.itacorubi.semana07.labschool.models.PedagogoModel;
import br.senaisc.lab365.itacorubi.semana07.labschool.repositories.PedagogosRepository;

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
public class PedagogosController {
    @Autowired
    private PedagogosRepository pedagogosRepository;

    @PostMapping("pedagogo")
    public ResponseEntity<PedagogoModel> savePedagogo(
            @RequestBody @Valid PedagogoDto pedagogoDto
    ) {
        var pedagogoModel = new PedagogoModel();

        BeanUtils.copyProperties(pedagogoDto, pedagogoModel);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(
                        pedagogosRepository.save(pedagogoModel)
                );
    }

    @GetMapping("pedagogos")
    public ResponseEntity<List<PedagogoDto>> getAllPedagogos() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(
                        pedagogosRepository
                                .findAll()
                                .stream()
                                .map(PedagogoDto::new)
                                .toList()
                );
    }

    @GetMapping("pedagogo/{id}")
    public ResponseEntity<Object> getOnePedagogo(@PathVariable(value = "id") UUID id) {
        Optional<PedagogoModel> pedagogo = pedagogosRepository.findById(id);

        if (pedagogo.isEmpty()) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("Pedagogo não encontrado!");
        }

        Optional<PedagogoDto> pedagogoDto = pedagogo.map(PedagogoDto::new);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(pedagogoDto.get());
        /*return pedagogo.<ResponseEntity<Object>>map(pedagogoModel -> ResponseEntity
                .status(HttpStatus.OK)
                .body(pedagogoModel)).orElseGet(() -> ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body("Pedagogo não encontrado!"));*/
    }

    @PutMapping("pedagogo/{id}")
    public ResponseEntity<Object> updatePedagogo(
            @PathVariable(value = "id") UUID id,
            @RequestBody @Valid PedagogoDto pedagogoDto
    ) {
        Optional<PedagogoModel> pedagogoFound = pedagogosRepository.findById(id);

        if (pedagogoFound.isEmpty()) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("Pedagogo não encontrado!");
        }

        var pedagogoModel = pedagogoFound.get();

        BeanUtils.copyProperties(pedagogoDto, pedagogoModel);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(pedagogosRepository.save(pedagogoModel));
    }

    @DeleteMapping("pedagogo/{id}")
    public ResponseEntity<String> deletePedagogo(
            @PathVariable(value = "id") UUID id
    ) {
        boolean isPedagogoFound = pedagogosRepository.existsById(id);

        if (!isPedagogoFound) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("Pedagogo não encontrado!");
        }

        pedagogosRepository.deleteById(id);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body("O pedagogo foi excluído com sucesso!");
    }
}
