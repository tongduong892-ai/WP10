package com.metrolauncher.wp10

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class TileAdapter(
    private val apps: List<AppInfo>,
    private val onTileClick: (AppInfo) -> Unit
) : RecyclerView.Adapter<TileAdapter.TileViewHolder>() {

    private val accentColors = intArrayOf(
        R.color.tile_cobalt, R.color.tile_cyan, R.color.tile_teal, R.color.tile_emerald,
        R.color.tile_lime, R.color.tile_mango, R.color.tile_amber, R.color.tile_orange,
        R.color.tile_brick, R.color.tile_rose, R.color.tile_magenta, R.color.tile_purple,
        R.color.tile_violet, R.color.tile_pink, R.color.tile_steel, R.color.tile_mauve
    )

    class TileViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val root: FrameLayout = view.findViewById(R.id.tileRoot)
        val icon: ImageView = view.findViewById(R.id.imageIcon)
        val label: TextView = view.findViewById(R.id.textLabel)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TileViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_tile, parent, false)
        return TileViewHolder(view)
    }

    override fun onBindViewHolder(holder: TileViewHolder, position: Int) {
        val app = apps[position]
        holder.icon.setImageDrawable(app.icon)
        holder.label.text = app.label

        val colorRes = accentColors[position % accentColors.size]
        holder.root.setBackgroundColor(holder.itemView.context.getColor(colorRes))

        holder.root.setOnClickListener { onTileClick(app) }
    }

    override fun getItemCount(): Int = apps.size
}
