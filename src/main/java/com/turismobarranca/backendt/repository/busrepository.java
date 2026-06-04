package com.turismobarranca.backendt.repository;

import com.turismobarranca.backendt.model.bus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface busrepository extends JpaRepository<bus, Long> {
}