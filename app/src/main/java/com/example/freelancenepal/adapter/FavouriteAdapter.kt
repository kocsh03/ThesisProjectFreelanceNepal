package com.example.freelancenepal.adapter

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.freelancenepal.R
import com.example.freelancenepal.entity.Favourite
import com.example.freelancenepal.repository.FavouriteRepo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FavouriteAdapter(private val listPet: ArrayList<Favourite>, val context: Context)
    : RecyclerView.Adapter<FavouriteAdapter.FavouriteViewHolder>() {

    class FavouriteViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val name: TextView = view.findViewById(R.id.PetName)
        val age: TextView = view.findViewById(R.id.PetAge)
        val price:TextView = view.findViewById(R.id.PetPrice)
        val piece: TextView = view.findViewById(R.id.PetPiece)
        val desc: TextView = view.findViewById(R.id.PetDesc)
        val image: ImageView = view.findViewById(R.id.petimage)
        val petbuy: Button = view.findViewById(R.id.PetBuy)
        val petdelete: Button = view.findViewById(R.id.PetRemove)

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavouriteViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.favourite_layout, parent, false)
        return FavouriteViewHolder(view)

    }
    override fun onBindViewHolder(holder: FavouriteViewHolder, position: Int) {
        val favourite = listPet[position]
        holder.name.text = favourite.petname
        holder.age.text = favourite.petage
        holder.price.text = favourite.petprice.toString()
        holder.piece.text = favourite.petpiece
        holder.desc.text = favourite.petdesc



        Glide.with(context)
            .load(favourite.photo)
            .fitCenter()
            .into(holder.image)
        holder.petdelete.setOnClickListener {
            val builder = AlertDialog.Builder(context)
            builder.setTitle("Delete student")
            builder.setMessage("Are you sure you want to delete ${favourite.petname} ?")
            builder.setIcon(android.R.drawable.ic_delete)
            builder.setPositiveButton("Yes") { _, _ ->

                CoroutineScope(Dispatchers.IO).launch {
                    try {
                        val favouriteRepo = FavouriteRepo()
                        val response = favouriteRepo.deleteCartItem(favourite._id!!)
                        if (response.success == true) {
                            withContext(Dispatchers.Main) {
                                Toast.makeText(
                                    context,
                                    "${favourite.petname} deleted from Cart!!",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                            withContext(Dispatchers.Main) {
                                listPet.remove(favourite)
                                notifyDataSetChanged()
                            }
                        }
                    } catch (ex: Exception) {
                        withContext(Dispatchers.Main) {
                            Toast.makeText(
                                context,
                                ex.toString(),
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                }
            }
            builder.setNegativeButton("No") { _, _ ->
            }
            val alertDialog: AlertDialog = builder.create()
            alertDialog.setCancelable(false)
            alertDialog.show()
        }

//        holder.petbuy.setOnClickListener {
//            val intent = Intent(context, PaymentActivity::class.java)
//            context.startActivity(intent)
//        }
    }

    override fun getItemCount(): Int {
        return listPet.size
    }
}