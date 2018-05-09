package fr.wildcodeschool.githubtracker.dao;


import com.fasterxml.jackson.databind.ObjectMapper;
import fr.wildcodeschool.githubtracker.model.Githuber;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import java.io.IOException;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@ApplicationScoped
@InMemory
public class MemoryGithuberDAO implements GithuberDAO, Serializable {

    @Inject
    private ObjectMapper om;


    HashMap<String, Githuber> githubers = new HashMap<>();

    public List<Githuber> getGithubers(){
         List<Githuber> githubersList = new LinkedList<>();
         if(githubers !=null && githubers.size() > 0) {
             for (Map.Entry<String, Githuber> entry : githubers.entrySet()) {
                 String key = entry.getKey();
                 Githuber g = entry.getValue();

                 githubersList.add(g);
             }
         }

         return githubersList;

    }


    public void saveGithuber(Githuber githuber){
        System.out.println(githuber.getLogin());
        if(githuber!=null) {
            githubers.put(githuber.getLogin(), githuber);
        }
    }


    // This was used in a previous version to initialize the list
    @PostConstruct
    private void getGithubersFromWeb() {

//        saveGithuber(parseGithuber("christellsahli"));
//        saveGithuber(parseGithuber("Gregbee"));
//        saveGithuber(parseGithuber("jbourbo"));
//        saveGithuber(parseGithuber("Lucilediague"));
//        saveGithuber(parseGithuber("arnogc33"));



    }



}
