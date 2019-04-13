package com.example.waterBalance

import android.os.Bundle
import android.widget.ImageView
import kotlinx.android.synthetic.main.activity_femalebodyvisual.*

class FemaleBodyVisualActivity : DayActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_femalebodyvisual)
        fillFemalePart()
    }

    private fun fillFemalePart() {

        val imageView = findViewById<ImageView>(R.id.femaleBody)
        howManyWater = intent.getIntExtra("waterAmounts", 0)
        usersNorm = intent.getIntExtra("usersNorm",0)
        val partOfNorm : Float = countPartOfNorm()
        if (partOfNorm == 0.0f)
            imageView.setImageResource(R.drawable.female0percent)
        else if (partOfNorm in 0.01f..0.05f)
            imageView.setImageResource(R.drawable.female5percent)
        else if (partOfNorm in 0.05f..0.1f)
            imageView.setImageResource(R.drawable.female10percent)
        else if (partOfNorm in 0.1f..0.15f)
            imageView.setImageResource(R.drawable.female15percent)
        else if (partOfNorm in 0.15f..0.20f)
            imageView.setImageResource(R.drawable.female20percent)
        else if (partOfNorm in 0.2f..0.25f)
            imageView.setImageResource(R.drawable.female25percent)
        else if (partOfNorm in 0.25f..0.30f)
            imageView.setImageResource(R.drawable.female30percent)
        else if (partOfNorm in 0.30f..0.35f)
            imageView.setImageResource(R.drawable.female35percent)
        else if (partOfNorm in 0.35f..0.40f)
            imageView.setImageResource(R.drawable.female40percent)
        else if (partOfNorm in 0.40f..0.45f)
            imageView.setImageResource(R.drawable.female45percent)
        else if (partOfNorm in 0.45f..0.50f)
            imageView.setImageResource(R.drawable.female50percent)
        else if (partOfNorm in 0.50f..0.55f)
            imageView.setImageResource(R.drawable.female55percent)
        else if (partOfNorm in 0.55f..0.60f)
            imageView.setImageResource(R.drawable.female60percent)
        else if (partOfNorm in 0.60f..0.65f)
            imageView.setImageResource(R.drawable.female65percent)
        else if (partOfNorm in 0.65f..0.70f)
            imageView.setImageResource(R.drawable.female70percent)
        else if (partOfNorm in 0.70f..0.75f)
            imageView.setImageResource(R.drawable.female75percent)
        else if (partOfNorm in 0.75f..0.80f)
            imageView.setImageResource(R.drawable.female80percent)
        else if (partOfNorm in 0.80f..0.85f)
            imageView.setImageResource(R.drawable.female85percent)
        else if (partOfNorm in 0.85f..0.90f)
            imageView.setImageResource(R.drawable.female90percent)
        else if (partOfNorm in 0.90f..0.95f)
            imageView.setImageResource(R.drawable.female90percent)
        else if (partOfNorm in 0.95f..0.98f)
            imageView.setImageResource(R.drawable.female95percent)
        else if (partOfNorm > 0.99f)
            imageView.setImageResource(R.drawable.female100percent)

    }
}