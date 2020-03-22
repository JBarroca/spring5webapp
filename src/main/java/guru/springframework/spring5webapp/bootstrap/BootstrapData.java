package guru.springframework.spring5webapp.bootstrap;

import guru.springframework.spring5webapp.domain.Author;
import guru.springframework.spring5webapp.domain.Book;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootstrapData implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;

    public BootstrapData(AuthorRepository authorRepository, BookRepository bookRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        //initialize data
        Author eric = new Author("Eric", "Evans");
        Book ddd = new Book("Domain Driven Design", "1234567");
        //assign one to the other (adding to their HashSets)
        eric.getBooks().add(ddd);
        ddd.getAuthors().add(eric);
        //saving both entities into their respective repository (= saving into the H2 Database)
        authorRepository.save(eric);
        bookRepository.save(ddd);

        //same:
        Author rod = new Author("Rod", "Johnson");
        Book noEJB = new Book("J2EE Development without EJB", "65465321");
        rod.getBooks().add(noEJB);
        noEJB.getAuthors().add(rod);
        authorRepository.save(rod);
        bookRepository.save(noEJB);

        System.out.println("Started in bootstrap");
        System.out.println("Number of authors: " + authorRepository.count());
    }
}
