package com.example.simpleweathermvp.data.network.response

import com.google.gson.annotations.SerializedName

data class CityResponse(
        @SerializedName("html_attributions") val html_attributions : List<String>,
        @SerializedName("result") val result : Result,
        @SerializedName("status") val status : String
)
{

    data class Result (

            @SerializedName("geometry") val geometry : Geometry
    )
    {
        data class Geometry (

                @SerializedName("location") val location : Location,
                @SerializedName("viewport") val viewport : Viewport
        )
        {
            data class Location (

                    @SerializedName("lat") val lat : Double,
                    @SerializedName("lng") val lng : Double
            )

            data class Viewport (

                    @SerializedName("northeast") val northeast : Northeast,
                    @SerializedName("southwest") val southwest : Southwest
            )
            {
                data class Northeast (

                        @SerializedName("lat") val lat : Double,
                        @SerializedName("lng") val lng : Double
                )

                data class Southwest (

                        @SerializedName("lat") val lat : Double,
                        @SerializedName("lng") val lng : Double
                )
            }
        }
    }
}