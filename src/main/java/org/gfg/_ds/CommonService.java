package org.gfg._ds;

import org.gfg._ds.Author.AuthorRepository;
import org.gfg._ds.Author.Author;
import org.gfg._ds.Person.Person;
import org.gfg._ds.Person.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommonService {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private AuthorRepository authorRepository;

    public Person addPerson(Person person) {
        return personRepository.save(person);
    }

    public Author addAuthor(Author author) {
        return authorRepository.save(author);
    }
}
