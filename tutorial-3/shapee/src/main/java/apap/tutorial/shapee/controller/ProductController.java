package apap.tutorial.shapee.controller;

import apap.tutorial.shapee.model.ProductModel;
import apap.tutorial.shapee.model.StoreModel;
import apap.tutorial.shapee.service.ProductService;
import apap.tutorial.shapee.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ProductController {
    @Autowired
    ProductService productService;

    @Qualifier("storeServiceImpl")
    @Autowired
    StoreService storeService;

    @RequestMapping(value = "product/add/{storeId}", method = RequestMethod.GET)
    private String addProductFormPage(@PathVariable(value = "storeId") Long storeId, Model model){
        ProductModel product = new ProductModel();
        StoreModel store = storeService.getStoreById(storeId);
        product.setStoreModel(store);

        model.addAttribute("product", product);

        return "form-add-product";

    }

    @RequestMapping(value = "product/add", method = RequestMethod.POST)
    private String addProductSubmit(@ModelAttribute ProductModel productModel, Model model){
        productService.addProduct(productModel);

        model.addAttribute("nama", productModel.getNama());

        return "add-product";
    }

    //API yang digunakan untuk menuju halaman form change product
    @RequestMapping(value="product/change/{idProduct}", method = RequestMethod.GET)
    public String changeProductFormPage(@PathVariable Long idProduct, Model model) {
        ProductModel existingProduct = productService.getProductById(idProduct);
        if(existingProduct!=null){
            model.addAttribute("product", existingProduct);
            return "form-change-product";

        }
        else{
            model.addAttribute("id", idProduct);
            return  "product-not-found";
        }
    }
    //API yang digunakan untuk submit form change product
    @RequestMapping(value="product/change/{idProduct}", method = RequestMethod.POST)
    public String changeProductFormSubmit(@PathVariable Long idProduct, @ModelAttribute ProductModel product, Model model) {

        ProductModel productModel = productService.changeProduct(product);
        model.addAttribute("product", productModel);
        return "change-product";
    }

    //API yang digunakan untuk delete product
    @RequestMapping(value="product/delete/{idProduct}")
    public String deleteWithPathVar(@PathVariable Long idProduct, Model model) {
        ProductModel productTarget =  productService.getProductById(idProduct);
        if(productTarget!=null){
            productService.deleteProduct(productTarget);
            return "delete-product";
        }
        else{
            model.addAttribute("id", idProduct);
            return "product-not-found";
        }
    }


}
