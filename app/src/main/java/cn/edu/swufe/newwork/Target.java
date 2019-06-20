package cn.edu.swufe.newwork;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class Target extends AppCompatActivity {
    EditText editTarget;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.target);

        editTarget=(EditText)findViewById(R.id.edit_target);
    }

    public void saveTarget(View btn){
        float newTarget=Float.parseFloat(editTarget.getText().toString());

        Intent intent=getIntent();
        Bundle bdlTarget=new Bundle();
        bdlTarget.putFloat("newTarget_key",newTarget);
        intent.putExtras(bdlTarget);
        setResult(2,intent);
        finish();
    }
}
