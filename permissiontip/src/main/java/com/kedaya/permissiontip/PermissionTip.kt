package com.kedaya.permissiontip

import android.content.Context
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.StyleRes
import androidx.appcompat.app.AppCompatDialog


var tag = 0

/**
 * Create by xujie on 2023/12/8
 */
class PermissionTip constructor(
    private val builder: Builder,
    @StyleRes style: Int = R.style.TipsDialog
) : AppCompatDialog(builder.context, style) {


    init {
        val view = layoutInflater.inflate(R.layout.layout_tip, null)
        view.findViewById<View>(R.id.frame).setOnClickListener { dismiss() }
        view.findViewById<TextView>(R.id.message).text =
            builder.msg ?: "需要获取定位权限，无法获取你的定位信息，建议开启"
        setContentView(view)
        window?.also {
            it.setGravity(Gravity.TOP)
            it.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        }
    }

    class Builder(val context: Context) {

        internal var msg: CharSequence? = ""
        internal var r: Boolean = false

        fun message(msg: CharSequence) = apply {
            this.msg = msg
        }

        fun ssrpr(r: Boolean) = apply {
            this.r = r
        }

        fun build() = PermissionTip(this)

    }

    companion object {

        fun reset() {
            tag = 0
        }
    }
}