package bhouse.jtenun;

import android.content.Context;

/**
 * Created by JERRY on 7/4/2016.
 */
public class MotifJenisTenun {
    public int id_potongan;
    public int id_jenis_tenun;
    public String nama_potongan_jenis_ulos;

    public MotifJenisTenun(){
        super();
    }

    public MotifJenisTenun(String id_potongan, String id_jenis_tenun, String nama_potongan_jenis_ulos) {
        super();
        this.id_potongan = Integer.parseInt(id_potongan);
        this.id_jenis_tenun = Integer.parseInt(id_jenis_tenun);
        this.nama_potongan_jenis_ulos = nama_potongan_jenis_ulos;
    }
    public int getImageResourceId(Context context) {
        return context.getResources().getIdentifier(this.nama_potongan_jenis_ulos, "drawable", context.getPackageName());
    }
}
