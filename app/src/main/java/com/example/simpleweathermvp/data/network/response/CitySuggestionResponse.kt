package com.example.simpleweathermvp.data.network.response

import com.google.gson.annotations.SerializedName

data class CitySuggestionResponse(

        @SerializedName("predictions") val predictions : List<Prediction>,
        @SerializedName("status") val status : String
)
{
    data class Prediction (

            @SerializedName("description") val description : String,
            @SerializedName("id") val id : String,
            @SerializedName("matched_substrings") val matchedSubstrings : List<MatchedSubstrings>,
            @SerializedName("place_id") val placeId : String,
            @SerializedName("reference") val reference : String,
            @SerializedName("structured_formatting") val structuredFormatting : StructuredFormatting,
            @SerializedName("terms") val terms : List<Terms>,
            @SerializedName("types") val types : List<String>
    )

    data class MatchedSubstrings (

            @SerializedName("length") val length : Int,
            @SerializedName("offset") val offset : Int
    )

    data class StructuredFormatting (

            @SerializedName("main_text") val mainText : String,
            @SerializedName("main_text_matched_substrings") val mainTextMatchedSubstrings : List<MainTextMatchedSubstrings>,
            @SerializedName("secondary_text") val secondaryText : String
    )

    data class Terms (

            @SerializedName("offset") val offset : Int,
            @SerializedName("value") val value : String
    )

    data class MainTextMatchedSubstrings (

            @SerializedName("length") val length : Int,
            @SerializedName("offset") val offset : Int
    )
}