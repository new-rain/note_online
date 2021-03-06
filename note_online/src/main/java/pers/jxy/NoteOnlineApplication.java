package pers.jxy;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan({"pers.jxy.dao"})
public class NoteOnlineApplication {

    public static void main(String[] args) {
        SpringApplication.run(NoteOnlineApplication.class, args);
    }

}
