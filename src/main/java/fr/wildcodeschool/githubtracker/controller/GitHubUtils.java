package fr.wildcodeschool.githubtracker.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.wildcodeschool.githubtracker.dao.JDBCGithuberDAO;
import fr.wildcodeschool.githubtracker.model.Githuber;


import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;


@ApplicationScoped
public class GitHubUtils {


    private static final Logger LOGGER = Logger.getLogger( GitHubUtils.class.getName() );

    @Inject
    private ObjectMapper om;

//    private static final Logger LOGGER = Logger.getLogger( GitHubUtils.class.getName() );

    public static boolean doesURLExist(URL url) throws IOException
    {
        // We want to check the current URL
        HttpURLConnection.setFollowRedirects(false);

        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();

        // We don't need to get data
        httpURLConnection.setRequestMethod("HEAD");

        // Some websites don't like programmatic access so pretend to be a browser
        httpURLConnection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows; U; Windows NT 6.0; en-US; rv:1.9.1.2) Gecko/20090729 Firefox/3.5.2 (.NET CLR 3.5.30729)");
        int responseCode = httpURLConnection.getResponseCode();

        // We only accept response code 200
        return responseCode == HttpURLConnection.HTTP_OK;
    }

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

            if(doesURLExist(url)){
                githuber = om.readValue(url, Githuber.class);
                System.out.println(githuber.getLogin());
            }else{
                LOGGER.log(Level.SEVERE, "This githuber does not exist!");
            }


        }
        catch(MalformedURLException e){
//            LOGGER.log(Level.SEVERE, e.getMessage(), e);
            throw new RuntimeException(e);

        }
        catch(IOException e){
//            LOGGER.log(Level.SEVERE, e.getMessage(), e);
            throw new RuntimeException(e);
        }

        return githuber;

    }
}
