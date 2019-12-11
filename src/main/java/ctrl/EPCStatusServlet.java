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


@WebServlet(name = "EPCStatusServlet")
public class EPCStatusServlet extends HttpServlet {

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
                jo.put("status", 1);

            }else{
                jo.put("status", 0);

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
