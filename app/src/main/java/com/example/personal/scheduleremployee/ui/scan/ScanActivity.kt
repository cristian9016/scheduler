package com.example.personal.scheduleremployee.ui.scan

import android.app.ProgressDialog
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.example.personal.scheduleremployee.R
import com.example.personal.scheduleremployee.ui.login.LoginActivity
import com.example.personal.scheduleremployee.util.LifeDisposable
import com.example.personal.scheduleremployee.util.buildViewModel
import com.google.zxing.Result
import com.jakewharton.rxbinding2.view.clicks
import com.tbruyelle.rxpermissions2.RxPermissions
import kotlinx.android.synthetic.main.activity_scan.*
import me.dm7.barcodescanner.zxing.ZXingScannerView
import org.jetbrains.anko.*
import java.util.jar.Manifest

class ScanActivity : AppCompatActivity(), ZXingScannerView.ResultHandler {

    val dis = LifeDisposable(this)
    val viewModel:ScanViewModel by lazy { buildViewModel<ScanViewModel>() }
    val permissions : RxPermissions by lazy { RxPermissions(this) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scan)
        barCodeReader.setResultHandler(this)
    }

    override fun onResume() {
        super.onResume()

        permissions.request(android.Manifest.permission.CAMERA)
                .subscribe {
                    if(it) barCodeReader.startCamera()
                    else toast("No tiene permisos, verifique la configuración.")
                }
        dis add btnLogout.clicks()
                .subscribe {
                    alert {
                        title = "Alerta!!"
                        message = "¿Desea cerrar sesión?"
                        yesButton { viewModel.logout(this@ScanActivity)
                                .subscribe {
                                    startActivity<LoginActivity>()
                                    finish()
                                }
                        }
                        noButton {  }
                    }.show()
                }

        dis add correctReg.clicks()
                .subscribe {
                    barCodeReader.setResultHandler(this)
                    barCodeReader.startCamera()
                    correctReg.visibility = View.GONE
                }
    }

    override fun handleResult(result: Result?) {
        toast(result!!.text)
        correctReg.visibility = View.VISIBLE
        barCodeReader.stopCamera()
    }

}
