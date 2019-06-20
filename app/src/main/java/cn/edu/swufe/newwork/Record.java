package cn.edu.swufe.newwork;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.icu.text.AlphabeticIndex;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.List;

public class Record extends ListActivity implements AdapterView.OnItemLongClickListener {
    ArrayList<String> recordList;
    ArrayAdapter adapter;
    RecordManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        recordList = new ArrayList<String>();

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, recordList);
        setListAdapter(adapter);

        Intent intent = getIntent();
        String record = intent.getStringExtra("recordStr_key");
        Log.i("TAG", "record:" + record);

        RecordItem  item=new RecordItem(record);
        manager=new RecordManager(this);
        manager.add(item);

        for(RecordItem recordItem:manager.ListAll()){
            recordList.add(recordItem.getRecord());
        }
        getListView().setOnItemLongClickListener(this);

    }

    public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {

        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle("提示").setMessage("请确认是否删除当前数据")
                .setPositiveButton("是", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        List<RecordItem> list=new ArrayList<RecordItem>();

                        recordList.remove(position);
                        adapter.notifyDataSetChanged();

                        String item_d=recordList.get(position);


//                       manager.delete(recordList.get(position));
//
//
//                        int listSize=recordList.size();
//                        for(int i=0;i<listSize;i++){
//                            manager.delete(i);
//                        }
//
//                        for(int i=0;i<listSize;i++){
//                            String listStr=recordList.get(i);
//                            RecordItem item2=new RecordItem(listStr);
//                            manager.add(item2);
//                        }

                    }
                })
                .setNegativeButton("否",null);
        builder.create().show();

        return false;
    }
}
