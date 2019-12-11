package ctrl;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Date;
import java.util.List;

//import com.cetc7.UHFReader.UHFReaderClass;
import service.Impl.IProductServiceImpl;
import vo.Product;


@WebServlet(name = "EPCWriteServlet")
public class EPCWriteServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        //设置编码
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");


        IProductServiceImpl productService = new IProductServiceImpl();
        //获取输入流

        ServletInputStream inputStream = request.getInputStream();
        BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder stringBuilder=new StringBuilder();
        String temp="";
        while((temp=bufferedReader.readLine())!=null){
            stringBuilder.append(temp);
        }
        //处理成json对象
        String jsonString=stringBuilder.toString();
        JSONObject jsonObject= JSON.parseObject(jsonString);
        //解析json对象

        String epc=jsonObject.getString("epc");
        String productId=jsonObject.getString("pid");
        String productName=jsonObject.getString("pname");
        Date entryDate=Date.valueOf(jsonObject.getString("eDate"));
        Date leftDate=Date.valueOf(jsonObject.getString("lDate"));

        Product product=new Product();
        product.setEPC(epc);
        product.setProductID(productId);
        product.setProductName(productName);
        product.setEntryDate(entryDate);
        product.setLeftDate(leftDate);
        product.setQR("");

        productService.writeProduct(product);


        response.getWriter().write("产品绑定成功");



        //判断是否存在这个产品
            //写epc
        //存在：
            //保错


    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
