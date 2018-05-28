package fr.wildcodeschool.githubtracker.controller;

import fr.wildcodeschool.githubtracker.dao.GithuberDAO;
import fr.wildcodeschool.githubtracker.dao.InMemory;
import fr.wildcodeschool.githubtracker.dao.JDBCAnnot;
import fr.wildcodeschool.githubtracker.model.Githuber;
import fr.wildcodeschool.githubtracker.model.GithubersService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;
import java.util.logging.Logger;

@ApplicationScoped
@Named("githubersController")
public class GithubersController {


    private static final Logger LOGGER = Logger.getLogger( GithubersController.class.getName() );

    @Inject
    @JDBCAnnot
    GithuberDAO dao;

    @Inject
    GithubersService ghs;

    public List<Githuber> getAllGithubers(){

        return dao.getGithubers();

    }

    public void untrack(long githuberId){

//        LOGGER.info("UNTRACK");

        ghs.deleteGithuber(githuberId);
    }


}
