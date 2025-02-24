package com.radlance.deezermusic.common

import android.content.Context
import javax.inject.Inject

class BaseResourceProvider @Inject constructor(
    private val context: Context
): ResourceProvider {
    override fun getString(id: Int): String = context.getString(id)
}