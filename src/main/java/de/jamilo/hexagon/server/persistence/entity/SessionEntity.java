package de.jamilo.hexagon.server.persistence.entity;

import java.time.OffsetDateTime;
import java.util.UUID;

import jakarta.persistence.*;

@Entity
@Table(name = "user_session")
public class SessionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    @Column(name = "login_at", nullable = false)
    private OffsetDateTime loginAt;

    @Column(name = "logout_at")
    private OffsetDateTime logoutAt;

    protected SessionEntity() {
        // Für JPA
    }

    public SessionEntity(UserEntity user, OffsetDateTime loginAt) {
        this.user = user;
        this.loginAt = loginAt;
    }

    public UUID getId() {
        return id;
    }

    public UserEntity getUser() {
        return user;
    }

    public OffsetDateTime getLoginAt() {
        return loginAt;
    }

    public OffsetDateTime getLogoutAt() {
        return logoutAt;
    }

    public void logout(OffsetDateTime logoutAt) {
        this.logoutAt = logoutAt;
    }

    public void setLoginAt(OffsetDateTime now) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setLoginAt'");
    }

    public void setUser(UserEntity user2) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setUser'");
    }
}