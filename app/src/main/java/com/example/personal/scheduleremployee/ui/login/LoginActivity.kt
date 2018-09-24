package com.example.personal.scheduleremployee.ui.login

import android.app.ProgressDialog
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.personal.scheduleremployee.R
import com.example.personal.scheduleremployee.data.models.Login
import com.example.personal.scheduleremployee.data.preferences.UserSession
import com.example.personal.scheduleremployee.ui.scan.ScanActivity
import com.example.personal.scheduleremployee.util.*
import com.jakewharton.rxbinding2.view.clicks
import kotlinx.android.synthetic.main.activity_login.*
import org.jetbrains.anko.indeterminateProgressDialog
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

class LoginActivity : AppCompatActivity() {

    val dis = LifeDisposable(this)
    val viewModel:LoginViewModel by lazy { buildViewModel<LoginViewModel>() }
    val dialog : ProgressDialog by lazy { indeterminateProgressDialog ( "PorFavor Espere, Iniciando Sesión","") }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        if(UserSession.logged) {
            startActivity<ScanActivity>()
            finish()
        }
    }

    override fun onResume() {
        super.onResume()

        dis add btnLogin.clicks()
                .flatMap { validateForm(R.string.empty_fields,usrLogin.text(),passLogin.text()) }
                .subscribe {
                    dialog.show()
                    viewModel.login(Login(it[0], AppTool.encodeSHA512(it[1])))
                            .subscribe(
                                    {
                                        dialog.dismiss()
                                        UserSession.logged = true
                                        UserSession.idUser = it
                                        startActivity<ScanActivity>()
                                        finish()
                                    },
                                    {
                                        dialog.dismiss()
                                        toast("usuario o contraseña incorrectos")
                                    }
                            )
                }
    }

}
