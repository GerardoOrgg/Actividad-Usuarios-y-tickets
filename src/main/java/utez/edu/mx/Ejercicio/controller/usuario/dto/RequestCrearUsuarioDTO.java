package utez.edu.mx.Ejercicio.controller.usuario.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RequestCrearUsuarioDTO {
    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;

    private Boolean activo;
}
