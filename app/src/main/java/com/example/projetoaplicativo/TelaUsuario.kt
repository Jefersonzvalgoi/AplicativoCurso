package com.example.projetoaplicativo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.projetoaplicativo.databinding.ActivityTelaUsuarioBinding
import com.google.firebase.auth.FirebaseAuth

class TelaUsuario : AppCompatActivity() {
    private lateinit var view : ActivityTelaUsuarioBinding
    private var autenticacao = FirebaseAuth.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        view = ActivityTelaUsuarioBinding.inflate(layoutInflater)
        setContentView(view.root)
        supportActionBar?.hide()
        sair()
    }
    fun sair(){
        view.btSair.setOnClickListener {
            autenticacao.signOut()
            var intencao = Intent(this, MainActivity::class.java)
            startActivity(intencao)
            finish()
        }
    }

}