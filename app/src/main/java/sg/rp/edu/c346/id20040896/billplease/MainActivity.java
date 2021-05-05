package sg.rp.edu.c346.id20040896.billplease;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {
    EditText amt;
    EditText discount;

    EditText numOfPax;
    ToggleButton svc;
    ToggleButton gst;
    RadioGroup rgBill;
    TextView Dtotal;
    TextView Dsplit;
    Button bSplit;
    Button bReset;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        numOfPax = findViewById(R.id.editTextNOP);
        Dtotal = findViewById(R.id.textViewDisplay);
        Dsplit = findViewById(R.id.textViewDisplay1);
        amt = findViewById(R.id.editTextNumberDecimal);
        discount = findViewById(R.id.editTextNumberDiscount);
        svc = findViewById(R.id.toggleButtonSVC);
        gst = findViewById(R.id.toggleButtonGST);
        rgBill = findViewById(R.id.bill);
        bSplit = findViewById(R.id.buttonSplit);
        bReset = findViewById(R.id.buttonReset);
        final double[] total = {0};
        double amount = Double.parseDouble(amt.getText().toString());
        String stringResponse = "";
        String stringResponse2 = "";


        bSplit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (amt.getText().toString().trim().length() != 0 && numOfPax.getText().toString().trim().length() != 0) {
                    double amount = Double.parseDouble(amt.getText().toString());
                    if (!svc.isChecked() && !gst.isChecked()) {
                        total[0] = amount;
                    } else if (svc.isChecked() && !gst.isChecked()) {
                        total[0] = amount * 1.1;
                    } else if (!svc.isChecked() && gst.isChecked()) {
                        total[0] = amount * 1.07;
                    } else {
                        total[0] = amount * 1.17;
                    }
                }
                if (discount.getText().toString().trim().length() != 0) {
                    int dc = Integer.parseInt(discount.getText().toString());
                    total[0] = total[0] * ((100 - Double.parseDouble(discount.getText().toString())) / 100);

                }
                Dtotal.setText("Total Bill: $" + String.format("%.2f", total[0]));
                int nop = Integer.parseInt(numOfPax.getText().toString());
                int checkedRadio = rgBill.getCheckedRadioButtonId();
                if (nop > 1) {
                    if (checkedRadio == R.id.radioButtonCash) {
                        Dsplit.setText("Each Pays: $" + String.format("%.2f", total[0] / nop) + " in Cash");
                    } else {
                        Dsplit.setText("Each Pays: $" + String.format("%.2f", total[0] / nop) + " Via Paynow to 91234567");
                    }
                }

                else {
                    if (checkedRadio == R.id.radioButtonCash) {
                        Dsplit.setText("Each Pays: $" + String.format("%.2f", total[0]) + " in Cash");
                    }
                    else {
                        Dsplit.setText("Each Pays: $" + String.format("%.2f", total[0] / nop) + " Via Paynow to 91234567");
                    }

                }


            }


        });
        bReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                amt.setText("");
                numOfPax.setText("");
                svc.setChecked(false);
                gst.setChecked(false);
                discount.setText("");
            }
        });
    }
}
