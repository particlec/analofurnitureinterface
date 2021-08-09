package com.example.analofurnitureinterface.service;

import com.example.analofurnitureinterface.dao.PersonRepository;
import com.example.analofurnitureinterface.entity.Person;
import com.example.analofurnitureinterface.pojo.PersonVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class PersonService {
    @Autowired
    PersonRepository personRepository;


    public void createPerson(PersonVO personVO){
        Person person = new Person();
        PersonVO personVO1 = new PersonVO();
        personVO1.setId(5173L);
        personVO1.setName("particlec");
        personVO1.setAge(18);
        personVO1.setAddress("广州");
        BeanUtils.copyProperties(personVO1, person);
        personRepository.save(person);
    }

}
