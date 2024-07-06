 		
 		  
 // Obtener la fecha actual
var fechaActual = new Date();

// Convertir la fecha a un formato legible
var opcionesFecha = { weekday: 'long', year: 'numeric', month: 'long', day: 'numeric' };
var fechaFormateada = fechaActual.toLocaleDateString('es-ES', opcionesFecha);

// Convertir la primera letra de cada palabra a mayúscula para el modo oración
var fechaFormateadaOracion = fechaFormateada.toLowerCase().split(' ').map(function(palabra) {
    return palabra.charAt(0).toUpperCase() + palabra.slice(1);
}).join(' ');

// Mostrar la fecha en el mismo lugar
document.getElementById("fecha_actual").innerText = fechaFormateadaOracion;
 		
 		
 		 let map;
        let directionsService;
        let directionsRenderer;
        let selectedRouteIndex = -1;

        function initMap() {
            map = new google.maps.Map(document.getElementById("map"), {
                center: { lat: -12.0464, lng: -77.0428 },
                zoom: 12,
            });

            directionsService = new google.maps.DirectionsService();
            directionsRenderer = new google.maps.DirectionsRenderer();
            directionsRenderer.setMap(map);

            const trafficLayer = new google.maps.TrafficLayer();
            trafficLayer.setMap(map);

            const origenAutocompletar = new google.maps.places.Autocomplete(document.getElementById("origen"));
            const destinoAutocompletar = new google.maps.places.Autocomplete(document.getElementById("destino"));

            document.getElementById("buscar").addEventListener("click", () => {
                if (document.getElementById("useCurrentLocation").checked) {
                    navigator.geolocation.getCurrentPosition(position => {
                        const latLng = new google.maps.LatLng(position.coords.latitude, position.coords.longitude);
                        const geocoder = new google.maps.Geocoder();
                        geocoder.geocode({ location: latLng }, (results, status) => {
                            if (status === "OK") {
                                document.getElementById("origen").value = results[0].formatted_address;
                                calculateAndDisplayRoute(directionsService, directionsRenderer, latLng);
                            } else {
                                window.alert("No se pudo obtener la ubicación actual: " + status);
                            }
                        });
                    }, () => {
                        window.alert("Error: El servicio de geolocalización falló.");
                    });
                } else {
                    calculateAndDisplayRoute(directionsService, directionsRenderer);
                }
            });
        }

 function calculateAndDisplayRoute(directionsService, directionsRenderer, currentLocation = null) {
    // Mostrar el loader
    document.getElementById("loader-container").style.display = "flex";

    const origen = currentLocation || document.getElementById("origen").value;
    const destino = document.getElementById("destino").value;

    const request = {
        origin: origen,
        destination: destino,
        travelMode: google.maps.TravelMode.TRANSIT,
        drivingOptions: {
            departureTime: new Date(),
            trafficModel: 'bestguess'
        },
        provideRouteAlternatives: true
    };

    directionsService.route(request, (response, status) => {
        // Ocultar el loader
        document.getElementById("loader-container").style.display = "none";

        if (status === "OK") {
            showBusRoutes(response);
        } else {
            window.alert("No se encontró una ruta de autobús.");
        }
    });
}
 function showBusRoutes(directionsResponse) {
    const routes = directionsResponse.routes;
    const busRoutesDiv = document.getElementById("bus-routes");
    const recommendedRouteDiv = document.getElementById("recommended-route");
    const userPreference = document.getElementById("preference").value;

    busRoutesDiv.innerHTML = "<h3>Rutas de Autobús:</h3>";
    recommendedRouteDiv.innerHTML = ""; // Limpiar el contenido anterior

    if (routes.length > 0) {
        const busesContainer = document.createElement("div");
        busesContainer.classList.add("bus-buttons-container");

        let recommendedRoute = null;

        if (userPreference === "shortest-duration") {
            let minDuration = Number.MAX_SAFE_INTEGER;

            routes.forEach((route, index) => {
                const buses = route.legs.flatMap(leg => {
                    return leg.steps.filter(step => step.travel_mode === "TRANSIT").map(step => {
                        return {
                            numero: step.transit.line.short_name,
                            IDLinea: enviarIdRutaAlControlador(step.transit.line.short_name),
                            empresa: obtenerEmpresaTransporte(step.transit.line.short_name),
                            estado: obtenerEstadoAutobus(step.transit.line.short_name),
                            duracion: leg.duration.value, // Duración en segundos
                            duracionText: leg.duration.text // Duración en formato legible
                        };
                    });
                });

                const totalDuration = route.legs.reduce((sum, leg) => sum + leg.duration.value, 0);
                if (totalDuration < minDuration) {
                    minDuration = totalDuration;
                    recommendedRoute = { route, index, buses };
                }

                const busButton = createBusButton(route, index, buses, directionsResponse);
                busesContainer.appendChild(busButton);
            });
        } else if (userPreference === "fewest-transfers") {
            let minTransfers = Number.MAX_SAFE_INTEGER;

            routes.forEach((route, index) => {
                const buses = route.legs.flatMap(leg => {
                    return leg.steps.filter(step => step.travel_mode === "TRANSIT").map(step => {
                        return {
                            numero: step.transit.line.short_name,
                             IDLinea: enviarIdRutaAlControlador(step.transit.line.short_name),
                            empresa: obtenerEmpresaTransporte(step.transit.line.short_name),
                            estado: obtenerEstadoAutobus(step.transit.line.short_name),
                            duracion: leg.duration.value, // Duración en segundos
                            duracionText: leg.duration.text // Duración en formato legible
                        };
                    });
                });

                const transfers = buses.length - 1;
                if (transfers < minTransfers) {
                    minTransfers = transfers;
                    recommendedRoute = { route, index, buses };
                }

                const busButton = createBusButton(route, index, buses, directionsResponse);
                busesContainer.appendChild(busButton);
            });
        }

        busRoutesDiv.appendChild(busesContainer);

        if (recommendedRoute) {
            const recommendedButton = createRecommendedButton(recommendedRoute.route, recommendedRoute.index, recommendedRoute.buses, directionsResponse);
            recommendedRouteDiv.appendChild(recommendedButton);
        }
    } else {
        busRoutesDiv.innerHTML += "<p>No se encontraron rutas de autobús.</p>";
    }
}

function createBusButton(route, index, buses, directionsResponse) {
    const busButton = document.createElement("button");
    busButton.classList.add("bus-button", "vertical-button");

    if (buses.length > 1) {
        busButton.innerHTML = `<i class="fas fa-bus"></i> ${buses[0].numero} - ${buses[0].empresa}<br>(${buses[0].estado})<br>Tiene ${buses.length - 1} transbordos.<br>Duración: ${buses[0].duracionText}`;
    } else {
        busButton.innerHTML = `<i class="fas fa-bus"></i> ${buses[0].numero} - ${buses[0].empresa}<br>(${buses[0].estado})<br>Duración: ${buses[0].duracionText}`;
    }

    busButton.addEventListener("click", () => {
        selectedRouteIndex = index;
        directionsRenderer.setDirections(directionsResponse);
        directionsRenderer.setRouteIndex(index);
        showTripDetails(directionsResponse, index);
        centerMapOnRoute(directionsResponse, index);
        highlightSelectedRoute(busButton);

     });

    return busButton;
}
 
function createRecommendedButton(route, index, buses, directionsResponse) {
    const recommendedButton = document.createElement("button");
    recommendedButton.classList.add("bus-button", "vertical-button", "recommended-route");

    let estadoColor = buses[0].estado === 'Lleno' ? 'red' : 'black';

    if (buses.length > 1) {
        recommendedButton.innerHTML = `
            <div style="margin-bottom: 10px;"><i class="fas fa-bus"></i> Ruta Recomendada: ${buses[0].numero} - ${buses[0].empresa}</div>
            <div style="color: ${estadoColor}; margin-bottom: 10px;">(${buses[0].estado})</div>
            <div style="margin-bottom: 10px;">Tiene ${buses.length - 1} transbordos.</div>
            <div style="margin-bottom: 10px;">Duración: ${buses[0].duracionText}</div>
        `;
    } else {
        recommendedButton.innerHTML = `
            <div style="margin-bottom: 10px;"><i class="fas fa-bus"></i> Ruta Recomendada: ${buses[0].numero} - ${buses[0].empresa}</div>
            <div style="color: ${estadoColor}; margin-bottom: 10px;">(${buses[0].estado})</div>
            <div style="margin-bottom: 10px;">Duración: ${buses[0].duracionText}</div>
        `;
    }

    recommendedButton.addEventListener("click", () => {
        showPopup(route, index, buses, directionsResponse);
        solicitarUnidadTransporte(buses[0].numero); // Llamar a la función para solicitar la unidad de transporte
    });

    return recommendedButton;
}

function createRecommendedButton(route, index, buses, directionsResponse) {
    const recommendedButton = document.createElement("button");
    recommendedButton.classList.add("bus-button", "vertical-button", "recommended-route");

    let estadoColor = buses[0].estado === 'Lleno' ? 'red' : 'black';

    if (buses.length > 1) {
        recommendedButton.insertAdjacentHTML('beforeend', `<div><i class="fas fa-bus"></i> Ruta Recomendada: ${buses[0].numero} - ${buses[0].empresa}</div><br>`);
        recommendedButton.insertAdjacentHTML('beforeend', `<div style="color: ${estadoColor}">(${buses[0].estado})</div><br>`);
        recommendedButton.insertAdjacentHTML('beforeend', `<div>Tiene ${buses.length - 1} transbordos.</div><br>`);
        recommendedButton.insertAdjacentHTML('beforeend', `<div>Duración: ${buses[0].duracionText}</div>`);
    } else {
        recommendedButton.insertAdjacentHTML('beforeend', `<div><i class="fas fa-bus"></i> Ruta Recomendada: ${buses[0].numero} - ${buses[0].empresa}</div><br>`);
        recommendedButton.insertAdjacentHTML('beforeend', `<div style="color: ${estadoColor}">(${buses[0].estado})</div><br>`);
        recommendedButton.insertAdjacentHTML('beforeend', `<div>Duración: ${buses[0].duracionText}</div>`);
    }

    recommendedButton.addEventListener("click", () => {
        showPopup(route, index, buses, directionsResponse);
        solicitarUnidadTransporte(buses[0].numero); // Llamar a la función para solicitar la unidad de transporte
    });

    return recommendedButton;
}



function showPopup(route, index, buses, directionsResponse) {
    const popup = document.getElementById("ruta-popup");
    popup.style.display = "block";

    // Inicializar el mapa en el popup
    const mapPopup = new google.maps.Map(document.getElementById("mapa-popup"), {
        center: route.legs[0].start_location,
        zoom: 12,
    });

    // Mostrar la ruta recomendada en el mapa del popup
    const routeRenderer = new google.maps.DirectionsRenderer();
    routeRenderer.setMap(mapPopup);
    routeRenderer.setDirections(directionsResponse);
    routeRenderer.setRouteIndex(index);

    // Mostrar los detalles de la ruta en el div correspondiente
    showTripDetails(directionsResponse, index, document.getElementById("detalles-viaje"));
}

function hidePopup() {
    const popup = document.getElementById("ruta-popup");
    popup.style.display = "none";
}




function highlightSelectedRoute(selectedButton) {
    const allButtons = document.querySelectorAll(".bus-button");
    allButtons.forEach(button => {
        button.classList.remove("selected-route");
    });
    selectedButton.classList.add("selected-route");
}
 
	
		function enviarIdRutaAlControlador(idRuta) {
		    // Hacer una solicitud Ajax al controlador para enviar el ID de la ruta
		    $.ajax({
		        type: "POST", // O el método que necesites
		        url: "/user/recibirIdRuta", // La ruta del controlador
		        contentType: "application/json", // Especificar el tipo de contenido
		        data: JSON.stringify({ idRuta: idRuta }), // Convertir el objeto a JSON
		        success: function(response) {
		            // Manejar la respuesta del controlador si es necesario
		            console.log("ID de ruta enviada al controlador: " + idRuta);
		        },
		        error: function() {
		            // Manejar errores si es necesario
		            console.error("Error al enviar la ID de la ruta al controlador: " + idRuta);
		        }
		    });
		}
 function solicitarUnidadTransporte(idRuta) {
    $.ajax({
        type: "POST",
        url: "/user/seleccionarUnidadTransporte",
        contentType: "application/json",
        data: JSON.stringify({ idRuta: idRuta }),
        success: function(response) {
            if (response) {
                mostrarInformacionUnidad(response);
            } else {
                console.error("No se encontró una unidad de transporte para la ruta especificada.");
            }
        },
        error: function() {
            console.error("Error al solicitar la unidad de transporte.");
        }
    });
}


 function mostrarInformacionUnidad(unidad) {
    const detallesUnidad = document.getElementById("detalles-unidad");
    detallesUnidad.innerHTML = `
        <h3>Detalles de la Unidad de Transporte</h3>
        <p><strong>ID:</strong> ${unidad.id}</p>
        <p><strong>Placa:</strong> ${unidad.placa}</p>
        <p><strong>Estado:</strong> ${unidad.estado ? 'Lleno' : 'Con espacio'}</p>
        <p><strong>Conductor:</strong> ${unidad.nombreConductor}</p>
        <p><strong>Capacidad:</strong> ${unidad.capacidad}</p>
    `;
      // Asignar los valores a los campos ocultos
    document.getElementById("idUnidadTransporte").value = unidad.id;
}
 
		
    function obtenerEmpresaTransporte(codigoLinea) {
    // Esta función podría ser redefinida según las necesidades de tu aplicación
    // Por ahora, simplemente devuelve el código de línea
    return codigoLinea;
}

        function obtenerEstadoAutobus(numeroLinea) {
            const estadosPosibles = ["con espacio", "Lleno"];
            const estadoAleatorio = estadosPosibles[Math.floor(Math.random() * estadosPosibles.length)];
            return estadoAleatorio;
        }
		



function showTripDetails(directionsResponse, routeIndex, container) {
    const leg = directionsResponse.routes[routeIndex].legs[0];

    // Calcular el retraso del tráfico y la hora estimada de llegada
    const trafficDelay = leg.duration_in_traffic ? ` (+${leg.duration_in_traffic.text} con tráfico)` : "";
    const arrivalTime = new Date(new Date().getTime() + leg.duration.value * 1000);

    let walkingAndTransferDetails = ""; // Inicializar la variable para detalles de caminata y traslado

    const paraderosLista = document.createElement("ul"); // Crear la lista de paraderos

    // Agregar paraderos y detalles de caminata
    leg.steps.forEach((step, index) => {
        // Agregar paraderos
        if (step.travel_mode === "TRANSIT" && step.transit) {
            // Agregar el paradero de salida a la lista
            const paraderoSalida = document.createElement("li");
            paraderoSalida.textContent = `Subir al Bus de la línea ${step.transit.line.short_name} en ${step.transit.departure_stop.name}`;
            paraderosLista.appendChild(paraderoSalida);

            // Obtener y agregar paraderos intermedios si están disponibles
            if (step.transit.stops && step.transit.stops.length > 0) {
                step.transit.stops.forEach(paradero => {
                    const paraderoItem = document.createElement("li");
                    paraderoItem.textContent = `Paradero intermedio: ${paradero.name}`;
                    paraderosLista.appendChild(paraderoItem);
                });
            }

            // Agregar el paradero de llegada a la lista
            const paraderoLlegada = document.createElement("li");
            paraderoLlegada.textContent = `Bajar en ${step.transit.arrival_stop.name}`;
            paraderosLista.appendChild(paraderoLlegada);
        }

        // Agregar detalles de caminata
        if (step.travel_mode === "WALKING" && index < leg.steps.length - 1) {
            const nextStep = leg.steps[index + 1];
            if (nextStep.travel_mode === "TRANSIT" && nextStep.transit) {
                walkingAndTransferDetails += `
                    <p>Caminar ${step.distance.text} hasta ${nextStep.transit.departure_stop.name} para subir al Bus de la línea ${nextStep.transit.line.short_name}</p>
                `;
            }
        }
    });
$(document).ready(function() {
    $('#idDelFormulario').on('submit', function(event) {
        event.preventDefault(); // Cancelar el envío del formulario

        $.ajax({
            type: 'POST',
            url: '/user/saveRV',
            data: $(this).serialize(), // Serializar los datos del formulario
            success: function(response) {
                // Manejar la respuesta del servidor
                console.log(response);
                // Mostrar un mensaje de éxito
                alert('Se envió correctamente');
            },
            error: function(error) {
                // Manejar errores
                console.error(error);
            }
        });
    });
});
    // Construir el contenido de los detalles del viaje
    const tripDetailsContent = `
        <h3>Detalles del Viaje:</h3>
        <p><strong>Distancia:</strong> ${leg.distance.text}</p>
        <p><strong>Duración:</strong> ${leg.duration.text}${trafficDelay}</p>
        <p><strong>Hora de llegada estimada:</strong> ${arrivalTime.toLocaleTimeString('es-ES')}</p>
        <p><strong>Origen:</strong> ${leg.start_address}</p>
        <p><strong>Destino:</strong> ${leg.end_address}</p>
        <h4>Indicaciones de Subida Y Bajada:</h4>
    `;

    // Agregar contenido de detalles del viaje
    container.innerHTML = tripDetailsContent;
    container.appendChild(paraderosLista);

    // Agregar detalles de caminata y traslado si existen
    if (walkingAndTransferDetails !== "") {
        container.innerHTML += walkingAndTransferDetails;
    }

    // Agregar información sobre el tráfico
    const trafficInfo = `
        <p><strong>Tráfico:</strong> ${leg.duration_in_traffic ? 'Hay tráfico' : 'No hay tráfico'}</p>
    `;
    container.innerHTML += trafficInfo;
    // Obtener la fecha y hora actuales en Perú
const fechaHoraPeruActual = new Date().toLocaleString('es-PE', {
    timeZone: 'America/Lima',
    hour12: false,
    hour: '2-digit',
    minute: '2-digit',
    second: '2-digit'
});
  const currentDate = new Date();
const formattedDate = currentDate.toISOString().split('T')[0]; // Obtener la fecha en formato 'YYYY-MM-DD'
const formattedTime = currentDate.toTimeString().split(' ')[0]; // Obtener la hora en formato 'HH:MM:SS'

const arrivalTimee = new Date(currentDate.getTime() + leg.duration.value * 1000);
const formattedArrivalDate = arrivalTime.toISOString().split('T')[0]; // Obtener la fecha en formato 'YYYY-MM-DD'
const formattedArrivalTime = arrivalTime.toTimeString().split(' ')[0]; // Obtener la hora en formato 'HH:MM:SS'

    // Asignar los valores a los campos ocultos del viaje
    document.getElementById("distancia").value = leg.distance.text;
    document.getElementById("duracion").value = `${leg.duration.text}${trafficDelay}`;
    document.getElementById("fechaHoraSalida").value = `${formattedDate} ${formattedTime}`;
	document.getElementById("fechaHoraLlegada").value = `${formattedArrivalDate} ${formattedArrivalTime}`;
    document.getElementById("origenn").value = leg.start_address;
   document.getElementById("destinoo").value = leg.end_address;
    document.getElementById("trafico").value = leg.duration_in_traffic ? 'Hay tráfico' : 'No hay tráfico';

}

// Evento de clic para cerrar el popup
const cerrarPopup = document.getElementsByClassName("cerrar-popup")[0];
cerrarPopup.onclick = function() {
    hidePopup();
}

// Evento de clic fuera del popup para ocultarlo
window.onclick = function(event) {
    const popup = document.getElementById("ruta-popup");
    if (event.target == popup) {
        hidePopup();
    }
}



// Guardar datos en el localStorage
localStorage.setItem('selectedRouteIndex', JSON.stringify(selectedRouteIndex));

// Recuperar datos del localStorage
selectedRouteIndex = JSON.parse(localStorage.getItem('selectedRouteIndex')) || -1; // Use -1 as default value



		