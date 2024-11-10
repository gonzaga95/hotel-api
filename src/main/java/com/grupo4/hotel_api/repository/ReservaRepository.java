package com.grupo4.hotel_api.repository;

import com.grupo4.hotel_api.model.ReservaHotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservaRepository extends JpaRepository<ReservaHotel, Long> {
    List<ReservaHotel> findByUfOrigemCliente(String ufOrigemCliente);
}
