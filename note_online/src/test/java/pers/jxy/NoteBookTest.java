package pers.jxy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pers.jxy.dao.NoteBookDao;
import pers.jxy.service.NoteBookService;

/**
 * @author:靳新宇
 * @date : 11-08 21:18
 */
@SpringBootTest
public class NoteBookTest {
    @Autowired
    NoteBookService noteBookService;


    @Autowired
    NoteBookDao noteBookDao;

}
