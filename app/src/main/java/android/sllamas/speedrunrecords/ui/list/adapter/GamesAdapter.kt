package android.sllamas.speedrunrecords.ui.list.adapter

import android.sllamas.speedrunrecords.R
import android.sllamas.speedrunrecords.ui.common.extensions.inflate
import android.sllamas.speedrunrecords.ui.common.extensions.load
import android.sllamas.speedrunrecords.ui.list.model.GameViewEntity
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_game.view.*

class GamesAdapter(val onGameClicked: (GameViewEntity) -> Unit) :
    RecyclerView.Adapter<GamesAdapter.GameViewHolder>() {

    var items = mutableListOf<GameViewEntity>()
        set(value) {
            field.clear()
            field.addAll(value)
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        GameViewHolder(parent.inflate(R.layout.item_game))

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: GameViewHolder, position: Int) =
        holder.bind(items[position])

    inner class GameViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(item: GameViewEntity) = with(itemView) {
            logoImageView.load(item.logoUrl)
            gameTextView.text = item.name
            setOnClickListener { onGameClicked(item) }
        }
    }
}