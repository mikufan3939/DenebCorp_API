package com.example.api.repository;

import com.example.api.model.ServiceSolicitation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServiceSolicitationRepository extends JpaRepository<ServiceSolicitation, Long> {
}
