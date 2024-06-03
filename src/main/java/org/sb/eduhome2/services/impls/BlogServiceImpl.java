package org.sb.eduhome2.services.impls;
import org.modelmapper.ModelMapper;
import org.sb.eduhome2.dtos.blogs.BlogDetailDto;
import org.sb.eduhome2.dtos.blogs.BlogDto;
import org.sb.eduhome2.dtos.course.CourseDetailDto;
import org.sb.eduhome2.dtos.course.CourseDto;
import org.sb.eduhome2.models.Blog;
import org.sb.eduhome2.models.Course;
import org.sb.eduhome2.repositories.BlogRepository;
import org.sb.eduhome2.services.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BlogServiceImpl implements BlogService {
    @Autowired
    private BlogRepository blogRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<BlogDto> getBlogs() {
        List<BlogDto> blogDtoList=blogRepository.findAll().stream()
                .filter(x->x.isDeleted()==false)
                .map(blog->modelMapper.map(blog, BlogDto.class))
                .collect(Collectors.toList());
        return blogDtoList;

    }

    @Override
    public List<BlogDto> getHomeBlogs() {
        List<BlogDto> blogDtoList=blogRepository.findAll().stream()
                .filter(x->x.isDeleted()==false)
                .map(blog->modelMapper.map(blog, BlogDto.class))
                .limit(3)
                .collect(Collectors.toList());
        return blogDtoList;
    }

    @Override
    public BlogDetailDto blogDetail(int id) {
        Blog blog = blogRepository.findById(id).orElse(null);
        BlogDetailDto blogDetailDto = modelMapper.map(blog, BlogDetailDto.class);
        return blogDetailDto;
    }
}
