package br.com.fiap.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "ocorrencias_alerta")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OcorrenciaAlerta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "A configuração de alerta associada não pode ser nula.")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "configuracao_alerta_id", nullable = false)
    private ConfiguracaoAlerta configuracaoAlerta;

    @NotNull(message = "O tipo de alerta da ocorrência não pode ser nulo.")
    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_alerta_ocorrencia", nullable = false)
    private TipoAlerta tipoAlertaOcorrencia;

    @NotBlank(message = "A descrição da ocorrência não pode estar em branco.")
    @Column(nullable = false)
    private String descricao;

    @NotNull(message = "A data e hora da ocorrência não pode ser nula.")
    @Column(name = "data_hora_ocorrencia", nullable = false)
    private LocalDateTime dataHoraOcorrencia;

    @Column(name = "valor_registrado")
    private Double valorRegistrado; // Valor que disparou o alerta, se aplicável

    @Column(name = "resolvido")
    private boolean resolvido = false;

    @Column(name = "data_resolucao")
    private LocalDateTime dataResolucao;

    @Column(name = "observacoes_resolucao")
    private String observacoesResolucao;

    @PrePersist
    protected void onCreate() {
        if (dataHoraOcorrencia == null) {
            dataHoraOcorrencia = LocalDateTime.now();
        }
    }
}

