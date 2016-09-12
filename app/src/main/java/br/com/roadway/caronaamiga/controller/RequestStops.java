package br.com.roadway.caronaamiga.controller;

import android.os.AsyncTask;
import android.util.Log;

import org.springframework.http.HttpMethod;

import java.io.IOException;

import br.com.roadway.caronaamiga.model.RideStop;
import br.com.thindroid.commons.web.request.ClientException;
import br.com.thindroid.commons.web.request.HttpClient;
import br.com.thindroid.commons.web.request.ServerException;

public class RequestStops extends AsyncTask {
    @Override
    protected Object doInBackground(Object[] params) {
        HttpClient httpClient = new HttpClient();
        try {
            RideStop obg = httpClient.executeJSONResponse(HttpClient.buildRequest("http://localhost:8080/stops", HttpMethod.GET, null), RideStop.class);
            Log.d("EOQ", obg.getName());
        } catch (IOException|ClientException|ServerException e) {
            e.printStackTrace();
        }
        return null;
    }
}
