package utez.edu.mx.Ejercicio.controller.ticket.dto;


import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import utez.edu.mx.Ejercicio.model.Ticket.Prioridad;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RequestPrioridadDTO {
    @NotNull(message = "La prioridad es obligatoria (BAJA, MEDIA o ALTA)")
    private Prioridad prioridad;
}
