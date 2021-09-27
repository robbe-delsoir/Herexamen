package com.example.myapplication

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    private lateinit var rollButton: Button
    private lateinit var deleteButton: Button
    private lateinit var diceImageView: ImageView
    private lateinit var diceImageView2: ImageView
    private lateinit var diceImageView3: ImageView
    private lateinit var diceImageView4: ImageView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        this.diceImageView = findViewById(R.id.imageDices)
        this.diceImageView2 = findViewById(R.id.imageDices2)
        this.diceImageView3 = findViewById(R.id.imageDices3)
        this.diceImageView4 = findViewById(R.id.imageDices4)
        this.rollButton = findViewById(R.id.btnrolldice)
        this.deleteButton = findViewById(R.id.btndeletedice)
    }

    override fun onStart() {
        super.onStart()
        this.rollButton.setOnClickListener{
            var iToasty = Random.nextInt(resources.getStringArray(R.array.toastText).size)

            Toast.makeText(this, resources.getStringArray(R.array.toastText)[iToasty], Toast.LENGTH_SHORT).show()

            rollDice()
        }
        this.deleteButton.setOnClickListener{
            deleteAllDice()
        }
    }

    private fun rollDice(){

        var dice = Random.nextInt(6) +1

        var result = when (dice) {
            //case 1:
            1 -> R.drawable.dice_1
            2 -> R.drawable.dice_2
            3 -> R.drawable.dice_3
            4 -> R.drawable.dice_4
            5 -> R.drawable.dice_5
            else -> R.drawable.dice_6
        }
        this.diceImageView.setImageResource(result)
        this.diceImageView2.setImageResource(result)
        this.diceImageView3.setImageResource(result)
        this.diceImageView4.setImageResource(result)
    }


    private fun deleteAllDice(){
        this.diceImageView.setImageResource(R.drawable.empty_dice)
        this.diceImageView2.setImageResource(R.drawable.empty_dice)
        this.diceImageView3.setImageResource(R.drawable.empty_dice)
        this.diceImageView4.setImageResource(R.drawable.empty_dice)
    }

}