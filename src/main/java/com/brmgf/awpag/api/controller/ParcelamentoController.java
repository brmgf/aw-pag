package com.brmgf.awpag.api.controller;

import com.brmgf.awpag.api.model.ParcelamentoModel;
import com.brmgf.awpag.domain.exception.AwpagException;
import com.brmgf.awpag.domain.model.Parcelamento;
import com.brmgf.awpag.domain.repository.ParcelamentoRepository;
import com.brmgf.awpag.domain.service.ParcelamentoService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RequestMapping("/parcelamentos")
@RestController
public class ParcelamentoController {

    private final ParcelamentoService parcelamentoService;
    private final ParcelamentoRepository parcelamentoRepository;
    private final ModelMapper modelMapper;

    @GetMapping
    public List<Parcelamento> listar() {
        return parcelamentoRepository.findAll();
    }

    @GetMapping("/{parcelamentoId}")
    public ResponseEntity<ParcelamentoModel> buscar(@PathVariable Long parcelamentoId) {
        return parcelamentoRepository.findById(parcelamentoId)
                .map(parcelamento -> modelMapper.map(parcelamento, ParcelamentoModel.class))
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Parcelamento cadastrar(@Valid @RequestBody Parcelamento novoParcelamento) {
        return parcelamentoService.salvar(novoParcelamento);
    }

}
