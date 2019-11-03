package com.example.testDatabase.demotest.Enricher;

import com.example.testDatabase.demotest.Entities.Subject;
import com.example.testDatabase.demotest.Repositories.SubjectRepository;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class SubjectEnricher {
    @Autowired
    private SubjectRepository subjectRepository;

    /**
     * @param subjectName
     * @return
     * @throws Exception
     */
    public String generateSubjectId(String subjectName) throws Exception {
        List<Subject> subjects;
        try{
            subjects = subjectRepository.findAll();
        } catch(Exception e){
            throw new Exception("No Repository found");
        }

        int maxExistingSubjectId = -1;
        for(int i = 0; i < subjects.size(); i++){
            if(Integer.parseInt(subjects.get(i).getId()) > maxExistingSubjectId){
                maxExistingSubjectId = Integer.parseInt(subjects.get(i).getId());
            }
        }
        return String.valueOf(maxExistingSubjectId + 1);
    }
}
