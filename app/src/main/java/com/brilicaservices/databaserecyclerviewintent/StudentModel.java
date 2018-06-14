package com.brilicaservices.databaserecyclerviewintent;
class StudentModel {
    String name;
    String collegeName;
    String address;
    long phoneNumber;
    int id;

    public StudentModel(int id, String name, String collegeName, String address, long phoneNumber) {
        this.id = id;
        this.name = name;
        this.collegeName = collegeName;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    public StudentModel(String name, String collegeName, String address, long phoneNumber) {
        this.id = id;
        this.name = name;
        this.collegeName = collegeName;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    public StudentModel() {

    }


    public long getPhoneNumber() {
        return phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public String getCollegeName() {
        return collegeName;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setCollegeName(String collegeName) {
        this.collegeName = collegeName;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhoneNumber(long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setId(int id) {
        this.id = id;
    }


}

