package com.example.domain.usecase.base

interface Iterator<out Results,in Params> {

    suspend fun execute(params: Params) : Results
}