package pers.jxy.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.jxy.dao.NoteBookDao;
import pers.jxy.dao.NoteDao;
import pers.jxy.entity.NoteBook;
import pers.jxy.service.NoteBookService;

import java.util.*;

/**
 * @author:靳新宇
 * @date : 11-08 21:09
 */
@Service
public class NoteBookServiceImpl implements NoteBookService {
    @Autowired
    NoteBookDao noteBookDao;

    @Autowired
    NoteDao noteDao;

    /**
     * 新建笔记本
     */
    @Override
    public Integer newNoteBook(NoteBook noteBook) {
        noteBookDao.newNoteBook(noteBook);
        return noteBook.getBNo();
    }

    /**
     * 根据用户账号获取用户所有笔记本信息并按修改时间进行排序
     */
    @Override
    public List<NoteBook> queryAllNoteBooks(Integer no) {
        return noteBookDao.queryAllNoteBooks(no);
    }

    /**
     * 删除笔记本
     */
    @Override
    public Boolean deleteNoteBook(Integer bNo) {
        Integer res = noteBookDao.deleteNoteBook(bNo);
        if (res > 0) {
            return Boolean.TRUE;
        } else {
            return Boolean.FALSE;
        }
    }

    /**
     * 根据编号查找笔记本
     */
    @Override
    public NoteBook getNoteBook(Integer no) {
        NoteBook noteBook = noteBookDao.getNoteBook(no);
        String bType = noteBook.getBType();
        String[] types = bType.split("-");
        ArrayList<String> strings = new ArrayList<>();
        for (String type : types) {
            if (!("".equals(type))) {
                strings.add(type);
            }
        }
        noteBook.setBTypes(strings);
        return noteBook;
    }

    /**
     * 修改笔记本信息
     */
    @Override
    public Boolean updateNoteBook(NoteBook noteBook) {
        Integer res = noteBookDao.updateNoteBook(noteBook);
        if (res > 0) {
            return Boolean.TRUE;
        } else {
            return Boolean.FALSE;
        }
    }

    /**
     * 查询目笔记本及其笔记信息
     */
    @Override
    public NoteBook queryNoteBookMenu(Integer bNo, Integer uNo) {
        NoteBook noteBook = noteBookDao.queryNoteBookMenu(bNo, uNo);
        String bType = noteBook.getBType();
        String[] types = bType.split("-");
        ArrayList<String> strings = new ArrayList<>();
        for (String type : types) {
            if (!("".equals(type))) {
                strings.add(type);
            }
        }
        noteBook.setBTypes(strings);
        return noteBook;
    }

    /**
     * 按时间分类查询笔记本
     */
    @Override
    public TreeMap<String, TreeMap<String, TreeSet<NoteBook>>> queryGroupByTime(Integer no, Boolean timeOrder) {
        TreeMap<String, TreeMap<String, TreeSet<NoteBook>>> result;
        if (timeOrder) {
            result = new TreeMap<>();
        } else {
            result = new TreeMap<>(
                    new Comparator<String>() {
                        @Override
                        public int compare(String o1, String o2) {
                            return o2.compareTo(o1);
                        }
                    }
            );
        }
        List<String> times = noteBookDao.queryTimeTypeDesc(no);
        for (String time : times) {
            String[] date = time.split(" ");
            String[] ymd = date[0].split("-");
            String year = ymd[0];
            String md = ymd[1] + "-" + ymd[2];
            if (result.get(year) == null) {
                if (timeOrder) {
                    result.put(year, new TreeMap<String, TreeSet<NoteBook>>());
                } else {
                    result.put(year, new TreeMap<String, TreeSet<NoteBook>>(
                            new Comparator<String>() {
                                @Override
                                public int compare(String o1, String o2) {
                                    return o2.compareTo(o1);
                                }
                            }
                    ));
                }
            }
            result.get(year).put(md, new TreeSet<NoteBook>());
        }
        Set<Map.Entry<String, TreeMap<String, TreeSet<NoteBook>>>> entries = result.entrySet();
        for (Map.Entry<String, TreeMap<String, TreeSet<NoteBook>>> entry : entries) {
            Set<Map.Entry<String, TreeSet<NoteBook>>> entries1 = entry.getValue().entrySet();
            for (Map.Entry<String, TreeSet<NoteBook>> s : entries1) {
                TreeSet<NoteBook> noteBooks = new TreeSet<>(
                        (n1, n2) -> {
                            String[] date1 = n1.getBCreateTime().split(" ");
                            String[] date2 = n2.getBCreateTime().split(" ");
                            String[] time1 = date1[1].split(":");
                            String[] time2 = date2[1].split(":");
                            int res = time1[0].compareTo(time2[0]);
                            res = res == 0 ? time1[1].compareTo(time2[1]) : res;
                            res = res == 0 ? time1[2].compareTo(time2[2]) : res;
                            if (timeOrder) {
                                return res;
                            } else {
                                return -res;
                            }
                        }
                );
                String date = entry.getKey() + "-" + s.getKey();
                List<NoteBook> noteBooks1 = noteBookDao.queryByTime(date, no);
                for (NoteBook noteBook : noteBooks1) {
                    noteBooks.add(noteBook);
                }
                result.get(entry.getKey()).put(s.getKey(), noteBooks);
            }
        }
        return result;
    }

    @Override
    public List<NoteBook> queryNoteBookByTimeAsc(Integer no, Integer type) {
        if (type == 1) {
            return noteBookDao.queryNoteBookByTimeAsc(no);
        } else if (type == 2) {
            return noteBookDao.queryNoteBookByTimeDesc(no);
        } else if (type == 3) {
            return noteBookDao.queryNoteBookByGoodNum(no);
        } else if (type == 4) {
            return noteBookDao.queryNoteBookByReadNum(no);
        } else {
            return noteBookDao.queryNoteBookByHot(no);
        }
    }

    @Override
    public HashSet<String> selectAllTypes(Integer no) {
        ArrayList<String> types = noteBookDao.selectAllTypes(no);
        HashSet<String> res = new HashSet<>();
        for (String type : types) {
            if (type != null && type != " " && type != "") {
                String[] t = type.split("-");
                for (String s : t) {
                    if (!"".equals(s) && !s.equals("null")) {
                        res.add(s);
                    }
                }
            }
        }
        return res;
    }

    /**
     * 根据标签查找笔记
     */
    @Override
    public Map<String, ArrayList<NoteBook>> selectNotebookByType(Integer no) {
        Map<String, ArrayList<NoteBook>> res = new HashMap<>();
        HashSet<String> types = this.selectAllTypes(no);
        for (String type : types) {
            res.put(type, noteBookDao.selectNotebookByType("-" + type + "-"));
        }
        return res;
    }

    /**
     * 搜索笔记本
     */
    @Override
    public LinkedHashSet<NoteBook> searchNotebooks(String keyWord) {
        LinkedHashSet<NoteBook> res = new LinkedHashSet<>();
        List<NoteBook> noteBooks = noteBookDao.accurateSearchNoteBook(keyWord);
        res.addAll(noteBooks);
        List<NoteBook> noteBooks1 = noteBookDao.fuzzySearchNoteBook1(keyWord);
        res.addAll(noteBooks1);
        List<NoteBook> noteBooks2 = noteBookDao.fuzzySearchNoteBook2(keyWord);
        res.addAll(noteBooks2);
        List<NoteBook> noteBooks3 = noteBookDao.fuzzySearchNoteBook3(keyWord);
        res.addAll(noteBooks3);
        return res;
    }

    @Override
    public ArrayList<NoteBook> getColNotebook(Integer no) {
        ArrayList<NoteBook> notebooks = new ArrayList<>();
        LinkedHashSet<Integer> colNos = noteDao.getColNo(no, 1);
        for (Integer colNo : colNos) {
            NoteBook nbook = noteBookDao.getNoteBook2(colNo);
            if (nbook != null) {
                notebooks.add(nbook);
            }
        }
        return notebooks;
    }

}
