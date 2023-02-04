package com.example.testtaskhandh.game

import com.example.testtaskhandh.game.objects.Creature
import com.example.testtaskhandh.game.objects.Player
import kotlin.random.Random

class PlayerBuilder: Builder {

    override fun build(): Creature {
        val attack: Int = Random.nextInt(1, 20)
        val protection: Int = Random.nextInt(1, 20)
        val maxHealth: Int = Random.nextInt(100, 300)
        val health: Int = maxHealth
        val damageNum: Int = Random.nextInt(10, 30)
        val damage: IntRange = IntRange(damageNum, damageNum + 10)
        return Player(attack, protection, maxHealth, health, damage, 3, 3)
    }
}