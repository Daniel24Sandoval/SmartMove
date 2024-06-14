package com.web.optiviaje.service;
import com.google.maps.GeoApiContext;
import com.google.maps.DirectionsApi;
import com.google.maps.errors.ApiException;
import com.google.maps.model.DirectionsResult;
import com.google.maps.model.TravelMode;
import org.springframework.beans.factory.annotation.Value;

import java.io.IOException;
public class GoogleMapsService {

    private final GeoApiContext context;

    public GoogleMapsService(@Value("${google.api.key}") String apiKey) {
        this.context = new GeoApiContext.Builder()
            .apiKey(apiKey)
            .build();
    }

    public DirectionsResult getDirections(String origin, String destination) throws InterruptedException, ApiException, IOException {
        return DirectionsApi.newRequest(context)
            .mode(TravelMode.TRANSIT)
            .origin(origin)
            .destination(destination)
            .await();
    }
}
