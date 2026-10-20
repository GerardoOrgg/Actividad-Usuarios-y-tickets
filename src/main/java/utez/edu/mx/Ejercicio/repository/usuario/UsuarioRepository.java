package utez.edu.mx.Ejercicio.repository.usuario;


import org.springframework.data.jpa.repository.JpaRepository;
import utez.edu.mx.Ejercicio.model.Usuario.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

}