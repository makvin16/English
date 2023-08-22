package com.zm.englishtraining.data

import android.content.res.AssetManager
import com.squareup.moshi.Moshi
import com.zm.englishtraining.data.model.Data

interface FileDataSource {

    fun read(): List<Data>

    fun read(fileName: String): Data?

    class Impl(
        private val assetManager: AssetManager
    ) : FileDataSource {

        private val moshi = Moshi.Builder().build()
        private val adapter = moshi.adapter(Data::class.java)

        override fun read(): List<Data> {
            val list = mutableListOf<Data>()
            assetManager.list("")?.forEach {
                if (it.endsWith(".json")) {
                    read(it)?.let { data -> list.add(data) }
                }
            }
            return list
        }

        override fun read(fileName: String): Data? {
            val inputStream = assetManager.open(fileName)
            val buffer = ByteArray(inputStream.available())
            inputStream.read(buffer)
            val json = String(buffer)
            return adapter.fromJson(json)
        }
    }
}