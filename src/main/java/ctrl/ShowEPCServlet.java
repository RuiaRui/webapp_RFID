package ctrl;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import service.Impl.IProductServiceImpl;
import vo.Product;


@WebServlet(name = "ShowEPCServlet")
public class ShowEPCServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        //设置编码
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        IProductServiceImpl productService = new IProductServiceImpl();
        List<Product> products=productService.selectAllProduct();
        JSONArray productJA = new JSONArray();
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        for(Product p : products){
            JSONObject jo = new JSONObject();
            jo.put("EPC", p.getEPC());
            if(!(p.getProductID()==null)){
                jo.put("id", p.getProductID());
                jo.put("name", p.getProductName());
                Date date1 = p.getEntryDate();
                String dateStr1 = format.format(date1);
                jo.put("eDate", dateStr1);
                Date date2 = p.getLeftDate();
                String dateStr2 = format.format(date2);
                jo.put("lDate", dateStr2);

            }else{
                jo.put("id","");
                jo.put("name", "");
                jo.put("eDate", "");
                jo.put("lDate", "");

            }
            productJA.add(jo);


        }

        JSONObject number = new JSONObject();
        number.put("number", products.size());

        JSONObject result = new JSONObject();
        result.put("products", productJA);
        result.put("number", number);

        response.getWriter().write(String.valueOf(result));
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
