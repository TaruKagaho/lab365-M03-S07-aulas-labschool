package br.senaisc.lab365.itacorubi.semana07.labschool.dtos;

import br.senaisc.lab365.itacorubi.semana07.labschool.models.AcompanhamentoPedagogicoModel;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Date;
import java.util.UUID;

public record AcompanhamentoPedagogicoResponseGetAllDto(
        @NotNull(message = "O campo ID é obrigatório!")
        UUID id,

        @NotBlank(message = "O campo aluno é obrigatório!")
        String aluno,

        @NotBlank(message = "O campo pedagogo é obrigatório!")
        String pedagogo,

        @NotNull(message = "O campo dataAcompanhamento é obrigatório!")
        @Temporal(TemporalType.DATE)
        Date dataAcompanhamento,

        @NotBlank(message = "O campo titulo é obrigatório!")
        String titulo,

        boolean finalizado
) {
        public AcompanhamentoPedagogicoResponseGetAllDto(AcompanhamentoPedagogicoModel acompanhamentoPedagogicoModel) {
                this(
                        acompanhamentoPedagogicoModel.getId(),
                        acompanhamentoPedagogicoModel.getAluno().getNome(),
                        acompanhamentoPedagogicoModel.getPedagogo().getNome(),
                        acompanhamentoPedagogicoModel.getDataAcompanhamento(),
                        acompanhamentoPedagogicoModel.getTitulo(),
                        acompanhamentoPedagogicoModel.isFinalizado()
                );
        }
}
