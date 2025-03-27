package com.example.api.controller;

import com.example.api.model.ServicoOffer;
import com.example.api.repository.ServicoOfferRepository;
import com.example.api.service.ServicoOfferService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/servico-offers")
public class ServicoOfferController {
    @Autowired
    private ServicoOfferService servicoOfferService;
    @Autowired
    private ServicoOfferRepository servicoOfferRepository;

    @PostMapping("")
    public ServicoOffer createServicoOffer(ServicoOffer servicoOffer){
        return servicoOfferService.createServicoOffer(servicoOffer);
    }

    @GetMapping("")
    public List<ServicoOffer> getAll(){
        return servicoOfferService.getAll();
    }

    @GetMapping("/{id}")
    public ServicoOffer getById(@PathVariable long id){
        return servicoOfferService.getById(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable long id){
        servicoOfferService.delete(id);
    }

    @PutMapping("/{id}")
    public ServicoOffer updateServicoOffer(@PathVariable long id, @RequestBody ServicoOffer servicoOffer){
        return servicoOfferService.updateServicoOffer(id, servicoOffer);
    }
}
