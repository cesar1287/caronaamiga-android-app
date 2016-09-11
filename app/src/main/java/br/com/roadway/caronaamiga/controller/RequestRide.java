package br.com.roadway.caronaamiga.controller;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.android.ui.utils.inject.Inject;
import com.android.ui.utils.inject.InjectView;

import br.com.roadway.caronaamiga.R;

public class RequestRide extends BaseActivity {

    @InjectView(R.id.confirm)
    private View wgtBtnConfirm;
    @InjectView(R.id.cancel)
    private View wgtBtnCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ride_request);
        Inject.inject(this, getView());
        initComponents();
    }

    private void initComponents() {
        moveToScreen(wgtBtnConfirm, SearchRide.class);
        moveToScreen(wgtBtnCancel, MainActivity.class);
    }

}
