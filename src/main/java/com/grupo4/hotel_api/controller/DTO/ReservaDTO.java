package com.grupo4.hotel_api.controller.DTO;

import lombok.Data;
import java.time.LocalDate;

@Data
public class ReservaDTO {
    private String nomeCliente;
    private Long numeroReserva;
    private String cidadeOrigemCliente;  // Novo campo
    private String ufOrigemCliente;      // Novo campo
    private LocalDate dataInicioReserva; // Novo campo
    private LocalDate dataFimReserva;    // Novo campo
}
