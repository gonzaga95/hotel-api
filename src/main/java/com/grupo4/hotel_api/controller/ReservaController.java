package com.grupo4.hotel_api.controller;

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

}
