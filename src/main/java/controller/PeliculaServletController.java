package controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import data.PeliculaDAO;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import model.Pelicula;

@WebServlet("/peliculas")
public class PeliculaServletController extends HttpServlet {

    private List<Pelicula> listaPeliculas = new ArrayList<>();
    ObjectMapper mapper = new ObjectMapper();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
        String route = req.getParameter("action");
           
        switch (route) {
            case "listAll" -> {
                res.setContentType("application/json; charset=UTF-8");
                listaPeliculas = PeliculaDAO.recuperarPeliculas();

                System.out.println("Cantidad de peliculas" + listaPeliculas.size());

                for (Pelicula pelicula : listaPeliculas) {
                    byte[] imagenBytes = pelicula.getImagen();
                    String imagenBase64 = Base64.getEncoder().encodeToString(imagenBytes);
                    pelicula.setImagenBase64(imagenBase64);
                }
                mapper.writeValue(res.getWriter(), listaPeliculas);
            }
            default ->
                System.out.println("Parametro Invalido");
        }
    }
}
