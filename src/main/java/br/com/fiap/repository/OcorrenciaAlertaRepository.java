package br.com.fiap.repository;

import br.com.fiap.model.OcorrenciaAlerta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OcorrenciaAlertaRepository extends JpaRepository<OcorrenciaAlerta, Long> {
    List<OcorrenciaAlerta> findByConfiguracaoAlertaDispositivoId(Long dispositivoId);
}

