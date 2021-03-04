package eccomapp.api;

import com.fasterxml.jackson.databind.node.ObjectNode;
import eccomapp.entity.OrderEntity;
import eccomapp.exception.InvalidInputException;
import eccomapp.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RequestMapping("/order")
@RestController
public class OrderControllerApi {
    private static java.sql.Connection connection = eccomapp.util.Connection.create();
    private OrderService orderService=new OrderService();
    private OrderEntity orderEntity=new OrderEntity();
    @PostMapping("/addOrder")
    public ResponseEntity addOrder(@Valid @RequestBody ObjectNode objectNode) {
        try {
            orderEntity.setOrderid(UUID.randomUUID());
            String email=objectNode.get("email").asText();
            String name=objectNode.get("name").asText();
            orderService.createOrder(connection,email,name);
            return new ResponseEntity("order placed",HttpStatus.OK);
        } catch (InvalidInputException e) {
            e.logError();
        }
        return new ResponseEntity("order not placed",HttpStatus.BAD_REQUEST);
    }
    @GetMapping("/display")
    public void printOrder() {
        orderService.displayOrder(connection);
    }
    @DeleteMapping("/deleteOrder")
    public ResponseEntity updateProduct(@Valid @RequestBody ObjectNode objectNode)
    {
        boolean flag=true;
        if(flag) {
            String name=objectNode.get("name").asText();

            orderService.deleteOrder(connection,name);
            flag = false;
        }
        if(flag)
        {
            return new ResponseEntity("order not deleted",HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity("order deleted",HttpStatus.OK);

    }
}
