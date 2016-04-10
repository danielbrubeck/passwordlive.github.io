package io.github.passwordlive.view;

import android.os.Bundle;
import android.preference.EditTextPreference;
import android.preference.PreferenceFragment;
import android.preference.SwitchPreference;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;

import com.valarauko.passwordlive.R;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Display the fragment as the main content.
        getFragmentManager().beginTransaction().replace(android.R.id.content,
                new PrefsFragment()).commit();
    }
    public static class PrefsFragment extends PreferenceFragment {
        EditTextPreference mClearClipboardTime;
        SwitchPreference mAutoStart;
        SwitchPreference mCopyToClipboard;
        SwitchPreference mClearClipboard;
        SwitchPreference mCloseApp;
        SwitchPreference mDisplayNotification;

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            // Load the preferences from an XML resource
            addPreferencesFromResource(R.xml.pref_general);
            mAutoStart = (SwitchPreference)findPreference("display_autostart");
            mCopyToClipboard = (SwitchPreference)findPreference("copy_clipboard");
            mClearClipboard = (SwitchPreference)findPreference("clear_clipboard");
            mCloseApp = (SwitchPreference)findPreference("close_app");
            mDisplayNotification = (SwitchPreference)findPreference("display_notification");
            mClearClipboardTime = (EditTextPreference)findPreference("keep_clipboard");

        }
    }
}
