package br.com.zup.charles.providerPact.model;

import javax.persistence.*;
import javax.validation.constraints.Email;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    @Email
    private String email;

    /**
     * @deprecated (Hibernate needs to persist data)
     */
    @Deprecated(forRemoval = true)
    public User(){}

    public User(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }
}
