package service.Impl;

import dao.mapper.ProductMapper;
import org.apache.ibatis.session.SqlSession;
import service.IProductService;
import utils.SqlSessionUtil;
import vo.Product;
import java.sql.SQLException;
import java.util.List;

public class IProductServiceImpl implements IProductService {
    SqlSession sqlSession = new SqlSessionUtil().sqlSession;
    ProductMapper productMapper = sqlSession.getMapper(ProductMapper.class);



    @Override
    public void writeProduct(Product product) {
        productMapper.writeProduct(product);
        sqlSession.commit();

    }

    @Override
    public void deleteProduct(String id) {
        productMapper.deleteProduct(id);

    }

    @Override
    public Product selectProduct(String id) {
        Product product=new Product();
        product=productMapper.selectProduct(id);
        return product;
    }

    @Override
    public List<Product> selectAllProduct() {
        List<Product> products=productMapper.selectAllProduct();
        return products;
    }



    @Override
    public void addEPC( String EPC) {
        productMapper.addEPC(EPC);
        sqlSession.commit();

    }

    @Override
    public void alterEPC(String oldEPC,String newEPC){
        productMapper.alterEPC(oldEPC, newEPC);
        sqlSession.commit();
    }





}
