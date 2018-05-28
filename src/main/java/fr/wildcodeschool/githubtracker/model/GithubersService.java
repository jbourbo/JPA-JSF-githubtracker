package fr.wildcodeschool.githubtracker.model;


import fr.wildcodeschool.githubtracker.controller.GitHubUtils;
import fr.wildcodeschool.githubtracker.dao.*;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.List;


@SessionScoped
public class GithubersService implements Serializable {


    @Inject @Jpa
    private GithuberDAO jdao;

    @Inject
    GitHubUtils ghu;


    public List<Githuber> getGithubers() {

            return jdao.getGithubers();


    }


    public boolean addGithuber(String login) {

        Githuber newGithuber = ghu.parseGithuber(login);

        if(newGithuber != null){
            jdao.saveGithuber(newGithuber);
            return true;
        }else{
            return false;
        }


    }

    public void deleteGithuber(String login){

        jdao.deleteGithuber(login);
    }


    public void deleteGithuber(long id){

        jdao.deleteGithuber(id);
    }
}