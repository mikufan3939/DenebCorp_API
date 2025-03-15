package com.example.api.service;

import com.example.api.model.Servico;
import com.example.api.repository.ServicoRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class ServicoService {
    @Autowired
    private ServicoRepository servicoRepository;

    public List<Servico> getAll(){
        return servicoRepository.findAll();
    }

    public Servico getById(long id){
        return servicoRepository.findById(id).orElseThrow();
    }

    public Servico createServico(Servico servico){
        return servicoRepository.save(servico);
    }

    public void deleteServico(long id){
        servicoRepository.deleteById(id);
    }

    public Servico updateServico(long id, Servico servico){
        Servico selectedServico=servicoRepository.findById(id).orElseThrow();
        selectedServico.setDescricao(servico.getDescricao());
        selectedServico.setName(servico.getName());
        return servicoRepository.save(selectedServico);
    }
}
