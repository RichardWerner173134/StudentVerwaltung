package com.example.testDatabase.demotest.Enricher;

import com.example.testDatabase.demotest.Entities.Course;
import com.example.testDatabase.demotest.Repositories.CourseRepository;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class CourseEnricher {
    private int calculatedCourseNr;

    @Autowired
    private CourseRepository courseRepository;

    /**
     * @param subject
     * @return neue courseNr (größte existierende CourseNr diese Fachs + 1)
     */
    public int generateCourseNr(String subject) throws Exception {
        List<Course> courses = new ArrayList<>();
        try{
            courses = courseRepository.findAll();
        } catch(Exception e){
            throw new Exception("No Repository found");
        }

        int maxExistingNr = 0;
        for(int i = 0; i < courses.size(); i++){
            if(courses.get(i).getSubject().getSubjectName().equals(subject)){
                maxExistingNr = checkCourseNr(courses.get(i).getCourseNr(), maxExistingNr);
            }
        }
        calculatedCourseNr = maxExistingNr + 1;
        return maxExistingNr + 1;
    }

    /**
     * @param subject
     * @param courseNr
     * @return subject + courseNr
     */
    public String generateCourseName(String subject, int courseNr){
        return subject + courseNr;
    }

    /**
     * @param subjectName
     * @return
     */
    public String generateCourseId(String subjectName){
        List<Course> courses;
        try{
            courses = courseRepository.findAll();
        } catch (Exception e){
            return "0";
        }

        int maxCourseId = -1;
        for(int i = 0; i < courses.size(); i++){
            if(Integer.parseInt(courses.get(i).getId()) > maxCourseId){
                maxCourseId = Integer.parseInt(courses.get(i).getId());
            }
        }
        return String.valueOf(maxCourseId + 1);
    }
    /**
     * @param courseNr
     * @param maxExistingNr
     * @return größeren Wert von courseNr und maxNr
     */
    private int checkCourseNr(int courseNr, int maxExistingNr){
        return Math.max(courseNr, maxExistingNr);
    }
}
