package com.grupo4.hotel_api.DTO;

import lombok.Data;
import java.time.LocalDate;

@Data
public class ReservaDTO {
    private String nomeCliente;
    private Long numeroReserva;
    private String cidadeOrigemCliente;
    private String ufOrigemCliente;
    private LocalDate dataInicioReserva;
    private LocalDate dataFimReserva;
    private Long diasReserva;
}
