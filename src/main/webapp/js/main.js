document.addEventListener("DOMContentLoaded", function () {
    const cardsPeliculas = document.getElementById("peliculasCard");
    const peliculas = [];

    function cargarListaPeliculas() {
        fetch("/app/peliculas?action=listAll")
                .then(res => res.json())
                .then(data => {
                    data.forEach(pelicula => {
                        peliculas.push(pelicula);
                        cardsPeliculas.innerHTML += `
                            <div class="col-md-3 mb-4 ident" data-pelicula-id="${pelicula.idPelicula}">
                                <div class="card h-100 animate-hover-card">
                                    <img src="data:image/jpeg;base64,${pelicula.imagenBase64}" class="card-img-top h-75" alt="Imagen Portada de la Pelicula">
                                    <div class="card-body">
                                        <h5 class="card-title">${pelicula.nombre}</h5>
                                        <p class="card-text">${pelicula.descripcion}</p>
                                    </div>
                                </div>
                            </div>
                        `
                    })
                })
    }
    
    cargarListaPeliculas();
    
})