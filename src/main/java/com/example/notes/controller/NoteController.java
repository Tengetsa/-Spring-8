package com.example.notes.controller;

import com.example.notes.model.Note;
import com.example.notes.service.NoteService;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

/**
 * Контроллер API сервиса создания заметок
 */
@RestController
@RequiredArgsConstructor
@Log
public class NoteController {
    @Autowired
    private NoteService service;

    /**
     * addNote - создать заметку
     */
    @PostMapping("/add")
    public ResponseEntity<?> createNote(@RequestBody Note note) {
        try{
            note.setDate(LocalDateTime.now());
            service.addNote(note);
            return ResponseEntity.ok(note);
        }catch (RuntimeException e){
            return ResponseEntity.badRequest().body(note);
        }

    }

    /**
     * getAllNotes - получить все заметки
     */
    @GetMapping("/")
    public ResponseEntity<?> getAllNotes() {
        return ResponseEntity.ok(service.getAllNotes());
    }

    /**
     * getNoteById - получения данных по id.
     */
    @GetMapping("/{id}")
    public ResponseEntity<?> getNoteById(@PathVariable Long id) {
        Note note = service.getNoteById(id);
        if (note == null) return ResponseEntity.badRequest().build();
        return ResponseEntity.ok(note);
    }

    /**
     * updateNote - редактирование заметки по id.
     */
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateNote(@PathVariable Long id, @RequestBody Note note) {
        note.setId(id);
        service.updateNote(note);
        return ResponseEntity.ok().build();
    }

    /**
     * deleteTask - удаление заметки по id.
     */
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteNote(@PathVariable Long id) {
        service.deleteNote(id);
        return ResponseEntity.ok().build();
    }


}