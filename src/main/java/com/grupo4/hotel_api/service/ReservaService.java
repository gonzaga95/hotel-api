package com.grupo4.hotel_api.service;

import com.grupo4.hotel_api.model.ReservaHotel;
import com.grupo4.hotel_api.repository.ReservaRepository;
import com.grupo4.hotel_api.controller.DTO.ReservaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReservaService {

    @Autowired
    private ReservaRepository reservaRepository;

    @PostMapping
    @PreAuthorize("hasRole('USER')")
    public ReservaDTO salvar(ReservaDTO reservaDTO) {
        ReservaHotel reservaHotel = new ReservaHotel();
        reservaHotel.setNomeCliente(reservaDTO.getNomeCliente());
        reservaHotel.setNumeroReserva(reservaDTO.getNumeroReserva());
        reservaRepository.save(reservaHotel);
        return reservaDTO;
    }
    @GetMapping
    @PreAuthorize("hasRole('USER')")
    public List<ReservaDTO> listarTodos() {
        return reservaRepository.findAll().stream().map(reservaHotel -> {
            ReservaDTO dto = new ReservaDTO();
            dto.setNomeCliente(reservaHotel.getNomeCliente());
            dto.setNumeroReserva(reservaHotel.getNumeroReserva());
            return dto;
        }).collect(Collectors.toList());
    }
}
