package com.example.unitarytests.source

import kotlin.random.Random

class NumberRepository{
    fun getRandomNumber(limit: Int): Int{
        return Random.nextInt(limit)
    }
}