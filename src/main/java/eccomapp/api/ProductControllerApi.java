package eccomapp.api;


import com.fasterxml.jackson.databind.node.ObjectNode;
import eccomapp.entity.ProductEntity;
import eccomapp.exception.InvalidInputException;
import eccomapp.service.ProductService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RequestMapping("/product")
@RestController
public class ProductControllerApi {
    static java.sql.Connection connection = eccomapp.util.Connection.create();
    ProductService productService = new ProductService();

    @PostMapping("/addProduct")
    public String addProduct(@Valid @RequestBody ProductEntity productEntity) {
        try {
            productEntity.setProductid(UUID.randomUUID());
            productService.addproduct(connection, productEntity);
            return "Product Added Successfully";
        } catch (InvalidInputException e) {
            e.logError();
        }
        return "Product not added to database";
    }

    @DeleteMapping("/deleteProduct")
    public String deleteUser(@Valid @RequestBody ProductEntity productEntity) {
        boolean flag = true;
        productEntity.setProductid(UUID.randomUUID());
        if (flag) {
            productService.deleteProduct(connection, productEntity);
            flag = false;
        }
        if (flag) {
            return "Product not present in  database";
        }
        return "product deleted" ;
    }
    @PutMapping("/updatedProductQuantity")
    public String updateUser(@Valid @RequestBody ProductEntity productEntity)
    {
        boolean flag=true;
        productEntity.setProductid(UUID.randomUUID());
        if(flag) {
            try {
                productService.updateProductQuantity(connection,productEntity);
            } catch (InvalidInputException e) {
                e.logError();
            }
            flag = false;
        }
        if(flag)
        {
            return "product quantity not updated";
        }
        return "product quantity updated";

    }
    @PutMapping("/updatedProductName")
    public String updateProduct(@Valid @RequestBody ObjectNode objectNode)
    {
        boolean flag=true;
        if(flag) {
            String oldName=objectNode.get("oldName").asText();
            String newName=objectNode.get("newName").asText();

            productService.updateProductName(connection,newName,oldName);
            flag = false;
        }
        if(flag)
        {
            return "product name not updated";
        }
        return "product name updated";

    }
}
