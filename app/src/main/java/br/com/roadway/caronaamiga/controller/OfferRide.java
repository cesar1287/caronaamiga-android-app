package br.com.roadway.caronaamiga.controller;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.android.ui.utils.CustomButton;
import com.android.ui.utils.inject.Inject;
import com.android.ui.utils.inject.InjectView;

import org.springframework.http.HttpMethod;

import java.io.IOException;

import br.com.roadway.caronaamiga.R;
import br.com.roadway.caronaamiga.model.RideStop;
import br.com.thindroid.commons.web.request.ClientException;
import br.com.thindroid.commons.web.request.HttpClient;
import br.com.thindroid.commons.web.request.ServerException;

public class OfferRide extends BaseActivity {

    @InjectView(R.id.confirm)
    private View wgtBtnConfirm;
    @InjectView(R.id.cancel)
    private View wgtBtnCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offer_ride);
        Inject.inject(this, getView());
        initComponents();
        new RequestStops().execute();
    }

    private void initComponents() {
        moveToScreen(wgtBtnConfirm, MainActivity.class);
        moveToScreen(wgtBtnCancel, MainActivity.class);
    }
}
