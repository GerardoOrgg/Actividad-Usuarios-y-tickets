package utez.edu.mx.Ejercicio.service.ticket;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import utez.edu.mx.Ejercicio.controller.ticket.dto.RequestCrearTicketDTO;
import utez.edu.mx.Ejercicio.exception.CustomBadRequestException;
import utez.edu.mx.Ejercicio.exception.CustomConflictException;
import utez.edu.mx.Ejercicio.exception.CustomNotFoundException;
import utez.edu.mx.Ejercicio.model.Ticket.Estado;
import utez.edu.mx.Ejercicio.model.Ticket.Prioridad;
import utez.edu.mx.Ejercicio.model.Ticket.Ticket;
import utez.edu.mx.Ejercicio.model.Usuario.Usuario;
import utez.edu.mx.Ejercicio.repository.ticket.TicketRepository;
import utez.edu.mx.Ejercicio.service.usuario.UsuarioService;

import java.util.List;

@Service
public class TicketService {

    private final TicketRepository ticketRepository;
    private final UsuarioService usuarioService;

    public TicketService(TicketRepository ticketRepository, UsuarioService usuarioService) {
        this.ticketRepository = ticketRepository;
        this.usuarioService = usuarioService;
    }


    @Transactional
    public Ticket crearTicket(RequestCrearTicketDTO payload) {
        Usuario usuario = usuarioService.obtenerPorId(payload.getUsuarioId());

        if (!Boolean.TRUE.equals(usuario.getActivo())) {
            throw new CustomBadRequestException("El usuario con id " + usuario.getId() + " no está activo");
        }
        if (payload.getDescripcion() == null || payload.getDescripcion().trim().isEmpty()) {
            throw new CustomBadRequestException("La descripción no puede estar vacía");
        }

        Ticket ticket = new Ticket();
        ticket.setUsuario(usuario);
        ticket.setDescripcion(payload.getDescripcion().trim());
        ticket.setPrioridad(payload.getPrioridad());
        ticket.setEstado(Estado.ABIERTO);
        return ticketRepository.save(ticket);
    }


    @Transactional
    public Ticket actualizarEstadoTicket(Long id) {
        Ticket ticket = obtenerPorId(id);

        switch (ticket.getEstado()) {
            case ABIERTO -> ticket.setEstado(Estado.EN_PROCESO);
            case EN_PROCESO -> ticket.setEstado(Estado.CERRADO);
            case CERRADO -> throw new CustomConflictException(
                    "El ticket " + id + " ya está CERRADO y no se puede modificar");
        }
        return ticketRepository.save(ticket);
    }

    @Transactional
    public Ticket reasignarPrioridad(Long id, Prioridad nuevaPrioridad) {
        Ticket ticket = obtenerPorId(id);

        if (ticket.getEstado() == Estado.CERRADO) {
            throw new CustomConflictException(
                    "El ticket " + id + " está CERRADO, no se puede cambiar su prioridad");
        }
        ticket.setPrioridad(nuevaPrioridad);
        return ticketRepository.save(ticket);
    }


    public List<Ticket> obtenerTicketsUsuario(Long usuarioId) {
        usuarioService.obtenerPorId(usuarioId);
        return ticketRepository.findByUsuarioId(usuarioId);
    }

    public List<Ticket> listar() {
        return ticketRepository.findAll();
    }

    public Ticket obtenerPorId(Long id) {
        return ticketRepository.findById(id)
                .orElseThrow(() -> new CustomNotFoundException("No existe un ticket con id " + id));
    }
}