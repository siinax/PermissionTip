package com.kedaya.permissiondemo

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.text.bold
import androidx.core.text.buildSpannedString
import androidx.core.text.scale
import com.kedaya.permissiontip.PermissionListener
import com.kedaya.permissiontip.PermissionTip
import com.kedaya.permissiontip.PermissionUtils
import com.kedaya.permissiontip.ShowTipsListener

class MainActivity : AppCompatActivity() {

    private var tips: PermissionTip? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        findViewById<View>(R.id.btn).setOnClickListener {
            request()
        }

    }

    private fun request() {

        when {
            ActivityCompat.checkSelfPermission(this, ss) == PackageManager.PERMISSION_GRANTED -> {


            }
            else -> {

                val tip = buildSpannedString {
                    scale(1.2f) { bold { append("地理位置权限使用说明") } }
                    append("\n")
                    append("用于发布动态、交易信息、聊天分享等位置信息时可附加显示地理位置")
                }
                val staus = PermissionUtils.getAuthorizeStaus(this, ss)
                Log.d("sss>>", "status=$staus")
                /*when (staus) {
                    PermissionUtils.STATUS_DEFAULT,
                    PermissionUtils.STATUS_REFUSE -> {

                    }
                }*/

                PermissionUtils.getShowTip(this, ss, object : ShowTipsListener {
                    override fun showTip() {
                        tips = PermissionTip.Builder(this@MainActivity)
                            .message(tip)
                            .build().apply { show() }
                    }

                })
                PermissionUtils.requestPermission(this, ss, object : PermissionListener {
                    override fun requestResult(isFlog: Boolean) {

                    }

                })
                ActivityCompat.requestPermissions(this, arrayOf(ss), code)

            }

        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        PermissionUtils.onRequestPermissionsResult(this, requestCode, permissions, grantResults)
        if (requestCode == PermissionUtils.REQUEST_CODE) {
            tips?.let { it.dismiss() }
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

            }
        }
    }

    companion object {
        const val ss = Manifest.permission.ACCESS_FINE_LOCATION
        const val code = 33
    }
}
