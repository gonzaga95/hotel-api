package edu.ada.grupo4.hotel_api.DTO;

import lombok.Data;
import java.time.LocalDate;

@Data
public class ReservaUpdateDTO {
    private LocalDate dataInicioReserva;
    private LocalDate dataFimReserva;
}
