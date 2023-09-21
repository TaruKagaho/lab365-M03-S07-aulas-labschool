package br.senaisc.lab365.itacorubi.semana07.labschool.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "TB_ACOMPANHAMENTO")
@Getter
@Setter
@ToString
public class AcompanhamentoPedagogicoModel {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "aluno_id", nullable = false)
    // @NotNull(message = "O campo aluno é obrigatório!")
    private AlunoModel aluno;

    @ManyToOne
    // @JoinColumn(name = "pedagogo_id", nullable = false)
    @JoinColumn(name = "professor_id", nullable = false)
    // @NotNull(message = "O campo pedagogo é obrigatório!")
    private PedagogoModel pedagogo;

    /*@NotNull(message = "O campo dataAcompanhamento é obrigatório!")
    @Temporal(TemporalType.DATE)*/
    private Date dataAcompanhamento;

    // @NotBlank(message = "O campo titulo é obrigatório!")
    private String titulo;

    // @NotBlank(message = "O campo descricao é obrigatório!")
    private String descricao;

    private boolean finalizado = false;
}
