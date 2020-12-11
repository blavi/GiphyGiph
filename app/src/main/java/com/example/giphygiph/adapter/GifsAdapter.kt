package com.example.giphygiph.adapter

import android.graphics.drawable.Drawable
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.gif.GifDrawable
import com.bumptech.glide.request.RequestOptions
import com.example.giphygiph.databinding.ItemLayoutBinding
import com.example.giphygiph.intent.UserIntent
import com.example.giphygiph.model.Gifs

class GifsAdapter(private val clickListener: (UserIntent.ShareGif) -> Boolean): RecyclerView.Adapter<GifsAdapter.DataViewHolder>() {
    private lateinit var gifs: Gifs

    class DataViewHolder(private val binding: ItemLayoutBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(giphUrl: String, clickListener: (UserIntent.ShareGif) -> Boolean) {
            Glide.with(binding.image.context)
                    .asGif()
                    .load(Uri.parse(giphUrl))
                    .fitCenter()
//                    .listener(object : RequestListener<GifDrawable> {
//                        override fun onLoadFailed(e: GlideException?, model: Any?, target: Target<GifDrawable>?, isFirstResource: Boolean): Boolean {
//                            return false
//                        }
//
//                        override fun onResourceReady(resource: GifDrawable?, model: Any?, target: Target<GifDrawable>?, dataSource: com.bumptech.glide.load.DataSource?, isFirstResource: Boolean): Boolean {
////                            binding.shareBtn.visibility = View.VISIBLE
//                            return false
//                        }
//                    })
                    .apply(RequestOptions().diskCacheStrategy(DiskCacheStrategy.ALL))
                    .placeholder(android.R.drawable.gallery_thumb)
                    .into(binding.image)

            binding.image.setOnLongClickListener {
//                Glide.with(binding.image.context) // Actually it won't download another time if the file has been cached
//                    .download(Uri.parse(giphUrl))
//                    .listener(object : RequestListener<File?> {
//                        override fun onLoadFailed(@Nullable e: GlideException?, model: Any, target: Target<File?>, isFirstResource: Boolean): Boolean {
//                            return false
//                        }
//
//                        override fun onResourceReady(resource: File?, model: Any?, target: Target<File?>?, dataSource: DataSource?, isFirstResource: Boolean): Boolean {
//                            val uri = Uri.fromFile(resource)
//                            clickListener(UserIntent.ShareGif(uri))
//                            return false
//                        }
//                    })
//                    .submit()
//
//                return@setOnLongClickListener false

                val newGifDrawable = (binding.image.drawable).constantState?.newDrawable()?.current as Drawable
                clickListener(UserIntent.ShareGif(newGifDrawable as GifDrawable))
//                newGifDrawable?.let {
//                    it as GifDrawable
//
//                }
//                if (newGifDrawable != null) {
//                    clickListener(UserIntent.ShareGif(newGifDrawable))
//                }
                return@setOnLongClickListener false
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        val binding = ItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return DataViewHolder(binding)
    }

    override fun getItemCount(): Int = gifs.data.size

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) =
        holder.bind(gifs.data[position].images.fixed_width.url, clickListener)

    fun addData(list: Gifs) {
        gifs =  list
    }



}