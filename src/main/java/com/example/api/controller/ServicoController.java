package com.example.api.controller;

import com.example.api.model.Servico;
import com.example.api.service.ServicoService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/servicos")
public class ServicoController {
    @Autowired
    private ServicoService servicoService;

    @PostMapping("")
    public Servico createServico(@RequestBody Servico servico){
        return servicoService.createServico(servico);
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
