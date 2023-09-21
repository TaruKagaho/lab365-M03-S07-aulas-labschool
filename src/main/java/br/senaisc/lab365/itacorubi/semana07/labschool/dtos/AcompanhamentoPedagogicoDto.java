package br.senaisc.lab365.itacorubi.semana07.labschool.dtos;

import br.senaisc.lab365.itacorubi.semana07.labschool.models.AcompanhamentoPedagogicoModel;
import br.senaisc.lab365.itacorubi.semana07.labschool.models.AlunoModel;
import br.senaisc.lab365.itacorubi.semana07.labschool.models.PedagogoModel;

import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Date;

public record AcompanhamentoPedagogicoDto(
        @NotNull(message = "O campo aluno é obrigatório!")
        AlunoModel aluno,

        @NotNull(message = "O campo pedagogo é obrigatório!")
        PedagogoModel pedagogo,

        @NotNull(message = "O campo dataAcompanhamento é obrigatório!")
        @Temporal(TemporalType.DATE)
        Date dataAcompanhamento,

        @NotBlank(message = "O campo titulo é obrigatório!")
        String titulo,

        @NotBlank(message = "O campo descricao é obrigatório!")
        String descricao,

        boolean finalizado
) {
        public AcompanhamentoPedagogicoDto(AcompanhamentoPedagogicoModel acompanhamentoPedagogicoModel) {
                this(
                        acompanhamentoPedagogicoModel.getAluno(),
                        acompanhamentoPedagogicoModel.getPedagogo(),
                        acompanhamentoPedagogicoModel.getDataAcompanhamento(),
                        acompanhamentoPedagogicoModel.getTitulo(),
                        acompanhamentoPedagogicoModel.getDescricao(),
                        acompanhamentoPedagogicoModel.isFinalizado()
                );
        }
}
