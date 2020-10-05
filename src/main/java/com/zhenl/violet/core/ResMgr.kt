package com.zhenl.violet.core

import android.content.Context
import android.content.res.Resources

/**
 * Created by lin on 2020/10/3.
 */
object ResMgr {

    lateinit var resource: Resources

    fun init(context: Context) {
        resource = context.resources
    }
}