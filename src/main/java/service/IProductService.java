package service;


import vo.Product;

import java.util.List;

public interface IProductService {

    void deleteProduct(String id);

    void writeProduct(Product product);

    Product selectProduct( String id);


    //void wirteQR(int id, String QR);


    void addEPC(String EPC);

    void alterEPC(String oldEPC,String newEPC);



    List<Product> selectAllProduct();
}
