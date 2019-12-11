package utils;


import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import vo.*;

import java.sql.SQLException;
import java.util.List;

public class DBUtils {
    /**
     * 添加Bean
     *
     * @param t 传入的javaBean对象
     * @param <T> 泛型
     */
    public static <T> void addBean(T t){

    }

    /**
     * 更新Bean
     *
     * @param t 传入的javaBean对象
     * @param id 指定javaBean的id
     * @param <T> 泛型
     */
    public static <T> void updateBean(T t, String id) {

    }

//    @Deprecated
//    public static List<Client> queryAll() {
//        QueryRunner qr = new QueryRunner(C3P0Utils.getDataSource());
//        String sql = "SELECT * FROM productInfo";
//        List<Client> list = null;
//        try {
//            list = qr.query(sql, new BeanListHandler<Client>(Client.class));
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return list;
//    }


}
