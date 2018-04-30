package fr.wildcodeschool.githubtracker.dao;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import fr.wildcodeschool.githubtracker.model.Githuber;

import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@SessionScoped
public class DumbGithuberDAO implements GithuberDAO, Serializable {

    public List<Githuber> getGithubers(){
        List<Githuber> githubers = new ArrayList<>(5);


        githubers.add(new Githuber(1L, "christellsahli", "christellsahli@gmail.com", "christellsahli", "https://avatars3.githubusercontent.com/u/38050036?s=88&v=4"));
        githubers.add(new Githuber(2L, "Gregbee", "backtopa@gmail.com", "Gregbee", "https://avatars1.githubusercontent.com/u/14792727?s=88&v=4"));
        githubers.add(new Githuber(3L, "Julien Bourbonnais", "jbourbo@gmail.com", "jbourbo", "https://avatars0.githubusercontent.com/u/5736082?s=88&v=4"));
        githubers.add(new Githuber(4L, "Lucilediague", "diaguelucile@gmail.com", "Lucilediague", "https://avatars3.githubusercontent.com/u/24568268?s=88&v=4"));
        githubers.add(new Githuber(5L, "arnogc33", "guillou.arnaud@gmail.com", "arnogc33", "https://avatars2.githubusercontent.com/u/35994942?s=88&v=4"));

        Collections.unmodifiableList(githubers);

        return githubers;

    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public void saveGithuber(Githuber githuber) throws UnsupportedOperationException{

    }

}
