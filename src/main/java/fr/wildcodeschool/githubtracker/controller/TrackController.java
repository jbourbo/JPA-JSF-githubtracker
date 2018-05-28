package fr.wildcodeschool.githubtracker.controller;

import fr.wildcodeschool.githubtracker.model.GithubersService;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@RequestScoped
public class TrackController {


    @Inject
    GithubersService ghs;


    String login;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String track(){

        if(ghs.addGithuber(this.login)){

            return "githubers";
        }else{
            FacesMessage fm = new FacesMessage("Inexistent Github user: " + login);
            FacesContext.getCurrentInstance().addMessage(null, fm);
            return null;
        }



    }
}
