package org.sb.eduhome2.services.impls;
import org.modelmapper.ModelMapper;
import org.sb.eduhome2.dtos.course.CourseDetailDto;
import org.sb.eduhome2.dtos.course.CourseDto;
import org.sb.eduhome2.models.Course;
import org.sb.eduhome2.repositories.CourseRepository;
import org.sb.eduhome2.services.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CourseServiceImpl implements CourseService {
    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private ModelMapper modelMapper;




    @Override
    public List<CourseDto> getCourses() {
        List<CourseDto> courseDtoList=courseRepository.findAll().stream()
                .filter(x->x.isDeleted()==false)
                .map(course->modelMapper.map(course, CourseDto.class))
                .collect(Collectors.toList());
        return courseDtoList;
    }

    @Override
    public List<CourseDto> getHomeCourses() {
        List<CourseDto> courseDtoList=courseRepository.findAll().stream()
                .filter(x->x.isDeleted()==false)
                .map(course->modelMapper.map(course, CourseDto.class))
                .limit(3)
                .collect(Collectors.toList());
        return courseDtoList;
    }

    @Override
    public CourseDetailDto courseDetail(int id) {
        Course course = courseRepository.findById(id).orElse(null);
        CourseDetailDto courseDetailDto = modelMapper.map(course,CourseDetailDto.class);
        return courseDetailDto;
    }
}
