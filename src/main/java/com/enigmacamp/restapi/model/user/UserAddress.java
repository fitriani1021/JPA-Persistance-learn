package com.enigmacamp.restapi.model.user;

import lombok.Getter;
import lombok.Setter;

public class UserAddress {
    @Setter     @Getter
    private String street;
    @Setter     @Getter
    private String suite;
    @Setter     @Getter
    private String city;
    @Setter     @Getter
    private String zipcode;
    @Setter     @Getter
    private AddressGeo geo;
}
