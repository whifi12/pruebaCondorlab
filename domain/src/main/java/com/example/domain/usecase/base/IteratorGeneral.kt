package com.example.domain.usecase.base

interface IteratorGeneral <out Results,in Params> {

     fun execute(params: Params) : Results
}