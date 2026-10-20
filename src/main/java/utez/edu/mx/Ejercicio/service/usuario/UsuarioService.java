package utez.edu.mx.Ejercicio.service.usuario;


import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import utez.edu.mx.Ejercicio.controller.usuario.dto.RequestCrearUsuarioDTO;
import utez.edu.mx.Ejercicio.exception.CustomNotFoundException;
import utez.edu.mx.Ejercicio.model.Usuario.Usuario;
import utez.edu.mx.Ejercicio.repository.usuario.UsuarioRepository;

import java.util.List;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public Usuario crear(RequestCrearUsuarioDTO payload) {
        Usuario usuario = new Usuario();
        usuario.setNombre(payload.getNombre());
        usuario.setActivo(payload.getActivo() == null ? true : payload.getActivo());
        return usuarioRepository.save(usuario);
    }

    public List<Usuario> listar() {
        return usuarioRepository.findAll();
    }

    public Usuario obtenerPorId(Long id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new CustomNotFoundException("No existe un usuario con id " + id));
    }

    @Transactional
    public Usuario desactivarUsuario(Long id) {
        Usuario usuario = obtenerPorId(id);
        usuario.setActivo(false);
        return usuarioRepository.save(usuario);
    }
}