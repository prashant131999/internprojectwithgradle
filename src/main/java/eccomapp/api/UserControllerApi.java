package eccomapp.api;

import eccomapp.entity.UserEntity;
import eccomapp.exception.ApplicationRuntimeException;
import eccomapp.exception.InvalidInputException;
import eccomapp.model.UserAddModel;
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

    /**This method generates the post Api to create user
     *
     * @param userAddModel class for taking input as a object
     * @return response entity
     */
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success|OK"),
            @ApiResponse(code = 404, message = "not found in database!!!"),
            @ApiResponse(code = 500, message = "sql exception")})

    @PostMapping("/add")
    public ResponseEntity createUser(@Valid @RequestBody UserAddModel userAddModel) {
        try {
            userService.addUser(connection, userAddModel);
        } catch (InvalidInputException e) {
            return new ResponseEntity(e.getErrorMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity("User Added", HttpStatus.OK);

    }

    /** Generates the delete mapping by taking email as a parameter and deletes from database
     *
     * @param email for email of user
     * @return response entity
     */

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success|OK"),
            @ApiResponse(code = 404, message = "not found in database!!!"),
            @ApiResponse(code = 500, message = "sql exception")})

    @DeleteMapping("/delete/{email}")
    public ResponseEntity deleteUser(@Valid @PathVariable String email) {

        UserEntity userEntity = null;
        try {
            userEntity = userService.displayUsers(email, connection);
            if (userEntity != null) {
                userService.deleteUser(connection, email);
            } else {
                return new ResponseEntity("User not exist", HttpStatus.BAD_REQUEST);
            }
        } catch (InvalidInputException e) {
            return new ResponseEntity(e.getErrorMessage(), HttpStatus.BAD_REQUEST);
        } catch (ApplicationRuntimeException e) {
            return new ResponseEntity(e.getErrorMessage(), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity("User deleted", HttpStatus.OK);
    }

    /** Generates the put mapping to update user
     *
     * @param userModel for taking input
     * @return response entity
     */
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success|OK"),
            @ApiResponse(code = 404, message = "not found in database!!!"),
            @ApiResponse(code = 500, message = "sql exception")})
    @PutMapping("/updatedDetails")
    public ResponseEntity updateUser(@Valid @RequestBody UserModel userModel) {
        UserEntity userEntity = null;
        try {
            userEntity = userService.displayUsers(userModel.getEmail(), connection);
            if (userEntity != null) {
                userService.updateUser(connection, userModel);
            } else {
                return new ResponseEntity("User not exist", HttpStatus.BAD_REQUEST);
            }
        } catch (InvalidInputException e) {
            return new ResponseEntity(e.getErrorMessage(), HttpStatus.BAD_REQUEST);
        } catch (ApplicationRuntimeException e) {
            return new ResponseEntity(e.getErrorMessage(), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity("User updated", HttpStatus.OK);

    }

    /**Generates the get mapping to display user details
     *
     * @param email for email of user
     * @return response entity
     */

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success|OK"),
            @ApiResponse(code = 404, message = "not found in database!!!"),
            @ApiResponse(code = 500, message = "sql exception")})

    @GetMapping("/displayUserDetail/{email}")
    public ResponseEntity userDetail(@Valid @PathVariable String email) {
        UserEntity user;
        try {
            user = userService.displayUsers(email, connection);
        } catch (InvalidInputException e) {
            return new ResponseEntity(e.getErrorMessage(), HttpStatus.BAD_REQUEST);
        } catch (ApplicationRuntimeException e) {
            return new ResponseEntity(e.getErrorMessage(), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(user, HttpStatus.OK);
    }

}
