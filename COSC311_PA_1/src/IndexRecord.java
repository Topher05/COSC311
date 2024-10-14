public class IndexRecord {

    private String key;
    private int where;

    public IndexRecord(String k, int w){
        key =new String(k);
        where = w;

    }
    public int compareTo(IndexRecord otherRecord){
        return(key.compareTo(otherRecord.key));
    }

    public String getKey() {
        return key;
    }
    public int getWhere(){
        return where;
    }

}
