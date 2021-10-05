package br.com.zup.charles.provider_pact.controller;

import br.com.zup.charles.provider_pact.model.User;
import br.com.zup.charles.provider_pact.repository.UserRepository;
import br.com.zup.charles.provider_pact.request.UserRequestDto;
import br.com.zup.charles.provider_pact.response.CreateResponse;
import br.com.zup.charles.provider_pact.response.UserResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.List;
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

    @GetMapping
    public List<UserResponse> getAllUsers(){
        List<User> users = userRepository.findAll();
        return UserResponse.convert(users);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable("id") Long id){
        Optional<User> user = userRepository.findById(id);
        return user.map(value -> ResponseEntity.ok(new UserResponse(value)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
