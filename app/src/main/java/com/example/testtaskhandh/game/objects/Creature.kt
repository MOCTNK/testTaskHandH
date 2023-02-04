package com.example.testtaskhandh.game.objects

import kotlin.random.Random

abstract class Creature {
    private var attack: Int = 0
    private var protection: Int = 0
    private var health: Int = 0
    private var maxHealth: Int = 0
    private var damage: IntRange? = null

    constructor(attack: Int, protection: Int, maxHealth: Int, health: Int, damage: IntRange) {
        this.set(attack, protection, health, maxHealth, damage)
    }

    fun set(attack: Int, protection: Int, health: Int, maxHealth: Int, damage: IntRange) {
        this.setAttack(attack)
        this.setProtection(protection)
        this.setMaxHealth(maxHealth)
        this.setHealth(health)
        this.setDamage(damage)
    }

    fun hit(otherCreature: Creature): Map<String, Any> {
        val result = mutableMapOf("attackModifier" to 0, "success" to false, "damage" to 0)
        var attackModifier: Int = this.getAttack()!! - otherCreature.getProtection()!! + 1
        if(attackModifier <= 0) {
            attackModifier = 1
        }
        result.put("attackModifier", attackModifier)

        var success: Boolean = false
        for (i in 1..attackModifier) {
            val sideCube: Int = Random.nextInt(1, 6)
            if(sideCube == 5 || sideCube == 6) {
                success = true
                break
            }
        }
        result.put("success", success)

        if(success) {
            val tempDamage = this.getDamage()!!.random()
            val healthOtherCreature: Int = otherCreature.getHealth() - tempDamage
            otherCreature.setHealth(healthOtherCreature)
            result.put("damage", tempDamage)
        }

        return result
    }

    fun isDead(): Boolean {
        return this.health <= 0
    }

    fun setAttack(attack: Int) {
        var tempAttack = attack
        if(tempAttack < 1) {
            tempAttack = 1;
        }
        if(tempAttack > 20) {
            tempAttack = 20;
        }
        this.attack = tempAttack
    }

    fun setProtection(protection: Int) {
        var tempProperties = protection
        if(tempProperties < 1) {
            tempProperties = 1;
        }
        if(tempProperties > 20) {
            tempProperties = 20;
        }
        this.protection = tempProperties
    }

    fun setHealth(health: Int) {
        var tempHealth = health
        if(tempHealth < 0) {
            tempHealth = 0
        }
        if(tempHealth > this.maxHealth) {
            tempHealth = this.maxHealth
        }
        this.health = tempHealth
    }

    fun setMaxHealth(health: Int) {
        var tempHealth = health
        if(tempHealth < 0) {
            tempHealth = 0
        }
        if(tempHealth < this.health) {
            this.health = this.maxHealth
        }
        this.maxHealth = tempHealth
    }

    fun setDamage(damage: IntRange) {
        this.damage = damage
    }

    fun getAttack(): Int {
        return this.attack
    }

    fun getProtection(): Int {
        return this.protection
    }

    fun getHealth(): Int {
        return this.health
    }

    fun getMaxHealth(): Int {
        return this.maxHealth
    }

    fun getDamage(): IntRange? {
        return this.damage
    }
}