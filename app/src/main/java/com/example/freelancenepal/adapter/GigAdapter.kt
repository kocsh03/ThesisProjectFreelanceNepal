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
import com.example.freelancenepal.api.ServiceBuilder
import com.example.freelancenepal.entity.Favourite
import com.example.freelancenepal.entity.Gig
import com.example.freelancenepal.repository.FavouriteRepo
import com.example.freelancenepal.repository.GigRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class GigAdapter (
    val listGig : MutableList<Gig>,
    val context: Context
): RecyclerView.Adapter<GigAdapter.ShowViewHolder>(){
    class ShowViewHolder(view: View): RecyclerView.ViewHolder(view){
        val tvPetName : TextView
        val tvPetAge : TextView
        val tvPetPiece : TextView
        val tvPetDesc : TextView
        val tvPetPrice : TextView
        val image : ImageView
        val btnFavourite: Button
        val delete : Button

        init {
            tvPetName= view.findViewById(R.id.tvPetName)
            tvPetAge= view.findViewById(R.id.tvPetAge)
            tvPetPiece= view.findViewById(R.id.tvPetPiece)
            tvPetDesc= view.findViewById(R.id.tvPetDesc)
            tvPetPrice= view.findViewById(R.id.tvPetPrice)
            btnFavourite = view.findViewById(R.id.btnFavourite)
            image = view.findViewById(R.id.stdimage)
            delete = view.findViewById(R.id.PetRemove)

        }

    }
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): GigAdapter.ShowViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.display_layout_gig, parent, false)
        return ShowViewHolder(view)
    }

    override fun onBindViewHolder(holder: GigAdapter.ShowViewHolder, position: Int) {
        val prdlst = listGig[position]
        holder.tvPetName.text = prdlst.petname
        holder.tvPetAge.text = prdlst.petage.toString()
        holder.tvPetPiece.text = prdlst.petpiece.toString()
        holder.tvPetDesc.text = prdlst.petdesc.toString()
        holder.tvPetPrice.text = prdlst.petprice.toString()
        val imagePath = ServiceBuilder.loadImagePath() + prdlst.photo
        if (!prdlst.photo.equals("no-photo.jpg")) {
            Glide.with(context)
                .load(imagePath)
                .fitCenter()
                .into(holder.image)
        }
        holder.btnFavourite.setOnClickListener {
            val name = prdlst.petname
            val age = prdlst.petage
            val piece = prdlst.petpiece
            val price = prdlst.petprice
            val desc = prdlst.petdesc
            val pic = prdlst.photo

            val favourites = Favourite(
                petname = name,
                petage = age,
                petpiece = piece,
                petprice = price,
                petdesc = desc,
                photo = pic
            )
            CoroutineScope(Dispatchers.IO).launch {
                try {
                    val favouriteRepo = FavouriteRepo()
                    val response = favouriteRepo.addItemToFavourite(favourites)
                    if (response.success == true) {

                        withContext(Dispatchers.Main) {
                            Toast.makeText(
                                context,
                                "$name Added to Cart", Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                } catch (ex: Exception) {
                    withContext(Dispatchers.Main) {
                        Toast.makeText(
                            context,
                            ex.toString(), Toast.LENGTH_LONG
                        ).show()
                    }
                }
            }
        }

//        holder.update.setOnClickListener{
//            val intent = Intent(context, UpdateProductActivity::class.java)
//            intent.putExtra("product", prdlst)
//            context.startActivity(intent)
//        }
                holder.delete.setOnClickListener {
                    val builder = AlertDialog.Builder(context)
                    builder.setTitle("Delete pet")
                    builder.setMessage("Are you sure you want to delete ${prdlst.petname} ??")
                    builder.setIcon(android.R.drawable.ic_delete)

                    builder.setPositiveButton("Yes") { _, _ ->
                        CoroutineScope(Dispatchers.IO).launch {
                            try {
                                val petRepository = GigRepository()
                                val response = petRepository.deletePet(prdlst._id!!)
                                if (response.success == true) {
                                    withContext(Dispatchers.Main) {
                                        Toast.makeText(
                                            context,
                                            "Pet Deleted",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                    }
                                    withContext(Dispatchers.Main) {
                                        listGig.remove(prdlst)
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
            }


    override fun getItemCount(): Int {
        return listGig.size
    }
}