package com.brmgf.awpag.api.controller;

import com.brmgf.awpag.domain.exception.AwpagException;
import com.brmgf.awpag.domain.model.Cliente;
import com.brmgf.awpag.domain.repository.ClienteRepository;
import com.brmgf.awpag.domain.service.CadastroClienteService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@RequestMapping("/clientes")
@RestController
public class ClienteController {

    private final CadastroClienteService cadastroClienteService;
    private final ClienteRepository clienteRepository;

    @GetMapping
    public List<Cliente> listar() {
        return clienteRepository.findAll();
    }

    @GetMapping("/{clienteId}")
    public ResponseEntity<Cliente> buscar(@PathVariable Long clienteId) {
        Optional<Cliente> cliente = clienteRepository.findById(clienteId);

        if (cliente.isPresent()) {
            return ResponseEntity.ok(cliente.get());
        }

        return ResponseEntity.notFound().build();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Cliente adicionar(@Valid @RequestBody Cliente cliente) {
        return cadastroClienteService.salvar(cliente);
    }

    @PutMapping("/{clienteId}")
    public ResponseEntity<Cliente> atualizar(@PathVariable Long clienteId, @Valid @RequestBody Cliente cliente) {
        if (!clienteRepository.existsById(clienteId)) {
            return ResponseEntity.notFound().build();
        }

        cliente.setId(clienteId);
        cliente = cadastroClienteService.salvar(cliente);
        return ResponseEntity.ok(cliente);
    }

    @DeleteMapping("/{clienteId}")
    public ResponseEntity<Void> excluir(@PathVariable Long clienteId) {
        if (!clienteRepository.existsById(clienteId)) {
            return ResponseEntity.notFound().build();
        }

        cadastroClienteService.excluir(clienteId);
        return ResponseEntity.noContent().build();
    }

}
