package br.com.zup.charles.pact_consumer.clients;

import br.com.zup.charles.pact_consumer.model.User;
import br.com.zup.charles.pact_consumer.request.UserRequestDto;
import br.com.zup.charles.pact_consumer.response.CreateResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.List;

@FeignClient(name = "status", url = "${user.api}")
public interface UserClient {

    @GetMapping(value = "/users", consumes = "application/json", produces = "application/json")
    List<User> getAllUsers();

    @GetMapping(value = "/users/{id}", consumes = "application/json", produces = "application/json")
    User getUserById(@PathVariable("id") Long id);

    @PostMapping(value = "/users", consumes = "application/json", produces = "application/json")
    CreateResponse postUser(@RequestBody @Valid UserRequestDto userRequest);
}
