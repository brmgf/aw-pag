package com.brmgf.awpag.api.controller;

import com.brmgf.awpag.api.model.Cliente;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class ClienteController {

    @GetMapping("/clientes")
    public List<Cliente> listar() {
        var cliente1 = new Cliente(1L, "João", "joao@gmail.com", "48887903456");
        var cliente2 = new Cliente(2L, "Maria Luiza", "maria@email.com", "37998675501");
        return Arrays.asList(cliente1, cliente2);
    }

}
