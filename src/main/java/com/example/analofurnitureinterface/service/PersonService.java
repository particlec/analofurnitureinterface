package com.example.analofurnitureinterface.service;

import com.example.analofurnitureinterface.dao.PersonRepository;
import com.example.analofurnitureinterface.entity.Person;
import com.example.analofurnitureinterface.pojo.PersonVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.function.Function;

@Slf4j
@Service
public class PersonService {
    @Autowired
    PersonRepository personRepository;

//    public void createPerson() {
//        Person person = new Person();
//        PersonVO personVO1 = new PersonVO();
//        personVO1.setId(51735L);
//        personVO1.setName("particle");
//        personVO1.setAge(19);
//        personVO1.setAddress("上海");
//        personVO1.setCreateTime(new Date());
//        BeanUtils.copyProperties(personVO1, person);
//        personRepository.save(person);
//    }

    public Page<PersonVO> queryPerson() {
        Pageable pageable = new Pageable() {
            @Override
            public int getPageNumber() {
                return 0;
            }

            @Override
            public int getPageSize() {
                return 4;
            }

            @Override
            public long getOffset() {
                return 0;
            }

            @Override
            public Sort getSort() {
                return null;
            }

            @Override
            public Pageable next() {
                return null;
            }

            @Override
            public Pageable previousOrFirst() {
                return null;
            }

            @Override
            public Pageable first() {
                return null;
            }

            @Override
            public Pageable withPage(int i) {
                return null;
            }

            @Override
            public boolean hasPrevious() {
                return false;
            }
        };
        Date startDate = new Date(1610517360000L);
        Date endDate = new Date(1634104560000L);
        Specification<Person> specification = (root, query, builder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (startDate != null && endDate != null && startDate.before(endDate)) {
                predicates.add(
                        builder.greaterThanOrEqualTo(root.get("createTime").as(Date.class), startDate));
                predicates.add(
                        builder.lessThanOrEqualTo(root.get("createTime").as(Date.class), endDate));
            }
            Predicate[] p = new Predicate[predicates.size()];
            return builder.and(predicates.toArray(p));
        };

        Page<Person> people = personRepository.findAll(specification, pageable);
        log.info(String.valueOf(people));
        return people.map(binFile -> new PersonVO(binFile.getId(),binFile.getName(), binFile.getAge(),binFile.getAddress(),
                 binFile.getCreateTime()));

    }
}
