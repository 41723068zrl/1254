package cn.edu.swufe.newwork;

public class RecordItem {
    private int id;
    private String Record;

    public RecordItem(){
        this.Record=" ";
    }

    public RecordItem(String Record){
        this.Record=Record;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRecord() {
        return Record;
    }

    public void setRecord(String Record) {
        this.Record = Record;
    }


}