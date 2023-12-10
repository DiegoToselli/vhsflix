package data;

import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.Pelicula;


public class PeliculaDAO {

    public static List<Pelicula> recuperarPeliculas() {
        String query = "SELECT * FROM Peliculas WHERE activa=1";
        Connection con = null;
        Statement stp = null;
        ResultSet rs = null;
        Pelicula pelicula;
        List<Pelicula> listaPeliculas = new ArrayList<>();
        try {
            con = Conexion.getConexion();
            stp = con.prepareStatement(query);
            rs = stp.executeQuery(query);
            while (rs.next()) {
                pelicula = new Pelicula();
                pelicula.setIdPelicula(rs.getInt("idPelicula"));
                pelicula.setNombre(rs.getString("nombre"));
                pelicula.setAnioLanzamiento(rs.getInt("anioLanzamiento"));
                pelicula.setDuracion(rs.getString("duracion"));
                pelicula.setGenero(rs.getString("genero"));
                pelicula.setDirector(rs.getString("director"));
                pelicula.setReparto(rs.getString("reparto"));
                pelicula.setDescripcion(rs.getString("descripcion"));
                pelicula.setUrlThriller(rs.getString("urlThriller"));
                Blob blob = rs.getBlob("portada");
                byte[] imagenBytes = blob.getBytes(1, (int) blob.length());
                pelicula.setImagen(imagenBytes);
                listaPeliculas.add(pelicula);

            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            Conexion.close(rs);
            Conexion.close(stp);
            Conexion.close(con);
        }

        return listaPeliculas;
    }

    public static int insertar(Pelicula pelicula) {
        String query = "INSERT INTO Peliculas (nombre, anioLanzamiento, duracion, genero, director, reparto, descripcion, urlThriller, portada, activa) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        Connection con = null;
        PreparedStatement stmt = null;
        int records = 0;

        try {
            con = Conexion.getConexion();
            stmt = con.prepareStatement(query);

            stmt.setString(1, pelicula.getNombre());
            stmt.setInt(2, pelicula.getAnioLanzamiento());
            stmt.setString(3, pelicula.getDuracion());
            stmt.setString(4, pelicula.getGenero());
            stmt.setString(5, pelicula.getDirector());
            stmt.setString(6, pelicula.getReparto());
            stmt.setString(7, pelicula.getDescripcion());
            stmt.setString(8, pelicula.getUrlThriller());

            Blob blobImage = con.createBlob();
            blobImage.setBytes(1, pelicula.getImagen());
            stmt.setBlob(9, blobImage);

            stmt.setBoolean(10, true);

            records = stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            Conexion.close(stmt);
            Conexion.close(con);
        }
        return records;

    }

    public void insertarPelicula(Pelicula pelicula) {
        String query = "INSERT INTO peliculas (nombre,anioLanzamiento,duracion,genero,director,reparto,descripcion,urlThriller,portada,activa) VALUES (?, ?, ?, ?, ?, ?, ?,?,?,?)";
        Connection con = null;
        PreparedStatement stp = null;

        try {
            con = Conexion.getConexion();
            stp = con.prepareStatement(query);
            stp.setString(1, pelicula.getNombre());
            stp.setInt(2, pelicula.getAnioLanzamiento());
            stp.setString(3, pelicula.getDuracion());
            stp.setString(4, pelicula.getGenero());
            stp.setString(5, pelicula.getDirector());
            stp.setString(6, pelicula.getReparto());
            stp.setString(7, pelicula.getDescripcion());
            stp.setString(8, pelicula.getUrlThriller());
            Blob portadaBlob = con.createBlob();
            portadaBlob.setBytes(1, pelicula.getImagen());
            stp.setBlob(9, portadaBlob);
            stp.setBoolean(10, true);
            int cantidadRegistro = stp.executeUpdate();
            if (cantidadRegistro > 0) {
                System.out.println("se insertaron " + cantidadRegistro + " registros");
            } else {
                System.out.println("No se logro insertar ningun registro");

            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            Conexion.close(stp);
            Conexion.close(con);
        }

    }
    
//    private static final String SQL_DELETE = "DELETE FROM Peliculas WHERE idPelicula = ?"; 
   
}
