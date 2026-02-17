package com.Auth.auth_app.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "users")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name="user_id")
    private UUID id;

    private String email;
    private String name;
    private String password;
    private String image;
    private boolean enable= true;
    private Instant createdAt= Instant.now();
    private Instant updatedAt= Instant.now();

    //To see which provider used for Oauth2, we use an enum class
    @Enumerated(EnumType.STRING)
    private Provider provider= Provider.LOCAL;
    


    @ManyToMany(fetch=FetchType.EAGER)
    @JoinTable(name="user_roles",
            joinColumns = @JoinColumn(name="user_id"),
            inverseJoinColumns = @JoinColumn(name="roles_id")
    )
    private Set<Role> roles= new HashSet<>();
//It runs automatically before an entity is inserted (saved) into the database.
    @PrePersist
    protected void onCreate(){
        Instant now= Instant.now();
        if(createdAt==null) createdAt = now;
        updatedAt=now;
    }
    @PreUpdate
    protected void onUpdate(){
        Instant now= Instant.now();
        updatedAt= now;
    }


}
