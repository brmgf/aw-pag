package com.brmgf.awpag.domain.model;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Getter
@Setter
public class Parcelamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @ManyToOne
    @JoinColumn(name = "cliente_id")

    private Cliente cliente;
    private String descricao;
    private BigDecimal valorTotal;
    private Integer quantidadeParcelas;

    @Column(name = "data_criacao")
    private LocalDateTime dataHoraCriacao;

}
