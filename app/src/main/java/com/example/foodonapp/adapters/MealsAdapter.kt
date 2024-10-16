package com.example.foodonapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.foodonapp.databinding.MealItemBinding
import com.example.foodonapp.pojo.Meal
import com.example.foodonapp.pojo.MealsByCategory

class MealsAdapter: RecyclerView.Adapter<MealsAdapter.FavoritesMealsAdapterViewHolder>() {
    lateinit var onItemClick:((Meal)->Unit)
    private var mealsList = ArrayList<MealsByCategory>()
    fun setMeals(mealsList:ArrayList<MealsByCategory>){
        this.mealsList = mealsList
        notifyDataSetChanged()
    }
    inner class FavoritesMealsAdapterViewHolder(val binding: MealItemBinding) :
        RecyclerView.ViewHolder(binding.root){
        fun bind(meal: Meal) {
            Glide.with(itemView).load(meal.strMealThumb).into(binding.imgMeal)
            binding.root.setOnClickListener {
                onItemClick?.invoke(
                    meal
                )  // Pass Meal instead of MealsByCategory
            }
        }
        }


    private val diffUtil = object : DiffUtil.ItemCallback<Meal>() {
        override fun areItemsTheSame(oldItem: Meal, newItem: Meal): Boolean {
            return oldItem.idMeal == newItem.idMeal
        }

        override fun areContentsTheSame(oldItem: Meal, newItem: Meal): Boolean {
            return oldItem == newItem
        }

    }

    val differ = AsyncListDiffer(this,diffUtil)
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FavoritesMealsAdapterViewHolder {
        return FavoritesMealsAdapterViewHolder(
            MealItemBinding.inflate(
                LayoutInflater.from(parent.context),parent,false
            )
        )
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: FavoritesMealsAdapterViewHolder, position: Int) {
//        val meal = differ.currentList[position]
//        Glide.with(holder.itemView).load(meal.strMealThumb).into(holder.binding.imgMeal)
//        holder.binding.tvMealName.text = meal.strMeal
//        holder.itemView.setOnClickListener {
//            onItemClick.invoke(mealsList[position])
//        }

        val meal = differ.currentList[position]
        holder.binding.tvMealName.text = meal.strMeal
        holder.bind(meal)
    }



}

