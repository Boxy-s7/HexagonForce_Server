package de.jamilo.hexagon.server.persistence.entity;

import java.util.UUID;

import jakarta.persistence.*;

@Entity
@Table(
    name = "app_user",
    uniqueConstraints = @UniqueConstraint(
        name = "uk_app_user_username",
        columnNames = "username"
    )
)
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, length = 100)
    private String username;

    protected UserEntity() {
        // Für JPA
    }

    public UserEntity(String username) {
        this.username = username;
    }

    public UUID getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
}


