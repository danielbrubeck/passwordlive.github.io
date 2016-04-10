package io.github.passwordlive.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.valarauko.passwordlive.R;

import io.github.passwordlive.presenter.PasswordLiveOps;


public class MainActivity extends AppCompatActivity {

    private EditText mSecretKeyword;
    private EditText mWhatFor;
    private EditText mPasswordLength;
    private CheckBox mNumbers;
    private CheckBox mSpecialChars;
    private CheckBox mUppercase;
    private CheckBox mLowercase;
    private EditText mResult;
    private Button mGeneratePassword;
    private PasswordLiveOps passwordLiveOps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSecretKeyword = (EditText)findViewById(R.id.editSecretKeyword);
        mWhatFor = (EditText)findViewById(R.id.editWhatFor);
        mPasswordLength = (EditText)findViewById(R.id.editPasswordLength);
        mUppercase = (CheckBox) findViewById(R.id.uppercaseSwitch);
        mLowercase = (CheckBox) findViewById(R.id.lowercaseSwitch);
        mNumbers = (CheckBox) findViewById(R.id.numbersSwitch);
        mSpecialChars = (CheckBox) findViewById( R.id.specialCharsSwitch);
        mResult = (EditText) findViewById(R.id.txtResult);
        mGeneratePassword = (Button) findViewById(R.id.btnGenerate);

        passwordLiveOps = new PasswordLiveOps(this);

        mGeneratePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mResult.setText(passwordLiveOps.getPassword(mSecretKeyword.getText().toString(),
                        mWhatFor.getText().toString(),
                        mPasswordLength.getText().toString(),
                        mUppercase.isChecked(),
                        mLowercase.isChecked(),
                        mNumbers.isChecked(),
                        mSpecialChars.isChecked()));
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            startActivity(new Intent(this.getApplicationContext(), SettingsActivity.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
