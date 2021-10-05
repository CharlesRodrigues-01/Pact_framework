package br.com.zup.charles.pact_consumer.controller;

import br.com.zup.charles.pact_consumer.clients.UserClient;
import br.com.zup.charles.pact_consumer.model.User;
import br.com.zup.charles.pact_consumer.request.UserRequestDto;
import br.com.zup.charles.pact_consumer.response.CreateResponse;
import feign.FeignException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserClient userClient;

    public UserController(UserClient userClient) {
        this.userClient = userClient;
    }

    @PostMapping
    public ResponseEntity<CreateResponse> post(@RequestBody @Valid UserRequestDto userRequestDto,
                                               UriComponentsBuilder uriComponentsBuilder){
        try {
            CreateResponse response = userClient.postUser(userRequestDto);
            URI uri = uriComponentsBuilder.path("/users/{id}").build(response.getId());
            return ResponseEntity.created(uri).body(response);
        }catch(FeignException.BadRequest e){
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping
    public List<User> getAllUsers() {
        return userClient.getAllUsers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") Long id){
        try{
            User user = userClient.getUserById(id);
            return ResponseEntity.ok(user);
        }catch (FeignException.NotFound e) {
            return ResponseEntity.notFound().build();
        }
    }
}
