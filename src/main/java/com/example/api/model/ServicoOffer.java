package com.example.api.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "service_offer")
public class ServicoOffer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    //Funcao de escolher no mapa?
    private String place;

    private double price;

    @ManyToOne
    @JoinColumn(name = "service_id")
    private Servico servico;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
