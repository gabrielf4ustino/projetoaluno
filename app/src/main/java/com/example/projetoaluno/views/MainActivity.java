package com.example.projetoaluno.views;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.projetoaluno.databinding.ActivityMainBinding;


public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.btnCelular.setOnClickListener(v -> {
            Intent it=new Intent(MainActivity.this, AlunoList.class);
            startActivity(it);
        });
        binding.btnMarcas.setOnClickListener(v -> {
            Intent it=new Intent(MainActivity.this, CursoList.class);
            startActivity(it);
        });

    }
}