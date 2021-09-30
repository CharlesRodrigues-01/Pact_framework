package br.com.zup.charles.providerPact.controller;

import br.com.zup.charles.providerPact.model.User;
import br.com.zup.charles.providerPact.repository.UserRepository;
import br.com.zup.charles.providerPact.request.UserRequestDto;
import br.com.zup.charles.providerPact.response.CreateResponse;
import br.com.zup.charles.providerPact.response.UserResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    public final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping
    @Transactional
    public ResponseEntity<CreateResponse> post(@RequestBody @Valid UserRequestDto userRequestDto,
                                       UriComponentsBuilder uriComponentsBuilder){

        boolean existsByEmail = userRepository.existsByEmail(userRequestDto.getEmail());
        if (existsByEmail){
            return ResponseEntity.badRequest().build();
        }
        User user = userRequestDto.toModel();
        userRepository.save(user);
        URI uri = uriComponentsBuilder.path("/users/{id}").build(user.getId());
        return ResponseEntity.created(uri).body(new CreateResponse(user));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable("id") Long id){
        Optional<User> user = userRepository.findById(id);
        return user.map(value -> ResponseEntity.ok(new UserResponse(value)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
