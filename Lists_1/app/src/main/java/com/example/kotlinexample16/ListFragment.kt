package com.example.kotlinexample16

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Parcelable
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.view.isInvisible
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class ListFragment : Fragment(R.layout.fragment_animal_list) {

    private var beasts: Array<Parcelable> = emptyArray()

    private var beastAdapter: BeastAdapter? = null

    @SuppressLint("NotifyDataSetChanged")
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        if (savedInstanceState != null) {
            beasts = savedInstanceState.getParcelableArray(KEY_BEASTS) ?: error("Error")
            isVisibleTextView()
        }

        initList()

        val addFab = requireView().findViewById<FloatingActionButton>(R.id.addFab)
        addFab.setOnClickListener { showDialog() }
        beastAdapter?.updateBeasts(beasts)
        beastAdapter?.notifyDataSetChanged()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        beastAdapter = null
    }

    private fun initList() {
        beastAdapter = BeastAdapter { position -> deleteBeast(position) }
        val animalList: RecyclerView = requireView().findViewById(R.id.animalList)

        with(animalList) {
            adapter = beastAdapter
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
        }
    }

    private fun deleteBeast(position: Int) {
        Toast.makeText(
            requireContext(),
            "Delete animal",
            Toast.LENGTH_SHORT
        ).show()
        beasts = beasts.filterIndexed { index, _ -> index != position }.toTypedArray()
        beastAdapter?.updateBeasts(beasts)
        beastAdapter?.notifyItemRemoved(position)
        isVisibleTextView()

    }


    private fun showDialog() {

        val beastsName = arrayOf("Rhinoceros", "Cheetah", "Crocodile", "Honey badger", "Doe")

        activity?.let {
            val dialog = AlertDialog.Builder(it)
                .setTitle("Add an animal")
                .setIcon(R.drawable.ic_add)
                .setItems(
                    beastsName
                ) { _, which ->
                    Toast.makeText(
                        it, "Added animal: ${beastsName[which]}",
                        Toast.LENGTH_SHORT
                    ).show()
                    when (which) {
                        0 -> {
                            beasts = (listOf(
                                Beast.Animals(
                                    name = "Rhinoceros",
                                    avatarLink = "https://cdnimg.rg.ru/img/content/180/26/88/888888888_d_850.jpg",
                                    age = 13
                                )
                            ) + beasts).toTypedArray()
                            updateAdapter()
                        }
                        1 -> {
                            beasts = (listOf(
                                Beast.Predator(
                                    name = "Cheetah",
                                    avatarLink = "https://animalsglobe.ru/wp-content/uploads/2011/09/d0b3d0b5d0bfd0b0d180d0b41.jpg",
                                    age = 7,
                                    weapon = "claws"
                                )
                            ) + beasts).toTypedArray()
                            updateAdapter()
                        }
                        2 -> {
                            beasts = (listOf(
                                Beast.Predator(
                                    name = "Crocodile",
                                    avatarLink = "https://naked-science.ru/wp-content/uploads/2021/01/9JjSZDs8K459WC5U4a_qT_.jpg",
                                    age = 15,
                                    weapon = "teeth"
                                )
                            ) + beasts).toTypedArray()
                            updateAdapter()
                        }
                        3 -> {
                            beasts = (listOf(
                                Beast.Predator(
                                    name = "Honey badger",
                                    avatarLink = "https://63samara.ru/wp-content/uploads/2020/12/1-108.jpg",
                                    age = 4,
                                    weapon = "fearlessness"
                                )
                            ) + beasts).toTypedArray()
                            updateAdapter()
                        }
                        4 -> {
                            beasts = (listOf(
                                Beast.Animals(
                                    name = "Doe",
                                    avatarLink = "https://i.ytimg.com/vi/KvZuawWUsjs/maxresdefault.jpg",
                                    age = 2
                                )
                            ) + beasts).toTypedArray()
                            updateAdapter()
                        }
                    }
                }
                .create()
            dialog.show()
        }
    }

    private fun updateAdapter() {
        beastAdapter?.updateBeasts(beasts)
        beastAdapter?.notifyItemInserted(0)
        requireView().findViewById<RecyclerView>(R.id.animalList).scrollToPosition(0)
        isVisibleTextView()
    }

    private fun isVisibleTextView() {
        val emptyListTextView = requireView().findViewById<TextView>(R.id.emptyListTextView)
        emptyListTextView.isInvisible = beasts.isNotEmpty()
    }

    companion object {
        private const val KEY_BEASTS = "counter"
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelableArray(KEY_BEASTS, beasts)
    }


}