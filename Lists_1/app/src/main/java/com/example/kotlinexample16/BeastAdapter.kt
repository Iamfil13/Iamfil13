package com.example.kotlinexample16

import android.annotation.SuppressLint
import android.os.Parcelable
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class BeastAdapter(
    private val onItemClicked: (position: Int) -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var beasts: Array<Parcelable> = emptyArray()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            TYPE_ANIMAL -> AnimalHolder(parent.inflate(R.layout.item_animal), onItemClicked)
            TYPE_PREDATOR -> PredatorHolder(parent.inflate(R.layout.item_predator), onItemClicked)
            else -> error("Incorrect viewType= $viewType")
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (beasts[position]) {
            is Beast.Predator -> TYPE_PREDATOR
            is Beast.Animals -> TYPE_ANIMAL
            else -> error("Incorrect position $position")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is AnimalHolder -> {
                val beast = beasts[position].let { it as? Beast.Animals }
                    ?: error("Beast in position $position is not a animal")
                holder.bind(beast)
            }

            is PredatorHolder -> {
                val beast = beasts[position].let { it as? Beast.Predator }
                    ?: error("Beast in position $position is not a predator")
                holder.bind(beast)
            }

            else -> error("Incorrect view holder = $holder")
        }
    }

    override fun getItemCount(): Int = beasts.size

    fun updateBeasts(newBeast: Array<Parcelable>) {
        beasts = newBeast
    }

    abstract class BaseBeastHolder(
        view: View,
        onItemClicked: (position: Int) -> Unit
    ) : RecyclerView.ViewHolder(view) {

        private val nameTextView: TextView = view.findViewById(R.id.nameTextView)
        private val ageTextView: TextView = view.findViewById(R.id.ageTextView)
        private val avatarImageView: ImageView = view.findViewById(R.id.avatarImageView)

        init {
            view.setOnClickListener {
                onItemClicked(adapterPosition)
            }
        }

        @SuppressLint("SetTextI18n")
        protected fun bindMainInfo(
            name: String,
            avatarLink: String,
            age: Int
        ) {
            nameTextView.text = name
            ageTextView.text = "Возраст: $age"

            Glide.with(itemView)
                .load(avatarLink)
                .placeholder(R.drawable.ic_portrait)
                .into(avatarImageView)
        }
    }

    class AnimalHolder(
        view: View,
        onItemClicked: (position: Int) -> Unit
    ) : BaseBeastHolder(view, onItemClicked) {
        init {
            view.findViewById<TextView>(R.id.predatorTextView).isVisible = false
        }

        fun bind(beast: Beast.Animals) {
            bindMainInfo(beast.name, beast.avatarLink, beast.age)
        }
    }

    class PredatorHolder(
        view: View,
        onItemClicked: (position: Int) -> Unit
    ) : BaseBeastHolder(view, onItemClicked) {

        private val weaponTextView: TextView = view.findViewById(R.id.weaponTextView)

        @SuppressLint("SetTextI18n")
        fun bind(beast: Beast.Predator) {
            bindMainInfo(beast.name, beast.avatarLink, beast.age)
            weaponTextView.text = "Weapon: ${beast.weapon}"
        }
    }

    companion object {
        private const val TYPE_ANIMAL = 1
        private const val TYPE_PREDATOR = 2
    }
}