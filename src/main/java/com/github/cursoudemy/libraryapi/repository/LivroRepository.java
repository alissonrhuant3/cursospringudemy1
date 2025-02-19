package com.github.cursoudemy.libraryapi.repository;

import com.github.cursoudemy.libraryapi.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface LivroRepository extends JpaRepository<Livro, UUID> {
}
