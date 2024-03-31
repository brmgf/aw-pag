package com.brmgf.awpag.api.controller;

import com.brmgf.awpag.api.model.ClienteModel;
import com.brmgf.awpag.domain.model.Cliente;
import com.brmgf.awpag.domain.repository.ClienteRepository;
import com.brmgf.awpag.domain.service.CadastroClienteService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
@RequestMapping("/clientes")
@RestController
public class ClienteController {

    private final CadastroClienteService cadastroClienteService;
    private final ClienteRepository clienteRepository;
    private final ModelMapper modelMapper;

    @GetMapping
    public List<ClienteModel> listar() {
        return clienteRepository
                .findAll()
                .stream()
                .map(cliente -> modelMapper.map(cliente, ClienteModel.class))
                .collect(Collectors.toList());
    }

    @GetMapping("/{clienteId}")
    public ResponseEntity<ClienteModel> buscar(@PathVariable Long clienteId) {
        Optional<Cliente> clienteOptional = clienteRepository.findById(clienteId);

        if (clienteOptional.isPresent()) {
            Cliente cliente = clienteOptional.get();
            return ResponseEntity.ok(modelMapper.map(cliente, ClienteModel.class));
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
