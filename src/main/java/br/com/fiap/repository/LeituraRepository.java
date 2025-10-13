package br.com.fiap.repository;

import br.com.fiap.model.Leitura;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface LeituraRepository extends JpaRepository<Leitura, Long> {
    List<Leitura> findByDispositivoId(Long dispositivoId);
    List<Leitura> findByDispositivoIdAndDataHoraLeituraBetween(Long dispositivoId, LocalDateTime inicio, LocalDateTime fim);
}

