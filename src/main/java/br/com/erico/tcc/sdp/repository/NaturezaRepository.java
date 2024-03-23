package br.com.erico.tcc.sdp.repository;

import br.com.erico.tcc.sdp.model.Natureza;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NaturezaRepository extends JpaRepository<Natureza, Integer> {
}
