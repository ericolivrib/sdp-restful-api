package br.com.erico.tcc.sdp.repository;

import br.com.erico.tcc.sdp.model.Projeto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ProjetoRepository extends JpaRepository<Projeto, UUID> {

    List<Projeto> findByUsuarioId(UUID usuario_id);

    boolean existsByNumero(String numero);

}
