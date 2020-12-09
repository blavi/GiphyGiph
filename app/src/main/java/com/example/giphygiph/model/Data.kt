package com.example.giphygiph.model

import com.google.gson.annotations.SerializedName

/*
Copyright (c) 2020 Kotlin Data Classes Generated from JSON powered by http://www.json2kotlin.com

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.

For support, please feel free to contact me at https://www.linkedin.com/in/syedabsar */


data class Data (

	@SerializedName("type") val type : String,
	@SerializedName("id") val id : String,
	@SerializedName("url") val url : String,
	@SerializedName("slug") val slug : String,
	@SerializedName("bitly_gif_url") val bitly_gif_url : String,
	@SerializedName("bitly_url") val bitly_url : String,
	@SerializedName("embed_url") val embed_url : String,
	@SerializedName("username") val username : String,
	@SerializedName("source") val source : String,
	@SerializedName("title") val title : String,
	@SerializedName("rating") val rating : String,
	@SerializedName("content_url") val content_url : String,
	@SerializedName("source_tld") val source_tld : String,
	@SerializedName("source_post_url") val source_post_url : String,
	@SerializedName("is_sticker") val is_sticker : Int,
	@SerializedName("import_datetime") val import_datetime : String,
	@SerializedName("trending_datetime") val trending_datetime : String,
	@SerializedName("images") val images : Images,
	@SerializedName("user") val user : User,
	@SerializedName("analytics_response_payload") val analytics_response_payload : String,
	@SerializedName("analytics") val analytics : Analytics
)