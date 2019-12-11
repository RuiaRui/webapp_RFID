package utils;

import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.http.HttpServletRequest;

public class WebUtils {
    /**
     * 用于直接将表单提交的信息封装到javaBean中
     *
     * @param req request请求
     * @param clazz JavaBean类
     * @param <T> JavaBean类型
     *
     * @return JavaBean
     */
    public static <T> T copyToBean(HttpServletRequest req, Class<T> clazz) {
        try {
            T t = clazz.newInstance();
            BeanUtils.populate(t, req.getParameterMap());
            return t;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
