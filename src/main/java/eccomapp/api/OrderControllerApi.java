package eccomapp.api;

import eccomapp.entity.OrderEntity;
import eccomapp.entity.UserEntity;
import eccomapp.exception.ApplicationRuntimeException;
import eccomapp.exception.InvalidInputException;
import eccomapp.model.OrderDisplay;
import eccomapp.model.OrderModel;
import eccomapp.model.ProductModel;
import eccomapp.service.OrderService;
import eccomapp.service.ProductService;
import eccomapp.service.UserService;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequestMapping("/order")
@RestController
public class OrderControllerApi {
    private static java.sql.Connection connection = eccomapp.util.Connection.create();
    private OrderService orderService = new OrderService();
    private UserService userService = new UserService();
    private ProductService productService=new ProductService();
    private OrderEntity orderEntity = new OrderEntity();

    /**Generates the post mapping to add order to database through api
     *
     * @param orderModel for taking valid inputs
     * @return response
     */
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
        UserEntity userEntity=null;
        ProductModel productModel=null;
        try {
            userEntity=userService.displayUsers(email,connection);
            productModel=productService.displayProducts(name,connection);
            if(userEntity!=null && productModel!=null) {
                orderService.createOrder(connection, email, name);
            }
        } catch (InvalidInputException e) {
            return new ResponseEntity(e.getErrorMessage(), HttpStatus.BAD_REQUEST);
        }
        catch (ApplicationRuntimeException e) {
            return new ResponseEntity(e.getErrorMessage(), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity("order placed", HttpStatus.OK);
    }

    /**Generates the get mapping to display order detail
     *
     * @param prodName for product name
     * @return orderdispaly
     */
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success|OK"),
            @ApiResponse(code = 401, message = "not authorized!"),
            @ApiResponse(code = 403, message = "forbidden!!!"),
            @ApiResponse(code = 404, message = "not found in database!!!"),
            @ApiResponse(code = 500, message = "sql exception")})
    @GetMapping("/displayOrderDetail/{prodName}")
    public ResponseEntity orderDetail( @PathVariable String prodName) {
        OrderDisplay orders;
        try {
            orders=orderService.displayOrderDetail(prodName,connection);
        } catch (InvalidInputException e) {
            return new ResponseEntity(e.getErrorMessage(), HttpStatus.BAD_REQUEST);
        }
        catch (ApplicationRuntimeException e) {
            return new ResponseEntity(e.getErrorMessage(), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(orders,HttpStatus.OK);
    }

    /**Generates the delete mapping to delete order from database
     *
     * @param name for name of product
     * @return response
     */
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success|OK"),
            @ApiResponse(code = 401, message = "not authorized!"),
            @ApiResponse(code = 403, message = "forbidden!!!"),
            @ApiResponse(code = 404, message = "not found in database!!!"),
            @ApiResponse(code = 500, message = "sql exception")})

    @DeleteMapping("/deleteOrder/{name}")
    public ResponseEntity deleteOrder(@Valid @PathVariable String name) {
        OrderDisplay orderDisplay=null;
        try {
            orderDisplay=orderService.displayOrderDetail(name,connection);
            if(orderDisplay!=null) {
                orderService.deleteOrder(connection, name);
            }
        } catch (InvalidInputException e) {
            return new ResponseEntity(e.getErrorMessage(), HttpStatus.BAD_REQUEST);
        }
        catch (ApplicationRuntimeException e) {
            return new ResponseEntity(e.getErrorMessage(), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity("order deleted", HttpStatus.OK);
    }
}
