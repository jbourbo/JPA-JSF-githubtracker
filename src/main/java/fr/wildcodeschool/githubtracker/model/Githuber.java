package fr.wildcodeschool.githubtracker.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name="githuber")
public class Githuber {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="github_id")
    private Long github_id;
    @Column(name="name")
    private String name;
    @Column(name="email")
    private String email;
    @Column(name="login")
    private String login;
    @Column(name="avatar_url")
    private String avatarUrl;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getGithub_id() {
        return github_id;
    }

    public void setGithub_id(Long github_id) {
        this.github_id = github_id;
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


    public Githuber(){}

    @JsonCreator
    public Githuber(@JsonProperty("id") Long id, @JsonProperty("name") String name, @JsonProperty("login") String login, @JsonProperty("email") String email, @JsonProperty("avatar_url") String avatarUrl) {
        this.github_id = id;
        this.name = name;
        this.login = login;
        this.email = email;

        this.avatarUrl = avatarUrl;
    }
}
