package eccomapp.api;

import eccomapp.entity.UserEntity;
import eccomapp.exception.InvalidInputException;
import eccomapp.service.UserService;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequestMapping("/user")
@RestController
public class UserControllerApi {
    private static java.sql.Connection connection = eccomapp.util.Connection.create();
    private UserService userService = new UserService();

    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Success|OK"),
        @ApiResponse(code = 401, message = "not authorized!"),
        @ApiResponse(code = 403, message = "forbidden!!!"),
        @ApiResponse(code = 404, message = "not found in database!!!"),
        @ApiResponse(code = 500, message = "sql exception")})

    @PostMapping("/add")
    public ResponseEntity createUser(@Valid @RequestBody UserEntity userEntity) {
        try {
            userService.addUser(connection, userEntity);
        } catch (InvalidInputException e) {
            return new ResponseEntity(e.getErrorMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity("User Added",HttpStatus.OK);

    }
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success|OK"),
            @ApiResponse(code = 401, message = "not authorized!"),
            @ApiResponse(code = 403, message = "forbidden!!!"),
            @ApiResponse(code = 404, message = "not found in database!!!"),
            @ApiResponse(code = 500, message = "sql exception")})

    @DeleteMapping("/delete")
    public ResponseEntity deleteUser(@Valid @RequestBody UserEntity userEntity) {
        try {
            userService.deleteUser(connection, userEntity);
        } catch (InvalidInputException e) {
            return new ResponseEntity(e.getErrorMessage(),HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity("User deleted",HttpStatus.OK);
    }
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success|OK"),
            @ApiResponse(code = 401, message = "not authorized!"),
            @ApiResponse(code = 403, message = "forbidden!!!"),
            @ApiResponse(code = 404, message = "not found in database!!!"),
            @ApiResponse(code = 500, message = "sql exception")})
    @PutMapping("/updatedDetails")
    public ResponseEntity updateUser(@Valid @RequestBody UserEntity userEntity) {
        boolean flag = true;
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
