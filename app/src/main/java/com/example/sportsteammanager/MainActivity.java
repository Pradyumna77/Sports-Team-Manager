package com.example.sportsteammanager;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

/**
 * MainActivity
 *
 * Main screen for the Sports Team Manager app.
 * Lets the user enter a team name and postal code,
 * and open the postal code in Google Maps.
 */
public class MainActivity extends AppCompatActivity {

    // ImageView that will display the team flag avatar on the main screen
    private ImageView imageTeamFlag;

    // Text field where the user types the team name
    private EditText editTeamName;

    // Text field where the user types the postal code / address
    private EditText editPostalCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Attach this activity to the XML layout
        setContentView(R.layout.activity_main);

        // Link Java variables to the views defined in activity_main.xml
        imageTeamFlag = findViewById(R.id.image_team_flag);
        editTeamName = findViewById(R.id.edit_team_name);
        editPostalCode = findViewById(R.id.edit_postal_code);
    }

    /**
     * Called when the "Open in Google Maps" button is pressed.
     * Reads the postal code from the text field and launches
     * Google Maps with a search for that postal code.
     */
    public void onOpenInGoogleMaps(View view) {
        // Get the postal code typed by the user and remove extra spaces
        String postalCode = editPostalCode.getText().toString().trim();

        // Make sure the field is not empty
        if (postalCode.isEmpty()) {
            Toast.makeText(this, "Please enter a postal code", Toast.LENGTH_SHORT).show();
            return;
        }

        // Build a geo URI that will search for the given postal code
        Uri geoUri = Uri.parse("geo:0,0?q=" + Uri.encode(postalCode));

        // Create an ACTION_VIEW intent for the geo URI
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, geoUri);

        // Explicitly request the Google Maps package, as in the lab slides
        mapIntent.setPackage("com.google.android.apps.maps");

        // Start the external Google Maps activity
        startActivity(mapIntent);
    }
}

