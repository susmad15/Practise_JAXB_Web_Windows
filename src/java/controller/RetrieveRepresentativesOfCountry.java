package controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import data.Representative;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import server.DB_Access;

@WebServlet(name = "RetrieveRepresentativesOfCountry", urlPatterns = {"/RetrieveRepresentativesOfCountry"})
public class RetrieveRepresentativesOfCountry extends HttpServlet {
    
    private DB_Access db = DB_Access.getInstance();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json");
        
        String bodyText = new BufferedReader(new InputStreamReader(request.getInputStream())).readLine();
        
        List<Representative> representatives = db.getRepresentativesOfCountry(bodyText);
        
        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
        
        String jsonString = gson.toJson(representatives);
        
        OutputStreamWriter out = new OutputStreamWriter(response.getOutputStream());
        
        out.write(jsonString);
        
        out.flush();
        
        
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
