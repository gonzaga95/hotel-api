package edu.ada.grupo4.hotel_api.service;

import edu.ada.grupo4.hotel_api.DTO.ReservaUpdateDTO;
import edu.ada.grupo4.hotel_api.mapper.ReservaMapper;
import edu.ada.grupo4.hotel_api.model.ReservaHotel;
import edu.ada.grupo4.hotel_api.repository.ReservaRepository;
import edu.ada.grupo4.hotel_api.DTO.ReservaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReservaService {

    @Autowired
    private ReservaRepository reservaRepository;

    @Autowired
    private ReservaMapper reservaMapper;

    @PostMapping
    public ReservaDTO salvar(ReservaDTO reservaDTO) {

        ReservaHotel reservaHotel = new ReservaMapper().toEntity(reservaDTO);
        reservaRepository.save(reservaHotel);

        return reservaDTO;
    }

    @GetMapping
    public List<ReservaDTO> listarTodos() {
        return reservaRepository.findAll()
                .stream()
                .map(reservaHotel -> {
                    ReservaDTO reservaDTO = reservaMapper.toDTO(reservaHotel);
                    return reservaDTO;
                })
                .collect(Collectors.toList());
    }

    @GetMapping("/uf/{uf}")
    public List<ReservaDTO> listarPorEstado(@PathVariable("uf") String uf) {
        String ufCaseInsensitive = uf.toUpperCase();

        return reservaRepository.findByUfOrigemCliente(ufCaseInsensitive)
                .stream()
                .map(reservaHotel -> {
                    ReservaDTO reservaDTO = reservaMapper.toDTO(reservaHotel);
                    return reservaDTO;
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

    @PutMapping("/{numeroReserva}")
    public ReservaDTO atualizarDataReserva(@PathVariable Long numeroReserva,
                                           @RequestBody ReservaUpdateDTO reservaUpdateDTO) {
        return reservaRepository.findByNumeroReserva(numeroReserva)
                .map(reservaExistente -> {
                    reservaExistente.setDataInicioReserva(reservaUpdateDTO.getDataInicioReserva());
                    reservaExistente.setDataFimReserva(reservaUpdateDTO.getDataFimReserva());

                    ReservaHotel reservaAtualizada = reservaRepository.save(reservaExistente);
                    ReservaDTO dtoAtualizado = new ReservaDTO();
                    dtoAtualizado.setNomeCliente(reservaAtualizada.getNomeCliente());
                    dtoAtualizado.setNumeroReserva(reservaAtualizada.getNumeroReserva());
                    dtoAtualizado.setCidadeOrigemCliente(reservaAtualizada.getCidadeOrigemCliente());
                    dtoAtualizado.setUfOrigemCliente(reservaAtualizada.getUfOrigemCliente());
                    dtoAtualizado.setDataInicioReserva(reservaAtualizada.getDataInicioReserva());
                    dtoAtualizado.setDataFimReserva(reservaAtualizada.getDataFimReserva());
                    return dtoAtualizado;
                })
                .orElseThrow(() -> new RuntimeException("Reserva não encontrada com o número: " + numeroReserva));
    }

    @DeleteMapping("/{numeroReserva}")
    public void deletar(@PathVariable Long numeroReserva) {
        ReservaHotel reserva = reservaRepository.findByNumeroReserva(numeroReserva)
                .orElseThrow(() -> new RuntimeException("Reserva não encontrada com o número: " + numeroReserva));

        reservaRepository.delete(reserva);
    }
}
