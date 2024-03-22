package br.com.erico.tcc.sdp.repository;

import br.com.erico.tcc.sdp.model.ParecerTecnico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ParecerTecnicoRepository extends JpaRepository<ParecerTecnico, UUID> {
}
