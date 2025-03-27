package com.example.api.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "service_solicitation")
public class ServicoSolicitation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private Date date;

    @ManyToOne
    @JoinColumn(name = "service_offer_id")
    private ServicoOffer servicoOffer;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
