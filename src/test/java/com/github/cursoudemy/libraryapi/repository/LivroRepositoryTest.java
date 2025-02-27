package com.github.cursoudemy.libraryapi.repository;

import com.github.cursoudemy.libraryapi.model.Autor;
import com.github.cursoudemy.libraryapi.model.GeneroLivro;
import com.github.cursoudemy.libraryapi.model.Livro;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@SpringBootTest
class LivroRepositoryTest {

    @Autowired
    LivroRepository livroRepository;

    @Autowired
    AutorRepository autorRepository;

    @Test
    void salvarTest() {
        Livro livro = new Livro();
        livro.setIsbn("51414-41241");
        livro.setPreco(100.0);
        livro.setTitulo("Livro Test");
        livro.setGenero(GeneroLivro.FICCAO);
        livro.setDataPublicacao(LocalDate.of(2020, 1, 1));

        Autor autor = autorRepository
                .findById(UUID.fromString("1f1a167a-56df-4035-a73d-dd083e350088"))
                .orElse(null);

        livro.setAutor(autor);

        livroRepository.save(livro);
    }

    @Test
    void salvarCascadeTest() {
        Livro livro = new Livro();
        livro.setIsbn("51414-41241");
        livro.setPreco(100.0);
        livro.setTitulo("Livro Test");
        livro.setGenero(GeneroLivro.FICCAO);
        livro.setDataPublicacao(LocalDate.of(2020, 1, 1));

        Autor autor = new Autor();
        autor.setNome("Junior");
        autor.setNacionalidade("Brasileiro");
        autor.setDataNascimento(LocalDate.of(1951, 1, 1));

        livro.setAutor(autor);

        livroRepository.save(livro);
    }

    @Test
    void salvarAutorELivroTest() {
        Livro livro = new Livro();
        livro.setIsbn("51414-41241");
        livro.setPreco(100.0);
        livro.setTitulo("Outro Livro");
        livro.setGenero(GeneroLivro.FICCAO);
        livro.setDataPublicacao(LocalDate.of(2020, 1, 1));

        Autor autor = new Autor();
        autor.setNome("Lula");
        autor.setNacionalidade("Brasileiro");
        autor.setDataNascimento(LocalDate.of(1951, 1, 1));

        autorRepository.save(autor);

        livro.setAutor(autor);

        livroRepository.save(livro);
    }

    @Test
    void atualizarAutorLivro() {
        UUID id = UUID.fromString("ce0541aa-e73f-4845-905a-5cc8d365a57e");
        var  livroParaAtualizar = livroRepository.findById(id).orElse(null);

        UUID idAutor = UUID.fromString("1f1a167a-56df-4035-a73d-dd083e350088");
        Autor maria = autorRepository.findById(idAutor).orElse(null);

        livroParaAtualizar.setAutor(maria);

        livroRepository.save(livroParaAtualizar);
    }

    @Test
    void deletar() {

        UUID id = UUID.fromString("ce0541aa-e73f-4845-905a-5cc8d365a57e");
        var  idLivro = livroRepository.findById(id).orElse(null);

        livroRepository.delete(idLivro);
    }

    @Test
    @Transactional // utilizando a anotação Transactional para abrir uma transação o que vai dar tempo de realizar a consulta do tipo Lazy.
    void buscarLivroTest() {
        UUID id = UUID.fromString("fdee3f9b-6377-4ece-ac14-1c63bbec14a3");
        Livro livro = livroRepository.findById(id).orElse(null);
        System.out.println("Livro:");
        System.out.println(livro.getTitulo());
        //System.out.println("Autor:");
        //System.out.println(livro.getAutor().getNome());
    }

    @Test
    void pesquisarPorTituloTest() {
        List<Livro> listaLivros = livroRepository.findByTitulo("Livro Test");
        listaLivros.forEach(System.out::println);
    }

    @Test
    void pesquisarPorIsbnTest() {
        List<Livro> listaLivros = livroRepository.findByIsbn("51414-41241");
        listaLivros.forEach(System.out::println);
    }

    @Test
    void pesquisarPorTituloAndPrecoTest() {
        var tituloPesquisa = "Livro Test";
        var preco = Double.valueOf(200.0);
        List<Livro> listaLivros = livroRepository.findByTituloAndPreco(tituloPesquisa, preco);
        listaLivros.forEach(System.out::println);
    }

    @Test
    void listarLivrosComQueryJPQL() {
        var resultado = livroRepository.listarTodos();
        resultado.forEach(System.out::println);
    }

    @Test
    void listarAutoresDosLivros() {
        var resultado = livroRepository.listarAutoresDosLivros();
        resultado.forEach(System.out::println);
    }

    @Test
    void listarTitulosNãoRepetidosDosLivros() {
        var resultado = livroRepository.listarNomesDoLivros();
        resultado.forEach(System.out::println);
    }

    @Test
    void listarGenerosDosLivrosDeAutoresBrasileiros() {
        var resultado = livroRepository.listarGenerosAutoresBrasileiros();
        resultado.forEach(System.out::println);
    }
}