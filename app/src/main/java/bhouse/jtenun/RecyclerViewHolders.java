package bhouse.jtenun;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Sursev on 7/19/2016.
 */
public class RecyclerViewHolders extends RecyclerView.ViewHolder implements View.OnClickListener{

    public TextView countryName;
    public ImageView countryPhoto;
    private final Context context;

    public RecyclerViewHolders(View itemView) {
        super(itemView);
        context = itemView.getContext();
        itemView.setOnClickListener(this);
        countryName = (TextView)itemView.findViewById(R.id.country_name);
        countryPhoto = (ImageView)itemView.findViewById(R.id.country_photo);
    }
    @Override
    public void onClick(View view) {
        final Intent intent;
        Toast.makeText(view.getContext(), "Motif yang dipilih : " + getPosition(), Toast.LENGTH_SHORT).show();
        intent = new Intent(context, GenerateMotifActivity.class);
        context.startActivity(intent);
    }
}