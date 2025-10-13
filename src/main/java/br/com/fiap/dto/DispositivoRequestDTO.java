package br.com.fiap.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class DispositivoRequestDTO {

    @NotBlank(message = "O nome do dispositivo não pode estar em branco.")
    @Size(min = 2, max = 100, message = "O nome do dispositivo deve ter entre 2 e 100 caracteres.")
    private String nome;

    @NotBlank(message = "A localização do dispositivo não pode estar em branco.")
    @Size(max = 255, message = "A localização do dispositivo deve ter no máximo 255 caracteres.")
    private String localizacao;

    @NotBlank(message = "O tipo do dispositivo não pode estar em branco.")
    @Size(max = 50, message = "O tipo do dispositivo deve ter no máximo 50 caracteres.")
    private String tipo; // Ex: "Medidor Inteligente", "Sensor de Presença"

    private boolean ativo = true;
}

