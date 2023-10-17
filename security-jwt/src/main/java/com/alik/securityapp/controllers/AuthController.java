package com.alik.securityapp.controllers;

import com.alik.securityapp.dto.AuthenticationDTO;
import com.alik.securityapp.dto.PersonDTO;
import com.alik.securityapp.models.Person;
import com.alik.securityapp.security.JWTUtil;
import com.alik.securityapp.services.PeopleService;
import com.alik.securityapp.util.PersonValidator;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final PersonValidator personValidator;
    private final PeopleService peopleService;
    private final JWTUtil jwtUtil;
    private final ModelMapper mapper;
    private final AuthenticationManager authenticationManager;

    public AuthController(PersonValidator personValidator, PeopleService peopleService, JWTUtil jwtUtil, ModelMapper mapper, AuthenticationManager authenticationManager) {
        this.personValidator = personValidator;
        this.peopleService = peopleService;
        this.jwtUtil = jwtUtil;
        this.mapper = mapper;
        this.authenticationManager = authenticationManager;
    }

//    @GetMapping("/login")
//    public String login() {
//        return "/auth/login";
//    }
//
//    @GetMapping("/registration")
//    public String registrationPage(@ModelAttribute("person") Person person) {
//        return "/auth/registration";
//    }



    @PostMapping("/login")
    public Map<String,String> performLogin(@RequestBody AuthenticationDTO authenticationDTO){
        UsernamePasswordAuthenticationToken authToken =
                new UsernamePasswordAuthenticationToken(authenticationDTO.getUsername(),
                        authenticationDTO.getPassword());
       try {
           authenticationManager.authenticate(authToken);
       }catch (BadCredentialsException e){
return Map.of("message", "Incorrect credentials");
       }

       String token = jwtUtil.generateToken(authenticationDTO.getUsername());
        return Map.of("jwt-token",token);
    }

    @PostMapping("/registration")
    public Map<String,String> performRegistration(@RequestBody @Valid PersonDTO personDTO,
                                      BindingResult bindingResult) {
        Person person = mapper.map(personDTO, Person.class);
        personValidator.validate(person, bindingResult);
        if (bindingResult.hasErrors()) {
            return Map.of("message","Registration error");
        }
        peopleService.save(person);

        String token = jwtUtil.generateToken(person.getUsername());
        return Map.of("jwt-token",token);
    }
}
