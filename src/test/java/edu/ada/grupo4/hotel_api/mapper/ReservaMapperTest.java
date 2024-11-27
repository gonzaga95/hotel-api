package edu.ada.grupo4.hotel_api.mapper;

import edu.ada.grupo4.hotel_api.DTO.ReservaDTO;
import edu.ada.grupo4.hotel_api.model.ReservaHotel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class ReservaMapperTest {

    private ReservaMapper reservaMapper;

    @BeforeEach
    public void setUp(){
        reservaMapper = new ReservaMapper();
    }

    @Test
    public void toEntity_deveRetornarUmReservaHotel() {
        ReservaDTO reservaDTO = new ReservaDTO();
        reservaDTO.setNomeCliente("João");
        reservaDTO.setNumeroReserva(1L);
        reservaDTO.setCidadeOrigemCliente("São Paulo");
        reservaDTO.setUfOrigemCliente("SP");
        reservaDTO.setDataInicioReserva(LocalDate.of(2021, 10, 10));
        reservaDTO.setDataFimReserva(LocalDate.of(2021, 10, 15));

        ReservaHotel reservaHotel = reservaMapper.toEntity(reservaDTO);

        assertNotNull(reservaHotel);
        assertEquals(reservaDTO.getNomeCliente(), reservaHotel.getNomeCliente());
    }

    @Test
    public void toEntity_deveRetornarUmReservaHotelComNumeroReservaNulo() {
        ReservaHotel reservaHotel = reservaMapper.toEntity(null);

        assertNull(reservaHotel);
    }

    @Test
    public void toDto_deveRetornarUmReservaDTO() {
        ReservaHotel reservaHotel = new ReservaHotel();
        reservaHotel.setNomeCliente("João");
        reservaHotel.setNumeroReserva(1L);
        reservaHotel.setCidadeOrigemCliente("São Paulo");
        reservaHotel.setUfOrigemCliente("SP");
        reservaHotel.setDataInicioReserva(LocalDate.of(2021, 10, 10));
        reservaHotel.setDataFimReserva(LocalDate.of(2021, 10, 15));

        ReservaDTO reservaDTO = reservaMapper.toDTO(reservaHotel);

        assertNotNull(reservaDTO);
        assertEquals(reservaHotel.getNomeCliente(), reservaDTO.getNomeCliente());
    }
}

