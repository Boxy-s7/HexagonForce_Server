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

    @Column(name = "last_command")
    private int lastCommand;

    @Column(name = "last_message")
    private int lastMessage;
    
    @Column(name = "open_connections")
    private int connections;

    


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

    public void setLogoutAt(OffsetDateTime logoutAt) {
        this.logoutAt = logoutAt;
    }

    public void setLoginAt(OffsetDateTime loginAt) {
        this.loginAt = loginAt;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public int getConnections() {
        return connections;
    }

    public void setConnections(int openConnections) {
        this.connections = openConnections;
    }

    public int getLastCommand() {
        return lastCommand;
    }

    public void setLastCommand(int lastCommand) {
        this.lastCommand = lastCommand;
    }

    public int getLastMessage() {
        return lastMessage;
    }

    public void setLastMessage(int lastMessage) {
        this.lastMessage = lastMessage;
    }

    public UUID getDeviceToken() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getDeviceToken'");
    }

    public UUID getSessionToken() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getSessionToken'");
    }

    public void addConnection() {
        this.connections++;
    }

    public void removeConnection() {
        this.connections--;
    }
}

