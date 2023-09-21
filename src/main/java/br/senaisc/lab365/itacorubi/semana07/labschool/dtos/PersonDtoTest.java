package br.senaisc.lab365.itacorubi.semana07.labschool.dtos;

import java.util.Date;

public record PersonDtoTest(
        String nome,
        String telefone,
        Date dataNascimento,
        String cpf
) {
}
