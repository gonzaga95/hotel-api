package com.grupo4.hotel_api.service;

import com.grupo4.hotel_api.model.ReservaHotel;
import com.grupo4.hotel_api.repository.ReservaRepository;
import com.grupo4.hotel_api.DTO.ReservaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReservaService {

    @Autowired
    private ReservaRepository reservaRepository;

    @PostMapping
    public ReservaDTO salvar(ReservaDTO reservaDTO) {

        ReservaHotel reservaHotel = new ReservaHotel();
        reservaHotel.setNomeCliente(reservaDTO.getNomeCliente());
        reservaHotel.setNumeroReserva(reservaDTO.getNumeroReserva());
        reservaHotel.setCidadeOrigemCliente(reservaDTO.getCidadeOrigemCliente());
        reservaHotel.setUfOrigemCliente(reservaDTO.getUfOrigemCliente());
        reservaHotel.setDataInicioReserva(reservaDTO.getDataInicioReserva());
        reservaHotel.setDataFimReserva(reservaDTO.getDataFimReserva());

        reservaRepository.save(reservaHotel);
        return reservaDTO;
    }

    @GetMapping
    public List<ReservaDTO> listarTodos() {
        return reservaRepository.findAll()
                .stream()
                .map(reservaHotel -> {
            ReservaDTO dto = new ReservaDTO();
            dto.setNomeCliente(reservaHotel.getNomeCliente());
            dto.setNumeroReserva(reservaHotel.getNumeroReserva());
            dto.setCidadeOrigemCliente(reservaHotel.getCidadeOrigemCliente());
            dto.setUfOrigemCliente(reservaHotel.getUfOrigemCliente());
            dto.setDataInicioReserva(reservaHotel.getDataInicioReserva());
            dto.setDataFimReserva(reservaHotel.getDataFimReserva());
            return dto;
        })
                .collect(Collectors.toList());
    }

    @GetMapping("/uf/{uf}")
    public List<ReservaDTO> listarPorEstado(@PathVariable("uf") String uf) {
        String ufCaseInsensitive = uf.toUpperCase();

        return reservaRepository.findByUfOrigemCliente(ufCaseInsensitive)
                .stream()
                .map(reservaHotel -> {
            ReservaDTO dto = new ReservaDTO();
            dto.setNomeCliente(reservaHotel.getNomeCliente());
            dto.setNumeroReserva(reservaHotel.getNumeroReserva());
            dto.setCidadeOrigemCliente(reservaHotel.getCidadeOrigemCliente());
            dto.setUfOrigemCliente(reservaHotel.getUfOrigemCliente());
            dto.setDataInicioReserva(reservaHotel.getDataInicioReserva());
            dto.setDataFimReserva(reservaHotel.getDataFimReserva());
            return dto;
        }).collect(Collectors.toList());
    }

    @GetMapping("/diarias-longas")
    public List<ReservaDTO> listarDiariasMaisLongas() {
        return reservaRepository.findAll().stream()
                .sorted((r1, r2) -> {
                    long diasR1 = ChronoUnit.DAYS.between(r1.getDataInicioReserva(), r1.getDataFimReserva());
                    long diasR2 = ChronoUnit.DAYS.between(r2.getDataInicioReserva(), r2.getDataFimReserva());
                    return Long.compare(diasR2, diasR1);
                })
                .map(reservaHotel -> {
                    ReservaDTO dto = new ReservaDTO();
                    dto.setNomeCliente(reservaHotel.getNomeCliente());
                    dto.setNumeroReserva(reservaHotel.getNumeroReserva());
                    dto.setCidadeOrigemCliente(reservaHotel.getCidadeOrigemCliente());
                    dto.setUfOrigemCliente(reservaHotel.getUfOrigemCliente());
                    dto.setDataInicioReserva(reservaHotel.getDataInicioReserva());
                    dto.setDataFimReserva(reservaHotel.getDataFimReserva());
                    long diasTotal = ChronoUnit.DAYS.between(reservaHotel.getDataInicioReserva(), reservaHotel.getDataFimReserva());
                    dto.setDiasReserva(diasTotal);
                    return dto;
                })
                .collect(Collectors.toList());
    }
}
