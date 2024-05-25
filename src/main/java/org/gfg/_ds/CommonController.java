package org.gfg._ds;

import jdk.jfr.Percentage;
import org.aspectj.lang.annotation.Around;
import org.gfg._ds.Author.Author;
import org.gfg._ds.Person.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1")
public class CommonController {

    @Autowired
    private CommonService commonService;

    @PostMapping("/addPerson")
    public Person addPerson(@RequestBody Person person){
        return commonService.addPerson(person);

    }

    @PostMapping("/addAuthor")
    public Author addAuthor(@RequestBody Author author){
        return  commonService.addAuthor(author);
    }
}

//@Transactional?
