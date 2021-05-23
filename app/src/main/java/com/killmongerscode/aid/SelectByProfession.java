package com.killmongerscode.aid;

public class SelectByProfession {

    private String name;
    private String surname;
    private String email;

    public SelectByProfession(String name, String surname, String email){
        this.name =name;
        this.surname =surname;
        this.email =email;
    }

    public String getDocName(){
        return name;
    }

    public String getDoSurname(){
        return surname;
    }

    public String getDocEmail(){
        return email;
    }
}
