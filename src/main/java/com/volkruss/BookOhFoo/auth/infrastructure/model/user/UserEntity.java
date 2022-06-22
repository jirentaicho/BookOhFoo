package com.volkruss.BookOhFoo.auth.infrastructure.model.user;

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
@Table(name="users")
public class UserEntity {
    @Id
    private int id;

    @Column(name="name")
    private String name;

    @Column(name="password")
    private String password;

    // 他に何かあれば
}
