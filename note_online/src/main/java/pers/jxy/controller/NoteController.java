package pers.jxy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pers.jxy.entity.Note;
import pers.jxy.service.NoteService;
import pers.jxy.util.NoteBookOnlineUtils;
import pers.jxy.util.ShieldUtil;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;

/**
 * @author:靳新宇
 * @date : 12-01 10:38
 */
@RestController
public class NoteController {

    @Autowired
    NoteService noteService;

    /**
     * 新建笔记
     *
     * @param nname:笔记名;
     * @param article:笔记内容;
     * @param nbNo:笔记本编号;
     * @param uNo:作者账号;
     * @return
     */
    @PostMapping("/writeNote")
    public Integer writeNote(String nname, String article, Integer uNo, Integer nbNo) {
        return noteService.saveNote(nname, article, uNo, nbNo);
    }

    /**
     * 根据编号查询笔记，并将md文件转换为html格式返回
     *
     * @param no:笔记编号;
     */
    @GetMapping("/seeNote")
    public Note seeNote(Integer no, Integer uNo) {
        return noteService.queryNote(no, uNo);
    }

    /**
     * 获取md格式文件
     */
    @GetMapping("/getNote")
    public Note getNote(Integer no, Integer uNo) {
        return noteService.getNote(no, uNo);
    }

    /**
     * 根据编号删除笔记(不可读)
     */
    @DeleteMapping("/delNote")
    public Boolean delNote(Integer no) {
        return noteService.delNote(no);
    }

    /**
     * 根据编号删除笔记（完全删除）
     */
    @DeleteMapping("/deleteNote")
    public Boolean deleteNote(Integer no) {
        return noteService.deleteNote(no);
    }

    /**
     * 搜索笔记
     */
    @GetMapping("/searchNotes")
    public LinkedHashSet<Note> searchNotes(String keyWord) {
        return noteService.searchNotes(keyWord);
    }

    /**
     * 点赞
     */
    @PutMapping("/addGood")
    public Boolean addGood(Integer uNo, Integer nNo, Integer type) {
        return noteService.addGood(uNo, nNo, type);
    }

    /**
     * 收藏
     */
    @PutMapping("/addCollection")
    public Boolean addCollection(Integer uNo, Integer nNo, Integer type) {
        return noteService.addCollection(uNo, nNo, type);
    }

    /**
     * 取消点赞
     */
    @PutMapping("/cancelGood")
    public Boolean cancelGood(Integer uNo, Integer nNo) {
        return noteService.cancelGood(uNo, nNo);
    }

    /**
     * 取消收藏
     */
    @PutMapping("/cancelCollection")
    public Boolean cancelCollection(Integer uNo, Integer nNo, Integer type) {
        return noteService.cancelCollection(uNo, nNo, type);
    }

    /**
     * 查看收藏
     */
    @GetMapping("/getColNote")
    public ArrayList<Note> getColNote(Integer no) {
        return noteService.getColNote(no);
    }

    /**
     * 查询点赞的笔记
     */
    @GetMapping("/getGoodNotes")
    public List<Note> getGoodNotes(Integer no) {
        return noteService.getGoodNotes(no);
    }

    /**
     * 修改笔记
     */
    @PutMapping("/changeNote")
    public Boolean changeNote(Integer nno, String nname, String arthicle, String url) {
        return noteService.changeNote(nno, nname, arthicle, url);
    }

    /**
     * 获取仅七天笔记新增数据
     */
    @GetMapping("/getNoteNumMsg")
    public Object[] getNoteNumMsg() {
        Object[] res = new Object[2];
        res[0] = NoteBookOnlineUtils.get7MdDate();
        res[1] = noteService.queryNoteNum7Day();
        return res;
    }

    /**
     * 获取热度排行前十
     */
    @GetMapping("/queryHot")
    public List<Note> queryHot() {
        return noteService.queryHot();
    }

    /**
     *
     */
    @GetMapping("/select7DayVNum")
    public Object[] select7DayVNum() {
        return noteService.select7DayVNum();
    }

    /**
     * 获取敏感词
     */
    @GetMapping("/getShields")
    public List<String> getShields() {
        return ShieldUtil.SHIELDS;
    }

    /**
     * 添加敏感词
     */
    @PostMapping("/addShield")
    public Integer addShield(String shield) {
        return ShieldUtil.addShield(shield);
    }
}
