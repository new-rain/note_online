package pers.jxy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.UUID;

/**
 * @author : 靳新宇
 * @date : 03-28 20:17
 */
@Controller
public class TestController {
    @GetMapping("/adminRegister")
    public String adminRegister() {
        return "adminRegister";
    }

    @GetMapping("/test")
    public String test() {
        return "test";
    }


    @GetMapping("/test1")
    public void test1(HttpServletResponse resp) {
        try {
            resp.sendRedirect("http://localhost:8080/");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @ResponseBody
    @GetMapping("/getimg")
    public byte[] getimg() {
        byte[] imageBytes = null;
        try {
            FileInputStream fileInputStream = new FileInputStream(new File("G:\\毕业设计\\项目\\upload\\41E00BF037AE4401AC48D022B72A3681_1.jpg"));
            imageBytes = new byte[fileInputStream.available()];
            fileInputStream.read(imageBytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return imageBytes;
    }

    @ResponseBody
    @PostMapping(value = "/upload")
    public String testFile(MultipartFile file) throws IOException {
        String path = "D:\\note_online\\upload";
        // 创建File对象，一会向该路径下上传文件
        File fileload = new File(path);
        // 判断路径是否存在，如果不存在，创建该路径
        if (!fileload.exists()) {
            fileload.mkdirs();
        }
        // 获取到上传文件的名称
        String filename = file.getOriginalFilename();
        String uuid = UUID.randomUUID().toString().replaceAll("-", "").toUpperCase();
        // 把文件的名称唯一化
        filename = uuid + "_" + filename;
        // 上传文件
        file.transferTo(new File(fileload, filename));
        return "adminRegister";
    }
}
