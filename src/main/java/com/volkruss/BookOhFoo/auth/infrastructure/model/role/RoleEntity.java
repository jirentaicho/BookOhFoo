package com.volkruss.BookOhFoo.auth.infrastructure.model.role;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name="roles")
public class RoleEntity {
    @Id
    private int id;

    @Column(name="username")
    private String username;

    @Column(name="role")
    private String role;

}
