package com.basic.myspringboot.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Entity
@Setter @Getter
public class User {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    @NotEmpty(message = "이름은 필수입력 항목입니다!")
    private String name;

    @NotBlank(message = "E메일은 필수입력 항목입니다!")
    @Email(message = "올바른 E메일 형식이 아닙니다!")
    @Column(unique=true)  //email 중복 허용x

    private String email;
}
