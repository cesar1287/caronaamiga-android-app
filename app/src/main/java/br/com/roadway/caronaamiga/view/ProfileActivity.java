package br.com.roadway.caronaamiga.view;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.android.ui.utils.inject.Inject;
import com.android.ui.utils.inject.InjectView;

import br.com.roadway.caronaamiga.R;
import br.com.roadway.caronaamiga.view.BaseActivity;
import br.com.roadway.caronaamiga.view.MainActivity;

public class ProfileActivity extends BaseActivity {


    @InjectView(R.id.confirm)
    private View wgtBtnConfirm;
    @InjectView(R.id.cancel)
    private View wgtBtnCancel;

    Spinner spinnerVinculos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        Inject.inject(this, getView());

        spinnerVinculos = (Spinner)findViewById(R.id.spinnerVinculos);
        String[] vinculos = getResources().getStringArray(R.array.vinculos);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, vinculos);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerVinculos.setAdapter(adapter);

        initComponents();
    }

    private void initComponents(){
        moveToScreen(wgtBtnConfirm, MainActivity.class);
        moveToScreen(wgtBtnCancel, MainActivity.class);
    }

}
