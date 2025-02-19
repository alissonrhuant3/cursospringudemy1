package com.github.cursoudemy.libraryapi;

import com.github.cursoudemy.libraryapi.model.Autor;
import com.github.cursoudemy.libraryapi.repository.AutorRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;

@SpringBootApplication
public class LibraryapiApplication {

	public static void main(String[] args) {
		var context = SpringApplication.run(LibraryapiApplication.class, args);
		AutorRepository repository = context.getBean(AutorRepository.class);

		exemploSalvarRegistro(repository);
	}

	public static void exemploSalvarRegistro(AutorRepository autorRepository) {
		Autor autor = new Autor();
		autor.setNome("João Silva");
		autor.setNacionalidade("Brasileiro");
		autor.setDataNascimento(LocalDate.of(1990, 1, 1));

		var autorSalvo = autorRepository.save(autor);

		System.out.println("Autor salvo: " + autorSalvo);
	}
}
