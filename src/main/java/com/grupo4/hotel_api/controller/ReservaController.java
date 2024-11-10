package com.grupo4.hotel_api.controller;

import com.grupo4.hotel_api.DTO.ReservaDTO;
import com.grupo4.hotel_api.service.ReservaService;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reserva")
public class ReservaController {

    @Autowired
    private ReservaService reservaService;

    @PostMapping
    public ReservaDTO criar (@RequestBody ReservaDTO reservaDTO) {
        return reservaService.salvar(reservaDTO);
    }

    @GetMapping("/all")
    public List <ReservaDTO> todos(){
        return reservaService.listarTodos();
    }

    @GetMapping("/uf/{uf}")
    public List<ReservaDTO> listarReservasPorEstado(
            @Parameter(description = "Estado (UF) para filtrar as reservas", required = true)
            @PathVariable String uf) {
        return reservaService.listarPorEstado(uf);
    }

    @GetMapping("/diarias-longas")
    public List<ReservaDTO> listarDiariasMaisLongas() {
        return reservaService.listarDiariasMaisLongas();
    }

    @PutMapping("/reservas/{numeroReserva}")
    public ReservaDTO atualizarReserva(@PathVariable Long numeroReserva, @RequestBody ReservaDTO reservaDTO) {
        return reservaService.atualizar(numeroReserva, reservaDTO);
    }

    @DeleteMapping("/reservas/{numeroReserva}")
    public void deletarReserva(@PathVariable Long numeroReserva) {
        reservaService.deletar(numeroReserva);
    }

}
