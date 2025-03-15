package com.example.api.service;

import com.example.api.model.ServicoSolicitation;
import com.example.api.repository.ServicoSolicitationRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class ServicoSolicitationService {
    @Autowired
    private ServicoSolicitationRepository servicoSolicitationRepository;

    public ServicoSolicitation createServicoSolicitation(ServicoSolicitation servicoSolicitation){
        return servicoSolicitationRepository.save(servicoSolicitation);
    }

    public List<ServicoSolicitation> getAll(){
        return servicoSolicitationRepository.findAll();
    }

    public ServicoSolicitation getById(long id){
        return servicoSolicitationRepository.findById(id).orElseThrow();
    }

    public void delete(long id){
        servicoSolicitationRepository.deleteById(id);
    }

    public ServicoSolicitation updateServicoSolicitation(long id, ServicoSolicitation servicoSolicitation){
        ServicoSolicitation selectedServicoSolicitation=getById(id);
        selectedServicoSolicitation.setDate(servicoSolicitation.getDate());

        return servicoSolicitationRepository.save(selectedServicoSolicitation);
    }
}
