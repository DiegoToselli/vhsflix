document.addEventListener("DOMContentLoaded", function () {
    const cardsPeliculas = document.getElementById("peliculas-card");
    const peliculas = [];

    function cargarListaPeliculas() {
        fetch("/app/peliculas=?action=listAll")
                .then(res => res.json())
                .then(data => {
                    data.forEach(pelicula => {
                        peliculas.push(pelicula);
                        cardsPeliculas.innerHTML += ``
                    })
                })
    }
})