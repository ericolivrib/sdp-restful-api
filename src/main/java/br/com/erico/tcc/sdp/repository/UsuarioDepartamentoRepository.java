package br.com.erico.tcc.sdp.repository;

import br.com.erico.tcc.sdp.model.UsuarioDepartamento;
import br.com.erico.tcc.sdp.model.UsuarioDepartamentoId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioDepartamentoRepository extends JpaRepository<UsuarioDepartamento, UsuarioDepartamentoId> {
}
