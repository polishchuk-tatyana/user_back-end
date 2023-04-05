package com.user.userapp.web.rest;

import com.user.userapp.domain.User;
import com.user.userapp.service.UserService;
import com.user.userapp.web.errors.BadRequestAlertException;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.PaginationUtil;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * `POST  /api/users`  : Creates a new user.
     * <p>
     * Creates a new user if the id and userName are not already used
     *
     * @param user the user to create.
     * @return the `ResponseEntity` with status `201 (Created)` and with body the new user, or with status `400 (Bad Request)` if the id or userName is already in use.
     * @throws URISyntaxException       if the Location URI syntax is incorrect.
     * @throws BadRequestAlertException `400 (Bad Request)` if the id or userName is already in use.
     */
    @PostMapping
    public ResponseEntity<User> createUser(@Valid @RequestBody User user) throws URISyntaxException, IOException {
        if (user.getId() != null) {
            throw new BadRequestAlertException("A new user cannot already have an ID", "userManagement", "idexists");
        } else {
            userService.createUser(user);
            return ResponseEntity
                    .created(new URI("/api/user/" + user.getUserName()))
                    .headers(HeaderUtil.createAlert("userApp", "userManagement.created", user.getUserName()))
                    .body(user);
        }
    }

    /**
     * `GET /api/users` : get all users with all the details.
     *
     * @param pageable the pagination information.
     * @return the `ResponseEntity` with status `200 (OK)` and with body all users.
     */
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers(Pageable pageable) {
        Page<User> page = userService.findAll(pageable);
        HttpHeaders headers = PaginationUtil
                .generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * `DELETE /api/users/:id` : delete the User.
     *
     * @param userId the id of the user to delete.
     * @return the `ResponseEntity` with status `204 (NO_CONTENT)`.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable("id") Long userId) {
        userService.deleteUser(userId);
        return ResponseEntity
                .noContent()
                .headers(HeaderUtil.createAlert("userApp", "userManagement.deleted", userId.toString()))
                .build();
    }
}