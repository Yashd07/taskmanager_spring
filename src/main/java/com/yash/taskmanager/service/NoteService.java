package com.yash.taskmanager.service;

import com.yash.taskmanager.entities.NoteEntity;
import com.yash.taskmanager.entities.TaskEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class NoteService {
    private  TaskService taskService;
    private HashMap<Integer, TaskNoteHolders> taskNoteHolders = new HashMap<>();


    public NoteService(TaskService taskService) {
        this.taskService = taskService;
    }

    class TaskNoteHolders {
        protected int noteId = 1;
        protected ArrayList<NoteEntity> notes = new ArrayList<>();

    }

    public List<NoteEntity> getNotesForTask(int taskId){
        TaskEntity task = taskService.getTaskById(taskId);
        if(task == null){
            return null;
        }
        if(taskNoteHolders.get(taskId)== null){
            taskNoteHolders.put(taskId, new TaskNoteHolders());

        }
        return taskNoteHolders.get(taskId).notes;

    }

    public NoteEntity addNoteForTask(int taskId, String title, String body){
        TaskEntity task = taskService.getTaskById(taskId);
        if(task == null){
            return null;
        }
        if(taskNoteHolders.get(taskId)== null){
            taskNoteHolders.put(taskId, new TaskNoteHolders());

        }
        TaskNoteHolders taskNotesHolders = taskNoteHolders.get(taskId);
        NoteEntity note = new NoteEntity();
        note.setId(taskNotesHolders.noteId);
        note.setTitle(title);
        note.setBody(body);
        taskNotesHolders.notes.add(note);
        taskNotesHolders.noteId++;
        return note;
    }
}

