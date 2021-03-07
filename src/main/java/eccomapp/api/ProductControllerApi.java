package eccomapp.api;

import eccomapp.entity.ProductEntity;
import eccomapp.exception.ApplicationRuntimeException;
import eccomapp.exception.InvalidInputException;
import eccomapp.model.ProductModel;
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

    @DeleteMapping("/deleteProduct/{prodName}")
    public ResponseEntity deleteProduct(@Valid @PathVariable String prodName) {
        ProductModel productModel=null;
        try {
            productModel=productService.displayProducts(prodName,connection);
            if(productModel!=null) {
                productService.deleteProduct(connection,prodName);
            }
            else
            {
                return new ResponseEntity("Product not exist",HttpStatus.BAD_REQUEST);
            }
        } catch (InvalidInputException e) {
            return new ResponseEntity(e.getErrorMessage(),HttpStatus.BAD_REQUEST);
        }
        catch (ApplicationRuntimeException e)
        {
            return new ResponseEntity(e.getErrorMessage(),HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity("Product deleted",HttpStatus.OK);
    }
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success|OK"),
            @ApiResponse(code = 401, message = "not authorized!"),
            @ApiResponse(code = 403, message = "forbidden!!!"),
            @ApiResponse(code = 404, message = "not found in database!!!"),
            @ApiResponse(code = 500, message = "sql exception")})

    @PutMapping("/updatedProductQuantity/{prodName}/{quantity}")
    public ResponseEntity updateProductQuantity(@PathVariable String prodName,@PathVariable int quantity)
    {
        ProductModel productModel=null;
        try {
            productModel=productService.displayProducts(prodName,connection);
            if(productModel!=null) {
                productService.updateProductQuantity(connection,prodName,quantity);
            }
            else
            {
                return new ResponseEntity("Product not exist",HttpStatus.BAD_REQUEST);
            }
        } catch (InvalidInputException e) {
            return new ResponseEntity(e.getErrorMessage(),HttpStatus.BAD_REQUEST);
        }
        catch (ApplicationRuntimeException e)
        {
            return new ResponseEntity(e.getErrorMessage(),HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity("Product quantity updated",HttpStatus.OK);
    }
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success|OK"),
            @ApiResponse(code = 401, message = "not authorized!"),
            @ApiResponse(code = 403, message = "forbidden!!!"),
            @ApiResponse(code = 404, message = "not found in database!!!"),
            @ApiResponse(code = 500, message = "sql exception")})
    @PutMapping("/updatedProductName/{oldName}/{newName}")
    public ResponseEntity updateProductName(@PathVariable String oldName,@PathVariable String newName)
    {
        ProductModel productModel=null;
        try {
            productModel=productService.displayProducts(oldName,connection);
            if(productModel!=null) {
                productService.updateProductName(connection,newName,oldName);
            }
            else
            {
                return new ResponseEntity("Product not exist",HttpStatus.BAD_REQUEST);
            }
            } catch (InvalidInputException e) {
                return new ResponseEntity(e.getErrorMessage(),HttpStatus.BAD_REQUEST);
            }
        catch (ApplicationRuntimeException e)
        {
            return new ResponseEntity(e.getErrorMessage(),HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity("Product name updated",HttpStatus.OK);
    }
    @GetMapping("/displayProductDetail/{productName}")
    public ResponseEntity productDetail(@Valid @PathVariable String productName) {
        ProductModel productDetail;
        try {
            productDetail=productService.displayProducts(productName,connection);
        } catch (InvalidInputException e) {
            return new ResponseEntity(e.getErrorMessage(), HttpStatus.BAD_REQUEST);
        }
        catch (ApplicationRuntimeException e)
        {
            return new ResponseEntity(e.getErrorMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(productDetail,HttpStatus.OK);
    }
}
