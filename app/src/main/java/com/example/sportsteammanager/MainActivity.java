package com.example.sportsteammanager;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
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

    // ActivityResultLauncher for profile selection
    private ActivityResultLauncher<Intent> profileLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                    Intent data = result.getData();
                    int imageId = data.getIntExtra("imageID", -1);

                    if (imageId != -1) {
                        String drawableName = getDrawableNameFromImageId(imageId);
                        int resID = getResources().getIdentifier(drawableName, "drawable", getPackageName());
                        imageTeamFlag.setImageResource(resID);
                    }
                }
            }
    );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Attachs this activity to the XML layout
        setContentView(R.layout.activity_main);

        // Links Java variables to the views defined in activity_main.xml
        imageTeamFlag = findViewById(R.id.image_team_flag);
        editTeamName = findViewById(R.id.edit_team_name);
        editPostalCode = findViewById(R.id.edit_postal_code);

        // Set click listener on the flag image to open profile selector
        imageTeamFlag.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, ProfileManger.class);
            profileLauncher.launch(intent);
        });
    }

    private String getDrawableNameFromImageId(int imageId) {
        if (imageId == R.id.imageView12) return "flag_ca";
        else if (imageId == R.id.imageView13) return "flag_eg";
        else if (imageId == R.id.imageView14) return "flag_fr";
        else if (imageId == R.id.imageView15) return "flag_jp";
        else if (imageId == R.id.imageView16) return "flag_kr";
        else if (imageId == R.id.imageView17) return "flag_sp";
        else if (imageId == R.id.imageView18) return "flag_us";
        else if (imageId == R.id.imageView19) return "flag_uk";
        else if (imageId == R.id.imageView20) return "flag_tr";
        return "flag_ca";
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

        // Builds a geo URI that will search for the given postal code
        Uri geoUri = Uri.parse("geo:0,0?q=" + Uri.encode(postalCode));

        // Create an ACTION_VIEW intent for the geo URI
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, geoUri);

        // Requests the Google Maps package
        mapIntent.setPackage("com.google.android.apps.maps");

        // Start the external Google Maps activity
        startActivity(mapIntent);
    }
}

