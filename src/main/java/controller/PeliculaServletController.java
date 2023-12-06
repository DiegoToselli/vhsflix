package controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import data.PeliculaDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import model.Pelicula;
import org.apache.commons.io.IOUtils;

@WebServlet("/peliculas")
@MultipartConfig(
        location = "/media/temp",
        fileSizeThreshold = 1024 * 1024,
        maxFileSize = 1024 * 1024 * 5,
        maxRequestSize = 1024 * 1024 * 10
)
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

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        String route = req.getParameter("action");

        switch (route) {
            case "add" -> {
                String nombre = req.getParameter("nombre");
                int anioLanzamiento = Integer.parseInt(req.getParameter("anioLanzamiento"));
                String duracion = req.getParameter("duracion");
                String genero = req.getParameter("genero");
                String director = req.getParameter("director");
                String reparto = req.getParameter("reparto");
                String descripcion = req.getParameter("descripcion");
                String urlThriller = req.getParameter("urlThriller");

                Part filePart = req.getPart("imagen");
                byte[] imagenBytes = IOUtils.toByteArray(filePart.getInputStream());

                Pelicula pelicula = new Pelicula(nombre, anioLanzamiento, duracion, genero, director, reparto, descripcion, urlThriller, imagenBytes, true);

                PeliculaDAO.insertar(pelicula);

                res.setContentType("application/json");
                Map<String, String> response = new HashMap();
                response.put("message", "Pelicula guardada con éxito!");

                mapper.writeValue(res.getWriter(), response);
            }
        }
    }
}
