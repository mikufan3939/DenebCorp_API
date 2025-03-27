package com.example.api.controller;

import com.example.api.model.ServicoSolicitation;
import com.example.api.service.ServicoSolicitationService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/service-solicitation")
public class ServicoSolicitationController {
    @Autowired
    private ServicoSolicitationService servicoSolicitationService;

    @PostMapping("")
    public ResponseEntity<ServicoSolicitation> createServicoSolicitation(@RequestBody ServicoSolicitation servicoSolicitation){
        return new ResponseEntity<>(servicoSolicitationService.createServicoSolicitation(servicoSolicitation), HttpStatus.CREATED);
    }

    @GetMapping("")
    public List<ServicoSolicitation> getAll(){
        return servicoSolicitationService.getAll();
    }

    @GetMapping("/{id}")
    public ServicoSolicitation getById(@PathVariable long id){
        return servicoSolicitationService.getById(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable long id){
        servicoSolicitationService.delete(id);
    }

    @PutMapping("/{id}")
    public ServicoSolicitation updateServicoSolicitation(@PathVariable long id, @RequestBody ServicoSolicitation servicoSolicitation){
        return servicoSolicitationService.updateServicoSolicitation(id, servicoSolicitation);
    }
}
