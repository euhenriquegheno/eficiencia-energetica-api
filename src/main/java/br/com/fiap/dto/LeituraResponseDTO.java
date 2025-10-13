package br.com.fiap.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class LeituraResponseDTO {
    private Long id;
    private Long dispositivoId;
    private String nomeDispositivo; // Para facilitar a exibição no frontend
    private Double valor;
    private String unidadeMedida;
    private LocalDateTime dataHoraLeitura;
    private LocalDateTime dataRegistro;
}

