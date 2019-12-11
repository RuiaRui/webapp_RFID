package dao.mapper;

import org.apache.ibatis.annotations.Param;


import java.util.List;
import vo.Product;

public interface ProductMapper {

    void deleteProduct(String productID);



    Product selectProduct(@Param("productID") String productID);


    //void wirteQR(@Param("id") int id,@Param("QR") String QR);

    //写入EPC
    void writeProduct(Product product);


    void addEPC(@Param("EPC") String EPC);

    void alterEPC(@Param("oldEPC") String oldEPC,@Param("newEPC") String newEPC);


    List<Product> selectAllProduct();



}
