public class IndexArray {

    private IndexRecord[] records;
    private int size;
    private int itor;

    public IndexArray(){
        records = new IndexRecord[101];
        size = 0;
        itor = 0;
    }
    public IndexArray(int size){
        records = new IndexRecord[size];
        size = 0;
        itor = 0;
    }

    public void insert(IndexRecord key){

        int j;
        for(j = size - 1; j>=0; j --){
            if((records[j].compareTo(key))<0)break;
            records[j+1]=records[j];
        }

        records[j+1]=key;
        size++;
        //System.out.println("insert method size after insert : " + size);
    }

    public void display(){
        for(int j=0; j<size; j++){
            System.out.println(records[j]);
        }
    }

    public int find(String key){

        int hi,lo,mid=0;
        lo=0;
        hi=size-1;
        if (size!= 0){
            while(lo<=hi) {
                mid = (lo + hi) / 2;
                if (records[mid].getKey().compareTo(key) == 0) break;
                if (records[mid].getKey().compareTo(key) < 0) {
                    lo = mid + 1;
                } else {
                    hi = mid - 1;
                }
            }
        }
        return((records[mid].getKey().compareTo(key)==0) ? mid : -1);

    }

    // I had to use a separate find(linear) inside,
    // so I could delete from the first and last name arrays
    public boolean delete(int key){
        boolean retVal=true;
        int where;
        int i;
        //System.out.println("delete method size: " + size);
        for(i = size - 1 ; i>=0; i--){
            //System.out.println("delete method for loop i: "+ i);
            if(key == records[i].getWhere()) break;
        }
        where = i;

        if(where!=-1){
            for(int j=where+1;j<size;j++){
                records[j-1]=records[j];
            }
            size--;
        }else{
            retVal=false;
        }
        return retVal;

    }
    public int getPosInDB(int posInArray){ //finds the location in the main array
        return records[posInArray].getWhere();
    }

    public void iteratorInitFront(){
        itor = 0;
    }

    public void iteratorInitBack(){
        itor = size-1;
    }

    public boolean hasNext(){
        //System.out.println("hasNext method itor: " + itor);
        //System.out.println("hasNext method size: " + size);
        return (itor!=size-1?true : false); //returns true if itor is at the back, else it returns false
    }

    public boolean hasPrevious(){
        return(itor!=-1? true : false);
    }

    public int getNext(){
        int curry=itor;
        itor++;
        return curry; //curry is the current
    }

    public int getPrevious(){
        int curry=itor;
        if(itor!=-1) itor--;
        return curry;
    }

}
