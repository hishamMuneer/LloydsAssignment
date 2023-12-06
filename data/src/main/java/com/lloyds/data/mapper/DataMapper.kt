package com.lloyds.data.mapper

interface DataMapper<T, R> {
    fun map(data: T): R
}