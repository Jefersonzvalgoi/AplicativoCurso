package com.example.projetoaplicativo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.projetoaplicativo.databinding.ActivityMainBinding
import com.example.projetoaplicativo.databinding.ActivityTelaCadastroBinding
import com.google.firebase.auth.FirebaseAuth

class TelaCadastro : AppCompatActivity() {
    private lateinit var view : ActivityTelaCadastroBinding
    private var autenticacao = FirebaseAuth.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        view = ActivityTelaCadastroBinding.inflate(layoutInflater)
        setContentView(view.root)
        cadastrar()

        supportActionBar?.hide()
    }
    fun cadastrar(){
        view.btCadastrar.setOnClickListener {
            val email = view.campoEmail.text.toString()
            val nome = view.campoNome.text.toString()
            val cpf = view.campoCpf.text.toString()
            val telefone = view.campoTelefone.text.toString()
            val endereco = view.campoEndereco.text.toString()
            val senha = view.campoSenha.text.toString()
            val condicao = senha.isEmpty() || email.isEmpty() || nome.isEmpty() || cpf.isEmpty() || telefone.isEmpty() || endereco.isEmpty()

            if(condicao){
                Toast.makeText(this,"Preencha todos os campos",Toast.LENGTH_LONG).show()
            }else{
                autenticacao.createUserWithEmailAndPassword(email,senha).addOnCompleteListener(){sucesso->
                    if(sucesso.isSuccessful){
                        Toast.makeText(this, "Cadastro realizado com sucesso!", Toast.LENGTH_LONG).show()
                    }
                }.addOnFailureListener(){
                    Toast.makeText(this, "Erro ao realizar o cadastro!", Toast.LENGTH_SHORT).show()
                }

            }

        }
    }
}