package com.Auth.auth_app.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
@Data
@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name="roles")
public class Role {
    @Id
    @Column(name="roles_id")
    private UUID id= UUID.randomUUID();
    @Column(unique = true, nullable = false)
    private String name;

//    @ManyToMany(mappedBy = "roles")
//    private Set<User> users= new HashSet<>();

}
