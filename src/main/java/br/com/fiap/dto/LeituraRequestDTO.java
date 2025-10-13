package br.com.fiap.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import java.time.LocalDateTime;

@Data
public class LeituraRequestDTO {

    @NotNull(message = "O ID do dispositivo não pode ser nulo.")
    private Long dispositivoId;

    @NotNull(message = "O valor da leitura não pode ser nulo.")
    @Positive(message = "O valor da leitura deve ser positivo.")
    private Double valor;

    @NotBlank(message = "A unidade de medida não pode estar em branco.")
    @Size(max = 20, message = "A unidade de medida deve ter no máximo 20 caracteres.")
    private String unidadeMedida;

    private LocalDateTime dataHoraLeitura; // Opcional, se não enviado, usa a data atual no backend
}

