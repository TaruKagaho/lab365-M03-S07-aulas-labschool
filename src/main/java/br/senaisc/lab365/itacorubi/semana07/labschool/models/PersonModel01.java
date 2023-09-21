package br.senaisc.lab365.itacorubi.semana07.labschool.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;

@MappedSuperclass
@Getter
@Setter
public class PersonModel01 {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    // @NotBlank(message = "O campo nome é obrigatório!")
    private String nome;

    /*@NotBlank(message = "O campo telefone é obrigatório!")
    @Size(min = 16, max = 16, message = "O campo telefone teve está neste formato: (99) 9 9999-9999.")*/
    private String telefone;

    /*@NotNull(message = "O campo dataNascimento é obrigatório!")
    @Temporal(TemporalType.DATE)*/
    private Date dataNascimento;

    // @Column(nullable=false)
    /*@NotBlank(message = "O campo cpf é obrigatório!")
    @Size(min = 14, max = 14, message = "O campo cpf teve está neste formato: 000.000.000-00.")*/
    private String cpf;
}
