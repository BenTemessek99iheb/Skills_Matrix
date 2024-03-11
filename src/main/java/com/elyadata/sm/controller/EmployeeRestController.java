package com.elyadata.sm.controller;

import com.elyadata.sm.dto.EmployeeDto;
import com.elyadata.sm.repository.IEmployeeRepo;
import com.elyadata.sm.service.IEmployeeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Tag(name = "Employee Managment")
@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
@ControllerAdvice
public class EmployeeRestController {
    //TODO VALIDATIONS ON iDS AND OTHERS
    private final IEmployeeService userService;
    private final IEmployeeRepo IemplRepo;


    @Operation(description = "Retrieve all users pageable")
    @GetMapping
    public ResponseEntity<Page<EmployeeDto>> getUsers(Pageable pageable) {
        return ResponseEntity.ok(userService.retrieveAllUsers(pageable));
    }

    @Operation(description = "Retrieve all users")
    @GetMapping("/all")
    public ResponseEntity<List<EmployeeDto>> getAllUsers() {
        return ResponseEntity.ok(userService.retrieveUsers());
    }


    @Operation(description = "Retrieve  user by id ")
    @GetMapping("/{id}")
    public EmployeeDto retrieveUser(@PathVariable UUID id) {
        return userService.retrieveUserById(id);
    }

    @Operation(description = "add new user ")
    @PostMapping
    public EmployeeDto addUser(@RequestBody EmployeeDto u) {

        return userService.addUser(u);
    }

    @Operation(description = "update user ")
    @PutMapping
    public EmployeeDto updateUser(@RequestBody EmployeeDto employeeDto) {

        return userService.updateUser(employeeDto);
    }

    @Operation(description = "delete user ")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable UUID id) {
        userService.removeUser(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/retrieveUserById/{id}")
    public EmployeeDto retrieveUserById(@PathVariable UUID id) {
        return userService.retrieveUserById(id);
    }


    @GetMapping("/count")
    public long countEmployees() {
        return userService.countUsers();
    }
}
