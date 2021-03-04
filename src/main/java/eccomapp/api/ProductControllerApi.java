package eccomapp.api;


import eccomapp.entity.ProductEntity;
import eccomapp.exception.InvalidInputException;
import eccomapp.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RequestMapping("/product")
@RestController
public class ProductControllerApi {
    private static java.sql.Connection connection = eccomapp.util.Connection.create();
    private ProductService productService = new ProductService();

    @PostMapping("/addProduct")
    public ResponseEntity addProduct(@Valid @RequestBody ProductEntity productEntity) {
        try {
            productEntity.setProductid(UUID.randomUUID());
            productService.addproduct(connection, productEntity);
            return new ResponseEntity("Product Added", HttpStatus.OK);
        } catch (InvalidInputException e) {
            e.logError();
        }
        return new ResponseEntity("Product not Added",HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/deleteProduct")
    public ResponseEntity deleteUser(@Valid @RequestBody ProductEntity productEntity) {
        boolean flag = true;
        productEntity.setProductid(UUID.randomUUID());
        if (flag) {
            productService.deleteProduct(connection, productEntity);
            flag = false;
        }
        if (flag) {
            return new ResponseEntity("Product not placed",HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity("Product placed",HttpStatus.OK);
    }
    @PutMapping("/updatedProductQuantity")
    public ResponseEntity updateUser(@Valid @RequestBody ProductEntity productEntity)
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
            return new ResponseEntity("Product Quantity not updated",HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity("Product Quantity updated",HttpStatus.OK);

    }
    @PutMapping("/updatedProductName/{oldName}/{newName}")
    public ResponseEntity updateProduct(@PathVariable String oldName,@PathVariable String newName)
    {
        boolean flag=true;
        if(flag) {
            productService.updateProductName(connection,newName,oldName);
            flag = false;
        }
        if(flag)
        {
            return new ResponseEntity("Product name not updated",HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity("Product name updated",HttpStatus.OK);

    }
}
