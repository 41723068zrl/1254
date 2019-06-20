package cn.edu.swufe.newwork;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.lang.annotation.Target;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    Button btn1,btn2,btn3;
    TextView targetView,presentView;
    float target,present;
    String view_time,presentStr,recordStr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void changeTarget(View btn1){
        Intent intent1 = new Intent(this, cn.edu.swufe.newwork.Target.class);
        startActivityForResult(intent1,1);
    }

    public void changePresent(View btn2){
        Intent intent2 = new Intent(this, Present.class);
        startActivityForResult(intent2,3);

    }

    public void onActivityResult(int requestCode,int resultCode,Intent data){
        targetView=(TextView)findViewById(R.id.target);
        presentView=(TextView)findViewById(R.id.present);


        if(requestCode==1 && resultCode==2){

            Bundle bundle1=data.getExtras();
            target=bundle1.getFloat("newTarget_key",0.0f);
            targetView.setText(String.valueOf(target+" "+"KG"));

        }
        else if(requestCode==3 && resultCode==4){

            Bundle bundle=data.getExtras();
            present=bundle.getFloat("newPresent_key",0.0f);
            presentView.setText(String.valueOf(present+" "+"KG"));

        }

        super.onActivityResult(requestCode,resultCode,data);

    }

    public void savePresent(View btn3){
        SimpleDateFormat form=new SimpleDateFormat("yyyy年MM月dd日 HH:mm" );
        Date curDate=new Date(System.currentTimeMillis());
        view_time=form.format(curDate);

        presentStr=presentView.getText().toString();
        recordStr=presentStr+"  "+view_time;

        Intent intent2 = new Intent(this, Record.class);
        intent2.putExtra("recordStr_key",recordStr);
        startActivity(intent2);


    }
}
