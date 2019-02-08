package com.example.mohit.hack4;

public class ShareItem {
    private String itemName;
    private String category;
    private String description;
    private String availability;
    private String name;
    private String phone;
    private String email;
    private String location;

    public ShareItem(){

    }


    public ShareItem(String itemName, String category, String description, String availability, String name, String phone, String email, String location) {
        this.itemName = itemName;
        this.category = category;
        this.description = description;
        this.availability = availability;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.location = location;
    }


    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public String getSharingTime() {
        return availability;
    }

    public void setSharingTime(String sharingTime) {
        this.availability = sharingTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }



}
