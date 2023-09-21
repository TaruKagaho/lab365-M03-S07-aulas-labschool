package br.senaisc.lab365.itacorubi.semana07.labschool.repositories;

import br.senaisc.lab365.itacorubi.semana07.labschool.models.PedagogoModel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PedagogosRepository extends JpaRepository<PedagogoModel, UUID> {
}
