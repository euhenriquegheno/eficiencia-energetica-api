package br.com.fiap.service;

import br.com.fiap.dto.LeituraRequestDTO;
import br.com.fiap.dto.LeituraResponseDTO;
import br.com.fiap.model.Dispositivo;
import br.com.fiap.model.Leitura;
import br.com.fiap.repository.DispositivoRepository;
import br.com.fiap.repository.LeituraRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LeituraService {

    private final LeituraRepository leituraRepository;
    private final DispositivoRepository dispositivoRepository;

    public LeituraService(LeituraRepository leituraRepository, DispositivoRepository dispositivoRepository) {
        this.leituraRepository = leituraRepository;
        this.dispositivoRepository = dispositivoRepository;
    }

    @Transactional
    public LeituraResponseDTO registrarLeitura(LeituraRequestDTO leituraRequestDTO) {
        Dispositivo dispositivo = dispositivoRepository.findById(leituraRequestDTO.getDispositivoId())
                .orElseThrow(() -> new RuntimeException("Dispositivo com ID " + leituraRequestDTO.getDispositivoId() + " não encontrado.")); //TODO: Criar exception customizada

        Leitura leitura = new Leitura();
        BeanUtils.copyProperties(leituraRequestDTO, leitura);
        leitura.setDispositivo(dispositivo);

        Leitura leituraSalva = leituraRepository.save(leitura);
        return toResponseDTO(leituraSalva);
    }

    @Transactional(readOnly = true)
    public List<LeituraResponseDTO> listarLeiturasPorDispositivo(Long dispositivoId) {
        if (!dispositivoRepository.existsById(dispositivoId)) {
            throw new RuntimeException("Dispositivo com ID " + dispositivoId + " não encontrado para listar leituras."); //TODO: Criar exception customizada
        }
        return leituraRepository.findByDispositivoId(dispositivoId).stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<LeituraResponseDTO> listarLeiturasPorDispositivoEPeriodo(Long dispositivoId, LocalDateTime inicio, LocalDateTime fim) {
        if (!dispositivoRepository.existsById(dispositivoId)) {
            throw new RuntimeException("Dispositivo com ID " + dispositivoId + " não encontrado para listar leituras por período."); //TODO: Criar exception customizada
        }
        if (inicio == null || fim == null) {
            throw new IllegalArgumentException("As datas de início e fim do período devem ser fornecidas.");
        }
        if (inicio.isAfter(fim)) {
            throw new IllegalArgumentException("A data de início não pode ser posterior à data de fim.");
        }
        return leituraRepository.findByDispositivoIdAndDataHoraLeituraBetween(dispositivoId, inicio, fim).stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }

    private LeituraResponseDTO toResponseDTO(Leitura leitura) {
        LeituraResponseDTO dto = new LeituraResponseDTO();
        BeanUtils.copyProperties(leitura, dto);
        if (leitura.getDispositivo() != null) {
            dto.setDispositivoId(leitura.getDispositivo().getId());
            dto.setNomeDispositivo(leitura.getDispositivo().getNome());
        }
        return dto;
    }
}

