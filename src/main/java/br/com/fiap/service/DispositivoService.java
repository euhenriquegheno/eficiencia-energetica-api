package br.com.fiap.service;

import br.com.fiap.dto.DispositivoRequestDTO;
import br.com.fiap.dto.DispositivoResponseDTO;
import br.com.fiap.model.Dispositivo;
import br.com.fiap.repository.DispositivoRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DispositivoService {

    private final DispositivoRepository dispositivoRepository;

    public DispositivoService(DispositivoRepository dispositivoRepository) {
        this.dispositivoRepository = dispositivoRepository;
    }

    @Transactional
    public DispositivoResponseDTO criarDispositivo(DispositivoRequestDTO dispositivoRequestDTO) {
        Optional<Dispositivo> byNome = dispositivoRepository.findByNome(dispositivoRequestDTO.getNome());
        if (byNome.isPresent()) {
            throw new RuntimeException("Dispositivo com nome '" + dispositivoRequestDTO.getNome() + "' já existe."); //TODO: Criar exception customizada
        }
        Dispositivo dispositivo = new Dispositivo();
        BeanUtils.copyProperties(dispositivoRequestDTO, dispositivo);
        Dispositivo dispositivoSalvo = dispositivoRepository.save(dispositivo);
        return toResponseDTO(dispositivoSalvo);
    }

    @Transactional(readOnly = true)
    public List<DispositivoResponseDTO> listarTodosDispositivos() {
        return dispositivoRepository.findAll().stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public Optional<DispositivoResponseDTO> buscarDispositivoPorId(Long id) {
        return dispositivoRepository.findById(id).map(this::toResponseDTO);
    }

    @Transactional
    public Optional<DispositivoResponseDTO> atualizarDispositivo(Long id, DispositivoRequestDTO dispositivoRequestDTO) {
        Optional<Dispositivo> dispositivoExistenteOpt = dispositivoRepository.findById(id);
        if (dispositivoExistenteOpt.isEmpty()) {
            return Optional.empty();
        }

        // Verifica se o novo nome já existe em outro dispositivo
        Optional<Dispositivo> byNome = dispositivoRepository.findByNome(dispositivoRequestDTO.getNome());
        if (byNome.isPresent() && !byNome.get().getId().equals(id)) {
            throw new RuntimeException("Outro dispositivo com nome '" + dispositivoRequestDTO.getNome() + "' já existe."); //TODO: Criar exception customizada
        }

        Dispositivo dispositivoExistente = dispositivoExistenteOpt.get();
        BeanUtils.copyProperties(dispositivoRequestDTO, dispositivoExistente, "id", "dataCadastro");
        Dispositivo dispositivoAtualizado = dispositivoRepository.save(dispositivoExistente);
        return Optional.of(toResponseDTO(dispositivoAtualizado));
    }

    @Transactional
    public boolean deletarDispositivo(Long id) {
        if (dispositivoRepository.existsById(id)) {
            // TODO: Adicionar lógica para verificar dependências antes de deletar (ex: leituras associadas)
            dispositivoRepository.deleteById(id);
            return true;
        }
        return false;
    }

    private DispositivoResponseDTO toResponseDTO(Dispositivo dispositivo) {
        DispositivoResponseDTO dto = new DispositivoResponseDTO();
        BeanUtils.copyProperties(dispositivo, dto);
        return dto;
    }
}

