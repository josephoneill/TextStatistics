package com.joneill.textstatistics.data.repository

import io.reactivex.Completable

interface MessagesRepo {
    fun loadData() : Completable
}