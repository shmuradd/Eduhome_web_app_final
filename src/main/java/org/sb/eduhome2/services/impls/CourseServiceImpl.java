package org.sb.eduhome2.services.impls;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.sb.eduhome2.dtos.course.CourseCreateDto;
import org.sb.eduhome2.dtos.course.CourseDetailDto;
import org.sb.eduhome2.dtos.course.CourseDto;
import org.sb.eduhome2.dtos.course.CourseUpdateDto;
import org.sb.eduhome2.models.Course;
import org.sb.eduhome2.repositories.CourseRepository;
import org.sb.eduhome2.services.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CourseServiceImpl implements CourseService {
    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private ModelMapper modelMapper;




    @Override
    public Page<CourseDto> getCourses(PageRequest pageRequest) {
        Page<Course> coursesPage = courseRepository.findByIsDeletedFalse(pageRequest);

        Page<CourseDto> courseDtoPage = coursesPage.map(course -> modelMapper.map(course, CourseDto.class));

        return courseDtoPage;
    }



    @Override
    public List<CourseDto> getHomeCourses() {
        // Create a PageRequest for fetching all courses sorted by ID descending
        PageRequest pageRequest = PageRequest.of(0, 3, Sort.by(Sort.Direction.DESC, "id"));

        // Fetch courses using pageRequest and filter
        List<CourseDto> courseDtoList = courseRepository.findAll(pageRequest).stream()
                .filter(course -> !course.isDeleted())
                .map(course -> modelMapper.map(course, CourseDto.class))
                .collect(Collectors.toList());

        return courseDtoList;
    }


    @Override
    public CourseDetailDto courseDetail(int id) {
        Course course = courseRepository.findById(id).orElse(null);
        CourseDetailDto courseDetailDto = modelMapper.map(course,CourseDetailDto.class);
        return courseDetailDto;
    }

    @Override
    public void addCourse(CourseCreateDto courseCreateDto) {
        try {
            Course course = new Course();
            course.setName(courseCreateDto.getName());
            course.setDescription(courseCreateDto.getDescription());
            course.setImage(courseCreateDto.getImage());
            course.setApplyDescription(courseCreateDto.getApplyDescription());
            course.setCertificationDescription(courseCreateDto.getCertificationDescription());
            course.setStartDate(courseCreateDto.getStartDate());
            course.setDurationTime(courseCreateDto.getDurationTime());
            course.setClassDuration(courseCreateDto.getClassDuration());
            course.setSkillLevel(courseCreateDto.getSkillLevel());
            course.setLanguage(courseCreateDto.getLanguage());
            course.setStudentCapacity(courseCreateDto.getStudentCapacity());
            course.setAssessments(courseCreateDto.getAssessments());
            course.setPrice(courseCreateDto.getPrice());
            // Add any additional fields here if needed

            courseRepository.save(course);

        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }


    @Override
    public CourseUpdateDto findUpdateCourse(int id) {
        Course course = courseRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Course not found with id " + id));
        return modelMapper.map(course, CourseUpdateDto.class);
    }

    @Override
    @Transactional
    public void updateCourse(int id, CourseUpdateDto courseUpdateDto) {
        Course findCourse = courseRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Course not found with id " + id));

        findCourse.setName(courseUpdateDto.getName());
        findCourse.setDescription(courseUpdateDto.getDescription());
        findCourse.setImage(courseUpdateDto.getImage());
        findCourse.setApplyDescription(courseUpdateDto.getApplyDescription());
        findCourse.setCertificationDescription(courseUpdateDto.getCertificationDescription());
        findCourse.setStartDate(courseUpdateDto.getStartDate());
        findCourse.setDurationTime(courseUpdateDto.getDurationTime());
        findCourse.setClassDuration(courseUpdateDto.getClassDuration());
        findCourse.setSkillLevel(courseUpdateDto.getSkillLevel());
        findCourse.setLanguage(courseUpdateDto.getLanguage());
        findCourse.setStudentCapacity(courseUpdateDto.getStudentCapacity());
        findCourse.setAssessments(courseUpdateDto.getAssessments());
        findCourse.setPrice(courseUpdateDto.getPrice());

        // Save the updated course entity
        courseRepository.saveAndFlush(findCourse);
    }

    @Override
    @Transactional
    public void removeCourse(int courseId) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new EntityNotFoundException("Course not found with id " + courseId));
        course.setDeleted(true);
        courseRepository.flush();
    }

    @Override
    @Transactional
    public void activityCourse(int courseId) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new EntityNotFoundException("Course not found with id " + courseId));
        course.setDeleted(!course.isDeleted()); // Toggle the deleted status
        courseRepository.flush();
    }

    public List<Course> searchCourses(String query) {
        // Implement search logic based on your requirements, for example:
        return courseRepository.findByNameContainingIgnoreCase(query);
    }
}
