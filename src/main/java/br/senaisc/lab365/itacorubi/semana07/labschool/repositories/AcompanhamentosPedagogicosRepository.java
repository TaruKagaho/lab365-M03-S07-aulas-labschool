package br.senaisc.lab365.itacorubi.semana07.labschool.repositories;

import br.senaisc.lab365.itacorubi.semana07.labschool.dtos.AcompanhamentoPedagogicoResponseGetAllDto;
import br.senaisc.lab365.itacorubi.semana07.labschool.models.AcompanhamentoPedagogicoModel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface AcompanhamentosPedagogicosRepository extends JpaRepository<AcompanhamentoPedagogicoModel, UUID> {
    /*@Query(value = "        SELECT new br.senaisc.lab365.itacorubi.semana07.labschool.dtos.AcompanhamentoPedagogicoResponseGetAllDto(\n" +
                   "            ac.titulo, al.nome, pd.nome, ac.dataAcompanhamento, ac.finalizado\n" +
                   "        )\n" +
                   "        FROM br.senaisc.lab365.itacorubi.semana07.labschool.models.AcompanhamentoPedagogicoModel ac\n" +
                   "        INNER JOIN ac.aluno al\n" +
                   "        INNER JOIN ac.pedagogo pd\n")*/
    @Query(value = """
                    SELECT new br.senaisc.lab365.itacorubi.semana07.labschool.dtos.AcompanhamentoPedagogicoResponseGetAllDto(
                        ac.id, al.nome, pd.nome, ac.dataAcompanhamento, ac.titulo, ac.finalizado
                    )
                    FROM br.senaisc.lab365.itacorubi.semana07.labschool.models.AcompanhamentoPedagogicoModel ac
                    INNER JOIN ac.aluno al
                    INNER JOIN ac.pedagogo pd
            """)
    List<AcompanhamentoPedagogicoResponseGetAllDto> findAllAcompanhamentos();
}
