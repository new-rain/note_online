package pers.jxy.util;

import org.apache.ibatis.jdbc.SQL;

/**
 * @author : 靳新宇
 * @date : 04-02 19:36
 */
public class SQLUtil {

    public String selectAdminOrder(Integer type) {
        return new SQL() {
            {
                SELECT("id,name,mobile,email,ir_num,ap_num,achievement");
                FROM("admin");
                WHERE(" id != 1");
                if (type == 1) {
                    ORDER_BY("ir_num");
                } else if (type == 2) {
                    ORDER_BY("ir_num desc");
                } else if (type == 3) {
                    ORDER_BY("ap_num");
                } else if (type == 4) {
                    ORDER_BY("ap_num desc");
                } else if (type == 5) {
                    ORDER_BY("achievement");
                } else if (type == 6) {
                    ORDER_BY("achievement desc");
                } else if (type == 7) {
                    ORDER_BY("id");
                }else if (type == 8) {
                    ORDER_BY("id desc");
                }
            }
        }.toString();
    }
}

