package com.example.testtaskhandh.game

import android.widget.Button
import android.widget.TextView
import com.example.testtaskhandh.game.objects.Creature
import com.example.testtaskhandh.game.objects.Monster
import com.example.testtaskhandh.game.objects.Player

class GameEngine {
    private val creatureFactory: CreatureFactory = CreatureFactory()
    private var player: Player? = null
    private var monster: Monster? = null
    private var playerMove: Boolean = false
    private var gameOver: Boolean = false

    private var consoleTextView: TextView? = null
    private var hitButton: Button? = null
    private var healingButton: Button? = null
    private var healthMonster: TextView? = null
    private var protectionMonster: TextView? = null
    private var attackMonster: TextView? = null
    private var damageMonster: TextView? = null
    private var healthPlayer: TextView? = null
    private var protectionPlayer: TextView? = null
    private var attackPlayer: TextView? = null
    private var damagePlayer: TextView? = null
    private var chargesHealingPlayer: TextView? = null

    constructor(consoleTextView: TextView, hitButton: Button, healingButton: Button,
                healthMonster: TextView, protectionMonster: TextView, attackMonster: TextView,
                damageMonster: TextView, healthPlayer: TextView, protectionPlayer: TextView,
                attackPlayer: TextView, damagePlayer: TextView, chargesHealingPlayer: TextView) {
        this.consoleTextView = consoleTextView
        this.hitButton = hitButton
        this.healingButton = healingButton
        this.healthMonster = healthMonster
        this.protectionMonster = protectionMonster
        this.attackMonster = attackMonster
        this.damageMonster = damageMonster
        this.healthPlayer = healthPlayer
        this.protectionPlayer = protectionPlayer
        this.attackPlayer = attackPlayer
        this.damagePlayer = damagePlayer
        this.chargesHealingPlayer = chargesHealingPlayer

        hitButton.setOnClickListener() {
            if(!this.gameOver && playerMove) {
                this.message("Вы замахиваетесь!")
                this.hitCreature(this.player!!, this.monster!!)
                this.playerMove = false
                this.gameCycle()
            }
        }
        healingButton.setOnClickListener() {
            if(!this.gameOver && playerMove) {
                if(this.player!!.getChargesHealing() != 0) {
                    this.message("Вы используете лечение")
                    this.message("\t~ Восстановлено здоровья: "+this.player!!.heal().toString())
                    this.playerMove = false
                    this.message("")
                    this.gameCycle()
                } else {
                    this.message("У Вас закончились заряды лечения")
                }
            }
        }
    }

    fun start() {
        this.player = creatureFactory.create(CreatureType.PLAYER) as Player?
        this.monster = creatureFactory.create(CreatureType.MONSTER) as Monster?
        this.message("Появляется монстр!")
        this.gameCycle()
    }

    private fun gameCycle() {
        this.changeState()
        if(!gameOver) {
            if(this.monster!!.isDead()) {
                this.message("Монстр погибает!")
                this.monster = creatureFactory.create(CreatureType.MONSTER) as Monster?
                this.playerMove = false
                this.message("Появляется новый монстр!")
            }
            if(!this.playerMove) {
                this.message("Монстр атакует!")
                this.hitCreature(this.monster!!, this.player!!)
                this.playerMove = true
            }
            if(this.player!!.isDead()) {
                this.gameOver = true
                this.gameCycle()
            }
        } else {
            this.message("Вы проиграли! Конец игры!")
        }
        this.changeState()
    }

    private fun hitCreature(creature: Creature, otherCreature: Creature) {
        val result: Map<String, Any> = creature.hit(otherCreature)
        this.message("\t~ Модификатор атаки: "+result["attackModifier"].toString())
        if (result["success"] as Boolean) {
            this.message("\t~ Успех!")
            this.message("\t~ Нанесено урона: "+result["damage"].toString())
        } else {
            this.message("\t~ Промах!")
        }
        this.message("")
    }

    private fun changeState() {
        this.healthMonster?.setText("Здоровье: "+this.monster!!.getHealth().toString()+"/"+this.monster!!.getMaxHealth().toString())
        this.protectionMonster?.setText("Защита: "+this.monster!!.getProtection().toString())
        this.attackMonster?.setText("Атака: "+this.monster!!.getAttack().toString())
        this.damageMonster?.setText("Урон: "+this.monster!!.getDamage().toString())
        this.healthPlayer?.setText("Здоровье: "+this.player!!.getHealth().toString()+"/"+this.player!!.getMaxHealth().toString())
        this.protectionPlayer?.setText("Защита: "+this.player!!.getProtection().toString())
        this.attackPlayer?.setText("Атака: "+this.player!!.getAttack().toString())
        this.damagePlayer?.setText("Урон: "+this.player!!.getDamage().toString())
        this.chargesHealingPlayer?.setText("Заряды лечения: "+this.player!!.getChargesHealing().toString()+"/"+this.player!!.getMaxChargesHealing().toString())
    }

    private fun message(str: String) {
        val tempSting: String = this.consoleTextView?.text as String
        this.consoleTextView?.setText(tempSting+str+'\n')
    }

}