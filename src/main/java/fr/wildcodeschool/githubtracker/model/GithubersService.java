package fr.wildcodeschool.githubtracker.model;


import fr.wildcodeschool.githubtracker.dao.GithuberDAO;
import fr.wildcodeschool.githubtracker.dao.InMemory;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.List;


@SessionScoped
public class GithubersService implements Serializable {

    @Inject @InMemory
    private GithuberDAO dao;


    public List<Githuber> getGithubers() {

            return dao.getGithubers();


    }
}