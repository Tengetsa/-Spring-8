package com.example.notes.service;

import com.example.notes.aspect.TrackUserAction;
import com.example.notes.model.Note;
import com.example.notes.repository.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class NoteService {

    @Autowired
    private NoteRepository noteRepository;

    @TrackUserAction
    public void addNote(Note note) throws RuntimeException {
        noteRepository.save(note);
    }

    @TrackUserAction
    public List<Note> getAllNotes() {
        List<Note> noteList = new ArrayList<>();
        noteRepository.findAll().iterator().forEachRemaining(noteList::add);
        return noteList;
    }

    @TrackUserAction
    public Note getNoteById(Long id) {
        return noteRepository.findById(id).orElse(null);
    }

    @TrackUserAction
    public void updateNote(Note note) {
        noteRepository.save(note);
    }

    @TrackUserAction
    public void deleteNote(Long id) {
        noteRepository.deleteById(id);
    }
}