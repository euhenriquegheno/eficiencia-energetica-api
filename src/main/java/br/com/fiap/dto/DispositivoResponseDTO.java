package br.com.fiap.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class DispositivoResponseDTO {
    private Long id;
    private String nome;
    private String localizacao;
    private String tipo;
    private LocalDateTime dataCadastro;
    private LocalDateTime dataAtualizacao;
    private boolean ativo;
}

