package pers.jxy;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pers.jxy.dao.NoteBookDao;
import pers.jxy.service.NoteBookService;
import pers.jxy.util.ShieldUtil;

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

    @Test
    public void test1(){
        System.out.println(ShieldUtil.addShield("色情"));
    }

    @Test
    public void test2(){
//        System.out.println(ShieldUtil.SHIELDS);
    }

}
