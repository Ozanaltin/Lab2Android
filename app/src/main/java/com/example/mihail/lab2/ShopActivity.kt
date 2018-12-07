package com.example.mihail.lab2

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import kotlinx.android.synthetic.main.activity_shop.*

class ShopActivity : BaseActivity() {

    companion object {
        const val DATA_STRING = "DATA_STRING"
    }

    private enum class ShoppingItems {
        Pasta, Tomato, PC, Car, Toys, Elephant, Cat, Dog, Tea, Coffee, iMac, Appartment
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shop)

        ShoppingItems.values().forEach {
            val btn = Button(this)
            btn.text = it.name
            val params = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
            params.setMargins(16, 16, 16, 16)
            btn.setTextColor(Color.parseColor("#110033"))
            btn.layoutParams = params
            btn.setBackgroundColor(Color.parseColor("#33bb99"))

            btn.setOnClickListener { _ ->
                val resultIntent = Intent()
                resultIntent.putExtra(DATA_STRING, it.name)
                setResult(Activity.RESULT_OK, resultIntent)
                finish()
            }
            shop_list_view.addView(btn)
        }
    }

}