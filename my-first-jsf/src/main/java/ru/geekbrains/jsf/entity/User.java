package ru.geekbrains.jsf.entity;

public class User {

    private Long id;
    private String login;
    private String name;

    public User() {
    }

    public User(Long id, String login, String name) {
        this.id = id;
        this.login = login;
        this.name = name;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
