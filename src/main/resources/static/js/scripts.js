function initMap() {
    const map = new google.maps.Map(document.getElementById("map"), {
        center: { lat: -12.0464, lng: -77.0428 },
        zoom: 12,
    });

    const directionsService = new google.maps.DirectionsService();
    const directionsRenderer = new google.maps.DirectionsRenderer();
    directionsRenderer.setMap(map);

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
    };

    directionsService.route(request, (response, status) => {
        if (status === "OK") {
            directionsRenderer.setDirections(response);
            showBusRoutes(response);
            showTripDetails(response);
        } else {
            window.alert("No se encontró una ruta de autobús.");
        }
    });
}

function showBusRoutes(directionsResponse) {
    const routes = directionsResponse.routes;
    const buses = routes.flatMap(route => {
        return route.legs.flatMap(leg => {
            return leg.steps.filter(step => {
                return step.travel_mode === "TRANSIT";
            }).map(step => {
                return {
                    numero: step.transit.line.short_name,
                    empresa: obtenerEmpresaTransporte(step.transit.line.short_name),
                    estado: obtenerEstadoAutobus(step.transit.line.short_name)
                };
            });
        });
    });

    const busRoutesDiv = document.getElementById("bus-routes");
    busRoutesDiv.innerHTML = "<h3>Rutas de Autobús:</h3>";
    if (buses.length > 0) {
        const busesList = document.createElement("ul");
        buses.forEach(bus => {
            const busItem = document.createElement("li");
            busItem.textContent = `${bus.numero} - ${bus.empresa} (${bus.estado})`;
            busesList.appendChild(busItem);
        });

        busRoutesDiv.appendChild(busesList);
    } else {
        busRoutesDiv.innerHTML += "<p>No se encontraron rutas de autobús.</p>";
    }
}

function obtenerEmpresaTransporte(codigoLinea) {
    // Simulación de consulta a la base de datos para obtener el nombre de la empresa
    const empresasTransporte = {
        "SO44": "El Chama",
        "EO64": "La 9",
        "RUTA B": "Metropolitano B",
        "RUTA C": "Metropolitano C",
        "ICR01": " Empresa de Transportes Once de Noviembre S.A.",
        "ICR02": " Empresa de Transportes y Servicios Nuevo Reynoso S.A.",
        "ICR06": " Empresa de Transportes 12 de Enero S.A.",
        "IM01": " Ruta IM01 [Quilmaná - Imperial]",
        "IO34": " La S2C",
        "IO35": " Consorcio Briza",
        "IO38": " Consorcio Briza S.A.",
        "IO02": " Empresa de Transportes y Servicios San Antonio S.A.",
        "NCR09": " Empresa de Transportes y Servicios El Retablo S.A.C.",
        "NCR24": " Empresa de Transportes y Servicios 16 de Julio S.A.",
        "NM07": " Empresa de Transportes y Servicios Rápido Ramón Castilla S.A.",
        "NM39": " Empresa de Transportes 1ro de Julio S.A.",
        "NM39A": " La C",
        "NO98": " Empresa de Transportes Corazón de Jesús de San Diego S.A.",
        "EO48": " La 48",
        "IO44": " La VB",
        "NCR06": " La 06",
        "NM26": " Empresa de Transportes Miguel Grau S.A.",
        "NCR04": " La B",
        "NM42": " La 84",
        "NO63A": " La 71",
        "EO40": " Empresa de Transportes Salamanca Parral S.A.",
        "NCR01": " Translima S.A.",
        "NCR05": " Consorcio Haydee Alfaro Montufar S.A.C.",
        "NCR10": " Empresa de Transportes Urbano Víctor Raúl Haya de La Torre S.R.L.",
        "NCR13": " Empresa de Transportes Doce de Junio S.A.",
        "NCR23": "",
        "NM10": " Empresa de Transportes Impulsa Progreso S.A.C.",
        "NM16": " La 6M",
        "NM43A": " La C",
        "NO74": "",
        "NO03": " Transportes y Servicios Santa Cruz S.A.",
        "NM01": " Empresa de Transportes Turismo e Inversiones Señor de La Soledad S.A.",
        "NM02": " Empresa de Transportes Elpa Tours S.A.C.",
        "NM31": " La B",
        "NO36": "",
        "NO40": "",
        "NO45": " La C",
        "NO49": "El Anconero",
        "IO85": " La 87B",
        "IM19": " Cooperativa de Servicios Especiales Transportes Sol y Mar Ltda.",
        "IM20": " Servicio Interconectado de Transporte S.A.C.",
        "ICR10": " Empresa de Transportes y Turismo California Siglo XXI S.A.C.",
        "IO33": " La 9",
        "SO05": " La 5",
        "NM06": " La 49",
        "NM12": " La 36",
        "NM15": " Empresa de Transportes y Servicios El Retablo S.A.C.",
        "NM38": " La A - La B",
        "NO24": " La 24",
        "SO14": " La 25",
        "NM35": " La 60C",
        "NM29": " La 29",
        "EO35": " La 52T"
        // Otros códigos de línea aquí...
    };
    return empresasTransporte[codigoLinea] || "Empresa no encontrada";
}

function obtenerEstadoAutobus(numeroLinea) {
    const estadosPosibles = ["lleno", "con espacio"];
    const estadoAleatorio = estadosPosibles[Math.floor(Math.random() * estadosPosibles.length)];
    return estadoAleatorio;
}


function showTripDetails(directionsResponse) {
    const leg = directionsResponse.routes[0].legs[0];
    const detailsDiv = document.getElementById("trip-details");

    const trafficDelay = leg.duration_in_traffic ? ` (+${leg.duration_in_traffic.text} con tráfico)` : "";
    const arrivalTime = new Date(new Date().getTime() + leg.duration.value * 1000);

    let walkingAndTransferDetails = "";

    leg.steps.forEach((step, index) => {
        if (step.travel_mode === "WALKING" && index < leg.steps.length - 1) {
            const nextStep = leg.steps[index + 1];
            if (nextStep.travel_mode === "TRANSIT" && nextStep.transit) {
                walkingAndTransferDetails += `
                    <p>Caminar ${step.distance.text} hasta ${nextStep.transit.departure_stop.name} para subir al Bus de la linea ${nextStep.transit.line.short_name}</p>
                `;
            }
        }
    });

    detailsDiv.innerHTML = `
        <h3>Detalles del Viaje:</h3>
        <p><strong>Distancia:</strong> ${leg.distance.text}</p>
        <p><strong>Duración:</strong> ${leg.duration.text}${trafficDelay}</p>
        <p><strong>Hora de llegada estimada:</strong> ${arrivalTime.toLocaleTimeString('es-ES')}</p>
        <p><strong>Origen:</strong> ${leg.start_address}</p>
        <p><strong>Destino:</strong> ${leg.end_address}</p>
        <p><strong>Tráfico:</strong> ${leg.duration_in_traffic ? 'Hay tráfico' : 'No hay tráfico'}</p>
        <p><strong>Procede a:</strong> ${walkingAndTransferDetails}</p>
    `;
}

// Llamada a la función para consulta de base de datos (simulada)
//consultarBaseDatos();
