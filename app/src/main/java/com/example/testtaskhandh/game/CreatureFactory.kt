package com.example.testtaskhandh.game

import com.example.testtaskhandh.game.objects.Creature
import com.example.testtaskhandh.game.objects.Player

class CreatureFactory {

    private var creature: Creature? = null
    private var monsterBuilder: MonsterBuilder = MonsterBuilder()
    private var playerBuilder: PlayerBuilder = PlayerBuilder()

    fun create(type: CreatureType): Creature? {

        when (type) {
            CreatureType.PLAYER -> this.creature = playerBuilder.build()
            CreatureType.MONSTER -> this.creature = monsterBuilder.build()
            else -> this.creature = null
        }

        return this.creature
    }
}