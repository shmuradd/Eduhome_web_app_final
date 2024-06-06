package org.sb.eduhome2.services;

import org.sb.eduhome2.dtos.event.EventDetailDto;
import org.sb.eduhome2.dtos.event.EventDto;
import org.sb.eduhome2.dtos.teachers.TeacherCreateDto;
import org.sb.eduhome2.dtos.teachers.TeacherDetailDto;
import org.sb.eduhome2.dtos.teachers.TeacherDto;
import org.sb.eduhome2.dtos.teachers.TeacherUpdateDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface TeacherService {
    List<TeacherDto> getTeachers();

    List<TeacherDto> getAboutTeachers();

    TeacherDetailDto teacherDetail(int id);

    void addTeacher(TeacherCreateDto teacherCreateDto);

    TeacherUpdateDto findUpdateTeacher(int id);
    public void updateTeacher(int id, TeacherUpdateDto teacherUpdateDto);
    public void removeTeacher(int teacherId);
    public void activityTeacher(int teacherId);
}
