package bhouse.jtenun;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by JERRY on 7/5/2016.
 */
public class MotifJenisTenunAdapter extends ArrayAdapter<MotifJenisTenun> {
    Context context;
    int layoutResourceId;
    MotifJenisTenun data[] = null;

    public MotifJenisTenunAdapter(Context context, int layoutResourceId, MotifJenisTenun[] data) {
        super(context, layoutResourceId, data);
        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.data = data;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        PotonganJenisUlosHolder holder = null;

        if(row == null)
        {
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);

            holder = new PotonganJenisUlosHolder();
            holder.imgIcon = (ImageView)row.findViewById(R.id.imgIcon);
            holder.txtTitle = (TextView)row.findViewById(R.id.txtTitle);

            row.setTag(holder);
        }
        else
        {
            holder = (PotonganJenisUlosHolder)row.getTag();
        }

        MotifJenisTenun potongan = data[position];
        holder.txtTitle.setText(potongan.nama_potongan_jenis_ulos);
        String nama_gambar = potongan.nama_potongan_jenis_ulos.replaceAll("\\s", "");
//s        String nama_gambar = "R.drawable."+potongan.nama_potongan_jenis_ulos;
        String lowernamagambar = nama_gambar.toLowerCase();

        holder.imgIcon.setImageResource(R.drawable.potonganragiidup0);
//        holder.imgIcon.setImageResource(potongan.getImageResourceId(this));
        return row;
    }

    static class PotonganJenisUlosHolder
    {
        ImageView imgIcon;
        TextView txtTitle;
    }
    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }
}
