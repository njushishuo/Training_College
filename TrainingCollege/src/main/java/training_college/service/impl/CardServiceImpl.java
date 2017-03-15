package training_college.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import training_college.entity.Student;
import training_college.repository.StudentRepository;
import training_college.service.CardService;

/**
 * Created by ss14 on 2017/3/15.
 */

@Service
public class CardServiceImpl implements CardService {

    @Autowired
    StudentRepository studentRepository ;


    @Override
    public Student getStudentById(int id) {
        return studentRepository.getOne(id);
    }
}
