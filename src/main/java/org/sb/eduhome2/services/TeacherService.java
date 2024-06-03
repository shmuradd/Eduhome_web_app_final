package org.sb.eduhome2.services;

import org.sb.eduhome2.dtos.event.EventDetailDto;
import org.sb.eduhome2.dtos.event.EventDto;
import org.sb.eduhome2.dtos.teachers.TeacherDetailDto;
import org.sb.eduhome2.dtos.teachers.TeacherDto;

import java.util.List;

public interface TeacherService {
    List<TeacherDto> getTeachers();

    List<TeacherDto> getAboutTeachers();

    TeacherDetailDto teacherDetail(int id);
}
