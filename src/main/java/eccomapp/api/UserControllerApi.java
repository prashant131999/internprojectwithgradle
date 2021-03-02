package eccomapp.api;

import eccomapp.entity.UserEntity;
import eccomapp.exception.InvalidInputException;
import eccomapp.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RequestMapping("/user")
@RestController
public class UserControllerApi {
    static java.sql.Connection connection = eccomapp.util.Connection.create();
    UserService userService = new UserService();

    @GetMapping("/prashant")
    public String helloPrint() {
        return "hello";
    }

    @PostMapping("/db")
    public String createUser(@Valid @RequestBody UserEntity userEntity) {
        try {
            userEntity.setUserid(UUID.randomUUID());
            userService.addUser(connection, userEntity);
            return "User Added Successfully";
        } catch (InvalidInputException e) {
            e.logError();
        }
        return "Not added to database";
    }

    @DeleteMapping("/deleted")
    public String deleteUser(@Valid @RequestBody UserEntity userEntity) {
        try {
            userEntity.setUserid(UUID.randomUUID());
            userService.deleteUser(connection, userEntity);
            return "User Deleted";
        } catch (InvalidInputException e) {
            e.logError();
        }
        return "User not present in  database";
    }

    @PutMapping("/updatedDetails")
    public String updateUser(@Valid @RequestBody UserEntity userEntity) {
        boolean flag = true;
        userEntity.setUserid(UUID.randomUUID());
        if (flag) {
            userService.updateUser(connection, userEntity);
            flag = false;
        }
        if (flag) {
            return "user address not updated";
        }
        return "user address updated";

    }

}
