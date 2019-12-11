
package ctrl;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Date;
import java.util.List;

//import com.cetc7.UHFReader.UHFReaderClass;
import service.Impl.IProductServiceImpl;




@WebServlet(name = "EPCModifyServlet")
public class EPCModifyServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        //设置编码
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");


        IProductServiceImpl productService = new IProductServiceImpl();
        //UHFReaderClass RFID=UHFReaderClass.GetUHFReader();

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

        String oldEPC=jsonObject.getString("oldEPC");
        byte[] oldepc=HexString2Bytes(oldEPC);
        String newEPC=jsonObject.getString("newEPC");
        byte[] newepc=HexString2Bytes(newEPC);
        String pwd=jsonObject.getString("pwd");
        byte[] password=HexString2Bytes(pwd);
        String epcL=jsonObject.getString("epcLength");
        int epcLength= Integer.valueOf(epcL);
        String sta=jsonObject.getString("startAddress");
        int startAddress= Integer.valueOf(sta);



        productService.alterEPC(oldEPC,newEPC);
        response.getWriter().write("EPC码修改成功");

//        int i=RFID.Write6CEPC(oldepc, newepc, password,epcLength , startAddress);
//        if(i==1){
//
//        }


    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);


    }

     static byte[] HexString2Bytes(String src){
        if (null == src||0== src.length()) {
            return null;
        }
        byte[] ret = new byte[src.length()/2];
        byte[] tmp = src.getBytes();
        for(int i=0; i<src.length()/2; i++){
            ret[i] = uniteBytes(tmp[i*2], tmp[i*2+1]);
        }
        return ret;
    }
     static byte uniteBytes(byte src0, byte src1) {
        byte _b0 = Byte.decode("0x" + new String(new byte[] {src0})).byteValue();
        _b0 = (byte) (_b0 << 4);
        byte _b1 = Byte.decode("0x" + new String(new byte[] { src1 })).byteValue();
        byte ret = (byte) (_b0 ^ _b1);
        return ret;
    }
}


