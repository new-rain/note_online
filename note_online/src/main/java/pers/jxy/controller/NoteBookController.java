package pers.jxy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pers.jxy.entity.NoteBook;
import pers.jxy.service.NoteBookService;
import pers.jxy.util.NoteBookOnlineUtils;

import java.util.*;

/**
 * @author:靳新宇
 * @date : 11-08 21:08
 */
@RestController
public class NoteBookController {

    @Autowired
    NoteBookService noteBookService;

    /**
     * 新建笔记
     *
     * @param name:笔记本名;
     * @param describe:笔记本描述;
     * @param power:笔记本权限;
     * @param type:笔记本分类;
     * @param uno:作者账号;
     * @param coverImg:笔记本封面图片地址
     */
    @PostMapping("/newNoteBook")
    public Integer newNoteBook(String name, String describe, int power, String[] type, int uno, String coverImg) {
        NoteBook noteBook = new NoteBook();
        noteBook.setBName(name);
        noteBook.setBDescribe(describe);
        noteBook.setBType(NoteBookOnlineUtils.getNoteBookType(type));
        noteBook.setBPower(power);
        String now = NoteBookOnlineUtils.getNow();
        noteBook.setBCreateTime(now);
        noteBook.setBUpdateTime(now);
        noteBook.setBReadNum(0);
        noteBook.setBGoodNum(0);
        noteBook.setUNo(uno);
        noteBook.setCoverUrl(coverImg);
        return noteBookService.newNoteBook(noteBook);
    }

    /**
     * 根据账号获取所有笔记本信息
     *
     * @param no:用户账号;
     */
    @GetMapping("/getNoteBooks")
    public List<NoteBook> getNoteBooks(Integer no) {
        return noteBookService.queryAllNoteBooks(no);
    }

    /**
     * 根据编号获取笔记本信息
     *
     * @param no:笔记本编号;
     */
    @GetMapping("/getNoteBook")
    public NoteBook getNoteBook(Integer no) {
        return noteBookService.getNoteBook(no);
    }

    /**
     * 根据编号删除笔记本
     *
     * @param bNo:编号;
     */
    @DeleteMapping("/deleteNoteBook/{bNo}")
    public Boolean deleteNoteBook(@PathVariable("bNo") Integer bNo) {
        return noteBookService.deleteNoteBook(bNo);
    }

    /**
     * 修改笔记本
     */
    @PutMapping("/updateNoteBook")
    public Boolean updateNoteBook(Integer no, String name, String describe, String[] type, Integer power, String coverUrl) {
        NoteBook noteBook = new NoteBook();
        noteBook.setBNo(no);
        noteBook.setBName(name);
        noteBook.setBDescribe(describe);
        noteBook.setBType(NoteBookOnlineUtils.getNoteBookType(type));
        noteBook.setBPower(power);
        noteBook.setCoverUrl(coverUrl);
        return noteBookService.updateNoteBook(noteBook);
    }

    /**
     * 获取笔记本信息及其笔记信息
     *
     * @param no:笔记本编号;
     */
    @GetMapping("/getBookMenu")
    public NoteBook getBookMenu(Integer no, Integer uNo) {
        return noteBookService.queryNoteBookMenu(no, uNo);
    }

    /**
     * 根据时间分类查
     */
    @GetMapping("/queryGroupByTime")
    public TreeMap<String, TreeMap<String, TreeSet<NoteBook>>> queryGroupByTime(Integer no, Boolean timeOrder) {
        System.out.println(noteBookService.queryGroupByTime(no, timeOrder));
        return noteBookService.queryGroupByTime(no, timeOrder);
    }

    /**
     * 类别分类查询
     */
    @GetMapping("/queryGroupByType")
    public Map<String, ArrayList<NoteBook>> queryGroupByType(Integer no) {
        return noteBookService.selectNotebookByType(no);
    }

    /**
     * 排序查询笔记本
     */
    @GetMapping("/orderQueryNoteBookByTime")
    List<NoteBook> orderQueryNoteBookBy(Integer no, Integer type) {
        return noteBookService.queryNoteBookByTimeAsc(no, type);
    }

    /**
     * 搜索笔记本
     */
    @GetMapping("/searchNotebooks")
    public LinkedHashSet<NoteBook> searchNotebooks(String keyWord) {
        return noteBookService.searchNotebooks(keyWord);
    }

    /**
     * 获取收藏的笔记本
     */
    @GetMapping("/getColNotebook")
    public ArrayList<NoteBook> getColNotebook(Integer no) {
        return noteBookService.getColNotebook(no);
    }
}
