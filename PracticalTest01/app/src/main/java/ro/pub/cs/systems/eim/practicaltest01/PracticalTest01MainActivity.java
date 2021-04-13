package ro.pub.cs.systems.eim.practicaltest01;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class PracticalTest01MainActivity extends AppCompatActivity {

    // declaram butoanele folosite in Design
    private EditText leftEditText;  // EditText pentru stanga
    private EditText rightEditText; // EditText pentru dreapta
    private Button pressMeButton;   // Butonul din stanga
    private Button pressMeTooButton; // Butonul din dreapta
    private Button navigate_to_secondary_activity; // Butonul pentru a schimba cu a doua activitate

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practical_test01_main);

        // initializarea butoanelor folosite
        leftEditText = (EditText) findViewById(R.id.left_edit_text);
        rightEditText = (EditText) findViewById(R.id.right_edit_text);
        pressMeButton = (Button) findViewById(R.id.press_me_button);
        pressMeTooButton = (Button) findViewById(R.id.press_me_too_button);
        navigate_to_secondary_activity = (Button) findViewById(R.id.navigate_to_secondary_activity);

        // initial, ambele EditText-uri vor avea ca default value 0
        leftEditText.setText(String.valueOf(0));
        rightEditText.setText(String.valueOf(0));

        // pentru a functiona butoanele de listen trebuie sa le avem si in metoda onCreate()
        pressMeButton.setOnClickListener(buttonClickListener);
        pressMeTooButton.setOnClickListener(buttonClickListener);
        navigate_to_secondary_activity.setOnClickListener(buttonClickListener);

        // daca s-a salvat starea
        if (savedInstanceState != null) {

            // daca starea salvata contine valoarea EditText-ului din stanga
            if (savedInstanceState.containsKey(Constants.LEFT_COUNT)) {
                // se va afisa numarul de click-uri din EditText-ul din stanga
                leftEditText.setText(savedInstanceState.getString(Constants.LEFT_COUNT));
            } else {
                // se va afisa valoarea default pentru EditText-ul din stanga
                leftEditText.setText(String.valueOf(0));
            }

            // daca starea salvata contine valoarea EditText-ului din dreapta
            if (savedInstanceState.containsKey(Constants.RIGHT_COUNT)) {
                // se va afisa numarul de click-uri din EditText-ul din dreapta
                rightEditText.setText(savedInstanceState.getString(Constants.RIGHT_COUNT));
            } else {
                // se va afisa valoarea default pentru EditText-ul din dreapta
                rightEditText.setText(String.valueOf(0));
            }

            // daca nu s-a salvat nicio stare => este printata valoarea default pentru EditText-uri
        } else {
            leftEditText.setText(String.valueOf(0));
            rightEditText.setText(String.valueOf(0));
        }
    }

    @Override
    public void onRestart() {
        super.onRestart();
    }


    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putString(Constants.LEFT_COUNT, leftEditText.getText().toString());
        savedInstanceState.putString(Constants.RIGHT_COUNT, rightEditText.getText().toString());
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        if (savedInstanceState.containsKey(Constants.LEFT_COUNT)) {
            leftEditText.setText(savedInstanceState.getString(Constants.LEFT_COUNT));
        } else {
            leftEditText.setText(String.valueOf(0));
        }
        if (savedInstanceState.containsKey(Constants.RIGHT_COUNT)) {
            rightEditText.setText(savedInstanceState.getString(Constants.RIGHT_COUNT));
        } else {
            rightEditText.setText(String.valueOf(0));
        }
    }

    // ascultator care va incremena valorile cand sunt apasate butoanele
    private ButtonClickListener buttonClickListener = new ButtonClickListener();

    private class ButtonClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {

            // variabila pt numarul de click-uri stanga
            int leftNumberOfClicks = Integer.valueOf(leftEditText.getText().toString());

            // variabila pt numarul de click-uri dreapta
            int rightNumberOfClicks = Integer.valueOf(rightEditText.getText().toString());

            switch (view.getId()) {
                // daca apas pe butonul din stanga
                case R.id.press_me_button:
                    // creste numarul de apasari din stanga
                    leftNumberOfClicks++;
                    // este afisat numarul curent de click-uri din stanga
                    leftEditText.setText(String.valueOf(leftNumberOfClicks));
                    break;

                // daca apas pe butonul din dreapta
                case R.id.press_me_too_button:
                    // creste numarul de apasari din dreapta
                    rightNumberOfClicks++;
                    // afisez numarul curent de click-uri din dreapta
                    rightEditText.setText(String.valueOf(rightNumberOfClicks));
                    break;

                case R.id.navigate_to_secondary_activity:
                    Intent intent = new Intent(getApplicationContext(), PracticalTest01SecondaryActivity.class);
                    // suma nr de click-uri dreapta + nr de click-uri stanga
                    int numberOfClicks = Integer.parseInt(leftEditText.getText().toString()) + Integer.parseInt(rightEditText.getText().toString());
                    // printarea sumei cu ajutorul intentiei, in a doua activitate
                    intent.putExtra(Constants.NUMBER_OF_CLICKS, numberOfClicks);
                    startActivityForResult(intent, Constants.SECONDARY_ACTIVITY_REQUEST_CODE);
                    break;
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        if (requestCode == Constants.SECONDARY_ACTIVITY_REQUEST_CODE) {
            Toast.makeText(this, "The activity returned with result " + resultCode, Toast.LENGTH_LONG).show();
        }
    }
}
