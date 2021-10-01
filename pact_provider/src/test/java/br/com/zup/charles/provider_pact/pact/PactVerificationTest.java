package br.com.zup.charles.provider_pact.pact;

import au.com.dius.pact.provider.junit5.HttpTestTarget;
import au.com.dius.pact.provider.junit5.PactVerificationContext;
import au.com.dius.pact.provider.junitsupport.IgnoreMissingStateChange;
import au.com.dius.pact.provider.junitsupport.Provider;
import au.com.dius.pact.provider.junitsupport.State;
import au.com.dius.pact.provider.junitsupport.StateChangeAction;
import au.com.dius.pact.provider.junitsupport.loader.PactFolder;
import au.com.dius.pact.provider.spring.junit5.PactVerificationSpringProvider;
import br.com.zup.charles.provider_pact.model.User;
import br.com.zup.charles.provider_pact.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.TestTemplate;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import java.util.Map;
import java.util.Optional;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Provider("Provider")
@PactFolder("pacts")
@IgnoreMissingStateChange
//@PactBroker
class PactVerificationTest {

    @LocalServerPort
    private int port;

    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    void setup(PactVerificationContext context) {
        context.setTarget(new HttpTestTarget("localhost", port));
    }

    @TestTemplate
    @ExtendWith(PactVerificationSpringProvider.class)
    @DisplayName("Should check the contract with the consumer")
    void pactVerificationTestTemplate(PactVerificationContext context) {
        context.verifyInteraction();
    }

    @State(value = "user with ID 1 exists", action = StateChangeAction.SETUP)
    void userExists(Map<String, Object> params) {
        Long userId = ((Number) params.get("id")).longValue();
        Optional<User> user = userRepository.findById(userId);
        if (user.isEmpty()) {
            userRepository.save(new User("Charles", "charles@gmail.com"));
        }
    }

    @State(value = "user with ID 10 does not exist", action = StateChangeAction.SETUP)
    void userNotExist(Map<String, Object> params) {
        Long userId = ((Number) params.get("id")).longValue();
        Optional<User> product = userRepository.findById(userId);
        if (product.isPresent()) {
            userRepository.deleteById(userId);
        }
    }

    @State(value = "perform a POST request to create a user", action = StateChangeAction.SETUP)
    void createUser() {
        userRepository.deleteAll();
    }

    @State(value = "user with existent email", action = StateChangeAction.SETUP)
    void userWithExistentEmail() {
        userRepository.deleteAll();
        userRepository.save(new User("Charles", "teste2@hotmail.com"));
    }
}
