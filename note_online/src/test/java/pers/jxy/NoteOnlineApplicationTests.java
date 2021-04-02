package pers.jxy;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import pers.jxy.dao.AdminDao;
import pers.jxy.service.AdminService;
import pers.jxy.service.UserService;

import javax.annotation.Resource;
import java.util.Map;

@SpringBootTest
class NoteOnlineApplicationTests {

    @Resource
    UserService userService;


    @Autowired
    JavaMailSenderImpl javaMailSender;

    @Autowired
    AdminDao adminDao;

    @Autowired
    AdminService adminService;

    @Test
    void test(){
        System.out.println(adminDao.selectRegisterNum("12346")==null);
    }

    @Test
    void test1(){
//        System.out.println(adminService.getRNum(50));
    }

    @Test
    void test2(){
        Map<String, Integer> ra = adminDao.selectRegisterNum("299EpaIQr4p6V3ZvM1opPA4HAL40Mv436o1DkY3qg3D044X5oL");
        System.out.println(ra.get("rnum"));
        System.out.println(ra.get("admin"));
    }
//
//    @Test
//    void contextLoads() {
//        SimpleMailMessage message = new SimpleMailMessage();
//        //设置邮件标题
//        message.setSubject("爱你");
//        //设置邮件内容
//        message.setText("摸摸哒");
//        //发送给哪个账号
//        message.setTo("2504574877@qq.com");
//        //设置发件人（邮件中显示的）
//        message.setFrom("1551903505@qq.com");
//        //发送邮件
//        javaMailSender.send(message);
//    }
//
////    @Test
////    void test() {
////        userService.sendCode("1024", "2504574877@qq.com");
////    }
//
//    @Test
//    void testRegister() {
//        User user = new User();
//        user.setNickname("ls");
//        user.setPassword("123");
//        user.setEmail("1234567890@qq.com");
//        Integer res[] = userService.register(user);
//        System.out.println(res);
//    }
//
////    @Test
////    void testUpdatePassword() {
////        User user = new User();
////        user.setNo(10002);
////        user.setPassword("456");
////        user.setEmail("3418135780@qq.com");
////        Integer res = userService.updatePassword(user);
////        System.out.println(res);
////    }
//
//    @Test
//    public void test5() {
//        User user = new User();
//        user.setNickname("zs");
//        System.out.println(userService.register(user));
//    }
//
//    @Test
//    void testTime() {
//        String time = "Tue Nov 03 2020 00:00:00 GMT+0800 (中国标准时间)";
//        Date date = str2Date(time);
//        System.out.println(date.toString());
//    }
//
//    public static Date str2Date(String dateString) {
//
//        String FORMAT_STRING = "yyyy-MM-dd HH:mm:ss";
//
//        String[] REPLACE_STRING = new String[]{"GMT+0800", "GMT+08:00"};
//
//        String SPLIT_STRING = "(中国标准时间)";
//
//        try {
//            dateString = dateString.split(Pattern.quote(SPLIT_STRING))[0].replace(REPLACE_STRING[0], REPLACE_STRING[1]);
//            SimpleDateFormat sf1 = new SimpleDateFormat("E MMM dd yyyy HH:mm:ss z", Locale.US);
//            Date date = sf1.parse(dateString);
//            return date;
//        } catch (Exception e) {
//            throw new RuntimeException("时间转化格式错误" + "[dateString=" + dateString + "]" + "[FORMAT_STRING=" + FORMAT_STRING + "]");
//        }
//    }
////
////    @Test
////    void updateUser(){
////        User user = new User();
////        user.setNo(10002);
////        user.setNickname("秦一");
////        Integer res = userService.updateUser(user);
////        System.out.println(res);
////    }
//
//    @Test
//    void testMd() {
//        String arthicle = NoteUtil.getArthicle("E:\\Desktop\\test.md");
//        String s = NoteUtil.markdownToHtmlExtensions(arthicle);
//        System.out.println(s);
//    }
//
//    @Resource
//    CommentService commentService;
//
//    @Test
//    void t() {
//    }
//
//    @Test
//    void testNoteBook() {
//        NoteBook noteBook = new NoteBook();
//        noteBook.setBType("a-b-c-d-e");
//        System.out.println(noteBook);
//    }
//
//    @Test
//    void testSHA256() {
//        //a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3
//        User user = userService.login(10001, "123");
//        System.out.println(user);
//        String res = NoteBookOnlineUtils.getSHA256Res(user.getPassword());
//        System.out.println(res);
//        System.out.println(NoteBookOnlineUtils.getSHA256Res("123"));
//    }
//
//    @Test
//    void testymddate() throws ParseException {
//        String str = "2019-11-26T16:00:00.000+00:00";
//        String strDate = str.split("T")[0];
//
//        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
//        Date date = format.parse(strDate);
//        System.out.println(date);
//    }
//
////    @Test
////    void testSendMail(){
////        userService.sendCode("1551903505@qq.com");
////    }
//
//    @Test
//    void testa() {
//        System.out.println(userService.selectUserRelationship(10001, 10003));
//    }
//
//    @Resource
//    UserDao userDao;
//
//    @Test
//    void testx() {
////        Boolean aBoolean = userService.delRelationship(10004, 10001);
////        System.out.println(aBoolean);
////        System.out.println(userService.selectCareMe(10001));
//        System.out.println(userDao.selectEachOrder(10001, 10001));
//    }
//
//    @Autowired
//    CommentDao commentDao;
//    @Test
//    void tests(){
//        List<Comment> comments = commentDao.queryComment(100001, 0);
//        System.out.println(comments.get(0).getComments());
//        Date cTime = comments.get(0).getComments().get(0).getCTime();
//        Format format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        String format1 = format.format(cTime);
//        System.out.println(format1);
//        String s = cTime.toString();
//        String year=s.substring(s.length()-4,s.length());
//        System.out.println(year);
//        Calendar calendar = Calendar.getInstance();
//        int i = calendar.get(Calendar.YEAR);
//        System.out.println(Integer.valueOf(year)==calendar.get(Calendar.YEAR));
//    }
//    @Autowired
//    NoteService noteService;
//
//    @Test
//    void testss(){
//        Note note = noteService.queryNote(100025, 10001);
//        System.out.println(note.getArthicle());
//
//
//    }
}
