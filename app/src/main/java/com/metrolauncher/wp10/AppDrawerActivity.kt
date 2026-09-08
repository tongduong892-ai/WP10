package com.metrolauncher.wp10

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class AppDrawerActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_app_drawer)

        val recyclerApps: RecyclerView = findViewById(R.id.recyclerApps)
        val editSearch: EditText = findViewById(R.id.editSearch)

        val allApps = AppRepository.getLaunchableApps(this)

        val adapter = AppListAdapter(allApps) { app ->
            AppRepository.launchApp(this, app.packageName)
            finish()
        }

        recyclerApps.layoutManager = LinearLayoutManager(this)
        recyclerApps.adapter = adapter

        editSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                adapter.filter(s?.toString().orEmpty(), allApps)
            }
            override fun afterTextChanged(s: Editable?) {}
        })
    }
}
