package com.brmgf.awpag.domain.service;

import com.brmgf.awpag.domain.exception.AwpagException;
import com.brmgf.awpag.domain.model.Cliente;
import com.brmgf.awpag.domain.model.Parcelamento;
import com.brmgf.awpag.domain.repository.ParcelamentoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@AllArgsConstructor
@Service
public class ParcelamentoService {

    private final ParcelamentoRepository parcelamentoRepository;
    private final CadastroClienteService cadastroClienteService;

    @Transactional
    public Parcelamento salvar(Parcelamento novoParcelamento) {
        if (novoParcelamento.getId() != null) {
            throw new AwpagException("Parcelamento a ser criado não pode possuir um código");
        }

        Cliente cliente = cadastroClienteService.buscar(novoParcelamento.getCliente().getId());
        novoParcelamento.setCliente(cliente);
        novoParcelamento.setDataHoraCriacao(LocalDateTime.now());

        return parcelamentoRepository.save(novoParcelamento);
    }
}
