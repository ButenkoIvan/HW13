package com.HW13.HW13;

import lombok.Data;

@Data
public class NoteDto {
    private String id;
    private String title;
    private String content;

    public static NoteDto fromNote(Note note) {
        NoteDto res = new NoteDto();
        res.setId(note.getId());
        res.setTitle(note.getTitle());
        res.setContent(note.getContent());
        return res;
    }

    public Note toNote(){
        return new Note(id, title, content);
    }
}