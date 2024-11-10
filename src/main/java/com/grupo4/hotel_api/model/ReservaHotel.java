package com.grupo4.hotel_api.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
public class ReservaHotel {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nomeCliente;
    private Long numeroReserva;
    private String cidadeOrigemCliente;
    private String ufOrigemCliente;
    private LocalDate dataInicioReserva;
    private LocalDate dataFimReserva;

}
