package br.com.roadway.caronaamiga.view;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;

public class BaseActivity extends AppCompatActivity {

    protected View getView(){
        return ((ViewGroup) getWindow().getDecorView().findViewById(android.R.id.content)).getChildAt(0);
    }

    protected void moveToScreen(View target, final Class<? extends Activity> destiny){
        target.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BaseActivity.this, destiny);
                startActivity(intent);
            }
        });
    }

}
