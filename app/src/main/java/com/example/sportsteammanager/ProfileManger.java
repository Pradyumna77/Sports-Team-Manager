package com.example.sportsteammanager;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ProfileManger extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_profile_manger);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    ActivityResultLauncher<Intent> profileActivityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == Activity.RESULT_OK && result.getData() != null) {
                    Intent data = result.getData();
                    ImageView avatarImage = findViewById(R.id.image_team_flag);

                    int imageId = data.getIntExtra("imageID", R.id.imageView12);
                    String drawableName = "flag_ca";

                    if (imageId == R.id.imageView12) {
                        drawableName = "flag_ca";
                    } else if (imageId == R.id.imageView13) {
                        drawableName = "flag_eg";
                    } else if (imageId == R.id.imageView14) {
                        drawableName = "flag_fr";
                    } else if (imageId == R.id.imageView15) {
                        drawableName = "flag_jp";
                    } else if (imageId == R.id.imageView16) {
                        drawableName = "flag_kr";
                    } else if (imageId == R.id.imageView17) {
                        drawableName = "flag_sp";
                    } else if (imageId == R.id.imageView18) {
                        drawableName = "flag_us";
                    } else if (imageId == R.id.imageView19) {
                        drawableName = "flag_uk";
                    } else if (imageId == R.id.imageView20) {
                        drawableName = "flag_tr";
                    }

                    int resID = getResources().getIdentifier(drawableName, "drawable", getPackageName());
                    avatarImage.setImageResource(resID);
                }
            }
    );

    public void OnSetAvatarButton(View view) {
        Intent intent = new Intent(getApplicationContext(), ProfileManger.class);
        profileActivityResultLauncher.launch(intent);
    }

    public void SetTeamIcon(View view) {
        Intent returnIntent = new Intent();
        ImageView selectedImage = (ImageView) view;
        returnIntent.putExtra("imageID", selectedImage.getId());
        setResult(RESULT_OK, returnIntent);
        finish();
    }
}