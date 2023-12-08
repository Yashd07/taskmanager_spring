package com.yash.taskmanager.controller;

import com.yash.taskmanager.dto.CreateNoteResponseDTO;
import com.yash.taskmanager.dto.CreateNotesDTO;
import com.yash.taskmanager.entities.NoteEntity;
import com.yash.taskmanager.service.NoteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks/{taskId}/notes")
public class NotesController {
    private NoteService noteService;

    public NotesController(NoteService noteService) {
        this.noteService = noteService;
    }

    @GetMapping("")
    public ResponseEntity<List<NoteEntity>> getNotes(@PathVariable("taskId") Integer taskId){
        return ResponseEntity.ok(noteService.getNotesForTask(taskId));

    }

    @PostMapping("")
    public ResponseEntity<CreateNoteResponseDTO> addNote(@PathVariable("taskId") Integer taskId,
                                                         @RequestBody CreateNotesDTO body){
        var note = noteService.addNoteForTask(taskId, body.getTitle(), body.getBody());
        return ResponseEntity.ok(new CreateNoteResponseDTO(taskId, note));
    }


}
