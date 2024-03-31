package com.brmgf.awpag.api.model;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ParcelamentoModel {

    private Long id;
    private String nomeCliente;
    private String descricao;
    private BigDecimal valorTotal;
    private Integer parcelas;

}
