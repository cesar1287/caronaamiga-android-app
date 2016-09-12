package br.com.roadway.caronaamiga.view;

import android.os.Bundle;
import android.view.View;

import com.android.ui.utils.inject.Inject;
import com.android.ui.utils.inject.InjectView;

import br.com.roadway.caronaamiga.R;
import br.com.roadway.caronaamiga.view.BaseActivity;
import br.com.roadway.caronaamiga.view.MainActivity;
import br.com.roadway.caronaamiga.view.SearchRide;

public class RideFounded extends BaseActivity {

    @InjectView(R.id.confirm)
    private View wgtBtnConfirm;
    @InjectView(R.id.cancel)
    private View wgtBtnCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ride_founded);
        Inject.inject(this, getView());

        initComponents();
    }

    private void initComponents(){
        moveToScreen(wgtBtnConfirm, MainActivity.class);
        moveToScreen(wgtBtnCancel, SearchRide.class);
    }
}
