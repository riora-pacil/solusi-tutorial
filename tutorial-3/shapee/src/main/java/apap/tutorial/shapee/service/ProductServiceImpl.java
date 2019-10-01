package apap.tutorial.shapee.service;

import apap.tutorial.shapee.model.ProductModel;
import apap.tutorial.shapee.model.StoreModel;
import apap.tutorial.shapee.repository.ProductDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductDb productDb;

    @Override
    public void addProduct(ProductModel product){
        productDb.save(product);
    }

    @Override
    public List<ProductModel> findAllProductByStoreId(Long storeId){
        return productDb.findByStoreModelId(storeId);
    }

    @Override
    public ProductModel getProductById(Long productId){
        if(productDb.findById(productId).isPresent()){
            return productDb.findById(productId).get();
        }
        else{
            return null;
        }
    }

    @Override
    public ProductModel changeProduct(ProductModel product){

        //Mengambil objek product yang akan diubah dari database
        ProductModel targetProduct = productDb.findById(product.getId()).get();

        //Mengubah atribut
        targetProduct.setNama(product.getNama());
        targetProduct.setHarga(product.getHarga());
        targetProduct.setDeskripsi(product.getDeskripsi());
        targetProduct.setStok(product.getStok());
        productDb.save(targetProduct);

        return targetProduct;
    }

    @Override
    public void deleteProduct(ProductModel productModel){
        productDb.delete(productModel);

    }



}
