package com.cat.cattasticapp

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide

class BreedDetailActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_breed_detail)

        // Get the breed object from the intent
        val breed = intent.getSerializableExtra("breed") as responseDataClassItem

        // Initialize views
        val breedImageView = findViewById<ImageView>(R.id.breedDetailImageView)
        val breedNameTextView = findViewById<TextView>(R.id.breedDetailNameTextView)
        val breedDescriptionTextView = findViewById<TextView>(R.id.breedDetailDescriptionTextView)
        val breedTemperamentTextView = findViewById<TextView>(R.id.breedDetailTemperamentTextView)
        val breedOriginTextView = findViewById<TextView>(R.id.breedDetailOriginTextView)
        val breedLifeSpanTextView = findViewById<TextView>(R.id.breedDetailLifeSpanTextView)
        val breedWeightTextView = findViewById<TextView>(R.id.breedDetailWeightTextView)

        // Additional details
        val breedDogFriendlyTextView = findViewById<TextView>(R.id.breedDetailDogFriendlyTextView)
        val breedEnergyLevelTextView = findViewById<TextView>(R.id.breedDetailEnergyLevelTextView)
        val breedAffectionLevelTextView = findViewById<TextView>(R.id.breedDetailAffectionLevelTextView)
        val breedSheddingLevelTextView = findViewById<TextView>(R.id.breedDetailSheddingLevelTextView)
        val breedGroomingTextView = findViewById<TextView>(R.id.breedDetailGroomingTextView)
        val breedHealthIssuesTextView = findViewById<TextView>(R.id.breedDetailHealthIssuesTextView)
        val breedIntelligenceTextView = findViewById<TextView>(R.id.breedDetailIntelligenceTextView)
        val breedSocialNeedsTextView = findViewById<TextView>(R.id.breedDetailSocialNeedsTextView)
        val breedStrangerFriendlyTextView = findViewById<TextView>(R.id.breedDetailStrangerFriendlyTextView)
        val breedWikipediaTextView = findViewById<TextView>(R.id.breedDetailWikipediaTextView)

        // Set breed details to the respective views
        breedNameTextView.text = breed.name
        breedDescriptionTextView.text = breed.description
        breedTemperamentTextView.text = "Temperament: ${breed.temperament}"
        breedOriginTextView.text = "Origin: ${breed.origin}"
        breedLifeSpanTextView.text = "Life Span: ${breed.life_span} years"
        breedWeightTextView.text = "Weight: ${breed.weight.metric} kg"

        // Set additional breed details
        breedDogFriendlyTextView.text = "Dog Friendly: ${breed.dog_friendly}"
        breedEnergyLevelTextView.text = "Energy Level: ${breed.energy_level}"
        breedAffectionLevelTextView.text = "Affection Level: ${breed.affection_level}"
        breedSheddingLevelTextView.text = "Shedding Level: ${breed.shedding_level}"
        breedGroomingTextView.text = "Grooming: ${breed.grooming}"
        breedHealthIssuesTextView.text = "Health Issues: ${breed.health_issues}"
        breedIntelligenceTextView.text = "Intelligence: ${breed.intelligence}"
        breedSocialNeedsTextView.text = "Social Needs: ${breed.social_needs}"
        breedStrangerFriendlyTextView.text = "Stranger Friendly: ${breed.stranger_friendly}"

        // Load breed image using Glide
        Glide.with(this)
            .load("https://cdn2.thecatapi.com/images/${breed.reference_image_id}.jpg")
            .into(breedImageView)

        // Optional: Wikipedia link
        breedWikipediaTextView.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(breed.wikipedia_url)
            startActivity(intent)
        }
    }
}
