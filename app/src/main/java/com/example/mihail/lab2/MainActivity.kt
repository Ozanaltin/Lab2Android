package com.example.mihail.lab2

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.widget.TableRow
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : BaseActivity() {

    companion object {
        private const val TEXT_FROM_TV = "TEXT_FROM_TV"
        private const val SHOPPING_ITEMS = "SHOPPING_ITEMS"
        private const val DATA_RESULT = 123;
    }

    private var shoppingList = arrayListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_to_shopping_list.setOnClickListener {
            startActivityForResult(Intent(this, ShopActivity::class.java), DATA_RESULT)
        }

        if (savedInstanceState != null) {
            val text = savedInstanceState.getString(TEXT_FROM_TV)
            shoppingList = savedInstanceState.getStringArrayList(SHOPPING_ITEMS)
            text_to_edit.setText(text)
            updateShoppingList()
        }
    }

    private fun updateShoppingList() {
        linear_layout_container.removeAllViews()
        Log.d(TAG, shoppingList.toString())
        for (item in shoppingList) {
            val textView = TextView(this)
            val params = TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT)
            params.setMargins(16, 16, 16, 16)
            textView.setPadding(16, 16, 16, 16)
            textView.textSize = 21f
            textView.layoutParams = params
            textView.setTextColor(Color.parseColor("#110033"))
            textView.setBackgroundColor(Color.parseColor("#33bb99"))
            textView.id = item.hashCode()
            textView.tag = "tv${item.hashCode()}"
            textView.text = item
            linear_layout_container.addView(textView)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            DATA_RESULT -> {
                if (resultCode == Activity.RESULT_OK) {
                    val returnValue = data?.getStringExtra(ShopActivity.DATA_STRING)
                    Log.d(TAG, returnValue)
                    returnValue?.let { shoppingList.add(it) }
                    updateShoppingList()
                }
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        val text = text_to_edit.text.toString()
        if (text.isNotEmpty()) {
            outState?.putString(TEXT_FROM_TV, text)
        }
        if (shoppingList.isNotEmpty()) {
            outState?.putStringArrayList(SHOPPING_ITEMS, shoppingList)
        }
    }

}
