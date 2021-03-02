package eccomapp.api;

import com.fasterxml.jackson.databind.node.ObjectNode;
import eccomapp.entity.OrderEntity;
import eccomapp.exception.InvalidInputException;
import eccomapp.service.OrderService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RequestMapping("/order")
@RestController
public class OrderControllerApi {
    static java.sql.Connection connection = eccomapp.util.Connection.create();
    OrderService orderService=new OrderService();
    OrderEntity orderEntity=new OrderEntity();
    @PostMapping("/addOrder")
    public String addOrder(@Valid @RequestBody ObjectNode objectNode) {
        try {
            orderEntity.setOrderid(UUID.randomUUID());
            String email=objectNode.get("email").asText();
            String name=objectNode.get("name").asText();
            orderService.createOrder(connection,email,name);
            return "Order Added Successfully";
        } catch (InvalidInputException e) {
            e.logError();
        }
        return "Order not added to database";
    }
    @GetMapping("/display")
    public void printOrder() {
        orderService.displayOrder(connection);

    }
}
