package com.example.giphygiph.model

import com.google.gson.annotations.SerializedName

/*
Copyright (c) 2020 Kotlin Data Classes Generated from JSON powered by http://www.json2kotlin.com

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.

For support, please feel free to contact me at https://www.linkedin.com/in/syedabsar */


data class Images (

	@SerializedName("original") val original : Original,
	@SerializedName("downsized") val downsized : Downsized,
	@SerializedName("downsized_large") val downsized_large : Downsized_large,
	@SerializedName("downsized_medium") val downsized_medium : Downsized_medium,
	@SerializedName("downsized_small") val downsized_small : Downsized_small,
	@SerializedName("downsized_still") val downsized_still : Downsized_still,
	@SerializedName("fixed_height") val fixed_height : Fixed_height,
	@SerializedName("fixed_height_downsampled") val fixed_height_downsampled : Fixed_height_downsampled,
	@SerializedName("fixed_height_small") val fixed_height_small : Fixed_height_small,
	@SerializedName("fixed_height_small_still") val fixed_height_small_still : Fixed_height_small_still,
	@SerializedName("fixed_height_still") val fixed_height_still : Fixed_height_still,
	@SerializedName("fixed_width") val fixed_width : Fixed_width,
	@SerializedName("fixed_width_downsampled") val fixed_width_downsampled : Fixed_width_downsampled,
	@SerializedName("fixed_width_small") val fixed_width_small : Fixed_width_small,
	@SerializedName("fixed_width_small_still") val fixed_width_small_still : Fixed_width_small_still,
	@SerializedName("fixed_width_still") val fixed_width_still : Fixed_width_still,
	@SerializedName("looping") val looping : Looping,
	@SerializedName("original_still") val original_still : Original_still,
	@SerializedName("original_mp4") val original_mp4 : Original_mp4,
	@SerializedName("preview") val preview : Preview,
	@SerializedName("preview_gif") val preview_gif : Preview_gif,
	@SerializedName("preview_webp") val preview_webp : Preview_webp,
	@SerializedName("480w_still") val W_480_still : W_480_still
)