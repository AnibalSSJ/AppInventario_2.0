package com.example.appinv.adapter;

import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appinv.R;
import com.example.appinv.model.Producto;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

import org.w3c.dom.Text;

public class ProductoAdapter extends FirestoreRecyclerAdapter<Producto,ProductoAdapter.viewHolder> {

    /**
     * Create a new RecyclerView adapter that listens to a Firestore Query.  See {@link
     * FirestoreRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public ProductoAdapter(@NonNull FirestoreRecyclerOptions<Producto> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull viewHolder holder, int i, @NonNull Producto Producto) {
        holder.cantidad.setText(Producto.getCantidadProducto());
        holder.Producto.setText(Producto.getNombreProducto());
        holder.ProductoConjunto.setText(Producto.getPrecioProductoConjunto());
        holder.ProductoUnidad.setText(Producto.getPrecioProductoUnidad());
        holder.Tipo.setText(Producto.getSpinnerTipo());
    }


    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.view_producto_single, parent, false);
        return new viewHolder(v);
    }

    public class viewHolder extends RecyclerView.ViewHolder {

     TextView cantidad, Producto, ProductoConjunto, ProductoUnidad, Tipo;

        public viewHolder(@NonNull View itemView) {
            super(itemView);


            cantidad= itemView.findViewById(R.id.tvCant);
            Producto = itemView.findViewById(R.id.tvNom);
            ProductoConjunto = itemView.findViewById(R.id.tvPrecioM);
            ProductoUnidad = itemView.findViewById(R.id.tvPrecioU);
            Tipo = itemView.findViewById(R.id.tvTipo);
        }
    }
}
