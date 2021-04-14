package pers.jxy;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;
import pers.jxy.util.ShieldUtil;

import java.io.File;

@SpringBootApplication
@MapperScan({"pers.jxy.dao"})
@PropertySource("classpath:application.yml")
public class NoteOnlineApplication {

    private String notePath;

    private String userHeadPath;

    private String adminHeadPath;

    public static void main(String[] args) {
        File file = new File("D:/artices/");
        if (!file.exists()) {
            file.mkdirs();
        }
        ShieldUtil.initializeShield();
        SpringApplication.run(NoteOnlineApplication.class, args);
    }
}
