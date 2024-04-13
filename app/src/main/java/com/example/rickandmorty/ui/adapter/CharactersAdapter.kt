package com.example.rickandmorty.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmorty.R
import com.example.rickandmorty.domain.model.Character
import com.example.rickandmorty.ui.listeners.OnCharacterListener
import com.squareup.picasso.Picasso

class CharactersAdapter(private val context: Context, private var characters: List<Character>, private val listener:OnCharacterListener) :
    RecyclerView.Adapter<CharactersAdapter.CharacterViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.item_character, parent, false)
        return CharacterViewHolder(view)
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        val character = characters[position]
        holder.bind(character, listener)
    }

    override fun getItemCount(): Int {
        return characters.size
    }

    fun submitList(newCharacters: List<Character>) {
        val startPosition = characters.size // Obtener la posición inicial de los nuevos elementos
        if (characters.isEmpty()){
            characters = newCharacters
            notifyDataSetChanged()
        }else{
            characters += newCharacters // Añadir los nuevos personajes a la lista existente
            notifyItemRangeInserted(startPosition, newCharacters.size)
        }
    }


    inner class CharacterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val imageViewCharacter: ImageView = itemView.findViewById(R.id.imageViewImage)
        private val textViewName: TextView = itemView.findViewById(R.id.textViewName)
        private val textStatus: TextView = itemView.findViewById(R.id.textViewStatus)

        fun bind(character: Character, listener: OnCharacterListener) {
            textViewName.text = character.name
            Picasso.get().load(character.image).into(imageViewCharacter)
            when(character.status){
                "Alive" ->{
                    textStatus.text = character.status
                    textStatus.setTextColor(ContextCompat.getColor(context, R.color.green))
                    val drawable = ContextCompat.getDrawable(context, R.drawable.estado_vivo)
                    textStatus.setCompoundDrawablesWithIntrinsicBounds(drawable, null, null, null)
                }
                "Dead" ->{
                    textStatus.text = character.status
                    textStatus.setTextColor(ContextCompat.getColor(context , R.color.red))
                    val drawable = ContextCompat.getDrawable(context, R.drawable.estado_muerto)
                    textStatus.setCompoundDrawablesWithIntrinsicBounds(drawable,null,null,null)
                }
                "unknown" ->{
                    textStatus.text = character.status
                    textStatus.setTextColor(ContextCompat.getColor(context , R.color.desconocido))
                    val drawable = ContextCompat.getDrawable(context, R.drawable.estado_desconocido)
                    textStatus.setCompoundDrawablesWithIntrinsicBounds(drawable,null,null,null)
                }

            }
            itemView.setOnClickListener { listener.onCharacterClickListener(character) }
        }
    }

}