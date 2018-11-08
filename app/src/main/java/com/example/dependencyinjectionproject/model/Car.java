package com.example.dependencyinjectionproject.model;

public class Car {

  private String id;
  private String model;
  private String type;
  private int age;
  private String owner;

  public Car(String id, String model, String type, int age, String owner) {
    this.id = id;
    this.model = model;
    this.type = type;
    this.age = age;
    this.owner = owner;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getModel() {
    return model;
  }

  public void setModel(String model) {
    this.model = model;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public int getAge() {
    return age;
  }

  public void setAge(int age) {
    this.age = age;
  }

  public String getOwner() {
    return owner;
  }

  public void setOwner(String owner) {
    this.owner = owner;
  }

  public String printCarInformation() {
    return "Car's id = " + id + " Car's model is " + model + " Car's type is " + " Car's age is " + age + " Car's owner is " + owner;
  }
}
