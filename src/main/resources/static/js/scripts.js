function initMap() {
    const map = new google.maps.Map(document.getElementById("map"), {
        center: { lat: 0, lng: 0 },
        zoom: 2,
    });

    const directionsService = new google.maps.DirectionsService();
    const directionsRenderer = new google.maps.DirectionsRenderer();
    directionsRenderer.setMap(map);

    const origenAutocompletar = new google.maps.places.Autocomplete(document.getElementById("origen"));
    const destinoAutocompletar = new google.maps.places.Autocomplete(document.getElementById("destino"));

    document.getElementById("buscar").addEventListener("click", () => {
        calculateAndDisplayRoutes(directionsService, directionsRenderer);
    });
}

function calculateAndDisplayRoutes(directionsService, directionsRenderer) {
    const origen = document.getElementById("origen").value;
    const destino = document.getElementById("destino").value;

    directionsService.route(
        {
            origin: origen,
            destination: destino,
            travelMode: google.maps.TravelMode.TRANSIT,
        },
        (response, status) => {
            if (status === "OK") {
                directionsRenderer.setDirections(response);
                showBusRoutes(response);
            } else {
                window.alert("No se encontraron rutas de autobús.");
            }
        }
    );
}

function showBusRoutes(directionsResponse) {
    const routes = directionsResponse.routes;
    const busRoutesDiv = document.getElementById("bus-routes");
    busRoutesDiv.innerHTML = "<h3>Rutas de Autobús:</h3>";
    if (routes.length > 0) {
        routes.forEach((route, index) => {
            const routeButton = document.createElement("button");
            routeButton.textContent = `Ruta ${index + 1}`;
            routeButton.classList.add("btn", "btn-outline-primary", "mb-2");
            routeButton.addEventListener("click", () => {
                showRouteInfo(route);
            });
            busRoutesDiv.appendChild(routeButton);
        });
    } else {
        busRoutesDiv.innerHTML += "<p>No se encontraron rutas de autobús.</p>";
    }
}

function showRouteInfo(route) {
    const selectedRouteInfoDiv = document.getElementById("selected-route-info");
    const distanceSpan = document.getElementById("route-distance");
    const durationSpan = document.getElementById("route-duration");
    const detailsList = document.getElementById("route-details");
    const notificationDiv = document.getElementById("notification");

    // Mostramos la información de la distancia y duración
    distanceSpan.textContent = route.legs.reduce((total, leg) => total + leg.distance.value, 0) / 1000 + " km";
    durationSpan.textContent = route.legs.reduce((total, leg) => total + leg.duration.value, 0) / 60 + " minutos";

    // Mostramos los detalles de la ruta
    detailsList.innerHTML = "";
    route.legs.forEach((leg, index) => {
        const legItem = document.createElement("li");
        legItem.textContent = `Leg ${index + 1}: ${leg.distance.text}, ${leg.duration.text}`;
        detailsList.appendChild(legItem);
    });

    // Mostramos el contenedor de la información de la ruta seleccionada
    selectedRouteInfoDiv.style.display = "block";

    // Mostramos la notificación de llegada al destino
    notificationDiv.innerHTML = "<p>Recibirás una notificación cuando llegues a tu destino.</p>";

    // Agregar información de los autobuses disponibles y trasbordo
    const busAgency1 = document.getElementById("bus-agency1");
    const busAgency2 = document.getElementById("bus-agency2");

    // Aquí podrías agregar la lógica para obtener la información de los autobuses disponibles
    // y el trasbordo, y luego agregarla a los elementos HTML correspondientes
}
