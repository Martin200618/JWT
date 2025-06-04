package com.sena.segurity.Controller;

import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.sena.segurity.DTO.pageDTO;
import com.sena.segurity.Service.pageService;

@CrossOrigin(origins = {"http://127.0.0.1:5500", "http://localhost:5500"}) // Permite solicitudes desde estos orígenes
@RestController
@RequestMapping("api/v1/pages")
public class pageController {

    @Autowired
    private pageService pageService;

    // Registrar una nueva página
    @PostMapping("/")
    public ResponseEntity<Object> registerPage(@RequestBody pageDTO pageDTO) {
        String response = pageService.save(pageDTO);
        boolean isSuccessful = response.startsWith("200");

        return new ResponseEntity<>(
            Map.of(
                "message", response,
                "status", isSuccessful ? "success" : "error"
            ),
            isSuccessful ? HttpStatus.OK : HttpStatus.BAD_REQUEST
        );
    }

    // Obtener todas las páginas
    @GetMapping("/")
    public ResponseEntity<Object> getAllPages() {
        try {
            return new ResponseEntity<>(
                pageService.findAll(),
                HttpStatus.OK
            );
        } catch (Exception e) {
            return new ResponseEntity<>(
                Map.of("message", "Error al obtener las páginas", "error", e.getMessage()),
                HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }

    // Obtener una página por ID
    @GetMapping("/get/{pageId}")
    public ResponseEntity<Object> getOnePage(@PathVariable("pageId") int pageId) {
        Optional<Object> page = Optional.ofNullable(pageService.findById(pageId));
        return page.map(value -> new ResponseEntity<>(
            value,
            HttpStatus.OK
        )).orElseGet(() -> new ResponseEntity<>(
            Map.of("message", "Página no encontrada"),
            HttpStatus.NOT_FOUND
        ));
    }

    // Actualizar una página por ID
    @PostMapping("/update/{pageId}")
    public ResponseEntity<Object> updatePage(@PathVariable("pageId") int pageId, @RequestBody pageDTO pageDTO) {
        try {
            String response = pageService.update(pageId, pageDTO);
            return new ResponseEntity<>(
                Map.of(
                    "message", response,
                    "status", response.startsWith("200") ? "success" : "error"
                ),
                response.startsWith("200") ? HttpStatus.OK : HttpStatus.BAD_REQUEST
            );
        } catch (Exception e) {
            return new ResponseEntity<>(
                Map.of("message", "Error al actualizar la página", "error", e.getMessage()),
                HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }

    // Eliminar una página por ID
    @DeleteMapping("/delete/{pageId}")
    public ResponseEntity<Object> deletePage(@PathVariable("pageId") int pageId) {
        try {
            String response = pageService.delete(pageId);
            return new ResponseEntity<>(
                Map.of(
                    "message", response,
                    "status", response.startsWith("200") ? "success" : "error"
                ),
                response.startsWith("200") ? HttpStatus.OK : HttpStatus.BAD_REQUEST
            );
        } catch (Exception e) {
            return new ResponseEntity<>(
                Map.of("message", "Error al eliminar la página", "error", e.getMessage()),
                HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }
}
