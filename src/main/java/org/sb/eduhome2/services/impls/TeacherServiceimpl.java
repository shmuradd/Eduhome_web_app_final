package org.sb.eduhome2.services.impls;
import org.modelmapper.ModelMapper;
import org.sb.eduhome2.dtos.event.EventDetailDto;
import org.sb.eduhome2.dtos.event.EventDto;
import org.sb.eduhome2.dtos.teachers.TeacherDetailDto;
import org.sb.eduhome2.dtos.teachers.TeacherDto;
import org.sb.eduhome2.models.Event;
import org.sb.eduhome2.models.Teacher;
import org.sb.eduhome2.repositories.TeacherRepository;
import org.sb.eduhome2.services.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TeacherServiceimpl implements TeacherService {
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private TeacherRepository teacherRepository;
    @Override
    public List<TeacherDto> getTeachers() {

        List<TeacherDto> teacherDtoList=teacherRepository.findAll().stream()
                .filter(x->x.isDeleted()==false)
                .map(teacher->modelMapper.map(teacher, TeacherDto.class))
                .collect(Collectors.toList());
        return teacherDtoList;
    }

    @Override
    public List<TeacherDto> getAboutTeachers() {
        List<TeacherDto> teacherDtoList = teacherRepository.findAll().stream()
                .filter(x -> x.isDeleted() == false)
                .map(teacher -> modelMapper.map(teacher, TeacherDto.class))
                .limit(4)
                .collect(Collectors.toList());
        return teacherDtoList;
    }
    @Override
    public TeacherDetailDto teacherDetail(int id) {
        Teacher teacher = teacherRepository.findById(id).orElse(null);
        TeacherDetailDto teacherDetailDto = modelMapper.map(teacher,TeacherDetailDto.class);
        return teacherDetailDto;
    }
}
