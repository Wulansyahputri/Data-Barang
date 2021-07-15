class ListContent(val ldata : List<DataItem?>?, val context: Context) :
    RecyclerView.Adapter<ListContent.Viewfolder>() {
    class Viewfolder(view: View) : RecyclerView.ViewHolder(view){
        val namaBarang = view.findViewById<TextView>(R.id.tv_nama_barang)
        val jumlahBarang = view.findViewById<TextView>(R.id.tv_jumlah_barang)
        val editBarang = view.findViewById<TextView>(R.id.tv_edit)
        val deleteBarang = view.findViewById<TextView>(R.id.tv_delete)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Viewfolder {
        val view= LayoutInflater.from(context).inflate(R.layout.item_barang, parent, false)
        return  Viewfolder(view)

    }

    override fun getItemCount(): Int {
        return ldata!!.size
    }

    override fun onBindViewHolder(holder: Viewfolder, position: Int) {
        val model = ldata?.get(position)
        holder.namaBarang.text = model?.namabarang
        holder.jumlahBarang.text = model?.jumlahbarang
        holder.editBarang.setOnClickListener {
            val i = Intent(context, UpdateDataActivity::class.java)
            i.putExtra("IDBARANG", model?.id)
            i.putExtra("NAMABARANG", model?.namabarang)
            i.putExtra("JMLBARANG", model?.jumlahbarang)
            context.startActivity(i)
        }

        holder.deleteBarang.setOnClickListener {
            AlertDialog.Builder(context)
                .setTitle("Delete" + model?.namabarang)
                .setMessage("Aapakah Anda Ingin Menghapus Data Ini?")
                .setPositiveButton("Ya", DialogInterface.OnClickListener { dialog, which ->
                    koneksi.service.deleteBarang(model?.id).enqueue(object : Callback<ResponseActionBarang>{
                        override fun onFailure(
                            call: retrofit2.Call<ResponseActionBarang>,
                            t: Throwable
                        ) {
                            Log.d("pesan1", t.localizedMessage)
                        }

                        override fun onResponse(
                            call: retrofit2.Call<ResponseActionBarang>,
                            response: Response<ResponseActionBarang>
                        ) {
                            if (response.isSuccessful) {
                                Toast.makeText(
                                    context,
                                    "Data Berhasil Dihapus",
                                    Toast.LENGTH_LONG
                                ).show()
                                notifyDataSetChanged()
                                notifyItemRemoved(position)
                                notifyItemChanged(position)
                                notifyItemRangeChanged(position, ldata!!.size)
                                Log.d("bpesan", response.body().toString())
                            }
                        }
                    })
                })
                .setNegativeButton("No", DialogInterface.OnClickListener{ dialog, which ->
                    dialog.dismiss()
                })
                .show()


        }
    }

}