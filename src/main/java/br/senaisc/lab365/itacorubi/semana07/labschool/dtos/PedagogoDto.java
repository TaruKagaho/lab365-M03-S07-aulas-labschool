package br.senaisc.lab365.itacorubi.semana07.labschool.dtos;

import br.senaisc.lab365.itacorubi.semana07.labschool.models.PedagogoModel;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.Date;

public record PedagogoDto(
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

        @NotBlank(message = "O campo email é obrigatório!")
        @Email(message = "O campo email deve ser um endereço de e-mail válido!")
        String email,

        @NotBlank(message = "O campo senha é obrigatório!")
        @Size(min = 8, message = "O campo senha deve ter pelo menos 8 caracteres.")
        String senha
) {
        public PedagogoDto(PedagogoModel pedagogoModel) {
                this(
                        pedagogoModel.getNome(),
                        pedagogoModel.getTelefone(),
                        pedagogoModel.getDataNascimento(),
                        pedagogoModel.getCpf(),
                        pedagogoModel.getEmail(),
                        pedagogoModel.getSenha()
                );
        }
}
