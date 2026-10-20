package utez.edu.mx.Ejercicio.controller.usuario.dto;

import lombok.*;
import utez.edu.mx.Ejercicio.model.Usuario.Usuario;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResponseUsuarioDTO {
    private Long id;
    private String nombre;
    private Boolean activo;

    public static ResponseUsuarioDTO desde(Usuario u) {
        return new ResponseUsuarioDTO(u.getId(), u.getNombre(), u.getActivo());
    }
}