package fr.wildcodeschool.githubtracker.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.wildcodeschool.githubtracker.model.Githuber;


import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;


@ApplicationScoped
public class GitHubUtils {

    @Inject
    private ObjectMapper om;

    private static final Logger LOGGER = Logger.getLogger( GitHubUtils.class.getName() );

    public Githuber parseGithuber(String login){

        String sUrl = "https://api.github.com/users/"+login;
        URL url = null;
        try {
            url = new URL(sUrl);
        }
        catch(MalformedURLException e){
            e.printStackTrace();

        }

        Githuber githuber = null;

        try {

            githuber = om.readValue(url, Githuber.class);
            System.out.println(githuber.getLogin());
        }
        catch(MalformedURLException e){
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
            throw new RuntimeException(e);

        }
        catch(IOException e){
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
            throw new RuntimeException(e);
        }
        
        return githuber;

    }
}
