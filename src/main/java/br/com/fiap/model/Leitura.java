package br.com.fiap.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "leituras_consumo")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Leitura {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "O dispositivo associado à leitura não pode ser nulo.")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dispositivo_id", nullable = false)
    private Dispositivo dispositivo;

    @NotNull(message = "O valor da leitura não pode ser nulo.")
    @Column(nullable = false)
    private Double valor; // Ex: kWh

    @NotBlank(message = "A unidade de medida não pode estar em branco.")
    @Size(max = 20, message = "A unidade de medida deve ter no máximo 20 caracteres.")
    @Column(name = "unidade_medida", nullable = false)
    private String unidadeMedida; // Ex: "kWh", "W"

    @NotNull(message = "A data e hora da leitura não pode ser nula.")
    @Column(name = "data_hora_leitura", nullable = false)
    private LocalDateTime dataHoraLeitura;

    @Column(name = "data_registro", nullable = false, updatable = false)
    private LocalDateTime dataRegistro;

    @PrePersist
    protected void onCreate() {
        dataRegistro = LocalDateTime.now();
        if (dataHoraLeitura == null) {
            dataHoraLeitura = LocalDateTime.now(); // Default to now if not provided
        }
    }
}

