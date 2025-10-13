package br.com.fiap.controller;

import br.com.fiap.dto.LeituraRequestDTO;
import br.com.fiap.dto.LeituraResponseDTO;
import br.com.fiap.service.LeituraService;
import jakarta.validation.Valid;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/leituras")
public class LeituraController {

    private final LeituraService leituraService;

    public LeituraController(LeituraService leituraService) {
        this.leituraService = leituraService;
    }

    @PostMapping
    public ResponseEntity<LeituraResponseDTO> registrarLeitura(@Valid @RequestBody LeituraRequestDTO leituraRequestDTO) {
        LeituraResponseDTO novaLeitura = leituraService.registrarLeitura(leituraRequestDTO);
        return new ResponseEntity<>(novaLeitura, HttpStatus.CREATED);
    }

    @GetMapping("/dispositivo/{dispositivoId}")
    public ResponseEntity<List<LeituraResponseDTO>> listarLeiturasPorDispositivo(@PathVariable Long dispositivoId) {
        List<LeituraResponseDTO> leituras = leituraService.listarLeiturasPorDispositivo(dispositivoId);
        if (leituras.isEmpty()) {
            return ResponseEntity.ok(leituras);
        }
        return ResponseEntity.ok(leituras);
    }

    @GetMapping("/dispositivo/{dispositivoId}/periodo")
    public ResponseEntity<List<LeituraResponseDTO>> listarLeiturasPorDispositivoEPeriodo(
            @PathVariable Long dispositivoId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime inicio,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fim) {
        List<LeituraResponseDTO> leituras = leituraService.listarLeiturasPorDispositivoEPeriodo(dispositivoId, inicio, fim);
        return ResponseEntity.ok(leituras);
    }
}

