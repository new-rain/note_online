package pers.jxy.service;

import pers.jxy.entity.Note;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;

public interface NoteService {
    Integer saveNote(String nname, String article, Integer no, Integer uNo);

    Note queryNote(Integer no, Integer uNo);

    Note getNote(Integer no, Integer uNo);

    Boolean delNote(Integer no);

    Boolean deleteNote(Integer no);

    LinkedHashSet<Note> searchNotes(String name);

    Boolean addGood(Integer uNo, Integer nNo, Integer type);

    Boolean addCollection(Integer uNo, Integer nNo, Integer type);

    Boolean cancelGood(Integer uNo, Integer nNo);

    Boolean cancelCollection(Integer uNo, Integer nNo, Integer type);

    /**
     * 获取用户收藏的所有笔记
     */
    ArrayList<Note> getColNote(Integer no);

    List<Note> getGoodNotes(Integer no);

    Boolean changeNote(Integer nno, String nname, String arthicle, String url);

    ArrayList<Integer> queryNoteNum7Day();

    List<Note> queryHot();

    Object[] select7DayVNum();
}
