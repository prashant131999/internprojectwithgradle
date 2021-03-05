package eccomapp.api;


import eccomapp.entity.ProductEntity;
import eccomapp.exception.InvalidInputException;
import eccomapp.service.ProductService;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequestMapping("/product")
@RestController
public class ProductControllerApi {
    private static java.sql.Connection connection = eccomapp.util.Connection.create();
    private ProductService productService = new ProductService();

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success|OK"),
            @ApiResponse(code = 401, message = "not authorized!"),
            @ApiResponse(code = 403, message = "forbidden!!!"),
            @ApiResponse(code = 404, message = "not found in database!!!"),
            @ApiResponse(code = 500, message = "sql exception")})

    @PostMapping("/addProduct")
    public ResponseEntity addProduct(@Valid @RequestBody ProductEntity productEntity) {
        try {
            productService.addproduct(connection, productEntity);
        } catch (InvalidInputException e) {
            return new ResponseEntity(e.getErrorMessage(),HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity("Product Added", HttpStatus.OK);
    }
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success|OK"),
            @ApiResponse(code = 401, message = "not authorized!"),
            @ApiResponse(code = 403, message = "forbidden!!!"),
            @ApiResponse(code = 404, message = "not found in database!!!"),
            @ApiResponse(code = 500, message = "sql exception")})

    @DeleteMapping("/deleteProduct")
    public ResponseEntity deleteUser(@Valid @RequestBody ProductEntity productEntity) {
        boolean flag = true;
        if (flag) {
            productService.deleteProduct(connection, productEntity);
            flag = false;
        }
        if (flag) {
            return new ResponseEntity("Product not deleted",HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity("Product deleted",HttpStatus.OK);
    }
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success|OK"),
            @ApiResponse(code = 401, message = "not authorized!"),
            @ApiResponse(code = 403, message = "forbidden!!!"),
            @ApiResponse(code = 404, message = "not found in database!!!"),
            @ApiResponse(code = 500, message = "sql exception")})

    @PutMapping("/updatedProductQuantity")
    public ResponseEntity updateUser(@Valid @RequestBody ProductEntity productEntity)
    {
            try {
                productService.updateProductQuantity(connection,productEntity);
            } catch (InvalidInputException e) {
                return new ResponseEntity(e.getErrorMessage(),HttpStatus.BAD_REQUEST);
            }
        return new ResponseEntity("Product Quantity updated",HttpStatus.OK);
    }
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success|OK"),
            @ApiResponse(code = 401, message = "not authorized!"),
            @ApiResponse(code = 403, message = "forbidden!!!"),
            @ApiResponse(code = 404, message = "not found in database!!!"),
            @ApiResponse(code = 500, message = "sql exception")})
    @PutMapping("/updatedProductName/{oldName}/{newName}")
    public ResponseEntity updateProduct(@PathVariable String oldName,@PathVariable String newName)
    {
            try {
                productService.updateProductName(connection,newName,oldName);
            } catch (InvalidInputException e) {
                return new ResponseEntity(e.getErrorMessage(),HttpStatus.BAD_REQUEST);
            }
        return new ResponseEntity("Product name updated",HttpStatus.OK);
    }
}
