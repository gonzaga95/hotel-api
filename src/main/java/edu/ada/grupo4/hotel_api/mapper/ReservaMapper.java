package edu.ada.grupo4.hotel_api.mapper;

import edu.ada.grupo4.hotel_api.DTO.ReservaDTO;
import edu.ada.grupo4.hotel_api.model.ReservaHotel;
import org.springframework.stereotype.Component;

@Component
public class ReservaMapper {
    public ReservaHotel toEntity(ReservaDTO reservaDTO) {

        if (reservaDTO == null) {
            return null;
        }

        ReservaHotel reservaHotel = new ReservaHotel();
        reservaHotel.setNomeCliente(reservaDTO.getNomeCliente());
        reservaHotel.setNumeroReserva(reservaDTO.getNumeroReserva());
        reservaHotel.setCidadeOrigemCliente(reservaDTO.getCidadeOrigemCliente());
        reservaHotel.setUfOrigemCliente(reservaDTO.getUfOrigemCliente());
        reservaHotel.setDataInicioReserva(reservaDTO.getDataInicioReserva());
        reservaHotel.setDataFimReserva(reservaDTO.getDataFimReserva());

        return reservaHotel;
    }

    public ReservaDTO toDTO(ReservaHotel reservaHotel) {
        ReservaDTO dto = new ReservaDTO();
        dto.setNomeCliente(reservaHotel.getNomeCliente());
        dto.setNumeroReserva(reservaHotel.getNumeroReserva());
        dto.setCidadeOrigemCliente(reservaHotel.getCidadeOrigemCliente());
        dto.setUfOrigemCliente(reservaHotel.getUfOrigemCliente());
        dto.setDataInicioReserva(reservaHotel.getDataInicioReserva());
        dto.setDataFimReserva(reservaHotel.getDataFimReserva());
        return dto;
    }
}
