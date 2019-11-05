package com.example.testDatabase.demotest.Enricher

import com.example.testDatabase.demotest.Entities.Subject
import com.example.testDatabase.demotest.Repositories.SubjectRepository
import com.example.testDatabase.demotest.Services.SubjectService
import lombok.AllArgsConstructor
import lombok.NoArgsConstructor
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.invocation.InvocationOnMock
import org.mockito.stubbing.Answer
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

import static org.mockito.ArgumentMatchers.any
import static org.mockito.ArgumentMatchers.anyLong
import static org.mockito.ArgumentMatchers.anyString
import static org.mockito.Mockito.when

@AllArgsConstructor
@NoArgsConstructor
@SpringBootTest
@RunWith(org.mockito.runners.MockitoJUnitRunner.class)
class SubjectEnricherTest extends GroovyTestCase {
    @InjectMocks
    private SubjectService subjectService = SubjectService.builder().build();

    @Mock
    @Autowired
    private SubjectRepository subjectRepository;
    private ArrayList<Subject> subjectMock = new ArrayList<>();

    @Before
    public void init(){
        when(subjectRepository.findAll()).thenReturn(subjectMock);
        when(subjectRepository.save(any(Subject))).thenAnswer(new Answer() {
            @Override
            Object answer(InvocationOnMock invocationOnMock) throws Throwable {
                subjectMock.add(invocationOnMock.getArgument(0));
                return null
            }
        });
        when(subjectRepository.findBySubjectName(anyString())).thenAnswer(new Answer() {
            @Override
            Object answer(InvocationOnMock invocationOnMock) throws Throwable {
                return getSubjectBySubjectName(invocationOnMock.getArgument(0));
            }
        });
    }

    @Test
    public void testGenerateSubjectId(){
        Subject s1 = Subject.builder().subjectName("Mathe")
                .build();
        subjectRepository.save(s1);
        Subject s2 = Subject.builder().subjectName("Deutsch").build();
        subjectRepository.save(s2);

        Assert.assertEquals(anyLong(), subjectRepository.findBySubjectName("Mathe").get().getId());
        Assert.assertEquals(anyLong(), subjectRepository.findBySubjectName("Deutsch").get().getId());
        Assert.assertEquals(2, subjectRepository.findAll().size());
    }

    private Optional<Subject> getSubjectBySubjectName(String subjectName){
        for(int i = 0; i < subjectMock.size(); i++){
            if(subjectMock.get(i).getSubjectName().equals(subjectName)){
                return Optional.of(subjectMock.get(i));
            }
        }
    }
}
