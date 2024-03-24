package com.brmgf.awpag.domain.service;

import com.brmgf.awpag.domain.exception.AwpagException;
import com.brmgf.awpag.domain.model.Cliente;
import com.brmgf.awpag.domain.repository.ClienteRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class CadastroClienteService {

    private final ClienteRepository clienteRepository;

    @Transactional
    public Cliente salvar(Cliente cliente) {
        boolean emailEmUso = clienteRepository.findByEmail(cliente.getEmail())
                .filter(c -> !c.equals(cliente)).isPresent();

        if (emailEmUso) {
            throw new AwpagException("Já existe um cliente cadastrado com esse e-mail");
        }
        return clienteRepository.save(cliente);
    }

    @Transactional
    public void excluir(Long clienteId) {
        clienteRepository.deleteById(clienteId);
    }
}
