package com.github.cursoudemy.libraryapi.repository;

import com.github.cursoudemy.libraryapi.model.Autor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

@SpringBootTest
public class AutorRepositoryTest {

    @Autowired
    AutorRepository autorRepository;

    @Test
    public void salvarTest() {
        Autor autor = new Autor();
        autor.setNome("Maria");
        autor.setNacionalidade("Brasileiro");
        autor.setDataNascimento(LocalDate.of(1951, 1, 1));

        var autorSalvo = autorRepository.save(autor);

        System.out.println("Autor salvo: " + autorSalvo);
    }

    @Test
    public void autalizarTest() {
        var id = UUID.fromString("3b68f3a2-9a1f-43ff-b72e-ef3de5a47ab2");

        Optional<Autor> possivelAutor = autorRepository.findById(id);

        if(possivelAutor.isPresent()) {

            Autor autorEncontrado = possivelAutor.get();
            System.out.println("Autor encontrado: " + autorEncontrado);
            System.out.println(autorEncontrado);

            autorEncontrado.setDataNascimento(LocalDate.of(2003, 3, 3));

            autorRepository.save(autorEncontrado);

        }

    }

}
