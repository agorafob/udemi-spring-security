package com.alik.securityapp.services;

import com.alik.securityapp.models.Person;
import com.alik.securityapp.repositories.PeopleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class PeopleService {
    private final PeopleRepository peopleRepository;

    @Autowired
    public PeopleService(PeopleRepository peopleRepository) {
        this.peopleRepository = peopleRepository;
    }


    public void save(Person person){
        peopleRepository.save(person);
    }

    public Optional<Person> findByUsername(String username){
        return peopleRepository.findByUsername(username);
    }
}
