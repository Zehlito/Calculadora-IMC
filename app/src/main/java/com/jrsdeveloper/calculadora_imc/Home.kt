package com.jrsdeveloper.calculadora_imc

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.MobileAds
import com.jrsdeveloper.calculadora_imc.databinding.ActivityHomeBinding

class Home : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    private lateinit var mAdView: AdView

    //Classe principal do app
    //declaração de variaveis/componentes da tela
    //Inicializar componentes



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Define a orientação do aplicativo para retrato
        //activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

        //inicializar componontes
        loadBannerAd()

        binding.idTxtInterpretacaoImc!!.visibility = View.GONE
    }


    //recebendo dados de entrada
    fun calcularImc(view: View?) {
        val strCampoPeso: String
        val strCampoAltura: String
        strCampoPeso = binding.editPeso!!.text.toString()
        strCampoAltura = binding.editAltura!!.text.toString()

        //verificar campos e valida
        if (!strCampoPeso.isEmpty()) {
             if (!strCampoAltura.isEmpty()) {
                val peso: Double
                var altura: Double
                val calculo: Double
                peso = strCampoPeso.toDouble()
                altura = strCampoAltura.toDouble()
                altura = altura * altura
                calculo = peso / altura

                if (calculo > 0) {

                    binding.idTxtInterpretacaoImc!!.visibility = View.VISIBLE
                    interpretacaoImc(calculo)
                }
            }else {
                //aviso para preencher o campos vazio
                Toast.makeText(
                    this@Home,
                    "Preencha o campo Altura !",
                    Toast.LENGTH_LONG
                ).show()
                //retornar p/ campo
                binding.editAltura!!.requestFocus()
            }
        } else {
            //aviso para preencher o campos vazio
            Toast.makeText(
                this@Home,
                "Preencha os campos!",
                Toast.LENGTH_LONG
            ).show()
            //retornar p/ campo
            binding.editPeso!!.requestFocus()
        }
    }

    //limpar campos
    fun limparCampos(view: View?) {
        supportActionBar?.hide()
        window.statusBarColor = Color.parseColor("#AE7502AF")
        binding.idTxtInterpretacaoImc!!.visibility = View.GONE
        binding.editPeso!!.setText("")
        binding.editAltura!!.setText("")
        binding.editPeso!!.requestFocus()
    }

    //intrepretação do imc
    fun interpretacaoImc(imc: Double) {
        // Esconder o teclado ao clicar no botão
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE)as InputMethodManager
        val imcformat = String.format("%.2f",imc)
        if (imc < 10) {
            imm.hideSoftInputFromWindow(binding.botaoCalcular.windowToken,0)
            binding.idTxtInterpretacaoImc!!.setText("Seu IMC é $imcformat Você digitou algum valor errado, lembre-se em altura de usar ponto '.' para separar casas decimais. \n Exemplo: Altura (1.70) \n Exemplo: Peso (100)")
            binding.idTxtInterpretacaoImc!!.setTextColor(Color.BLUE)
            supportActionBar?.hide()
            window.statusBarColor = Color.parseColor("Blue")
        } else if (imc <= 10 || imc < 18) {
            imm.hideSoftInputFromWindow(binding.botaoCalcular.windowToken,0)
            binding.idTxtInterpretacaoImc!!.setText("Seu IMC é $imcformat e ficou abaixo do ideal")
            binding.idTxtInterpretacaoImc!!.setTextColor(Color.BLACK)
            supportActionBar?.hide()
            window.statusBarColor = Color.parseColor("Yellow")
        } else if (imc <= 18 || imc < 25) {
            imm.hideSoftInputFromWindow(binding.botaoCalcular.windowToken,0)
            binding.idTxtInterpretacaoImc!!.setText("Seu IMC é $imcformat e está na faixa ideal")
            binding.idTxtInterpretacaoImc!!.setTextColor(Color.BLACK)
            supportActionBar?.hide()
            window.statusBarColor = Color.parseColor("Green")
        } else if (imc <= 25 || imc < 30) {
            imm.hideSoftInputFromWindow(binding.botaoCalcular.windowToken,0)
            binding.idTxtInterpretacaoImc!!.setText("Seu IMC é $imcformat e está com sobrepeso")
            binding.idTxtInterpretacaoImc.setTextColor(Color.BLACK)
            supportActionBar?.hide()
            window.statusBarColor = Color.parseColor("Gray")
        } else if (imc <= 30 || imc < 40) {
            imm.hideSoftInputFromWindow(binding.botaoCalcular.windowToken,0)
            binding.idTxtInterpretacaoImc!!.setText("Seu IMC é $imcformat e está com obesidade")
            binding.idTxtInterpretacaoImc!!.setTextColor(Color.BLACK)
            supportActionBar?.hide()
            window.statusBarColor = Color.parseColor("Magenta")
        } else {
            binding.idTxtInterpretacaoImc!!.setText("Seu IMC é $imcformat obesidade mórbida!")
            imm.hideSoftInputFromWindow(binding.botaoCalcular.windowToken,0)
            supportActionBar?.hide()
            window.statusBarColor = Color.parseColor("Red")
        }

    }
    private fun loadBannerAd() {

        MobileAds.initialize(this) {}
        mAdView = findViewById(R.id.adView)
        val adRequest = AdRequest.Builder().build()
        mAdView.loadAd(adRequest)

        mAdView.adListener = object: AdListener() {
            override fun onAdClicked() {
                // Code to be executed when the user clicks on an ad.

            }

            override fun onAdClosed() {
                // Code to be executed when the user is about to return
                // to the app after tapping on an ad.
                //Toast.makeText(this@Level_Medium, "Ad cloased", Toast.LENGTH_SHORT).show()
            }

            override fun onAdFailedToLoad(adError : LoadAdError) {
                // Code to be executed when an ad request fails.
            }

            override fun onAdImpression() {
                // Code to be executed when an impression is recorded
                // for an ad.
            }

            override fun onAdLoaded() {
                // Code to be executed when an ad finishes loading.
                //Toast.makeText(this@Level_Medium, "Ad Loaded", Toast.LENGTH_SHORT).show()
            }

            override fun onAdOpened() {
                // Code to be executed when an ad opens an overlay that
                // covers the screen.
            }
        }


    }

}