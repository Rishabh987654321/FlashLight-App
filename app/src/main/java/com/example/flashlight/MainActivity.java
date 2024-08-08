package com.example.flashlight;

import android.hardware.camera2.CameraManager;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.flashlight.databinding.ActivityMainBinding;


public class MainActivity extends AppCompatActivity {

ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.button.setOnClickListener(v -> {
            if(binding.button.getText().equals("Turn On")){
                binding.button.setText(R.string.turn_off);
                binding.flashimage.setImageResource(R.drawable.flashlight);
                changeLightState(true);
            }else{
                binding.button.setText(R.string.turn_on);
                binding.flashimage.setImageResource(R.drawable.turnedoff);
                changeLightState(false);
            }

        });
    }

    private void changeLightState(boolean state) {
        CameraManager cameraManager= (CameraManager) getSystemService(CAMERA_SERVICE);
        String camId;
        try{
            camId=cameraManager.getCameraIdList()[0];
            cameraManager.setTorchMode(camId,state);
        }catch (Exception e){
            e.printStackTrace();
            }
    }

    @Override
    protected void onStart() {
        super.onStart();
        binding.button.setText(R.string.turn_on);
    }
}