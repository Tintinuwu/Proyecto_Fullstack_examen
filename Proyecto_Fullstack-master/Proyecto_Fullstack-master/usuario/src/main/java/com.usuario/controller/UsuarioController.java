package com.cine.usuario.controller;

import com.cine.usuario.dto.UsuarioRequestDTO;
import com.cine.usuario.dto.UsuarioResponseDTO;
import com.cine.usuario.service.UsuarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;

    // GET /api/usuarios → Listar todos los usuarios
    @GetMapping
    public ResponseEntity<List<UsuarioResponseDTO>> obtenerTodos() {
        log.info("GET /api/usuarios - Listando todos los usuarios");
        return ResponseEntity.ok(usuarioService.obtenerTodos());
    }

    // GET /api/usuarios/activos → Listar solo usuarios activos
    @GetMapping("/activos")
    public ResponseEntity<List<UsuarioResponseDTO>> obtenerActivos() {
        log.info("GET /api/usuarios/activos - Listando usuarios activos");
        return ResponseEntity.ok(usuarioService.obtenerActivos());
    }

    // GET /api/usuarios/{id} → Obtener usuario por ID
    @GetMapping("/{id}")
    public ResponseEntity<UsuarioResponseDTO> obtenerPorId(@PathVariable Long id) {
        log.info("GET /api/usuarios/{} - Buscando usuario por ID", id);
        return usuarioService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // POST /api/usuarios → Crear un nuevo usuario
    @PostMapping
    public ResponseEntity<UsuarioResponseDTO> crear(@Valid @RequestBody UsuarioRequestDTO dto) {
        log.info("POST /api/usuarios - Creando nuevo usuario con email: {}", dto.getEmail());
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(usuarioService.crearUsuario(dto));
    }

    // PUT /api/usuarios/{id} → Actualizar datos del usuario
    @PutMapping("/{id}")
    public ResponseEntity<UsuarioResponseDTO> actualizar(
            @PathVariable Long id,
            @Valid @RequestBody UsuarioRequestDTO dto) {
        log.info("PUT /api/usuarios/{} - Actualizando usuario", id);
        return usuarioService.actualizar(id, dto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // DELETE /api/usuarios/{id} → Desactivar usuario
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> eliminar(@PathVariable Long id) {
        log.info("DELETE /api/usuarios/{} - Desactivando usuario", id);
        boolean eliminado = usuarioService.eliminar(id);
        if (!eliminado) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(Map.of("mensaje", "Usuario desactivado correctamente"));
    }

    // GET /api/usuarios/{id}/existe → Validación
    @GetMapping("/{id}/existe")
    public ResponseEntity<Map<String, Boolean>> existeUsuario(@PathVariable Long id) {
        log.info("GET /api/usuarios/{}/existe - Verificando existencia para MS-Reservas", id);
        return ResponseEntity.ok(Map.of("existe", usuarioService.existeUsuario(id)));
    }
}