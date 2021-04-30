package pers.jxy.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.jxy.dao.MessageDao;
import pers.jxy.dao.NoteDao;
import pers.jxy.entity.Message;
import pers.jxy.entity.Note;
import pers.jxy.service.NoteService;
import pers.jxy.util.NoteBookOnlineUtils;
import pers.jxy.util.NoteUtil;
import pers.jxy.util.ShieldUtil;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;

/**
 * @author:靳新宇
 * @date : 12-01 12:25
 */
@Service
public class NoteServiceImpl implements NoteService {
    @Autowired
    NoteDao noteDao;

    @Autowired
    MessageDao messageDao;

    @Autowired
    NoteUtil noteUtil;

    @Override
    public Integer saveNote(String nname, String article, Integer uNo, Integer nbNo) {
        article = ShieldUtil.detection(article);
        Note note = new Note();
        note.setNName(nname);
        note.setNbNo(nbNo);
        note.setUNo(uNo);
        note.setCreateTime(NoteBookOnlineUtils.getToday());
        Integer res = noteDao.saveNote(note);
        Integer nNo = note.getNNo();
        String path = noteUtil.saveArthicle(nNo + "---" + nname, article);
        Integer result = noteDao.savePath(path, nNo);
        if (res > 0 && result > 0) {
            return nNo;
        } else {
            return -1;
        }
    }

    @Override
    public Note queryNote(Integer no, Integer uNo) {
        String arthicle = null;
        noteDao.read(no);
        Note note = noteDao.queryNote(no, uNo);
        if (note == null) {
            return null;
        } else {
            String today = NoteBookOnlineUtils.getToday();
            if (noteDao.selectIsNull(today) == 0) {
                noteDao.insertDate(today);
            }
            noteDao.visitNumPlus(today);
            arthicle = noteUtil.markdownToHtmlExtensions(noteUtil.getArthicle(note.getNBodyUrl()));
            note.setArthicle(arthicle);
            return note;
        }
    }

    @Override
    public Note getNote(Integer no, Integer uNo) {
        Note note = noteDao.queryNote(no, uNo);
        String arthicle = noteUtil.getArthicle(note.getNBodyUrl());
        note.setArthicle(arthicle);
        return note;
    }

    @Override
    public Boolean delNote(Integer no) {
        noteDao.delCol(no);
        return noteDao.delNote(no, 0);
    }

    @Override
    public Boolean deleteNote(Integer no) {
        Boolean bol = noteUtil.deleteMd(noteDao.getFilePath(no));
        if (bol) {
            Integer res = noteDao.deleteNote(no);
            return res > 0;
        } else {
            return Boolean.FALSE;
        }
    }

    @Override
    public LinkedHashSet<Note> searchNotes(String name) {
        LinkedHashSet<Note> res = new LinkedHashSet<>();
        List<Note> notes = noteDao.accurateSearchNote(name);
        res.addAll(notes);
        List<Note> notes1 = noteDao.fuzzySearchNote1(name);
        res.addAll(notes1);
        List<Note> notes2 = noteDao.fuzzySearchNote2(name);
        res.addAll(notes2);
        return res;
    }

    @Override
    public Boolean addGood(Integer uNo, Integer nNo, Integer type) {
        Boolean res;
        Integer unNo = noteDao.getUnNo(uNo, nNo);
        if (unNo != null) {
            res = noteDao.addGoodRecord(unNo);
        } else {
            noteDao.addGoodCollectionRecord(uNo, nNo, type);
            res = noteDao.addGoodRecord(noteDao.getUnNo(uNo, nNo));
        }
        noteDao.goGoodNumPlus(nNo);
        Message message = new Message();
        message.setMBody("");
        message.setMToWhoNo(noteDao.selectAuthorNo(nNo));
        message.setMWhoToNo(uNo);
        message.setMType(2);
        messageDao.leaveMessage(message);
        return res;
    }

    @Override
    public Boolean addCollection(Integer uNo, Integer nNo, Integer type) {
        Boolean res;
        Integer unNo = noteDao.getUnNo(uNo, nNo);
        if (unNo != null) {
            res = noteDao.addCollectionRecord(unNo);
        } else {
            noteDao.addGoodCollectionRecord(uNo, nNo, type);
            res = noteDao.addCollectionRecord(noteDao.getUnNo(uNo, nNo));
        }
        if (type == 0) {
            noteDao.goCollecteNumPlus(nNo);
        }
        return res;
    }

    /**
     * 点赞数减一，点赞记录取消
     */
    @Override
    public Boolean cancelGood(Integer uNo, Integer nNo) {
        //取消点赞记录
        Boolean res = noteDao.cancelGoodRecord(uNo, nNo);
        //点赞数减一
        Boolean res1 = noteDao.goGoodNumReduce(nNo);
        return res && res1;
    }

    @Override
    public Boolean cancelCollection(Integer uNo, Integer nNo, Integer type) {
        //取消收藏记录
        Boolean res = noteDao.cancelCollectionRecord(uNo, nNo);
        if (type == 0) {
            //收藏数减一
            Boolean res1 = noteDao.goCollecteNumReduce(nNo);
            return res && res1;
        }
        return res;
    }

    @Override
    public ArrayList<Note> getColNote(Integer no) {
        ArrayList<Note> notes = new ArrayList<>();
        LinkedHashSet<Integer> colNos = noteDao.getColNo(no, 0);
        for (Integer colNo : colNos) {
            Note note = noteDao.queryNote(colNo, no);
            if (note != null) {
                notes.add(note);
            }
        }
        return notes;
    }

    @Override
    public List<Note> getGoodNotes(Integer no) {
        ArrayList<Note> goodNotes = new ArrayList<>();
        List<Integer> nos = noteDao.getGoodNotes(no);
        for (Integer gno : nos) {
            Note note = noteDao.queryNoteMessge(gno);
            if (note != null) {
                goodNotes.add(note);
            }
        }
        return goodNotes;
    }

    @Override
    public Boolean changeNote(Integer nno, String nname, String arthicle, String url) {
        arthicle = ShieldUtil.detection(arthicle);
        Boolean res1 = noteUtil.clearFile(url);
        Boolean res2 = noteUtil.writeNote(arthicle, url);
        Boolean res3 = noteDao.changeNote(nname, nno);
        return res1 && res2 && res3;
    }

    @Override
    public ArrayList<Integer> queryNoteNum7Day() {
        ArrayList<Integer> res = new ArrayList<>();
        List<String> dates = NoteBookOnlineUtils.get7yMdDate();
        for (String date : dates) {
            res.add(noteDao.queryNewNoteNum(date));
        }
        return res;
    }

    @Override
    public List<Note> queryHot() {
        return noteDao.queryHot();
    }

    @Override
    public Object[] select7DayVNum() {
        Object[] o = new Object[2];
        List<String> sevenDate = NoteBookOnlineUtils.get7MdDate();
        List<String> yMdDate = NoteBookOnlineUtils.get7yMdDate();
        LinkedList<Integer> sevenDayNums = new LinkedList<>();
        for (int i = 0; i < yMdDate.size(); i++) {
            Integer num = noteDao.select7DayVNum(yMdDate.get(i));
            sevenDayNums.add(num);
        }
        o[0] = sevenDate;
        o[1] = sevenDayNums;
        return o;
    }


}
