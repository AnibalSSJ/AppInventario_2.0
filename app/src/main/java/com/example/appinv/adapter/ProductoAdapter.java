package com.example.appinv.adapter;

import android.app.Activity;
import android.content.Intent;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appinv.EditarActivity;
import com.example.appinv.MisProductos;
import com.example.appinv.R;
import com.example.appinv.model.Producto;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import org.w3c.dom.Text;

public class ProductoAdapter extends FirestoreRecyclerAdapter<Producto,ProductoAdapter.ViewHolder> {
    FirebaseFirestore mFirestore;
    Activity activity;


    /**
     * Create a new RecyclerView adapter that listens to a Firestore Query.  See {@link
     * FirestoreRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public ProductoAdapter(@NonNull FirestoreRecyclerOptions<Producto> options, Activity activity ) {
        super(options);
        this.activity = activity;
        mFirestore = FirebaseFirestore.getInstance();

    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder viewHolder, int i, @NonNull Producto Producto) {
        DocumentSnapshot documentSnapshot = getSnapshots().getSnapshot(viewHolder.getAbsoluteAdapterPosition());
        final String id = documentSnapshot.getId();

        viewHolder.Producto.setText(Producto.getNombreProducto());
        viewHolder.cantidad.setText(Producto.getCantidadProducto());
        viewHolder.ProductoConjunto.setText(Producto.getPrecioProductoConjunto());
        viewHolder.ProductoUnidad.setText(Producto.getPrecioProductoUnidad());
        viewHolder.Tipo.setText(Producto.getSpinnerTipo());

        viewHolder.btn_editar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(activity, EditarActivity.class);
                intent.putExtra("productoId", id);
                activity.startActivity(intent);
            }
        });

        viewHolder.btn_Eliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mFirestore.collection("Producto").document(id).delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(activity, "Eliminado correctamente", Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(activity,"Error al eliminar", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.view_producto_single, parent, false);
        return new ViewHolder(v);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView cantidad, Producto, ProductoConjunto, ProductoUnidad, Tipo;
        Button btn_Eliminar, btn_editar;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cantidad= itemView.findViewById(R.id.tvCant);
            Producto = itemView.findViewById(R.id.tvNom);
            ProductoConjunto = itemView.findViewById(R.id.tvPrecioM);
            ProductoUnidad = itemView.findViewById(R.id.tvPrecioU);
            Tipo = itemView.findViewById(R.id.tvTipo);
            btn_Eliminar = itemView.findViewById(R.id.btn_Eliminar);
            btn_editar = itemView.findViewById(R.id.btn_editar);
        }



    }
}
