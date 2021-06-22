package com.example.kotlinexample118

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.appcompat.widget.SearchView


class MainActivity : AppCompatActivity() {

    private val users = listOf(
        "User1",
        "User2",
        "User3"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initToolbar()
    }

    private fun initToolbar(){
        findViewById<Toolbar>(R.id.toolbar).setNavigationOnClickListener{
            toast("Navigation")
        }

        findViewById<Toolbar>(R.id.toolbar).setOnMenuItemClickListener{ menuItem ->
            when(menuItem.itemId){
                R.id.action1 -> {
                    toast("Action 1")
                    true
                }
                R.id.action2 -> {
                    toast("Action 2")
                    true
                }
                else -> false
            }
        }


        val search = findViewById<Toolbar>(R.id.toolbar).menu.findItem(R.id.search)
        search.setOnActionExpandListener(object : MenuItem.OnActionExpandListener{

            @SuppressLint("SetTextI18n")
            override fun onMenuItemActionExpand(item: MenuItem?): Boolean {
                findViewById<TextView>(R.id.textView).text = "Search expanded "
                return true
            }

            @SuppressLint("SetTextI18n")
            override fun onMenuItemActionCollapse(item: MenuItem?): Boolean {
                findViewById<TextView>(R.id.textView).text = "Search collapsed "
                return true
            }

        })

        (search.actionView as SearchView).setOnQueryTextListener(
            object: SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                users.filter { it.contains(newText ?: "", ignoreCase = true) }
                    .joinToString()
                    .let {
                        findViewById<TextView>(R.id.searchResult).text = it
                    }
                return true
            }
            })
    }

    private fun toast(text: String){
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
    }
}