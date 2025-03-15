package com.example.api.service;

import com.example.api.model.ServicoOffer;
import com.example.api.repository.ServicoOfferRepository;
import com.example.api.repository.ServicoRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class ServicoOfferService {
    @Autowired
    private ServicoOfferRepository servicoOfferRepository;
    @Autowired
    private ServicoRepository servicoRepository;

    public ServicoOffer createServicoOffer(ServicoOffer serviceOffer){
        return servicoOfferRepository.save(serviceOffer);
    }

    public List<ServicoOffer> getAll(){
        return servicoOfferRepository.findAll();
    }

    public ServicoOffer getById(long id){
        return servicoOfferRepository.findById(id).orElseThrow();
    }

    public void delete(long id){
        servicoOfferRepository.deleteById(id);
    }

    public ServicoOffer updateServicoOffer(long id, ServicoOffer servicoOffer){
        ServicoOffer selectedServicoOffer=getById(id);
        selectedServicoOffer.setPrice(servicoOffer.getPrice());
        selectedServicoOffer.setPlace(servicoOffer.getPlace());
        return servicoOfferRepository.save(selectedServicoOffer);
    }
}
