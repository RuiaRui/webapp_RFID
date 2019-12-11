package ctrl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import service.Impl.IProductServiceImpl;
import service.Impl.IReaderServiceImpl;
import utils.Method;
import utils.Parameter;
import vo.Product;
import vo.Reader;
import vo.Tag;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "TagStatusServlet")
public class TagStatusServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        //设置编码
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        IProductServiceImpl productService = new IProductServiceImpl();
        IReaderServiceImpl readerService=new IReaderServiceImpl();
        //得到tags和readers信息
        String path1 = TagPositionServlet.class.getClassLoader().getResource("tags.json").getPath();
        String path2 = TagPositionServlet.class.getClassLoader().getResource("readers.json").getPath();
        Parameter.setTagParameter(path1,Parameter.currentslot);
        Parameter.setReaderParameter(path2);

        List<Product> products=productService.selectAllProduct();



        List<String> tagid=new ArrayList<>();
        for(Product product:products){
            tagid.add(product.getEPC());
        }


        List<Tag> tagList= Method.Position2TagList(tagid,Parameter.tagPositionList);
        List<Reader> readerList=Method.Position2ReaderList(Parameter.readerPositionList);


        JSONArray result1 = new JSONArray();
        for(int i=0;i<Parameter.tagNum;i++) {
            Tag t=tagList.get(i);
            Product p=products.get(i);
            JSONObject jo = new JSONObject();
            jo.put("EPC", t.id);

            if(!(p.getProductID()==null)){
                jo.put("status", 1);

            }else{
                jo.put("status", 0);
            }
            if (readerService.checkTagforAll(readerList, t)) {
                jo.put("exist", 1);
            } else {
                jo.put("exist", 0);

            }
            result1.add(jo);
        }
        JSONObject number = new JSONObject();
        number.put("number", Parameter.tagNum);

        JSONObject result = new JSONObject();
        result.put("tag", result1);
        result.put("number", number);
        result.put("slot",Parameter.currentslot+1);

        response.getWriter().write(String.valueOf(result));
        Parameter.nextSlot();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
