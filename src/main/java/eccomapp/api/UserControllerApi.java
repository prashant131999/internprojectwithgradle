package eccomapp.api;

import eccomapp.entity.UserEntity;
import eccomapp.exception.ApplicationRuntimeException;
import eccomapp.exception.InvalidInputException;
import eccomapp.model.UserModel;
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
            @ApiResponse(code = 404, message = "not found in database!!!"),
            @ApiResponse(code = 500, message = "sql exception")})

    @DeleteMapping("/delete/{email}")
    public ResponseEntity deleteUser(@Valid @PathVariable String email) {

        UserEntity userEntity=null;
        try {
            userEntity=userService.displayUsers(email,connection);
            if(userEntity!=null) {
                userService.deleteUser(connection, email);
            }
            else
            {
                return new ResponseEntity("User not exist",HttpStatus.BAD_REQUEST);
            }
        } catch (InvalidInputException e) {
            return new ResponseEntity(e.getErrorMessage(),HttpStatus.BAD_REQUEST);
        }
        catch (ApplicationRuntimeException e)
        {
            return new ResponseEntity(e.getErrorMessage(),HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity("User deleted",HttpStatus.OK);
    }
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success|OK"),
            @ApiResponse(code = 404, message = "not found in database!!!"),
            @ApiResponse(code = 500, message = "sql exception")})
    @PutMapping("/updatedDetails")
    public ResponseEntity updateUser(@Valid @RequestBody UserModel userModel) {
        UserEntity userEntity=null;
        try {
            userEntity=userService.displayUsers(userModel.getEmail(),connection);
            if(userEntity!=null) {
                userService.updateUser(connection,userModel);
            }
            else
            {
                return new ResponseEntity("User not exist",HttpStatus.BAD_REQUEST);
            }
        } catch (InvalidInputException e) {
            return new ResponseEntity(e.getErrorMessage(),HttpStatus.BAD_REQUEST);
        }
        catch (ApplicationRuntimeException e)
        {
            return new ResponseEntity(e.getErrorMessage(),HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity("User updated",HttpStatus.OK);

    }
    @GetMapping("/displayUserDetail/{email}")
    public ResponseEntity userDetail(@Valid @PathVariable String email) {
        UserEntity user;
        try {
            user=userService.displayUsers(email,connection);
        } catch (InvalidInputException e) {
            return new ResponseEntity(e.getErrorMessage(), HttpStatus.BAD_REQUEST);
        }
        catch (ApplicationRuntimeException e)
        {
            return new ResponseEntity(e.getErrorMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(user,HttpStatus.OK);
    }

}
