package pers.jxy;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
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
    public void test1() {
        System.out.println(ShieldUtil.addShield("色情"));
    }

    @Autowired
    RedisTemplate redisTemplate;

    @Test
    public void test2() {
//        ValueOperations operations = redisTemplate.opsForValue();
//        operations.set("sr","SpringBoot--SpringData Redis");
        Long expire = redisTemplate.opsForValue().getOperations().getExpire("black-menu-1000001");
        System.out.println(expire);

//        System.out.println(operations.get("sr"));
    }

}