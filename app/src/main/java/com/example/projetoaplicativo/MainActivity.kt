package com.example.projetoaplicativo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.projetoaplicativo.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {

    private lateinit var view2 : ActivityMainBinding
    private var autenticacao = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        view2 = ActivityMainBinding.inflate(layoutInflater)
        setContentView(view2.root)
        supportActionBar?.hide()
        entraCadastro()
        entrar()
    }
    fun entrar(){
        view2.btEntrar.setOnClickListener {
            val email = view2.campoEmail.text.toString()
            val senha = view2.campoSenha.text.toString()
            val condicao = email.isEmpty() || senha.isEmpty()
            if (condicao) {
                Toast.makeText(this, "Preencha os campos", Toast.LENGTH_LONG).show()
            }else{
                autenticacao.signInWithEmailAndPassword(email,senha).addOnCompleteListener(){sucesso->
                    if(sucesso.isSuccessful){
                        entraTelaPrincipal()
                    }
                }.addOnFailureListener(){
                    Toast.makeText(this, "Erro ao fazer login!", Toast.LENGTH_LONG)
                        .show()
                }
            }
        }

    }
    fun entraCadastro(){
        view2.textoTelaCadastro.setOnClickListener () {
            var click = Intent(this, TelaCadastro::class.java)
            startActivity(click)
            finish()
        }

    }
    fun entraTelaPrincipal(){
        var click = Intent(this, TelaUsuario::class.java)
        startActivity(click)
        finish()
    }
}