package org.sb.eduhome2.services.impls;
import org.modelmapper.ModelMapper;
import org.sb.eduhome2.dtos.event.EventDetailDto;
import org.sb.eduhome2.dtos.event.EventDto;
import org.sb.eduhome2.dtos.teachers.TeacherCreateDto;
import org.sb.eduhome2.dtos.teachers.TeacherDetailDto;
import org.sb.eduhome2.dtos.teachers.TeacherDto;
import org.sb.eduhome2.dtos.teachers.TeacherUpdateDto;
import org.sb.eduhome2.models.Event;
import org.sb.eduhome2.models.Teacher;
import org.sb.eduhome2.repositories.TeacherRepository;
import org.sb.eduhome2.services.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
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
                .filter(x->x.getIsDeleted()==false)
                .map(teacher->modelMapper.map(teacher, TeacherDto.class))
                .collect(Collectors.toList());
        return teacherDtoList;
    }

    @Override
    public List<TeacherDto> getAboutTeachers() {
        List<TeacherDto> teacherDtoList = teacherRepository.findAll().stream()
                .filter(x -> x.getIsDeleted() == false)
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

    @Override
    public void addTeacher(TeacherCreateDto teacherCreateDto) {
        try {
            Teacher teacher= new Teacher();
            teacher.setName(teacherCreateDto.getName());
            teacher.setEmail(teacherCreateDto.getEmail());
            teacher.setSurname(teacherCreateDto.getSurname());
            teacher.setImage(teacherCreateDto.getImage());
            teacher.setJob(teacherCreateDto.getJob());
            teacher.setFacebookUrl(teacherCreateDto.getFacebookUrl());
            teacher.setCommunicationPoint(teacherCreateDto.getCommunicationPoint());
            teacher.setDevelopmentPoint(teacherCreateDto.getDevelopmentPoint());
            teacher.setInnovationPoint(teacherCreateDto.getInnovationPoint());
            teacher.setTeamLeaderPoint(teacherCreateDto.getTeamLeaderPoint());
            teacher.setLanguagePoint(teacherCreateDto.getLanguagePoint());
            teacher.setPhoneNumbers(teacherCreateDto.getPhoneNumbers());
            teacher.setHobbies(teacherCreateDto.getHobbies());
            teacher.setExperience(teacherCreateDto.getExperience());
            teacher.setDegree(teacherCreateDto.getDegree());
            teacher.setAboutTeacher(teacherCreateDto.getAboutTeacher());
            teacherRepository.saveAndFlush(teacher);

        } catch (Exception e)
        {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public TeacherUpdateDto findUpdateTeacher(int id) {
        Teacher teacher=teacherRepository.findById(id).orElseThrow();
        TeacherUpdateDto teacherUpdateDto=modelMapper.map(teacher, TeacherUpdateDto.class);
        return teacherUpdateDto;
    }

    @Override
    public void updateTeacher(TeacherUpdateDto teacherUpdateDto) {
        Teacher findTeacher=teacherRepository.findById(teacherUpdateDto.getId()).orElseThrow();
        findTeacher.setId(teacherUpdateDto.getId());
        findTeacher.setName(teacherUpdateDto.getName());
        findTeacher.setSurname(teacherUpdateDto.getSurname());
        findTeacher.setImage(teacherUpdateDto.getImage());
        findTeacher.setJob(teacherUpdateDto.getJob());
        findTeacher.setFacebookUrl(teacherUpdateDto.getFacebookUrl());
        findTeacher.setCommunicationPoint(teacherUpdateDto.getCommunicationPoint());
        findTeacher.setDevelopmentPoint(teacherUpdateDto.getDevelopmentPoint());
        findTeacher.setInnovationPoint(teacherUpdateDto.getInnovationPoint());
        findTeacher.setDesignPoint(teacherUpdateDto.getDesignPoint());
        findTeacher.setTeamLeaderPoint(teacherUpdateDto.getTeamLeaderPoint());
        findTeacher.setLanguagePoint(teacherUpdateDto.getLanguagePoint());
        findTeacher.setPhoneNumbers(teacherUpdateDto.getPhoneNumbers());
        findTeacher.setEmail(teacherUpdateDto.getEmail());
        findTeacher.setHobbies(teacherUpdateDto.getHobbies());

        teacherRepository.saveAndFlush(findTeacher);
    }

    @Override
    public void removeTeacher(int teacherId) {
        Teacher teacher=teacherRepository.findById(teacherId).orElseThrow();
        teacher.setIsDeleted(true);
        teacherRepository.flush();
    }

    @Override
    public void activityTeacher(int teacherId) {
        Teacher teacher = teacherRepository.findById(teacherId).orElseThrow();
        teacher.setIsDeleted(!teacher.getIsDeleted()); // Toggle the deleted status
        teacherRepository.flush();
    }
}
