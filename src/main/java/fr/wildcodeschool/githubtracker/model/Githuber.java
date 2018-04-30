package fr.wildcodeschool.githubtracker.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class Githuber {

    //name, email, login, id et avatarUrl.
    Long id;
    String name;
    String email;
    String login;
    String avatarUrl;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    @JsonCreator
    public Githuber(@JsonProperty("id") Long id, @JsonProperty("name") String name, @JsonProperty("email") String email, @JsonProperty("login") String login, @JsonProperty("avatar_url") String avatarUrl) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.login = login;
        this.avatarUrl = avatarUrl;
    }

    /*
    public Githuber(Long id, String name, String email, String login, String avatarUrl) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.login = login;
        this.avatarUrl = avatarUrl;
    }
    */
}
