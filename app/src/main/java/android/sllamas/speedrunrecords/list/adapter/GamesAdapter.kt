package android.sllamas.speedrunrecords.list.adapter

import android.sllamas.speedrunrecords.R
import android.sllamas.speedrunrecords.common.extensions.inflate
import android.sllamas.speedrunrecords.common.extensions.load
import android.sllamas.speedrunrecords.list.model.Game
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_game.view.*

class GamesAdapter : RecyclerView.Adapter<GamesAdapter.GameViewHolder>() {

    var items = mutableListOf<Game>()
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

        fun bind(item: Game) = with(itemView) {
            logoImageView.load(item.logoUrl)
            gameTextView.text = item.name
        }
    }
}