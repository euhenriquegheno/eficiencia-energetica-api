package br.com.fiap.controller;

import br.com.fiap.dto.DispositivoRequestDTO;
import br.com.fiap.dto.DispositivoResponseDTO;
import br.com.fiap.service.DispositivoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/dispositivos")
public class DispositivoController {

    private final DispositivoService dispositivoService;

    public DispositivoController(DispositivoService dispositivoService) {
        this.dispositivoService = dispositivoService;
    }

    @PostMapping
    public ResponseEntity<DispositivoResponseDTO> criarDispositivo(@Valid @RequestBody DispositivoRequestDTO dispositivoRequestDTO) {
        DispositivoResponseDTO novoDispositivo = dispositivoService.criarDispositivo(dispositivoRequestDTO);
        return new ResponseEntity<>(novoDispositivo, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<DispositivoResponseDTO>> listarTodosDispositivos() {
        List<DispositivoResponseDTO> dispositivos = dispositivoService.listarTodosDispositivos();
        return ResponseEntity.ok(dispositivos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DispositivoResponseDTO> buscarDispositivoPorId(@PathVariable Long id) {
        return dispositivoService.buscarDispositivoPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<DispositivoResponseDTO> atualizarDispositivo(@PathVariable Long id, @Valid @RequestBody DispositivoRequestDTO dispositivoRequestDTO) {
        return dispositivoService.atualizarDispositivo(id, dispositivoRequestDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarDispositivo(@PathVariable Long id) {
        if (dispositivoService.deletarDispositivo(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}

