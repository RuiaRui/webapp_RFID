package ctrl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import service.Impl.IProductServiceImpl;
import service.Impl.IReaderServiceImpl;
import utils.DataUtils;
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

@WebServlet(name = "TagPositionServlet")
public class TagPositionServlet extends HttpServlet {
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

        JSONArray result = new JSONArray();
        for(int i=0;i<Parameter.tagNum;i++) {
            Tag t=tagList.get(i);
            JSONObject jo = new JSONObject();
            jo.put("EPC", t.id);
            int num=0;
            String s="";
            String temp="";
             for(int j=0;j<Parameter.readerNum;j++){
                 if(readerService.checkTagforOne(readerList.get(j),t)){
                     temp=temp+j+" ";
                     num++;
                 }
             }
             if(num==0){
                 s="不在读写器范围内";
             }else if(num==1){
                 s="在读写器"+temp+"的范围内";
             }else {
                 s="在读写器"+temp+"的交界范围内，请注意信号干扰";
             }

             jo.put("result",s);
            result.add(jo);
        }
        JSONObject para = new JSONObject();
        para.put("tagNum", Parameter.tagNum);
        para.put("readerNum", Parameter.readerNum);
        para.put("readerPosition",Parameter.readerPositionList);
        para.put("tagPosition",Parameter.tagPositionList);
        para.put("slot",Parameter.currentslot+1);

        JSONObject out = new JSONObject();
        out.put("result", result);
        out.put("para", para);

        response.getWriter().write(String.valueOf(out));
        Parameter.nextSlot();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }


}
