package pers.jxy;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import pers.jxy.util.ShieldUtil;

@SpringBootApplication
@MapperScan({"pers.jxy.dao"})
@EnableCaching
public class NoteOnlineApplication {
    public static void main(String[] args) {
        ShieldUtil.initializeShield();
        SpringApplication.run(NoteOnlineApplication.class, args);
    }
}
