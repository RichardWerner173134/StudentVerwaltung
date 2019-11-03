package com.example.testDatabase.demotest.Enricher;

import com.example.testDatabase.demotest.Entities.Student;
import com.example.testDatabase.demotest.Repositories.StudentRepository;
import lombok.Builder;
import java.util.ArrayList;
import java.util.List;

@Builder
public class StudentEnricher {
    /**
     * @return
     * @throws Exception
     */
    public String generateId(StudentRepository studentRepository) throws Exception {
        List<Student> students = new ArrayList<>();
        try{
            students = studentRepository.findAll();
        } catch (Exception e){
            throw new Exception("Repository not found");
        }
        int maxExistingStudentId = -1;
        for(int i = 0; i < students.size(); i++){
            if(Integer.parseInt(students.get(i).getId()) > maxExistingStudentId){
                maxExistingStudentId = Integer.parseInt(students.get(i).getId());
            }
        }

        return String.valueOf(maxExistingStudentId + 1);
    }
}
