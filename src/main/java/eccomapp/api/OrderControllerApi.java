package eccomapp.api;

import eccomapp.entity.OrderEntity;
import eccomapp.entity.ProductEntity;
import eccomapp.exception.InvalidInputException;
import eccomapp.model.OrderModel;
import eccomapp.service.OrderService;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequestMapping("/order")
@RestController
public class OrderControllerApi {
    private static java.sql.Connection connection = eccomapp.util.Connection.create();
    private OrderService orderService = new OrderService();
    private OrderEntity orderEntity = new OrderEntity();
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success|OK"),
            @ApiResponse(code = 401, message = "not authorized!"),
            @ApiResponse(code = 403, message = "forbidden!!!"),
            @ApiResponse(code = 404, message = "not found in database!!!"),
            @ApiResponse(code = 500, message = "sql exception")})

    @PostMapping("/addOrder")
    public ResponseEntity addOrder(@Valid @RequestBody OrderModel orderModel) {
        String email = orderModel.getEmail();
        String name = orderModel.getName();
        try {
            orderService.createOrder(connection, email, name);
        } catch (InvalidInputException e) {
            return new ResponseEntity(e.getErrorMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity("order placed", HttpStatus.OK);
    }
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success|OK"),
            @ApiResponse(code = 401, message = "not authorized!"),
            @ApiResponse(code = 403, message = "forbidden!!!"),
            @ApiResponse(code = 404, message = "not found in database!!!"),
            @ApiResponse(code = 500, message = "sql exception")})


    @GetMapping("/displayProducts")
    public ResponseEntity productsInCart() {
    List<ProductEntity> productList;
        try {
            productList=orderService.displayOrder(connection);
        } catch (InvalidInputException e) {
            return new ResponseEntity(e.getErrorMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(productList,HttpStatus.OK);
    }
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success|OK"),
            @ApiResponse(code = 401, message = "not authorized!"),
            @ApiResponse(code = 403, message = "forbidden!!!"),
            @ApiResponse(code = 404, message = "not found in database!!!"),
            @ApiResponse(code = 500, message = "sql exception")})

    @DeleteMapping("/deleteOrder/{name}")
    public ResponseEntity updateProduct(@Valid @PathVariable String name) {
        try {
            orderService.deleteOrder(connection, name);
        } catch (InvalidInputException e) {
            return new ResponseEntity(e.getErrorMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity("order deleted", HttpStatus.OK);
    }
}
