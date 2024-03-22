package br.com.erico.tcc.sdp.repository;

import br.com.erico.tcc.sdp.model.Periodo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PeriodoRepository extends JpaRepository<Periodo, Integer> {
}
