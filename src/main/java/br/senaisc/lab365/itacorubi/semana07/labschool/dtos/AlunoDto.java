package br.senaisc.lab365.itacorubi.semana07.labschool.dtos;

import br.senaisc.lab365.itacorubi.semana07.labschool.models.AlunoModel;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.Range;

import java.util.Date;

public record AlunoDto(
        @NotBlank(message = "O campo nome é obrigatório!")
        String nome,

        @NotBlank(message = "O campo telefone é obrigatório!")
        @Size(min = 16, max = 16, message = "O campo telefone teve está neste formato: (99) 9 9999-9999.")
        String telefone,

        @NotNull(message = "O campo dataNascimento é obrigatório!")
        @Temporal(TemporalType.DATE)
        Date dataNascimento,

        @NotBlank(message = "O campo cpf é obrigatório!")
        @Size(min = 14, max = 14, message = "O campo cpf teve está neste formato: 000.000.000-00.")
        String cpf,

        @NotNull(message = "O campo nota é obrigatório!")
        @Range(min = 0, max = 100, message = "O campo nota deve estar entre 0 e 100.")
        Integer nota
) {
        public AlunoDto(AlunoModel alunoModel) {
                this(
                        alunoModel.getNome(),
                        alunoModel.getTelefone(),
                        alunoModel.getDataNascimento(),
                        alunoModel.getCpf(),
                        alunoModel.getNota()
                );
        }
}
