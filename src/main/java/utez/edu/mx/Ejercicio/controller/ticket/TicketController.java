package utez.edu.mx.Ejercicio.controller.ticket;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utez.edu.mx.Ejercicio.controller.ticket.dto.RequestCrearTicketDTO;
import utez.edu.mx.Ejercicio.controller.ticket.dto.RequestPrioridadDTO;
import utez.edu.mx.Ejercicio.controller.ticket.dto.ResponseTicketDTO;
import utez.edu.mx.Ejercicio.service.ticket.TicketService;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/ticket")
public class TicketController {

    private final TicketService ticketService;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @PostMapping
    public ResponseEntity<ResponseTicketDTO> crear(@RequestBody @Valid RequestCrearTicketDTO payload) {
        return ResponseEntity.status(201)
                .body(ResponseTicketDTO.desde(ticketService.crearTicket(payload)));
    }

    @PatchMapping("/{id}/estado")
    public ResponseEntity<ResponseTicketDTO> actualizarEstado(@PathVariable Long id) {
        return ResponseEntity.status(200)
                .body(ResponseTicketDTO.desde(ticketService.actualizarEstadoTicket(id)));
    }

    @PatchMapping("/{id}/prioridad")
    public ResponseEntity<ResponseTicketDTO> reasignarPrioridad(@PathVariable Long id,
                                                                @RequestBody @Valid RequestPrioridadDTO payload) {
        return ResponseEntity.status(200).body(
                ResponseTicketDTO.desde(ticketService.reasignarPrioridad(id, payload.getPrioridad())));
    }

    @GetMapping
    public ResponseEntity<List<ResponseTicketDTO>> listar() {
        return ResponseEntity.status(200).body(
                ticketService.listar().stream().map(ResponseTicketDTO::desde).toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseTicketDTO> obtener(@PathVariable Long id) {
        return ResponseEntity.status(200)
                .body(ResponseTicketDTO.desde(ticketService.obtenerPorId(id)));
    }
}