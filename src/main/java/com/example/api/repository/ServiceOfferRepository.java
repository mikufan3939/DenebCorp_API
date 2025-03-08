package com.example.api.repository;

import com.example.api.model.ServiceOffer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServiceOfferRepository extends JpaRepository<ServiceOffer, Long> {
}
