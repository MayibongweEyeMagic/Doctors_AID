package com.killmongerscode.aid;

import android.app.Activity;

public class SelectByProfession extends Activity {

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
