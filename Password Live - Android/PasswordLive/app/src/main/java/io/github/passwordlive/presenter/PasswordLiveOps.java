package io.github.passwordlive.presenter;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import java.io.UnsupportedEncodingException;
import java.lang.ref.WeakReference;
import java.security.NoSuchAlgorithmException;

import io.github.passwordlive.view.MainActivity;
import io.github.passwordlive.model.PasswordGenerator;
import io.github.passwordlive.model.Constants;

/**
 * Created by brubecd on 8/12/2015.
 */
public class PasswordLiveOps {
    private WeakReference<MainActivity> mainActivity;
    private PasswordGenerator passwordGenerator;

    public PasswordLiveOps(MainActivity mainActivity) {
        this.mainActivity = new WeakReference<MainActivity>(mainActivity);
        this.passwordGenerator = new PasswordGenerator();
    }

    public String getPassword(String secretKey, String whatFor, String length, boolean useUpperCase, boolean useLowerCase, boolean useNumbers, boolean useSpecialCharacters) {
        Context context = mainActivity.get().getApplicationContext();
        int passwordLength = 0;
        try {
            passwordLength = Integer.parseInt(length);
        } catch(NumberFormatException ex) {
            Toast.makeText(context, "Invalid integer", Toast.LENGTH_SHORT).show();
            return "";
        }

        // Generate the password
        if ( canGeneratePassword(secretKey, whatFor, passwordLength) ) {
            String generatedPassword = "";
            try {
                generatedPassword = passwordGenerator.compute(secretKey, whatFor, passwordLength, useUpperCase, useLowerCase, useNumbers, useSpecialCharacters);
            } catch(NoSuchAlgorithmException e) {
                //Log.e()
            } catch (UnsupportedEncodingException e) {

            }

            return generatedPassword;
        }

        return "";
    }

    private boolean canGeneratePassword(String secretKey, String whatFor, int length) {
        Context context = mainActivity.get().getApplicationContext();
        boolean canGeneratePassword = false;
        if ( secretKey == null || secretKey.isEmpty() ) {
            Toast.makeText(context, "Invalid integer", Toast.LENGTH_SHORT).show();
        } else if ( whatFor == null || whatFor.isEmpty() ) {
            Toast.makeText(context, "Invalid integer", Toast.LENGTH_SHORT).show();
        } else if ( length < Constants.MIN_LENGTH ) {
            Toast.makeText(context, "Invalid integer", Toast.LENGTH_SHORT).show();
        } else if ( length > Constants.MAX_LENGTH ){
            Toast.makeText(context, "Invalid integer", Toast.LENGTH_SHORT).show();
        } else {
            canGeneratePassword = true;
        }

        return canGeneratePassword;
    }


}
