package pers.jxy;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import pers.jxy.util.ShieldUtil;

import java.io.File;

@SpringBootApplication
@MapperScan({"pers.jxy.dao"})
public class NoteOnlineApplication {

    public static void main(String[] args) {
        File file = new File("D:/artices/");
        file.mkdirs();
        ShieldUtil.initializeShield();
        SpringApplication.run(NoteOnlineApplication.class, args);
    }
}
