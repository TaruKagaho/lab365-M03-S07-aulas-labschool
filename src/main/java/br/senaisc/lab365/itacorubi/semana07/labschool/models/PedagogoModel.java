package br.senaisc.lab365.itacorubi.semana07.labschool.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "TB_PEDAGOGO")
@Getter
@Setter
public class PedagogoModel extends PersonModel {
    /*@NotBlank(message = "O campo email é obrigatório!")
    @Email(message = "O campo email deve ser um endereço de e-mail válido!")*/
    private String email;

    /*@NotBlank(message = "O campo senha é obrigatório!")
    @Size(min = 8, message = "O campo senha deve ter pelo menos 8 caracteres.")*/
    private String senha;

    @OneToMany(mappedBy = "pedagogo")
    @JsonIgnore
    private List<AcompanhamentoPedagogicoModel> acompanhamentosPedagogicos;
}
