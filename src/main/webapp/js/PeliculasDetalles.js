document.addEventListener("DOMContentLoaded", function () {
    const queryParams = new URLSearchParams(window.location.search);

    const PeliculaDetallesId = {
        id: queryParams.get("id")
    };

    const PeliculaDetallesContainer = document.getElementById("PeliculasDetalles");
    const btnEliminarElement = document.getElementById("btnEliminar");
    const btnModificarElement = document.getElementById("btnModificar");
    const btnGuardarElement = document.getElementById("btnGuardar");
    const btnContainerElement = document.getElementById("btnContainer");


    let objetoPelicula = {
        id: 0,
        nombre: "",
        anioLanzamiento: 0,
        duracion: 0,
        genero: "",
        director: "",
        reparto: "",
        descripcion: "",
        urlThriller: "",
        portada: ""

    };

    function loadPelicula() {

        fetch(`/app/peliculas?action=getById&id=${PeliculaDetallesId.id}`)
                .then(response => response.json())
                .then(data => {
                    PeliculaDetallesContainer.innerHTML += `
                    <div class="col-md-6 text-center">
                      <div class="clearfix">
                        <img src="data:image/jpeg;base64,${data.imagenBase64}" class="my-4 img-fluid" style="width: 75%" alt="imagen de pelicula"/>
                      </div>
                    </div>
                    <div class="card-body col-md-6">
                       <ul class="list-group list-group-flush">
                         <li class="list-group-item">
                           <h2 class="card-title">${data.nombre}</h2>
                         </li>
                         <li class="list-group-item">Año de Lanzamiento: ${data.anioLanzamiento} </li>
                         <li class="list-group-item">Duración: ${data.duracion} </li>
                         <li class="list-group-item">Genero: ${data.genero} </li>
                         <li class="list-group-item">Director: ${data.director} </li>
                         <li class="list-group-item">Reparto: ${data.reparto} </li>
                         <li class="list-group-item">Descripción: ${data.descripcion} </li>
                         <li class="list-group-item"><a href="${data.urlThriller}" target="_blank">Ver Thriller</a></li>
                       </ul>
                    </div>
                    `;
                    objetoPelicula.id = data.id;
                    objetoPelicula.nombre = data.nombre;
                    objetoPelicula.anioLanzamiento = data.anioLanzamiento;
                    objetoPelicula.duracion = data.duracion;
                    objetoPelicula.genero = data.genero;
                    objetoPelicula.director = data.director;
                    objetoPelicula.reparto = data.reparto;
                    objetoPelicula.descripcion = data.descripcion;
                    objetoPelicula.urlThriller = data.urlThriller;
                    objetoPelicula.portada = data.portada;


                });
    }


    loadPelicula();
});
