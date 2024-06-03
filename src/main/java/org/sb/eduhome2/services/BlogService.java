package org.sb.eduhome2.services;

import org.sb.eduhome2.dtos.blogs.BlogDetailDto;
import org.sb.eduhome2.dtos.blogs.BlogDto;
import org.sb.eduhome2.dtos.course.CourseDetailDto;
import org.sb.eduhome2.dtos.course.CourseDto;

import java.util.List;

public interface BlogService {
    List<BlogDto> getBlogs();

    List<BlogDto> getHomeBlogs();

    BlogDetailDto blogDetail(int id);
}
