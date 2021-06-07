package com.killmongerscode.aid;

public class InOrComplete {

    private final String Name;
    private final String Surname;
    private final String Email;
    private final  String id;

    public InOrComplete(String name, String surname, String email, String id) {
        this.Name = name;
        this.Surname = surname;
        this.Email = email;
        this.id =id;
    }

    public String getName() {
        return Name;
    }

    public String getSurname() {
        return Surname;
    }

    public String getEmail() {
        return Email;
    }

    public String getId() {
        return id;
    }
}
