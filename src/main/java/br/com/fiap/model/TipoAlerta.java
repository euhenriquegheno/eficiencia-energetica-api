package br.com.fiap.model;

public enum TipoAlerta {
    ACIMA_DO_LIMITE,
    ABAIXO_DO_LIMITE, // Caso haja um limite mínimo também
    DESCONEXAO_DISPOSITIVO,
    FALHA_LEITURA,
    MANUTENCAO_PREVISTA
}

