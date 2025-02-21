package com.github.cursoudemy.libraryapi.repository;

import com.github.cursoudemy.libraryapi.model.Autor;
import com.github.cursoudemy.libraryapi.model.GeneroLivro;
import com.github.cursoudemy.libraryapi.model.Livro;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@SpringBootTest
public class AutorRepositoryTest {

    @Autowired
    AutorRepository autorRepository;

    @Autowired
    LivroRepository livroRepository;

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

    @Test
    public void listarTest() {
        List<Autor> lista = autorRepository.findAll();
        lista.forEach(System.out::println);
    }

    @Test
    public void countTest() {
        System.out.println("Contagem de autores: " + autorRepository.count());
    }

    @Test
    public void deletePorIdTest() {
        var id = UUID.fromString("3b68f3a2-9a1f-43ff-b72e-ef3de5a47ab2");

        autorRepository.deleteById(id);
    }

    @Test
    public void deleteTest() {
        var id = UUID.fromString("bb5d57e9-828d-4345-9591-17adf12997a2");
        var maria = autorRepository.findById(id).get();
        autorRepository.delete(maria);
    }

    @Test
    void salvarAutorComLivrosTest() {
        Autor autor = new Autor();
        autor.setNome("Lucas");
        autor.setNacionalidade("Americano");
        autor.setDataNascimento(LocalDate.of(1951, 1, 1));

        Livro livro = new Livro();
        livro.setIsbn("51414-41241");
        livro.setPreco(200.0);
        livro.setTitulo("Desenvolvendo Java");
        livro.setGenero(GeneroLivro.CIENCIA);
        livro.setDataPublicacao(LocalDate.of(2025, 1, 1));
        livro.setAutor(autor);

        Livro livro2 = new Livro();
        livro2.setIsbn("51414-41241");
        livro2.setPreco(90.0);
        livro2.setTitulo("Desenvolvendo NodeJS");
        livro2.setGenero(GeneroLivro.CIENCIA);
        livro2.setDataPublicacao(LocalDate.of(2025, 1, 1));
        livro2.setAutor(autor);


        autor.setLivros(new ArrayList<>());
        autor.getLivros().add(livro);
        autor.getLivros().add(livro2);

        autorRepository.save(autor);

//        livroRepository.saveAll(autor.getLivros()); Salvando sem CASCADE.
    }

}
