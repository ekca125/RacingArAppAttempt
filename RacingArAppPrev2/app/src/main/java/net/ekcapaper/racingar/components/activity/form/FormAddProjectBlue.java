package net.ekcapaper.racingar.components.activity.form;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;

import net.ekcapaper.racingar.components.R;
import net.ekcapaper.racingar.components.utils.Tools;

public class FormAddProjectBlue extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_add_project_blue);
        Tools.setSystemBarColor(this, R.color.blue_900);

        (findViewById(R.id.bt_close)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
