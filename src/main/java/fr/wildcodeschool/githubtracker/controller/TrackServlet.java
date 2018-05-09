package fr.wildcodeschool.githubtracker.controller;

import fr.wildcodeschool.githubtracker.dao.InMemory;
import fr.wildcodeschool.githubtracker.dao.JDBCAnnot;
import fr.wildcodeschool.githubtracker.dao.JDBCGithuberDAO;
import fr.wildcodeschool.githubtracker.dao.MemoryGithuberDAO;


import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "TrackServlet", urlPatterns = {"/track"})
public class TrackServlet  extends HttpServlet {

    @Inject @JDBCAnnot
    private JDBCGithuberDAO dao;

    @Inject
    private GitHubUtils ghu;


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        doGet(request, response);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String login = request.getParameter("login");
        dao.saveGithuber(ghu.parseGithuber(login));
        response.sendRedirect(request.getContextPath() + "/githubers");

    }
}
