package br.senaisc.lab365.itacorubi.semana07.labschool.repositories;

import br.senaisc.lab365.itacorubi.semana07.labschool.models.AlunoModel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AlunosRepository extends JpaRepository<AlunoModel, UUID> {
}
