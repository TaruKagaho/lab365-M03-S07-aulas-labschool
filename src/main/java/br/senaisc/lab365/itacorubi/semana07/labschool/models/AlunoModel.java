package br.senaisc.lab365.itacorubi.semana07.labschool.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "TB_ALUNO")
@Getter
@Setter
public class AlunoModel extends PersonModel {
    // @NotNull(message = "O campo nota é obrigatório!")
    /*@DecimalMin(value = "0.0", message = "O campo nota deve ter valor maior ou igual a 0.0.")
    @DecimalMax(value = "100.0", message = "O campo nota deve ter valor igual ou menor que 100.0.")
    private double nota;*/
    // @Range(min = 0, max = 100, message = "O campo nota deve estar entre 0 e 100.")
    private Integer nota;

    @OneToMany(mappedBy = "aluno")
    @JsonIgnore
    private List<AcompanhamentoPedagogicoModel> acompanhamentosPedagogicos;
}
