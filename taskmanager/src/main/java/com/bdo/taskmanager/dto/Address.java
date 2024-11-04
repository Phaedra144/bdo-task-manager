package com.bdo.taskmanager.dto;

import jakarta.persistence.Embeddable;

@Embeddable
public record Address(String city, String zip, String street, Integer streetNumber) {

}
