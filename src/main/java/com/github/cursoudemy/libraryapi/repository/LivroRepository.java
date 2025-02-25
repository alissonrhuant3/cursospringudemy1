package com.github.cursoudemy.libraryapi.repository;

import com.github.cursoudemy.libraryapi.model.Autor;
import com.github.cursoudemy.libraryapi.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface LivroRepository extends JpaRepository<Livro, UUID> {

    // Query Method
    List<Livro> findByAutor(Autor autor);

    List<Livro> findByTitulo(String titulo);

    List<Livro> findByIsbn(String isbn);

    List<Livro> findByTituloAndPreco(String titulo, Double preco);

    List<Livro> findByTituloOrIsbn(String titulo, Double preco);

    List<Livro> findByDataPublicacaoBetween(LocalDate inicio, LocalDate fim);

    List<Livro> findByTituloLike(String titulo);
}
