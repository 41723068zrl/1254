package cn.edu.swufe.newwork;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.icu.text.AlphabeticIndex;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Record extends ListActivity implements AdapterView.OnItemLongClickListener {
   ArrayList<HashMap<String,String>> listItem;
   SimpleAdapter listItemAdapter;

    RecordManager manager = new RecordManager(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        recordListView();
        this.setListAdapter(listItemAdapter);
        getListView().setOnItemLongClickListener(this);
    }

        public void recordListView () {
            listItem = new ArrayList<HashMap<String, String>>();
            Intent intent = getIntent();
                String record = intent.getStringExtra("record_key");
                if(record!=null){
                RecordItem item = new RecordItem(record);
                manager.add(item);
                }

            for (RecordItem recordItem : manager.ListAll()) {
                HashMap<String, String> map = new HashMap<String, String>();
                int recordId = recordItem.getId();
                String recordIdStr = String.valueOf(recordId);
                map.put("record_id", recordIdStr);
                map.put("record_view", recordItem.getRecord());
                listItem.add(map);
            }

            listItemAdapter = new SimpleAdapter(this, listItem,
                    R.layout.list_item,
                    new String[]{"record_id", "record_view"},
                    new int[]{R.id.record_id, R.id.record_view});

        }


    public boolean onItemLongClick (AdapterView < ? > parent, View view,final int position,
                                    long id){
        List<RecordItem> list = new ArrayList<RecordItem>();
        final HashMap<String,String> recordMap = listItem.get(position);
        final String record_Id_Str= (String) recordMap.get("record_id");
        final int record_id=Integer.parseInt(record_Id_Str);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("提示").setMessage("请确认是否删除当前数据")
                .setPositiveButton("是", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        listItem.remove(position);
                        listItemAdapter.notifyDataSetChanged();
                        manager.delete(record_id);

                    }
                })
                .setNegativeButton("否", null);
        builder.create().show();

        return false;
    }

}
