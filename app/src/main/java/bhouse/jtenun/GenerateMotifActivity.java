package bhouse.jtenun;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class GenerateMotifActivity extends Activity {
    private int i = 0;
    private ImageButton GenerateButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generate_motif);
        GenerateButton = (ImageButton) findViewById(R.id.btn_generate);


        GenerateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Generate = new Intent(GenerateMotifActivity.this, GenerateMotifMoreActivity.class);
                startActivity(Generate);
            }
        });
    }
}
