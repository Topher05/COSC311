public class DataBaseRecord {
    private String Id, first, last;


    public DataBaseRecord(String a,String b, String c){
        Id=new String(a);
        first=new String(b);
        last=new String(c);

    }

    public String getFirst() {
        return first;
    }

    public String getLast() {
        return last;
    }

    public String toString(){
        return Id+" "+first+" "+last;
    }
}