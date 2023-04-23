package org.example.entity;

public class Membership {
    private int id;
    private String name;
    private int age;
    private int birth;
    private String gender;
    private String userID;
    private String password;
    private String result;

    public Membership(int id, String name, int age, int birth, String gender, String userID, String password) {
        this.id= id;
        this.name = name;
        this.age = age;
        this.birth = birth;
        this.gender = gender;
        this.userID = userID;
        this.password = password;
        this.result = result;
    }

    public int getId(){
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public int getBirth() {
        return birth;
    }

    public String getGender() {
        return gender;
    }

    public String getUserID() {
        return userID;
    }

    public String getPassword() {
        return password;
    }

    public String getResult() {
        return result;
    }
    public String toString() {
        return "membership_board{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age='" + age + '\'' +
                ", birth='" + birth + '\'' +
                ", gender='" + gender + '\'' +
                ", userID='" + userID + '\'' +
                ", password='" + password + '\'' +

                '}';
    }

}