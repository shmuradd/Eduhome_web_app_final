package org.sb.eduhome2.services.impls;
import org.sb.eduhome2.models.Blog;
import org.sb.eduhome2.models.Course;
import org.sb.eduhome2.models.Event;
import org.sb.eduhome2.models.Teacher;
import org.sb.eduhome2.repositories.BlogRepository;
import org.sb.eduhome2.repositories.CourseRepository;
import org.sb.eduhome2.repositories.EventRepository;
import org.sb.eduhome2.repositories.TeacherRepository;
import org.sb.eduhome2.services.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SearchServiceImpl implements SearchService {

    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private BlogRepository blogRepository;

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Override
    public List<Teacher> searchTeachers(String query) {
        return teacherRepository.findByNameContainingIgnoreCaseOrSurnameContainingIgnoreCase(query, query);
    }
    @Override
    public List<Blog> searchBlogs(String query) {
        return blogRepository.findByTitleContainingIgnoreCaseOrSubtitleContainingIgnoreCase(query, query);
    }
    @Override
    public List<Event> searchEvents(String query) {
        return eventRepository.findByNameContainingIgnoreCaseOrDescriptionContainingIgnoreCase(query, query);
    }
    @Override
    public List<Course> searchCourses(String query) {
        return courseRepository.findByNameContainingIgnoreCaseOrDescriptionContainingIgnoreCase(query, query);
    }
}