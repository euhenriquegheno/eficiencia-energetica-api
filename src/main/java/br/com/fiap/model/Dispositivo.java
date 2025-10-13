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
@Table(name = "dispositivos")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Dispositivo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O nome do dispositivo não pode estar em branco.")
    @Size(min = 2, max = 100, message = "O nome do dispositivo deve ter entre 2 e 100 caracteres.")
    @Column(nullable = false, unique = true)
    private String nome;

    @NotBlank(message = "A localização do dispositivo não pode estar em branco.")
    @Size(max = 255, message = "A localização do dispositivo deve ter no máximo 255 caracteres.")
    @Column
    private String localizacao;

    @NotBlank(message = "O tipo do dispositivo não pode estar em branco.")
    @Size(max = 50, message = "O tipo do dispositivo deve ter no máximo 50 caracteres.")
    @Column
    private String tipo; // Ex: "Medidor Inteligente", "Sensor de Presença"

    @NotNull(message = "A data de cadastro não pode ser nula.")
    @Column(name = "data_cadastro", nullable = false, updatable = false)
    private LocalDateTime dataCadastro;

    @Column(name = "data_atualizacao")
    private LocalDateTime dataAtualizacao;

    @Column(name = "ativo")
    private boolean ativo = true;

    @PrePersist
    protected void onCreate() {
        dataCadastro = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        dataAtualizacao = LocalDateTime.now();
    }
}

