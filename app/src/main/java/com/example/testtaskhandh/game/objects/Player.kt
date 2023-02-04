package com.example.testtaskhandh.game.objects

class Player: Creature {

    private var chargesHealing: Int = 0
    private var maxChargesHealing: Int = 0

    constructor(attack: Int, protection: Int, health: Int, maxHealth: Int, damage: IntRange,
                maxChargesHealing: Int, chargesHealing: Int)
            : super(attack, protection, health, maxHealth, damage) {
        this.setMaxChargesHealing(maxChargesHealing)
        this.setChargesHealing(chargesHealing)
    }

    fun heal(): Int {
        var result: Int = 0
        if(this.chargesHealing > 0) {
            val halfHealth: Int = this.getMaxHealth() / 2
            val health: Int = this.getHealth() + halfHealth
            result = halfHealth
            this.setHealth(health)
            this.setChargesHealing(this.chargesHealing - 1)
        }
        return result
    }

    fun setChargesHealing(chargesHealing: Int) {
        var tempChargesHealing = chargesHealing
        if(tempChargesHealing < 0) {
            tempChargesHealing = 0;
        }
        if(tempChargesHealing > this.maxChargesHealing) {
            tempChargesHealing = this.maxChargesHealing;
        }
        this.chargesHealing = tempChargesHealing
    }

    fun setMaxChargesHealing(maxChargesHealing: Int) {
        var tempMaxChargesHealing = maxChargesHealing
        if(tempMaxChargesHealing < 0) {
            tempMaxChargesHealing = 0;
        }
        if(tempMaxChargesHealing < this.chargesHealing) {
            this.chargesHealing = tempMaxChargesHealing
        }
        this.maxChargesHealing = tempMaxChargesHealing
    }

    fun getChargesHealing(): Int {
        return this.chargesHealing
    }

    fun getMaxChargesHealing(): Int {
        return this.maxChargesHealing
    }
}