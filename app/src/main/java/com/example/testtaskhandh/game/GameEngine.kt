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
                this.message("???? ??????????????????????????!")
                this.hitCreature(this.player!!, this.monster!!)
                this.playerMove = false
                this.gameCycle()
            }
        }
        healingButton.setOnClickListener() {
            if(!this.gameOver && playerMove) {
                if(this.player!!.getChargesHealing() != 0) {
                    this.message("???? ?????????????????????? ??????????????")
                    this.message("\t~ ?????????????????????????? ????????????????: "+this.player!!.heal().toString())
                    this.playerMove = false
                    this.message("")
                    this.gameCycle()
                } else {
                    this.message("?? ?????? ?????????????????????? ???????????? ??????????????")
                }
            }
        }
    }

    fun start() {
        this.player = creatureFactory.create(CreatureType.PLAYER) as Player?
        this.monster = creatureFactory.create(CreatureType.MONSTER) as Monster?
        this.message("???????????????????? ????????????!")
        this.gameCycle()
    }

    private fun gameCycle() {
        this.changeState()
        if(!gameOver) {
            if(this.monster!!.isDead()) {
                this.message("???????????? ????????????????!")
                this.monster = creatureFactory.create(CreatureType.MONSTER) as Monster?
                this.playerMove = false
                this.message("???????????????????? ?????????? ????????????!")
            }
            if(!this.playerMove) {
                this.message("???????????? ??????????????!")
                this.hitCreature(this.monster!!, this.player!!)
                this.playerMove = true
            }
            if(this.player!!.isDead()) {
                this.gameOver = true
                this.gameCycle()
            }
        } else {
            this.message("???? ??????????????????! ?????????? ????????!")
        }
        this.changeState()
    }

    private fun hitCreature(creature: Creature, otherCreature: Creature) {
        val result: Map<String, Any> = creature.hit(otherCreature)
        this.message("\t~ ?????????????????????? ??????????: "+result["attackModifier"].toString())
        if (result["success"] as Boolean) {
            this.message("\t~ ??????????!")
            this.message("\t~ ???????????????? ??????????: "+result["damage"].toString())
        } else {
            this.message("\t~ ????????????!")
        }
        this.message("")
    }

    private fun changeState() {
        this.healthMonster?.setText("????????????????: "+this.monster!!.getHealth().toString()+"/"+this.monster!!.getMaxHealth().toString())
        this.protectionMonster?.setText("????????????: "+this.monster!!.getProtection().toString())
        this.attackMonster?.setText("??????????: "+this.monster!!.getAttack().toString())
        this.damageMonster?.setText("????????: "+this.monster!!.getDamage().toString())
        this.healthPlayer?.setText("????????????????: "+this.player!!.getHealth().toString()+"/"+this.player!!.getMaxHealth().toString())
        this.protectionPlayer?.setText("????????????: "+this.player!!.getProtection().toString())
        this.attackPlayer?.setText("??????????: "+this.player!!.getAttack().toString())
        this.damagePlayer?.setText("????????: "+this.player!!.getDamage().toString())
        this.chargesHealingPlayer?.setText("???????????? ??????????????: "+this.player!!.getChargesHealing().toString()+"/"+this.player!!.getMaxChargesHealing().toString())
    }

    private fun message(str: String) {
        val tempSting: String = this.consoleTextView?.text as String
        this.consoleTextView?.setText(tempSting+str+'\n')
    }

}