package com.viu.patronAPP.infrastructure.in.web.controller.impl.user;

import com.viu.patronAPP.infrastructure.DTO.user.UpdateUsertDTO;
import com.viu.patronAPP.infrastructure.DTO.user.UserDTO;
import com.viu.patronAPP.infrastructure.DTO.user.UserLoginDTO;
import com.viu.patronAPP.infrastructure.DTO.user.UserRegisterDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/user")
@Tag(name = "User", description = "Operations related to user management, such as creation and retrieval of user data.")
public interface UserController {

    @Operation(
            summary = "Get user details by email",
            description = "This endpoint retrieves the user details based on the provided email address.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "User found successfully"),
                    @ApiResponse(responseCode = "404", description = "User not found with the given email")
            }
    )
    @GetMapping("/{email}")
    public ResponseEntity<UserDTO> getUserByEmail(
            @PathVariable @Parameter(description = "Email of the user to retrieve") String email
    );

    @Operation(
            summary = "Create a new user",
            description = "This endpoint allows you to create a new user by providing the required user details.",
            responses = {
                    @ApiResponse(responseCode = "201", description = "User created successfully"),
                    @ApiResponse(responseCode = "400", description = "Invalid input provided")
            }
    )
    @PostMapping()
    @CrossOrigin
    public ResponseEntity<UserDTO> createUser(
            @RequestBody @Parameter(description = "User details to be created") UserRegisterDTO userDTO
    );

    @Operation(
            summary = "Login user",
            description = "This endpoint allows you to login a user by providing the email and password.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "User logged in successfully"),
                    @ApiResponse(responseCode = "404", description = "Bad credentials")
            }
    )
    @PostMapping("/login")
    @CrossOrigin
    public ResponseEntity<UserDTO> login(@RequestBody @Parameter(description = "User login details") UserLoginDTO userLoginDTO);


    @Operation(
            summary = "Update user details",
            description = "This endpoint allows you to update the user details by providing the required user details.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "User updated successfully"),
                    @ApiResponse(responseCode = "400", description = "Invalid input provided")
            }
    )
    @PutMapping()
    @CrossOrigin
    public ResponseEntity<UserDTO> updateUser(@RequestBody @Parameter(description = "User details to be updated") UpdateUsertDTO userDTO);


    @Operation(
            summary = "Delete user",
            description = "This endpoint allows you to delete a user by providing the email address.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "User deleted successfully"),
                    @ApiResponse(responseCode = "404", description = "User not found")
            }
    )
    @DeleteMapping("/{email}")
    public ResponseEntity<String> deleteUser(@PathVariable @Parameter(description = "Email of the user to delete") String email);
}
