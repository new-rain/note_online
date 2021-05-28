package pers.jxy;

import com.github.pagehelper.PageInfo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import pers.jxy.dao.NoteBookDao;
import pers.jxy.entity.Appeal;
import pers.jxy.service.AdminService;
import pers.jxy.service.AppealService;
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
    AdminService adminService;

    @Autowired
    NoteBookDao noteBookDao;

    @Autowired
    AppealService appealService;

    @Test
    public void test1() {
        System.out.println(ShieldUtil.addShield("色情"));
    }

    @Autowired
    RedisTemplate redisTemplate;

    @Test
    public void test2() {
        PageInfo<Appeal> appealLog = appealService.getAppealLog(90000001, 3);
        for (Appeal appeal : appealLog.getList()) {
            System.out.println(appeal);
        }
        System.out.println(appealLog);
    }
    @Test
    public void test3() {
                                                                                                      }

}