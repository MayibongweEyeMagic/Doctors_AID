package com.killmongerscode.aid;

public class InOrComplete {

    private final String Name;
    private final String Surname;
    private final String Email;

    public InOrComplete(String name, String surname, String email) {
        this.Name = name;
        this.Surname = surname;
        this.Email = email;
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
}
