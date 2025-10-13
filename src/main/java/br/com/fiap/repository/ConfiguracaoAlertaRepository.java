package br.com.fiap.repository;

import br.com.fiap.model.ConfiguracaoAlerta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ConfiguracaoAlertaRepository extends JpaRepository<ConfiguracaoAlerta, Long> {
    Optional<ConfiguracaoAlerta> findByDispositivoId(Long dispositivoId);
}

