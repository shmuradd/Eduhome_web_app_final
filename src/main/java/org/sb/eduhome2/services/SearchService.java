package org.sb.eduhome2.services;

import org.sb.eduhome2.models.Blog;
import org.sb.eduhome2.models.Course;
import org.sb.eduhome2.models.Event;
import org.sb.eduhome2.models.Teacher;

import java.util.List;

public interface SearchService {


    public List<Teacher> searchTeachers(String query);
    public List<Blog> searchBlogs(String query);
    public List<Event> searchEvents(String query);
    public List<Course> searchCourses(String query);
}