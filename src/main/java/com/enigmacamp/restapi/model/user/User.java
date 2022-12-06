package com.enigmacamp.restapi.model.user;

import lombok.Getter;
import lombok.Setter;

public class User {
    @Setter     @Getter
    private Integer id;
    @Setter     @Getter
    private String name;
    @Setter     @Getter
    private String username;
    @Setter     @Getter
    private String email;
    @Setter     @Getter
    private UserAddress address;
    @Setter     @Getter
    private String phone;
    @Setter     @Getter
    private String website;
    @Setter     @Getter
    private Company company;
}
