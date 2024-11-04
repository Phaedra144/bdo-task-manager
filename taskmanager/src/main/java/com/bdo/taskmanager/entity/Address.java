package com.bdo.taskmanager.entity;

import jakarta.persistence.Embeddable;

@Embeddable
public class Address {
  private String city;
  private String zip;
  private String street;
  private int streetNumber;

  public Address() {
  }

  public Address(String city, String zip, String street, int streetNumber) {
    this.city = city;
    this.zip = zip;
    this.street = street;
    this.streetNumber = streetNumber;
  }

  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public String getZip() {
    return zip;
  }

  public void setZip(String zip) {
    this.zip = zip;
  }

  public String getStreet() {
    return street;
  }

  public void setStreet(String street) {
    this.street = street;
  }

  public int getStreetNumber() {
    return streetNumber;
  }

  public void setStreetNumber(int streetNumber) {
    this.streetNumber = streetNumber;
  }

}
