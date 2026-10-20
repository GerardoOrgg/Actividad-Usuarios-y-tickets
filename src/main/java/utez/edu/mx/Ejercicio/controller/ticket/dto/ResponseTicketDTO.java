package utez.edu.mx.Ejercicio.controller.ticket.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import utez.edu.mx.Ejercicio.model.Ticket.Estado;
import utez.edu.mx.Ejercicio.model.Ticket.Prioridad;
import utez.edu.mx.Ejercicio.model.Ticket.Ticket;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResponseTicketDTO {
    private Long id;
    private String descripcion;
    private Estado estado;
    private Prioridad prioridad;
    private Long usuarioId;

    public static ResponseTicketDTO desde(Ticket t) {
        return new ResponseTicketDTO(t.getId(), t.getDescripcion(), t.getEstado(),
                t.getPrioridad(), t.getUsuario().getId());
    }
}