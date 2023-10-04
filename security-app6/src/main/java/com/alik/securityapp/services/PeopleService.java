package com.alik.securityapp.services;

import com.alik.securityapp.models.Person;
import com.alik.securityapp.repositories.PeopleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class PeopleService {
    private final PeopleRepository peopleRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public PeopleService(PeopleRepository peopleRepository, PasswordEncoder passwordEncoder) {
        this.peopleRepository = peopleRepository;
        this.passwordEncoder = passwordEncoder;
    }


    public void save(Person person){
        person.setPassword(passwordEncoder.encode(person.getPassword()));
        peopleRepository.save(person);
    }

    public Optional<Person> findByUsername(String username){
        return peopleRepository.findByUsername(username);
    }
}
