package de.jamilo.hexagon.server.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@Table(name = "user_devices")
public class TokenEntity {

    @Id
    @Column(name = "device_token", nullable = false, length = 2048)
    public String deviceToken;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    public UserEntity user;

    public String getDeviceToken() { return deviceToken; }
    public void setDeviceToken(String deviceToken) { this.deviceToken = deviceToken; }
    public UserEntity getUser() { return user; }
    public void setUser(UserEntity user) { this.user = user; }

    @Transient
    public String sessionToken;
}


