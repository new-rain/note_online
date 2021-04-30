package pers.jxy.service;

import org.springframework.transaction.annotation.Transactional;
import pers.jxy.entity.Note;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;

@Transactional(readOnly = true)
public interface NoteService {
    @Transactional(readOnly = false)
    Integer saveNote(String nname, String article, Integer no, Integer uNo);

    Note queryNote(Integer no, Integer uNo);

    Note getNote(Integer no, Integer uNo);

    @Transactional(readOnly = false)
    Boolean delNote(Integer no);

    @Transactional(readOnly = false)
    Boolean deleteNote(Integer no);

    LinkedHashSet<Note> searchNotes(String name);

    @Transactional(readOnly = false)
    Boolean addGood(Integer uNo, Integer nNo, Integer type);

    @Transactional(readOnly = false)
    Boolean addCollection(Integer uNo, Integer nNo, Integer type);

    @Transactional(readOnly = false)
    Boolean cancelGood(Integer uNo, Integer nNo);

    @Transactional(readOnly = false)
    Boolean cancelCollection(Integer uNo, Integer nNo, Integer type);

    /**
     * 获取用户收藏的所有笔记
     */
    ArrayList<Note> getColNote(Integer no);

    List<Note> getGoodNotes(Integer no);

    @Transactional(readOnly = false)
    Boolean changeNote(Integer nno, String nname, String arthicle, String url);

    ArrayList<Integer> queryNoteNum7Day();

    List<Note> queryHot();

    Object[] select7DayVNum();
}
