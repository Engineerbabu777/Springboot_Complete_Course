package com.demo.first.app.controller;

import com.demo.first.app.model.Note;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/notes/")
public class NotesController {
    private final Map<Integer, Note> notes = new HashMap<>();


    @PostMapping
    public Map<String,String> createNote(@RequestBody Note note){
        if(notes.containsKey(note.getId())){
            return Map.of("message","Note with this ID already exists");
        }else{
            notes.putIfAbsent(note.getId(),note);
            return Map.of("message","Note has been created in Database");
        }
    }

    @PutMapping
    public Map<String,String> updateNote(@RequestBody Note note){
        if(notes.containsKey(note.getId())){
            return Map.of("message","Note with this ID not exist in database");
        }else{
            notes.put(note.getId(),note);
            return Map.of("message","Note has been updated in Database");
        }
    }

    @GetMapping
    public List<Note> deleteNote(){
        return new ArrayList<Note>(notes.values());
    }

    @DeleteMapping("{noteId}")
    public Map<String,String> deleteNote(@PathVariable Integer noteId){
        if(notes.containsKey(noteId)){
            notes.remove(noteId);
            return Map.of("message","Note has been deleted from Database");
        }else{
            return Map.of("message","Note with this ID not exist in database");
        }
    }

    @GetMapping("{noteId}")
    public Map<String,String> getNoteById(@PathVariable Integer noteId){
        if(!notes.containsKey(noteId)){
            return Map.of("message","Note with this ID not exist in database");
        }else{
            return  Map.of("note",notes.get(noteId).getNote(), "isDone",notes.get(noteId).getIsDone(), "noteId",String.valueOf(notes.get(noteId).getId()));
        }
    }
}
