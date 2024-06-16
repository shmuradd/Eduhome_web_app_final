package org.sb.eduhome2.controllers;
import org.sb.eduhome2.dtos.speakers.SpeakerCreateDto;
import org.sb.eduhome2.dtos.speakers.SpeakerUpdateDto;
import org.sb.eduhome2.models.Speaker;
import org.sb.eduhome2.repositories.SpeakerRepository;
import org.sb.eduhome2.services.SpeakerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
@Controller
@RequestMapping("/admin/speakers")
public class SpeakerController {

    @Autowired
    private SpeakerService speakerService;
    @Autowired
    private SpeakerRepository speakerRepository;

    @GetMapping
    public String index(Model model) {
        List<Speaker> speakers = speakerRepository.findAll().stream().toList();
        model.addAttribute("speakers", speakers);
        return "dashboard/speaker/speakers";
    }

    @GetMapping("/create")
    public String speakerCreate(Model model) {
        model.addAttribute("speakerCreateDto", new SpeakerCreateDto());
        return "dashboard/speaker/speaker-create";
    }

    @PostMapping("/create")
    public String speakerCreate(@ModelAttribute SpeakerCreateDto speakerCreateDto,
                                @RequestParam(value = "photoUrl", required = false) String photoUrl) throws IOException {
        speakerCreateDto.setImage(photoUrl);
        speakerService.addSpeaker(speakerCreateDto);
        return "redirect:/admin/speakers";
    }

    @GetMapping("/update/{id}")
    public String speakerUpdate(@PathVariable int id, Model model) {
        SpeakerUpdateDto speakerUpdateDto = speakerService.findUpdateSpeaker(id);
        model.addAttribute("speaker", speakerUpdateDto);
        return "dashboard/speaker/update";
    }

    @PostMapping("/update/{id}")
    public String updateSpeaker(@PathVariable int id, @ModelAttribute SpeakerUpdateDto speakerUpdateDto) {
        speakerService.updateSpeaker(id, speakerUpdateDto);
        return "redirect:/admin/speakers";
    }

    @GetMapping("/remove/{id}")
    public String speakerRemove(@ModelAttribute @PathVariable int id) {
        speakerService.removeSpeaker(id);
        return "redirect:/admin/speakers";
    }


}