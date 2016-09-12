package br.com.roadway.caronaamiga.view;

import android.os.Bundle;
import android.view.View;

import com.android.ui.utils.inject.Inject;
import com.android.ui.utils.inject.InjectView;

import br.com.roadway.caronaamiga.R;
import br.com.roadway.caronaamiga.view.BaseActivity;
import br.com.roadway.caronaamiga.view.MainActivity;

public class SearchRide extends BaseActivity {


    @InjectView(R.id.cancel)
    private View wgtBtnCancel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_ride);
        Inject.inject(this, getView());
        initComponents();
    }

    private void initComponents() {
        moveToScreen(wgtBtnCancel, MainActivity.class);
    }
}
