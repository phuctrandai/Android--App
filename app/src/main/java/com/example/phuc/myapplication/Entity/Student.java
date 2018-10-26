package com.example.phuc.myapplication.Entity;

import java.util.ArrayList;
import java.util.Date;

public class Student {
    private String id;
    private String name;
    private int age;
    private boolean male;
    private Date birthDate;
    private String address;
    private String classId;

    public Student() {

    }

    public Student(String id, String name, int age, boolean male, Date birthDate, String address, String classId) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.male = male;
        this.birthDate = birthDate;
        this.address = address;
        this.classId = classId;
    }

    /**
     * Lay danh sach sinh vien trong db
     *
     * @return ArrayList<Student> Danh sach sinh vien
     */
    public static ArrayList<Student> getList() {
        ArrayList<Student> list = new ArrayList<>();
        Student s;

        for (int i = 0; i < 10; i++) {
            s = new Student(i + "", "Sinh vien " + i, (i + 1) * 20, true, new Date(118, 0, 10), "Hue", i + 1 + "");
            list.add(s);
        }

        return list;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public boolean isMale() {
        return male;
    }

    public void setMale(boolean male) {
        this.male = male;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }
}
