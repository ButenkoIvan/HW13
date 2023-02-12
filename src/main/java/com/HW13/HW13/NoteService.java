package com.HW13.HW13;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@RequiredArgsConstructor
@Service
public class NoteService {
    private Map<String, Note> noteList = new HashMap<>();

    {
        Note note  = new Note();
        note.setTitle("Product List");
        note.setContent("milk, water, apples");
        add(note);
        Note note1  = new Note();
        note1.setTitle("Tasks for tomorrow");
        note1.setContent("morning running, meet Bob, party at Soho");
        add(note1);
    }

    public List<Note> listAll() {
        return new ArrayList<>(noteList.values());
    }

    public synchronized Note add(Note note) {
        String tempId = UUID.randomUUID().toString();
        while(isMatch(tempId, noteList)) {
            tempId = UUID.randomUUID().toString();
        }
        note.setId(tempId);
        noteList.put(tempId, note);
        return note;
    }

    public Note getById(String id) {
        return noteList.get(id);
    }

    public synchronized void deleteById(String id) {
        if(!isMatch(id, noteList)) {
            throw new RuntimeException("Note with " + id + " doesn't exist!");
        } else {
            noteList.remove(id);
        }
    }

    public synchronized void update(Note note) {
        noteList.put(note.getId(), note);
    }
    private static boolean isMatch(String tempId, Map<String, Note> noteList) {
        return noteList.containsKey(tempId);
    }
}
