package br.com.fiap.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "configuracoes_alerta")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConfiguracaoAlerta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "O dispositivo associado à configuração de alerta não pode ser nulo.")
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dispositivo_id", nullable = false, unique = true)
    private Dispositivo dispositivo;

    @NotNull(message = "O limite de consumo não pode ser nulo.")
    @Positive(message = "O limite de consumo deve ser um valor positivo.")
    @Column(name = "limite_consumo", nullable = false)
    private Double limiteConsumo; // Ex: kWh

    @NotBlank(message = "A unidade do limite de consumo não pode estar em branco.")
    @Size(max = 20, message = "A unidade do limite deve ter no máximo 20 caracteres.")
    @Column(name = "unidade_limite", nullable = false)
    private String unidadeLimite; // Ex: "kWh"

    @NotNull(message = "O tipo de alerta não pode ser nulo.")
    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_alerta", nullable = false)
    private TipoAlerta tipoAlerta; // Ex: ACIMA_DO_LIMITE, DESCONEXAO_DISPOSITIVO

    @NotNull(message = "A data de criação não pode ser nula.")
    @Column(name = "data_criacao", nullable = false, updatable = false)
    private LocalDateTime dataCriacao;

    @Column(name = "data_atualizacao")
    private LocalDateTime dataAtualizacao;

    @Column(name = "ativo")
    private boolean ativo = true;

    @PrePersist
    protected void onCreate() {
        dataCriacao = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        dataAtualizacao = LocalDateTime.now();
    }
}

