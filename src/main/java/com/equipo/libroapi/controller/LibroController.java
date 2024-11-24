package com.equipo.libroapi.controller;

import com.equipo.libroapi.model.Libro;
import com.equipo.libroapi.repository.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/Libros") 
public class LibroController {

    @Autowired
    private LibroRepository libroRepository;

    // Crear un libro
    @PostMapping
    public ResponseEntity<Libro> crearLibro(@RequestBody Libro libro) {
        Libro nuevoLibro = libroRepository.save(libro);
        return new ResponseEntity<>(nuevoLibro, HttpStatus.CREATED);
    }

    // Obtener un libro por ID
    @GetMapping("/{id}")
    public ResponseEntity<Libro> obtenerLibroPorId(@PathVariable Long id) {
        Optional<Libro> libro = libroRepository.findById(id);
        return libro.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Listar todos los libros
    @GetMapping("/Listado")
    public List<Libro> listarLibros() {
        return libroRepository.findAll();
    }

    // Actualizar un libro
    @PutMapping("/modifica/{id}")
    public ResponseEntity<Libro> actualizarLibro(@PathVariable Long id, @RequestBody Libro libro) {
        if (!libroRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        libro.setId();
        Libro libroActualizado = libroRepository.save(libro);
        return ResponseEntity.ok(libroActualizado);
    }

    // Eliminar un libro
    @DeleteMapping("/elimina/{id}")
    public ResponseEntity<Void> eliminarLibro(@PathVariable Long id) {
        if (!libroRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        libroRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}