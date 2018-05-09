package fr.wildcodeschool.githubtracker.controller;

import fr.wildcodeschool.githubtracker.model.GithubersService;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "DeleteServlet", urlPatterns = {"/delete"})
public class DeleteServlet extends HttpServlet {

    @Inject
    private GithubersService ghs;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        ghs.deleteGithuber(request.getParameter("GithuberLogin"));


        response.sendRedirect(request.getContextPath() + "/githubers");

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


    }

}
