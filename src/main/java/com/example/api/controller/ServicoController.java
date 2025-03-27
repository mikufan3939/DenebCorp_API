package com.example.api.controller;

import com.example.api.model.Servico;
import com.example.api.service.ServicoService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/servicos")
public class ServicoController {
    @Autowired
    private ServicoService servicoService;

    @PostMapping("")
    public ResponseEntity<Servico> createServico(@RequestBody Servico servico){
        return new ResponseEntity<>(servicoService.createServico(servico), HttpStatus.CREATED);
    }

    @GetMapping("")
    public List<Servico> getAll(){
        return servicoService.getAll();
    }

    @GetMapping("/{id}")
    public Servico getById(@PathVariable long id){
        return servicoService.getById(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable long id){
        servicoService.deleteServico(id);
    }

    @PutMapping("{id}")
    public Servico updateServico(@PathVariable long id, @RequestBody Servico servico){
        return servicoService.updateServico(id, servico);
    }
}
