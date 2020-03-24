package guru.springframework.spring5webapp.bootstrap;

import guru.springframework.spring5webapp.domain.Author;
import guru.springframework.spring5webapp.domain.Book;
import guru.springframework.spring5webapp.domain.Publisher;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import guru.springframework.spring5webapp.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootstrapData implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;

    public BootstrapData(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        Publisher mcg = new Publisher("McGraw-Hill", "Big Street 1", "Los Alamos", "79321");
        publisherRepository.save(mcg);

        Author eric = new Author("Eric", "Evans");
        Book ddd = new Book("Domain Driven Design", "1234567");

        //assign one to the other (adding to their HashSets)
        eric.getBooks().add(ddd);
        ddd.getAuthors().add(eric);

        ddd.setPublisher(mcg);
        mcg.getBooks().add(ddd);

        //saving both entities into their respective repository (= saving into the H2 Database)
        authorRepository.save(eric);
        bookRepository.save(ddd);
        publisherRepository.save(mcg);

        //same:
        Author rod = new Author("Rod", "Johnson");
        Book noEJB = new Book("J2EE Development without EJB", "65465321");
        rod.getBooks().add(noEJB);
        noEJB.getAuthors().add(rod);

        noEJB.setPublisher(mcg);
        mcg.getBooks().add(noEJB);

        authorRepository.save(rod);
        bookRepository.save(noEJB);
        publisherRepository.save(mcg);

        System.out.println("Started in bootstrap");
        System.out.println("Number of authors: " + authorRepository.count());
        System.out.println("Publishers: " + publisherRepository.findAll().toString());
        System.out.println("Books published by McGraw-Hill: " + mcg.getBooks().size());

    }
}
