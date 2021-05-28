package pers.jxy.service;

import org.springframework.transaction.annotation.Transactional;
import pers.jxy.entity.Note;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;

@Transactional(readOnly = false)
public interface NoteService {
    Integer saveNote(String nname, String article, Integer no, Integer uNo);

    Note queryNote(Integer no, Integer uNo);

    @Transactional(readOnly = true)
    Note getNote(Integer no, Integer uNo);

    Boolean delNote(Integer no);

    Boolean deleteNote(Integer no);

    @Transactional(readOnly = true)
    LinkedHashSet<Note> searchNotes(String name);

    Boolean addGood(Integer uNo, Integer nNo, Integer type);

    Boolean addCollection(Integer uNo, Integer nNo, Integer type);

    Boolean cancelGood(Integer uNo, Integer nNo);

    Boolean cancelCollection(Integer uNo, Integer nNo, Integer type);

    /**
     * 获取用户收藏的所有笔记
     */
    @Transactional(readOnly = true)
    ArrayList<Note> getColNote(Integer no);

    @Transactional(readOnly = true)
    List<Note> getGoodNotes(Integer no);

    Boolean changeNote(Integer nno, String nname, String arthicle, String url);

    @Transactional(readOnly = true)
    ArrayList<Integer> queryNoteNum7Day();

    @Transactional(readOnly = true)
    List<Note> queryHot();

    @Transactional(readOnly = true)
    Object[] select7DayVNum();
}
