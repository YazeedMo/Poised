package com.yazeed;

/**
 * Creates different Person objects.
 */
public class Person {

    // Attributes
    private int idNumber;
    private String name;
    private String tel_num;
    private String email;
    private String address;

    /**
     * Constructor to create Person object
     * @param idNumber Unique id number of the person.
     * @param name Name of the person.
     * @param tel_num Telephone number of the person
     * @param email Email address of the person
     * @param address Physical address of the person
     */
    // Constructor
    public Person(int idNumber, String name, String tel_num, String email, String address) {
        this.idNumber = idNumber;
        this.name = name;
        this.tel_num = tel_num;
        this.email = email;
        this.address = address;
    }


    // Getters and setters
    public int getIdNumber() {
        return idNumber;
    }
    public void setIdNumber(int idNumber) {
        this.idNumber = idNumber;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getTel_num() {
        return tel_num;
    }
    public void setTel_num(String tel_num) {
        this.tel_num = tel_num;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }

    // to String
    public String toString() {
        String output = "Id number:               " + idNumber;
        output += "Name:                       " + name;
        output += "\nTelephone Number:         " + tel_num;
        output += "\nEmail address:            " + email;
        output += "\nAddress:                  " + address;

        return output;

    }

}
