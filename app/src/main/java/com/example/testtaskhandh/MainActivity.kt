package com.example.testtaskhandh

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.example.testtaskhandh.game.GameEngine

class MainActivity : AppCompatActivity() {

    var consoleTextView: TextView? = null
    var hitButton: Button? = null
    var healingButton: Button? = null
    var healthMonster: TextView? = null
    var protectionMonster: TextView? = null
    var attackMonster: TextView? = null
    var damageMonster: TextView? = null
    var healthPlayer: TextView? = null
    var protectionPlayer: TextView? = null
    var attackPlayer: TextView? = null
    var damagePlayer: TextView? = null
    var chargesHealingPlayer: TextView? = null
    var gameEngine: GameEngine? = null

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        this.consoleTextView = findViewById(R.id.textView)
        this.hitButton = findViewById(R.id.button1)
        this.healingButton = findViewById(R.id.button2)
        this.healthMonster = findViewById(R.id.healthMonsterTextView)
        this.protectionMonster = findViewById(R.id.protectionMonsterTextView)
        this.attackMonster = findViewById(R.id.attackMonsterTextView)
        this.damageMonster = findViewById(R.id.damageMonsterTextView)
        this.healthPlayer = findViewById(R.id.healthPlayerTextView)
        this.protectionPlayer = findViewById(R.id.protectionPlayerTextView)
        this.attackPlayer = findViewById(R.id.attackPlayerTextView)
        this.damagePlayer = findViewById(R.id.damagePlayerTextView)
        this.chargesHealingPlayer = findViewById(R.id.chargesHealingPlayerTextView)
        gameEngine = GameEngine(
            this.consoleTextView!!,
            this.hitButton!!,
            this.healingButton!!,
            this.healthMonster!!,
            this.protectionMonster!!,
            this.attackMonster!!,
            this.damageMonster!!,
            this.healthPlayer!!,
            this.protectionPlayer!!,
            this.attackPlayer!!,
            this.damagePlayer!!,
            this.chargesHealingPlayer!!
        )
        gameEngine!!.start()
    }

}