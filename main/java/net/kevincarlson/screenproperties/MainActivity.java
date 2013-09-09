package net.kevincarlson.screenproperties;

import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.text.DecimalFormat;

public class MainActivity extends Activity {
    Button mButtonCalc;
    EditText mEditHRes;
    EditText mEditVRes;
    EditText mEditDSize;
    TextView mTVPixelDensity;
    TextView mTVHSize;
    TextView mTVVSize;

    private int horizontalResolution;
    private int verticalResolution;
    private Double diagonalSize;

    private double pixelDensity;
    private double horizontalSize;
    private double verticalSize;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mButtonCalc = (Button)findViewById(R.id.button);
        mEditHRes = (EditText)findViewById(R.id.teHorizontalResolution);
        mEditVRes = (EditText)findViewById(R.id.teVerticalResolution);
        mEditDSize = (EditText)findViewById(R.id.teDiagonalSize);
        mTVPixelDensity = (TextView)findViewById(R.id.tvPixelDensity);
        mTVHSize = (TextView)findViewById(R.id.tvHorizontalSize);
        mTVVSize = (TextView)findViewById(R.id.tvVerticalSize);

        mButtonCalc.setOnClickListener(
            new View.OnClickListener() {
                public void onClick(View view) {
                    calculate();
                }
            }
        );
    }

    private void calculate() {
        try {
            grabInputs();
            calculateValues();
            updateOutput();
        } catch (Exception e) {
            clearOutputs();
        }
    }

    private void grabInputs() {
        horizontalResolution = Integer.valueOf(mEditHRes.getText().toString());
        verticalResolution = Integer.valueOf(mEditVRes.getText().toString());
        diagonalSize = Double.valueOf(mEditDSize.getText().toString());
    }

    private void calculateValues() {
        pixelDensity = Math.sqrt(Math.pow(horizontalResolution, 2) + Math.pow(verticalResolution, 2)) / diagonalSize;
        horizontalSize = horizontalResolution / pixelDensity;
        verticalSize = verticalResolution / pixelDensity;
    }

    private void updateOutput() {
        DecimalFormat fmt = new DecimalFormat("#.##");

        ////////////////////////////////////////////////////////////////////////////////////////////
        //
        //  TODO: Convert these hardcoded values to the appropriate String resources.
        //  NOTE: When I tried to use the existing resources, they displayed as numerical values
        //  instead.
        //
        ////////////////////////////////////////////////////////////////////////////////////////////

        mTVPixelDensity.setText(fmt.format(pixelDensity) + " ppi");
        mTVHSize.setText(fmt.format(horizontalSize) + " inches");
        mTVVSize.setText(fmt.format(verticalSize) + " inches");
    }

    private void clearOutputs() {
        mTVPixelDensity.setText(R.string.unknown);
        mTVHSize.setText(R.string.unknown);
        mTVVSize.setText(R.string.unknown);
    }
}
