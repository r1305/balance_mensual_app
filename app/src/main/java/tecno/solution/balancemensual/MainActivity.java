package tecno.solution.balancemensual;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    TextView comida_pasaje_q1,comida_pasaje_q2,comida_pasaje_total;
    TextView tv_balance_q1,tv_balance_q2,tv_balance_mensual,tv_residuo_q1,tv_residuo_q2,tv_residuo_mensual;
    EditText dias,almuerzo,extras;
    Button calculate;
    final float pasajes =-5;
    double balance_q1=-81.99;
    double balance_q2=-150;
    double balance_q1_init=0;
    double balance_q2_init=0;
    double balance_mensual;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        comida_pasaje_q1 = findViewById(R.id.txt_comida_pasaje_q1);
        comida_pasaje_q2 = findViewById(R.id.txt_comida_pasaje_q2);
        comida_pasaje_total = findViewById(R.id.txt_comida_pasaje_total);
        dias = findViewById(R.id.et_dias);
        almuerzo = findViewById(R.id.et_almuerzo);
        extras = findViewById(R.id.et_extras);
        calculate = findViewById(R.id.btn_calculate);
        tv_balance_q1 = findViewById(R.id.tv_balance_q1);
        tv_balance_q2 = findViewById(R.id.tv_balance_q2);
        tv_balance_mensual = findViewById(R.id.tv_balance_total);
        tv_residuo_q1 = findViewById(R.id.tv_residuo_q1);
        tv_residuo_q2 = findViewById(R.id.tv_residuo_q2);
        tv_residuo_mensual = findViewById(R.id.tv_residuo_total);

        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                float fl_almuerzo;
                if(almuerzo.getText().toString().equals("")){
                    almuerzo.setText("0");
                    fl_almuerzo=0;
                }else{
                    fl_almuerzo=-Float.parseFloat(almuerzo.getText().toString());
                }

                float fl_extras;
                if(extras.getText().toString().equals("")){
                    extras.setText("0");
                    fl_extras=0;
                }else{
                    fl_extras=-Float.parseFloat(extras.getText().toString());
                }

                int int_dias;
                if(dias.getText().toString().equals("")){
                    dias.setText("0");
                    int_dias=0;
                }else{
                    int_dias=Integer.parseInt(dias.getText().toString());
                }

                float q1,q2;
                q1=q2=(pasajes+fl_almuerzo)*int_dias;

                float mensual=q1+q2;
                String egreso="Egreso: ";
                String residuo="Queda: ";
                comida_pasaje_q1.setText(String.valueOf(q1));
                comida_pasaje_q2.setText(String.valueOf(q2));
                comida_pasaje_total.setText(String.valueOf(mensual));
                balance_q1_init = balance_q1 + q1+(fl_extras/2.0);
                balance_q2_init = balance_q2 + q2+(fl_extras/2.0);
                balance_mensual = balance_q1_init + balance_q2_init;
                tv_balance_q1.setText(egreso+roundeNumbers(balance_q1_init));
                tv_balance_q2.setText(egreso+roundeNumbers(balance_q2_init));
                tv_balance_mensual.setText(egreso+roundeNumbers(balance_mensual));

                //Restante del mes
                tv_residuo_q1.setText(residuo+roundeNumbers(1551.37+balance_q1_init));
                tv_residuo_q2.setText(residuo+roundeNumbers(1400+balance_q2_init));
                tv_residuo_mensual.setText(residuo+roundeNumbers(2951.37+balance_mensual));
            }
        });


    }

    String roundeNumbers(double val)
    {
        return String.format("%.2f", val);
    }
}
