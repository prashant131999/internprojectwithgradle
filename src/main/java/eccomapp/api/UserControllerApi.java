package eccomapp.api;

import eccomapp.entity.UserEntity;
import eccomapp.exception.InvalidInputException;
import eccomapp.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RequestMapping("/user")
@RestController
public class UserControllerApi {
    private static java.sql.Connection connection = eccomapp.util.Connection.create();
    private UserService userService = new UserService();


    @PostMapping("/add")
    public ResponseEntity createUser(@Valid @RequestBody UserEntity userEntity) {
        try {
            userEntity.setUserid(UUID.randomUUID());
            userService.addUser(connection, userEntity);
            return new ResponseEntity("User Added",HttpStatus.OK);
        } catch (InvalidInputException e) {
            e.logError();
        }
        return new ResponseEntity("user not added", HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/delete")
    public ResponseEntity deleteUser(@Valid @RequestBody UserEntity userEntity) {
        try {
            userEntity.setUserid(UUID.randomUUID());
            userService.deleteUser(connection, userEntity);
            return new ResponseEntity("User deleted",HttpStatus.OK);
        } catch (InvalidInputException e) {
            e.logError();
        }
        return new ResponseEntity("User not deleted",HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/updatedDetails")
    public ResponseEntity updateUser(@Valid @RequestBody UserEntity userEntity) {
        boolean flag = true;
        userEntity.setUserid(UUID.randomUUID());
        if (flag) {
            userService.updateUser(connection, userEntity);
            flag = false;
        }
        if (flag) {
            return new ResponseEntity("user address not updated",HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity("user address updated",HttpStatus.OK);

    }

}
