package cn.edu.swufe.newwork;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

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
        targetView=(TextView)findViewById(R.id.target);
        presentView=(TextView)findViewById(R.id.present);


        SharedPreferences sharedPreferences=getSharedPreferences("mytarget",Activity.MODE_PRIVATE);
        target=sharedPreferences.getFloat("target",0.0f);
        targetView.setText(String.valueOf(target+" "+"KG"));

        SharedPreferences sharedPreferences2=getSharedPreferences("mypresent",Activity.MODE_PRIVATE);
        present=sharedPreferences2.getFloat("present",0.0f);
        presentView.setText(String.valueOf(present+" "+"KG"));

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

            SharedPreferences sharedPreferences=getSharedPreferences("mytarget",Activity.MODE_PRIVATE);
            SharedPreferences.Editor editor=sharedPreferences.edit();
            editor.putFloat("target",target);
            editor.commit();

        }
        else if(requestCode==3 && resultCode==4){

            Bundle bundle=data.getExtras();
            present=bundle.getFloat("newPresent_key",0.0f);
            presentView.setText(String.valueOf(present+" "+"KG"));

            SharedPreferences sharedPreferences=getSharedPreferences("mypresent",Activity.MODE_PRIVATE);
            SharedPreferences.Editor editor=sharedPreferences.edit();
            editor.putFloat("present",present);
            editor.commit();
        }
        super.onActivityResult(requestCode,resultCode,data);
    }


    public void savePresent(View btn3){
        presentView=(TextView)findViewById(R.id.present);
        SimpleDateFormat form=new SimpleDateFormat("yyyy年MM月dd日 HH:mm" );
         Date curDate=new Date(System.currentTimeMillis());
         view_time = form.format(curDate);

        if(presentView.getText().toString().equals("请记录当前体重")){
            new  AlertDialog.Builder(this)
                    .setTitle("提示" )
                    .setMessage("您还未输入体重值" )
                    .setPositiveButton("确定" ,  null )
                    .show();
        }else {
            presentStr=String.valueOf(presentView.getText());
            recordStr = presentStr + "    " + view_time;
            Intent intent2 = new Intent(this, Record.class);
            intent2.putExtra("record_key", recordStr);
            startActivity(intent2);
        }
    }

    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.record_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==R.id.menuItem){
            Intent intent3 = new Intent(this,Record.class);
            startActivity(intent3);
        }
        return super.onOptionsItemSelected(item);
    }
}
