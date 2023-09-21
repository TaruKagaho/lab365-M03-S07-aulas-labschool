package br.senaisc.lab365.itacorubi.semana07.labschool.controllers;

import br.senaisc.lab365.itacorubi.semana07.labschool.dtos.AlunoDto;
import br.senaisc.lab365.itacorubi.semana07.labschool.models.AlunoModel;
import br.senaisc.lab365.itacorubi.semana07.labschool.repositories.AlunosRepository;

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
public class AlunosController {
    @Autowired
    private AlunosRepository alunosRepository;

    @PostMapping("aluno")
    public ResponseEntity<AlunoModel> saveAluno(
            @RequestBody @Valid AlunoDto alunoDto
    ) {
        var alunoModel = new AlunoModel();

        BeanUtils.copyProperties(alunoDto, alunoModel);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(
                        alunosRepository.save(alunoModel)
                );
    }

    @GetMapping("alunos")
    public ResponseEntity<List<AlunoDto>> getAllAlunos() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(
                        alunosRepository
                                .findAll()
                                .stream()
                                .map(AlunoDto::new)
                                .toList()
                );
    }

    @GetMapping("aluno/{id}")
    public ResponseEntity<Object> getOneAluno(@PathVariable(value = "id") UUID id) {
        Optional<AlunoModel> aluno = alunosRepository.findById(id);

        if (aluno.isEmpty()) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("Aluno não encontrado!");
        }

        Optional<AlunoDto> alunoDto = aluno.map(AlunoDto::new);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(alunoDto.get());
    }

    @PutMapping("aluno/{id}")
    public ResponseEntity<Object> updateAluno(
            @PathVariable(value = "id") UUID id,
            @RequestBody @Valid AlunoDto alunoDto
            ) {
        boolean temAlunoId = alunosRepository.existsById(id);

        if (!temAlunoId) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("Aluno não encontrado!");
        }
        
        var alunoModel = new AlunoModel();

        BeanUtils.copyProperties(alunoDto, alunoModel);
        alunoModel.setId(id);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(alunosRepository.save(alunoModel));
    }

    @DeleteMapping("aluno/{id}")
    public ResponseEntity<Object> deleteAluno(
            @PathVariable(value = "id") UUID id
    ) {
        Optional<AlunoModel> alunoModel = alunosRepository.findById(id);

        if (alunoModel.isEmpty()) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("Aluno não encontrado!");
        }

        alunosRepository.deleteById(id);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body("O aluno foi excluído com sucesso!");
    }
}
