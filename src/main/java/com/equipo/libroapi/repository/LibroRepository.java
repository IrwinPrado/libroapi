package com.equipo.libroapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.equipo.libroapi.model.Libro;

public interface LibroRepository extends JpaRepository<Libro, Long> {
}
