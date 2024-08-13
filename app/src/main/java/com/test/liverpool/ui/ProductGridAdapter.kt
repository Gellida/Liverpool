package com.test.liverpool.ui

import android.graphics.Color
import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.test.liverpool.R
import com.test.liverpool.data.model.Record


class ProductGridAdapter(
    private var List: List<Record>,
    private val onClick: (Record) -> Unit
) : RecyclerView.Adapter<ProductGridAdapter.ViewHolder>() {


    fun updateList(newList: List<Record>){
        val categoryDiff = ProductGridDiffUtil(List,newList)
        val result = DiffUtil.calculateDiff(categoryDiff)
        List = newList
        result.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_product_grid, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val product = List[position]
        holder.bind(product)
        holder.itemView.setOnClickListener {
            onClick(product)
        }
    }

    override fun getItemCount(): Int {
        return List.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val textName: TextView = itemView.findViewById(R.id.text_name)
        private val textPrice:TextView = itemView.findViewById(R.id.text_price)
        private val textPromotionalPrice:TextView = itemView.findViewById(R.id.text_promotional_price)
        private val colorContainer: LinearLayout = itemView.findViewById(R.id.colorContainer)
        private val textRating:TextView = itemView.findViewById(R.id.text_rating)
        private val textRatingCount:TextView = itemView.findViewById(R.id.text_product_rating_count)
        private val imageProduct: ImageView = itemView.findViewById(R.id.image_product)


        fun bind(product: Record) {
            textName.text = product.productDisplayName
            textPrice.text = product.listPrice.toString()
            textPromotionalPrice.text = product.promoPrice.toString()

            textRating.text = product.productAvgRating.toString()
            textRatingCount.text = product.productRatingCount.toString()

            // Limpiar el contenedor de colores
            colorContainer.removeAllViews()

            // Añadir las variantes de color si no son nulas
            product.variantsColor?.let { variants ->
                variants.forEach { colorVariant ->
                    if (colorVariant.colorHex.isNotEmpty()) {
                        val colorView = View(itemView.context).apply {
                            layoutParams = LinearLayout.LayoutParams(50, 50).apply {
                                marginEnd = 8
                            }
                            setBackgroundColor(Color.parseColor(colorVariant.colorHex))
                        }
                        colorContainer.addView(colorView)
                    }
                }
            }

            // Verificar si existe un precio promocional
            if (product.promoPrice < product.listPrice) {
                textPrice.paintFlags = textPrice.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
            } else {
                textPrice.paintFlags = textPrice.paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()
            }

            // Cargar la imagen usando Glide
            Glide.with(itemView.context)
                .load(product.lgImage) // Usa la URL de la imagen de tamaño grande
                .into(imageProduct)
        }

    }
}