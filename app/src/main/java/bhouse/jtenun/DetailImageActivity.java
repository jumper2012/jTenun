package bhouse.jtenun;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by JERRY on 6/25/2016.
 */
public class DetailImageActivity extends Activity
{
    // These matrices will be used to move and zoom image
    Matrix matrix = new Matrix();
    Matrix savedMatrix = new Matrix();

    // We can be in one of these 3 states
    static final int NONE = 0;
    static final int DRAG = 1;
    static final int ZOOM = 2;
    int mode = NONE;

    // Remember some things for zooming
    PointF start = new PointF();
    PointF mid = new PointF();
    float oldDist = 1f;
    String savedItemClicked;
    ImageView imageView1;

    private TextView mTitle;


    String TAG = "DEBUG";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        Intent detailgambar = getIntent();
        String dataNama = detailgambar.getStringExtra(DetailActivity.NAMA_GAMBAR);


        setContentView(R.layout.activity_pinch_jenis_tenun);

//        mTitle = (TextView) findViewById(R.id.textView);
//        mTitle.setText(dataNama);

        imageView1 = (ImageView)findViewById(R.id.imageView1);

        String variableValue =  dataNama+"hr";
        variableValue = variableValue.replaceAll("\\s+","");
        String finalvariableValue = variableValue.toLowerCase();



        mTitle = (TextView) findViewById(R.id.textView);
        //mTitle.setText(finalvariableValue);

        imageView1.setImageResource(getResources().getIdentifier(finalvariableValue, "drawable", getPackageName()));

        //Bitmap b = ((BitmapDrawable)imageView1.getBackground()).getBitmap();
        //int w = b.getWidth();
        //int h = b.getHeight();
        //mTitle.setText("height : "+h+"| width : "+w );

        imageView1.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // TODO Auto-generated method stub
                ImageView view = (ImageView) v;
                ImageView view_dummy = null;
                Drawable imgDrawabletest = ((ImageView) imageView1).getDrawable();
                Bitmap bitmaptest = ((BitmapDrawable) imgDrawabletest).getBitmap();
                if (bitmaptest.getWidth() < 200 && bitmaptest.getHeight() < 100)
                dumpEvent(event);

                // Handle touch events here...
                switch (event.getAction() & MotionEvent.ACTION_MASK) {
                    case MotionEvent.ACTION_DOWN:
                        savedMatrix.set(matrix);
                        start.set(event.getX(), event.getY());
                        Log.d(TAG, "mode=DRAG");
                        mode = DRAG;
                        break;
                    case MotionEvent.ACTION_POINTER_DOWN:
                        oldDist = spacing(event);
                        Log.d(TAG, "oldDist=" + oldDist);
                        if (oldDist > 10f) {
                            savedMatrix.set(matrix);
                            midPoint(mid, event);
                            mode = ZOOM;
                            Log.d(TAG, "mode=ZOOM");
                        }
                        break;
                    case MotionEvent.ACTION_UP:
                    case MotionEvent.ACTION_POINTER_UP:
                        if ((event.getX() == start.x) && (event.getY() == start.y))
                        {
                            //KHUSUS UNTUK KLIK (TOUCH SAJA) TANPA DRAG ATAU ZOOM
                            //Toast.makeText(getApplicationContext(), "TOUCH", Toast.LENGTH_SHORT).show();
                            float eventX = event.getX();
                            float eventY = event.getY();
                            float[] eventXY = new float[] {eventX, eventY};

                            Matrix invertMatrix = new Matrix();
                            ((ImageView)imageView1).getImageMatrix().invert(invertMatrix);

                            invertMatrix.mapPoints(eventXY);
                            int px = Integer.valueOf((int)eventXY[0]);
                            int py = Integer.valueOf((int)eventXY[1]);

                            //touchedXY.setText(
                            //  "touched position: "
                            //  + String.valueOf(eventX) + " / "
                            //  + String.valueOf(eventY));
                            //invertedXY.setText(
                            //  "touched position: "
                            //  + String.valueOf(x) + " / "
                            //  + String.valueOf(y));

                            Drawable imgDrawable = ((ImageView)imageView1).getDrawable();
                            Bitmap bitmap = ((BitmapDrawable)imgDrawable).getBitmap();

                            //imgSize.setText(
                            //  "drawable size: "
                            //  + String.valueOf(bitmap.getWidth()) + " / "
                            //  + String.valueOf(bitmap.getHeight()));

                            //Limit x, y range within bitmap
                            if (px < 1000) {
                                px = 1000;
                            }else if(px > bitmap.getWidth()-1){
                                px = bitmap.getWidth()-1;
                            }

                            if (py < 1000) {
                                py = 1000;
                            }else if(py > bitmap.getHeight()-1){
                                py = bitmap.getHeight()-1;
                            }

                            //double pxx = (px / bitmap.getWidth()) * 100;
                            //double pyy = (py / bitmap.getHeight()) * 100;
                            double pxx = (px * 100);
                            double pww = bitmap.getWidth();
                            pxx = pxx / pww;
                            double pyy = (py * 100);
                            double phh = bitmap.getWidth();
                            pyy = pyy / phh;

                            Toast.makeText(getApplicationContext(), "TOUCH- " + pxx + " : " + pyy, Toast.LENGTH_SHORT).show();

                        }
                        mode = NONE;
                        Log.d(TAG, "mode=NONE");
                        break;
                    case MotionEvent.ACTION_MOVE:
                        if (mode == DRAG) {
                            // ...
                            matrix.set(savedMatrix);
                            matrix.postTranslate(event.getX() - start.x, event.getY()
                                    - start.y);
                        } else if (mode == ZOOM) {
                            float newDist = spacing(event);
                            Log.d(TAG, "newDist=" + newDist);
                            if (newDist > 10f) {
                                matrix.set(savedMatrix);
                                float scale = newDist / oldDist;
                                matrix.postScale(scale, scale, mid.x, mid.y);
                            }
                        }
                        break;
                }
                view_dummy.setImageMatrix(matrix);
                if (view_dummy.getWidth() > 150) ;
                {
                    view.setImageMatrix(matrix);
                    return true;
                }
            }
        });
    }

    private void dumpEvent(MotionEvent event) {
        String names[] = { "DOWN", "UP", "MOVE", "CANCEL", "OUTSIDE",
                "POINTER_DOWN", "POINTER_UP", "7?", "8?", "9?" };
        StringBuilder sb = new StringBuilder();
        int action = event.getAction();
        int actionCode = action & MotionEvent.ACTION_MASK;
        sb.append("event ACTION_").append(names[actionCode]);
        if (actionCode == MotionEvent.ACTION_POINTER_DOWN
                || actionCode == MotionEvent.ACTION_POINTER_UP) {
            sb.append("(pid ").append(
                    action >> MotionEvent.ACTION_POINTER_ID_SHIFT);
            sb.append(")");
        }
        sb.append("[");
        for (int i = 0; i < event.getPointerCount(); i++) {
            sb.append("#").append(i);
            sb.append("(pid ").append(event.getPointerId(i));
            sb.append(")=").append((int) event.getX(i));
            sb.append(",").append((int) event.getY(i));
            if (i + 1 < event.getPointerCount())
                sb.append(";");
        }
        sb.append("]");
        Log.d(TAG, sb.toString());
    }

    /** Determine the space between the first two fingers */
    private float spacing(MotionEvent event) {
        float x = event.getX(0) - event.getX(1);
        float y = event.getY(0) - event.getY(1);
        return (float)Math.sqrt(x * x + y * y);
//        return FloatMath.sqrt(x * x + y * y);
    }

    /** Calculate the mid point of the first two fingers */
    private void midPoint(PointF point, MotionEvent event) {
        float x = event.getX(0) + event.getX(1);
        float y = event.getY(0) + event.getY(1);
        point.set(x / 2, y / 2);
    }
}
