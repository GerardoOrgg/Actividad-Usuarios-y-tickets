package utez.edu.mx.Ejercicio.repository.ticket;


import org.springframework.data.jpa.repository.JpaRepository;
import utez.edu.mx.Ejercicio.model.Ticket.Ticket;

import java.util.List;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
    List<Ticket> findByUsuarioId(Long usuarioId);
}