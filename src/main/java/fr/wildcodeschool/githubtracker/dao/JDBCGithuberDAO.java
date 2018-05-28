package fr.wildcodeschool.githubtracker.dao;

import fr.wildcodeschool.githubtracker.model.Githuber;

import javax.annotation.Resource;
import javax.enterprise.context.ApplicationScoped;
import javax.sql.DataSource;
import java.io.Serializable;
import java.sql.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

@ApplicationScoped
@JDBCAnnot
public class JDBCGithuberDAO implements GithuberDAO, Serializable {


    private static final Logger LOGGER = Logger.getLogger( JDBCGithuberDAO.class.getName() );

    HashMap<String, Githuber> githubers = new HashMap<>();

    /** The name of the MySQL account to use (or empty for anonymous) */
    private final String userName = "julien";

    /** The password for the MySQL account (or empty for anonymous) */
    private final String password = "wild";

    /** The name of the computer running MySQL */
    private final String serverName = "localhost";

    /** The port of the MySQL server (default is 3306) */
    private final int portNumber = 3306;

    /** The name of the database we are testing with (this default is installed with MySQL) */
    private final String dbName = "githubtracker";

    /** The name of the table we are testing with */
    private final String tableName = "githuber";

    // JDBC driver name and database URL
//    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
//    static final String DB_URL = "jdbc:mysql://localhost/githubtracker";
//    static final String DB_URL = "jdbc:mysql://localhost/githubtracker?useUnicode=true&amp;serverTimezone=CET";

    //  Database credentials
    static final String USER = "julien";
    static final String PASS = "wild";



    @Resource(lookup = "jdbc/githubtracker")
    private DataSource dataSource;


    public void saveGithuber (Githuber githuber) {

        if(githuber!=null){

            LOGGER.log(Level.SEVERE, "save githuber");

            Connection conn = null;

            PreparedStatement preparedStatementGetGithuber = null;
            try{



                //STEP 2: Register JDBC driver
                Class.forName("com.mysql.jdbc.Driver");


                //STEP 3: Open a connection
                System.out.println("Connecting to database...");
    //            conn = DriverManager.getConnection(DB_URL,USER,PASS);
    //            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/githubtracker?useSSL=false&user=julien&password=wild&useUnicode=true&amp;serverTimezone=CET");
                conn = dataSource.getConnection();
                //STEP 4: Execute a query

                String sql =  "SELECT count(*) FROM githuber WHERE login = ?;";


                preparedStatementGetGithuber = conn.prepareStatement(sql);

                preparedStatementGetGithuber.setString(1, githuber.getLogin());



                // execute update SQL stetement
                ResultSet rs = preparedStatementGetGithuber.executeQuery();

                int counter = 0;
                //STEP 5: Extract data from result set
                while(rs.next()){
                    counter = rs.getInt(1);
                }

                if(counter == 0 ){

                    PreparedStatement ps = null;
                    try {


                        String sqlSave = "INSERT INTO githuber (github_id , name, login, email, avatar_url )VALUES ( ?, ?, ?, ?, ?);";

                        ps = conn.prepareStatement(sqlSave);


                        if (githuber.getId() == null) {
                            ps.setNull(1, Types.BIGINT);
                        } else {
                            ps.setLong(1, githuber.getId());
                        }
                        ps.setString(2, githuber.getName());
                        ps.setString(3, githuber.getLogin());
                        ps.setString(4, githuber.getEmail());
                        ps.setString(5, githuber.getAvatarUrl());

                        ps.executeUpdate();

                    } catch (SQLException se) {
                        //Handle errors for JDBC
                        se.printStackTrace();
                    }
                    catch (Exception e) {
                            //Handle errors for JDBC
                            e.printStackTrace();

                    }finally{
                        //finally block used to close resources
                        try{
                            if(ps!=null)
                                ps.close();
                        }catch(SQLException se2){
                        }// nothing we can do
                        try{
                            if(conn!=null)
                                conn.close();
                        }catch(SQLException se){
                            se.printStackTrace();
                        }//end finally try
                    }//end try
                }

                //STEP 6: Clean-up environment
                rs.close();
                preparedStatementGetGithuber.close();
                conn.close();
            }catch(SQLException se){
                //Handle errors for JDBC
                se.printStackTrace();
            }catch(Exception e){
                //Handle errors for Class.forName
                e.printStackTrace();
            }finally{
                //finally block used to close resources
                try{
                    if(preparedStatementGetGithuber!=null)
                        preparedStatementGetGithuber.close();
                }catch(SQLException se2){
                }// nothing we can do
                try{
                    if(conn!=null)
                        conn.close();
                }catch(SQLException se){
                    se.printStackTrace();
                }//end finally try
            }//end try

        }

    }//end function


    public List<Githuber> getGithubers(){
        List<Githuber> githubersList = new LinkedList<>();

        Connection conn = null;
        Statement stmt = null;
        try{

            //STEP 2: Register JDBC driver
            Class.forName("com.mysql.jdbc.Driver");

            //STEP 3: Open a connection
            System.out.println("Connecting to database...");
//            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/githubtracker?useSSL=false&user=julien&password=wild&useUnicode=true&amp;serverTimezone=CET");
            conn = dataSource.getConnection();

            //STEP 4: Execute a query
            stmt = conn.createStatement();
            System.out.println("Creating statement...");

            String sql =  "SELECT * FROM githuber";

            if (stmt != null) {
                ResultSet rs = stmt.executeQuery(sql);

                //STEP 5: Extract data from result set
                while(rs.next()){

                    Githuber githuber = new Githuber(
                            rs.getLong("github_id"),
                            rs.getString("name"),
                            rs.getString("login"),
                            rs.getString("email"),
                            rs.getString("avatar_url")
                    );


                    githubersList.add(githuber);

                }
            }

        }// end try
        catch(SQLException se){
            //Handle errors for JDBC
            se.printStackTrace();
        }catch(Exception e){
            //Handle errors for Class.forName
            e.printStackTrace();
        }finally {
            //finally block used to close resources
            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException se2) {
            }// nothing we can do
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }//end finally try
        }



        return githubersList;

    }// end function


    public void deleteGithuber(String login){

        Connection conn = null;
        PreparedStatement ps = null;
        try{

            //STEP 2: Register JDBC driver
            Class.forName("com.mysql.jdbc.Driver");

            //STEP 3: Open a connection
            System.out.println("Connecting to database...");
//            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/githubtracker?useSSL=false&user=julien&password=wild&useUnicode=true&amp;serverTimezone=CET");
            conn = dataSource.getConnection();
            //STEP 4: Execute a query
            System.out.println("Creating statement...");

            String sql =  "DELETE FROM githuber WHERE login = ?;";

            ps = conn.prepareStatement(sql);

            ps.setString(1, login);

            // execute update SQL stetement
            ps.executeUpdate();


        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        }
        catch (Exception e) {
            //Handle errors for JDBC
            e.printStackTrace();

        }finally{
            //finally block used to close resources
            try{
                if(ps!=null)
                    ps.close();
            }catch(SQLException se2){
            }// nothing we can do
            try{
                if(conn!=null)
                    conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }//end finally try
        }//end try
    }// end function



    public void deleteGithuber(long id){

        Connection conn = null;
        PreparedStatement ps = null;
        try{

            //STEP 2: Register JDBC driver
            Class.forName("com.mysql.jdbc.Driver");

            //STEP 3: Open a connection
            System.out.println("Connecting to database...");
//            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/githubtracker?useSSL=false&user=julien&password=wild&useUnicode=true&amp;serverTimezone=CET");
            conn = dataSource.getConnection();
            //STEP 4: Execute a query
            System.out.println("Creating statement...");

            String sql =  "DELETE FROM githuber WHERE github_id = ?;";

            ps = conn.prepareStatement(sql);

            ps.setLong(1, id);

            // execute update SQL stetement
            ps.executeUpdate();


        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        }
        catch (Exception e) {
            //Handle errors for JDBC
            e.printStackTrace();

        }finally{
            //finally block used to close resources
            try{
                if(ps!=null)
                    ps.close();
            }catch(SQLException se2){
            }// nothing we can do
            try{
                if(conn!=null)
                    conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }//end finally try
        }//end try
    }// end function

}
