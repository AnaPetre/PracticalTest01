package ro.pub.cs.systems.eim.practicaltest01;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelStoreOwner;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class PracticalTest01SecondaryActivity extends AppCompatActivity {

    private TextView number_of_clicks;
    private Button ok;
    private Button cancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practical_test01_secondary);

        number_of_clicks = (TextView)findViewById(R.id.number_of_clicks);
        // cream intentia prin care va deveni secondary activity accesibila
        Intent intent = getIntent();
        if (intent != null && intent.getExtras().containsKey(Constants.NUMBER_OF_CLICKS)) {
            // toate astea sunt declarate in Constants
            int numberOfClicks = intent.getIntExtra(Constants.NUMBER_OF_CLICKS, -1);
            // afisarea numarului total de click-uri
            number_of_clicks.setText(String.valueOf(numberOfClicks));
        }

        //initializez butonul de OK si apelez listener-ul lui
        ok = (Button)findViewById(R.id.ok);
        ok.setOnClickListener(buttonClickListener);

        //initializez butonul de OK si apelez listener-ul lui
        cancel = (Button)findViewById(R.id.cancel);
        cancel.setOnClickListener(buttonClickListener);

    }
    // buton de tip listener pentru butoanele de OK si CANCEL + clasa
    private ButtonClickListener buttonClickListener = new ButtonClickListener();
    private class ButtonClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.ok:
                    setResult(RESULT_OK, null);
                    break;

                case R.id.cancel:
                    setResult(RESULT_CANCELED, null);
                    break;
            }
            finish();
        }
    }

}