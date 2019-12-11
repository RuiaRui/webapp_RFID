
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



@WebServlet(name = "EPCAddServlet")
public class EPCAddServlet extends HttpServlet {

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

        String epc=jsonObject.getString("EPCN");
        String pwd=jsonObject.getString("pwdN");
        String epcLength=jsonObject.getString("epcLengthN");
        String startAddress=jsonObject.getString("startAddressN");





        productService.addEPC(epc);


        response.getWriter().write("EPC码添加成功");



    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
