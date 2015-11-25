package com.devtrigger.goeuro;

import com.devtrigger.goeuro.entity.Location;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;
import java.util.List;

/**
 * {@link LocationService} implementation that loads data using Location JSON API
 */
public class JsonApiLocationService implements LocationService {

    private static final String API_BASE_URL = "http://api.goeuro.com/api/v2/";
    private static final String CITY_LOCATIONS_URL = API_BASE_URL + "position/suggest/en/$CITY_NAME$";

    private final ObjectMapper mapper = new ObjectMapper();

    @Override
    public List<Location> getLocations(String cityName) throws IOException {
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpGet request = new HttpGet(CITY_LOCATIONS_URL.replace("$CITY_NAME$", cityName));
            HttpResponse response = httpClient.execute(request);
            int status = response.getStatusLine().getStatusCode();
            if (status < 200 || status >= 300)
                throw new ClientProtocolException("Unexpected response status: " + status);
            HttpEntity entity = response.getEntity();
            if (entity == null)
                throw new ClientProtocolException("Empty response received");
            return mapper.readValue(entity.getContent(), new TypeReference<List<Location>>() {});
        }
    }
}
