package br.com.erico.tcc.sdp.repository;

import br.com.erico.tcc.sdp.model.Demanda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DemandaRepository extends JpaRepository<Demanda, String> {
}
