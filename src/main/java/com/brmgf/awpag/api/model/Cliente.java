package com.brmgf.awpag.api.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Cliente {

    private Long id;
    private String nome;
    private String email;
    private String telefone;

}
