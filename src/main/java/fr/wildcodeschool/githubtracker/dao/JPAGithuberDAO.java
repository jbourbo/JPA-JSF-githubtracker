package fr.wildcodeschool.githubtracker.dao;

import fr.wildcodeschool.githubtracker.model.Githuber;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.SessionScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Logger;

@SessionScoped
@Jpa
public class JPAGithuberDAO implements  GithuberDAO, Serializable {

    private Class<Githuber> clazz;

    public final void setClazz( Class<Githuber> clazzToSet ){
        this.clazz = clazzToSet;
    }

    public JPAGithuberDAO(){
        setClazz(Githuber.class );
    }

    @PersistenceContext
    private EntityManager em;

    private static final Logger LOGGER = Logger.getLogger( JPAGithuberDAO.class.getName() );

    HashMap<String, Githuber> githubers = new HashMap<>();

    public List<Githuber> getGithubers(){

        return em.createQuery("select g from " + clazz.getName() + " g").getResultList();

    }


    @Transactional
    public void saveGithuber (Githuber githuber) {

        if(githuber != null) {
            em.persist(githuber);
        }

    }

    public Githuber getGithuber(String login) {
        try{
            Query query = em.createQuery("SELECT g from Githuber g where g.login = :login");
            query.setParameter("login", login );
            List results = query.getResultList();
            if (results.isEmpty()){
                return null;
            }
            else{
                return (Githuber) results.get(0);
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return null;


    }

    @Transactional
    public void deleteGithuber (String login) {
        Githuber githuber = getGithuber(login);

        if(githuber != null){
            try {
                em.remove(githuber);
            }
            catch(Exception e){
                e.printStackTrace();
            }
        }

    }


    @Transactional
    public void deleteGithuber (long id) {

        Githuber githuber = em.find(Githuber.class, id);


        if(githuber != null){
            try {
                em.remove(githuber);
            }
            catch(Exception e){
                e.printStackTrace();
            }
        }

    }
}
