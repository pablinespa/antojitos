package ucb.bo.edu.antojitosapp;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by ASUS on 19/11/2017.
 */

public class MenuListAdapter extends RecyclerView.Adapter<MenuListAdapter.MenuListViewHolder>  {

    private ArrayList<MenuRestaurante>lista;

    public MenuListAdapter(ArrayList<MenuRestaurante> lista) {
        this.lista = lista;
    }

    @Override
    public MenuListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v;
        v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_menu, parent, false);
        return new MenuListAdapter.MenuListViewHolder(v);

    }

    @Override
    public void onBindViewHolder(MenuListViewHolder holder, int position) {

        final MenuRestaurante usuario = lista.get(position);
        holder.txtNombre.setText(usuario.getNombre());
        holder.txtCorreo.setText(usuario.getPrecio());

    }

    @Override
    public int getItemCount() {
        return this.lista.size();
    }

    public static class MenuListViewHolder extends RecyclerView.ViewHolder {
        private TextView txtNombre;
        private TextView txtCorreo;
        public MenuListViewHolder(View itemView) {
            super(itemView);
            txtNombre = (TextView) itemView.findViewById(R.id.txt_nombre);
            txtCorreo =( TextView) itemView.findViewById(R.id.txt_correo);
        }
    }
}


