package com.github.cursoudemy.libraryapi.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "livro")
@Data
@ToString(exclude = "autor")
public class Livro {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "isbn", length = 20, nullable = false)
    private String isbn;

    @Column(name = "titulo", length = 150, nullable = false)
    private String titulo;

    @Column(name = "data_publicacao")
    private LocalDate dataPublicacao;

    @Enumerated(EnumType.STRING)
    @Column(name = "genero", length = 30, nullable = false)
    private GeneroLivro genero;

    @Column(name = "preco", precision = 12)
    private Double preco;
    //private BigDecimal preco;

    @ManyToOne(
            //cascade = CascadeType.ALL
            fetch = FetchType.LAZY // fetch para informar o tipo de busca se é lazy ou eager, por padrão é eager
    )
    @JoinColumn(name = "id_autor")
    private Autor autor;
}

