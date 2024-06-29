package org.sb.eduhome2.services.impls;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.sb.eduhome2.dtos.blogs.BlogCreateDto;
import org.sb.eduhome2.dtos.blogs.BlogDetailDto;
import org.sb.eduhome2.dtos.blogs.BlogDto;
import org.sb.eduhome2.dtos.blogs.BlogUpdateDto;
import org.sb.eduhome2.dtos.course.CourseDetailDto;
import org.sb.eduhome2.dtos.course.CourseDto;
import org.sb.eduhome2.models.Blog;
import org.sb.eduhome2.models.Course;
import org.sb.eduhome2.repositories.BlogRepository;
import org.sb.eduhome2.services.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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
    public Page<BlogDto> getBlogs(PageRequest pageRequest) {
        Page<Blog> blogsPage = blogRepository.findByIsDeletedFalse(pageRequest);

        Page<BlogDto> blogDtoPage = blogsPage.map(blog -> modelMapper.map(blog, BlogDto.class));

        return blogDtoPage;
    }




    @Override
    public List<BlogDto> getHomeBlogs() {
        // Create a PageRequest for fetching all courses sorted by ID descending
        PageRequest pageRequest = PageRequest.of(0, 4, Sort.by(Sort.Direction.DESC, "id"));

        // Fetch courses using pageRequest and filter
        List<BlogDto> blogDtoList = blogRepository.findAll(pageRequest).stream()
                .filter(blog -> !blog.isDeleted())
                .map(blog -> modelMapper.map(blog, BlogDto.class))
                .collect(Collectors.toList());

        return blogDtoList;
    }


    @Override
    public BlogDetailDto blogDetail(int id) {
        Blog blog = blogRepository.findById(id).orElse(null);
        BlogDetailDto blogDetailDto = modelMapper.map(blog, BlogDetailDto.class);
        return blogDetailDto;
    }


    @Override
    public void addBlog(BlogCreateDto blogCreateDto) {
        try {
            Blog blog = modelMapper.map(blogCreateDto, Blog.class);
            blogRepository.save(blog);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public BlogUpdateDto findUpdateBlog(int id) {
        Blog blog = blogRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Blog not found with id " + id));
        return modelMapper.map(blog, BlogUpdateDto.class);
    }

    @Override
    @Transactional
    public void updateBlog(int id, BlogUpdateDto blogUpdateDto) {
        Blog findBlog = blogRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Blog not found with id " + id));

        modelMapper.map(blogUpdateDto, findBlog);
        blogRepository.saveAndFlush(findBlog);
    }


    @Override
    @Transactional
    public void deleteBlog(int blogId) {
        Blog blog = blogRepository.findById(blogId)
                .orElseThrow(() -> new EntityNotFoundException("Blog not found with id " + blogId));
        blog.setDeleted(true);
        blogRepository.flush();
    }

    @Override
    @Transactional
    public void toggleBlogActivity(int blogId) {
        Blog blog = blogRepository.findById(blogId)
                .orElseThrow(() -> new EntityNotFoundException("Blog not found with id " + blogId));
        blog.setDeleted(!blog.isDeleted());
        blogRepository.flush();
    }


}
