package kerjapraktek.jtenun;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageButton;

public class GenerateMotifMoreActivity extends Activity {
    private Menu menu;
    private ImageButton GenerateButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generate_motif_more);
        GenerateButton = (ImageButton) findViewById(R.id.btn_generate_more);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.save_generate, menu);
        this.menu = menu;
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_save) {
            Dialog rankDialog = new Dialog(GenerateMotifMoreActivity.this);
            rankDialog.setTitle("RATING MOTIF INI");
            rankDialog.setContentView(R.layout.rating_dialog);
            rankDialog.show();
        }
        return super.onOptionsItemSelected(item);
    }

}
