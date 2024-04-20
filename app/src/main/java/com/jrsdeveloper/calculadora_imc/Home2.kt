package com.jrsdeveloper.calculadora_imc

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.jrsdeveloper.calculadora_imc.databinding.ActivityHomeBinding

class Home2 : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.editAltura
    }
}