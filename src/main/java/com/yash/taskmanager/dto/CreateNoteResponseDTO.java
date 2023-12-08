package com.yash.taskmanager.dto;

import com.yash.taskmanager.entities.NoteEntity;

public class CreateNoteResponseDTO {
    private Integer taskId;
    private NoteEntity note;

    public CreateNoteResponseDTO() {
    }

    public CreateNoteResponseDTO(Integer taskId, NoteEntity note) {
        this.taskId = taskId;
        this.note = note;
    }

    public Integer getTaskId() {
        return taskId;
    }

    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
    }

    public NoteEntity getNote() {
        return note;
    }

    public void setNote(NoteEntity note) {
        this.note = note;
    }
}
