package com.example.testDatabase.demotest.Enricher

import com.example.testDatabase.demotest.Entities.Course
import com.example.testDatabase.demotest.Entities.Subject
import com.example.testDatabase.demotest.Repositories.CourseRepository
import com.example.testDatabase.demotest.Services.CourseService
import lombok.AllArgsConstructor
import lombok.NoArgsConstructor
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentCaptor
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.invocation.InvocationOnMock
import org.mockito.stubbing.Answer
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

import static org.mockito.ArgumentMatchers.any
import static org.mockito.ArgumentMatchers.anyString
import static org.mockito.Mockito.lenient
import static org.mockito.Mockito.when

@AllArgsConstructor
@NoArgsConstructor
@SpringBootTest
@RunWith(org.mockito.runners.MockitoJUnitRunner.class)
class CourseEnricherTest extends GroovyTestCase {
    @InjectMocks
    private CourseEnricher courseEnricher = CourseEnricher.builder().build()
    @InjectMocks
    private CourseService courseService = CourseService.builder().build()
    @Mock
    @Autowired
    private CourseRepository courseRepository
    private ArrayList<Course> courseMock = new ArrayList<>()

    @Before
    void init(){
        when(courseRepository.findAll()).thenReturn(courseMock)
        lenient().when(courseRepository.findById(anyString())).thenAnswer(new Answer(){
            @Override
            Object answer(InvocationOnMock invocationOnMock) throws Throwable {
                return getCourseById(invocationOnMock.getArgument(0))
            }
        })
        when(courseRepository.save(any(Course))).thenAnswer(new Answer() {
            @Override
            Object answer(InvocationOnMock invocationOnMock) throws Throwable {
                courseMock.add(invocationOnMock.getArgument(0))
                return null
            }
        })
    }

    @Test
    void testGenerateCourseNrForEmptyRepo(){
        Assert.assertEquals(1, courseEnricher.generateCourseNr("Mathe"))
    }

    @Test
    void testGenerateCourseNrForPopulatedRepo(){
        Subject s1 = Subject.builder().id("0").subjectName("Mathe").build()
        Course c1 = Course.builder()
                .id(courseEnricher.generateCourseId(s1.getSubjectName()))
                .courseNr(courseEnricher.generateCourseNr(s1.getSubjectName()))
                .name(courseEnricher.generateCourseName(s1.getSubjectName(), courseEnricher.getCalculatedCourseNr()))
                .subject(s1)
                .build()
        courseRepository.save(c1)
        Course c2 = Course.builder()
                .id(courseEnricher.generateCourseId(s1.getSubjectName()))
                .courseNr(courseEnricher.generateCourseNr(s1.getSubjectName()))
                .name(courseEnricher.generateCourseName(s1.getSubjectName(), courseEnricher.getCalculatedCourseNr()))
                .subject(s1)
                .build()
        courseRepository.save(c2)

        Assert.assertEquals(c1.getCourseNr(), courseRepository.findById(c1.getId()).get().getCourseNr())
        Assert.assertEquals(c2.getCourseNr(), courseRepository.findById(c2.getId()).get().getCourseNr())
        Assert.assertEquals(2, courseRepository.findAll().size())
    }

    @Test
    void testGenerateCourseId(){
        for(int i = 0; i < 10; i++) {
            Course course = Course.builder().id(courseEnricher.generateCourseId("Mathe")).build();
            courseRepository.save(course);
            Assert.assertEquals(String.valueOf(i), courseRepository.findById(String.valueOf(i)).get().getId());
        }
        Assert.assertEquals(10, courseRepository.findAll().size());
    }

    @Test
    void testGenerateCourseName(){
        Subject subject = Subject.builder().subjectName("Mathe").id("0").build();
        for(int i = 0; i < 10; i++){
            int courseId = Integer.parseInt(courseEnricher.generateCourseId("Mathe"));
            Course course = Course.builder()
                    .id(String.valueOf(courseId))
                    .courseNr(courseEnricher.generateCourseNr("Mathe"))
                    .name(courseEnricher.generateCourseName("Mathe", courseEnricher.generateCourseNr("Mathe")))
                    .subject(subject)
            .build();
            courseRepository.save(course);
            Assert.assertEquals("Mathe" + (i+1), courseRepository.findById(String.valueOf(courseId)).get().getName());
        }
        Assert.assertEquals(10, courseRepository.findAll().size());
    }

    /**
     * getCourseById für ArrayList
     * @param id
     * @return
     */
    private Optional<Course> getCourseById(String id){
        for(int i = 0; i < courseMock.size(); i++){
            if(courseMock.get(i).getId().equals(id)){
                return Optional.of(courseMock.get(i))
            }
        }
        throw new NullPointerException("CourseId not found")
    }
}
