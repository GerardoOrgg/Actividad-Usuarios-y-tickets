package utez.edu.mx.Ejercicio.controller.usuario;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utez.edu.mx.Ejercicio.controller.ticket.dto.ResponseTicketDTO;
import utez.edu.mx.Ejercicio.controller.usuario.dto.RequestCrearUsuarioDTO;
import utez.edu.mx.Ejercicio.controller.usuario.dto.ResponseUsuarioDTO;
import utez.edu.mx.Ejercicio.service.ticket.TicketService;
import utez.edu.mx.Ejercicio.service.usuario.UsuarioService;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/usuario")
public class UsuarioController {

    private final UsuarioService usuarioService;
    private final TicketService ticketService;

    public UsuarioController(UsuarioService usuarioService, TicketService ticketService) {
        this.usuarioService = usuarioService;
        this.ticketService = ticketService;
    }

    @PostMapping
    public ResponseEntity<ResponseUsuarioDTO> crear(@RequestBody @Valid RequestCrearUsuarioDTO payload) {
        return ResponseEntity.status(201)
                .body(ResponseUsuarioDTO.desde(usuarioService.crear(payload)));
    }

    @GetMapping
    public ResponseEntity<List<ResponseUsuarioDTO>> listar() {
        return ResponseEntity.status(200).body(
                usuarioService.listar().stream().map(ResponseUsuarioDTO::desde).toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseUsuarioDTO> obtener(@PathVariable Long id) {
        return ResponseEntity.status(200)
                .body(ResponseUsuarioDTO.desde(usuarioService.obtenerPorId(id)));
    }

    @PatchMapping("/{id}/desactivar")
    public ResponseEntity<ResponseUsuarioDTO> desactivar(@PathVariable Long id) {
        return ResponseEntity.status(200)
                .body(ResponseUsuarioDTO.desde(usuarioService.desactivarUsuario(id)));
    }

    @GetMapping("/{id}/tickets")
    public ResponseEntity<List<ResponseTicketDTO>> tickets(@PathVariable Long id) {
        return ResponseEntity.status(200).body(
                ticketService.obtenerTicketsUsuario(id).stream().map(ResponseTicketDTO::desde).toList());
    }
}
