package pers.jxy.service;

import pers.jxy.entity.NoteBook;

import java.util.*;

public interface NoteBookService {
    /**
     * 新建笔记本
     */
    Integer newNoteBook(NoteBook noteBook);

    /**
     * 根据账号查询用户所有笔记本
     */
    List<NoteBook> queryAllNoteBooks(Integer no);

    /**
     * 根据编号删除笔记本
     */
    Boolean deleteNoteBook(Integer bNo);

    /**
     * 根据编号查询笔记本
     */
    NoteBook getNoteBook(Integer no);

    /**
     * 修改笔记本
     */
    Boolean updateNoteBook(NoteBook noteBook);

    /**
     * 查询目笔记本及其笔记信息
     */
    NoteBook queryNoteBookMenu(Integer bNo,Integer uNo);

    /**
     * 根据时间分类查询
     */
    TreeMap<String, TreeMap<String, TreeSet<NoteBook>>> queryGroupByTime(Integer no,Boolean timeOrder);

    /**
     * 根据时间倒叙查询笔记本
     */
    List<NoteBook> queryNoteBookByTimeAsc(Integer no,Integer type);

    /**
     * 查询所有笔记本标签
     */
    HashSet<String> selectAllTypes(Integer no);

    /**
     * 根据标签查找笔记
     */
    Map<String,ArrayList<NoteBook>> selectNotebookByType(Integer no);

    /**
     * 搜索笔记本
     */
    LinkedHashSet<NoteBook> searchNotebooks(String keyWord);

    ArrayList<NoteBook> getColNotebook(Integer no);

}
