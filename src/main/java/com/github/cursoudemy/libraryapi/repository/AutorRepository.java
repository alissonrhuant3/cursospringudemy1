package com.github.cursoudemy.libraryapi.repository;

import com.github.cursoudemy.libraryapi.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AutorRepository extends JpaRepository<Autor, UUID> {
}
