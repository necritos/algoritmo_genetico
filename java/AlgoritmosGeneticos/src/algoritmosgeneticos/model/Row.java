/*
 * 
 */
package algoritmosgeneticos.model;

import java.util.List;

/**
 * @version 0.1
 * @author Manuel
 */
public class Row {
    private int id;
    private List<Integer> code;
    private int val;
    private int function;
    private int pair;

    public Row() {
    }

    public Row(int id, List<Integer> code) {
        this.id = id;
        this.code = code;
        this.val=0;
        this.function=0;
        
    }

    private int function() {
        return val*val;
    }
    public void addElement(int e){    
        code.add(e);
        updateVal();   
    }
    public void setElement(int id,int e){    
        code.set(id, e);
        updateVal();   
    }
    
    public List<Integer> getCode() {
        return code;
    }

    public void setCode(List<Integer> code) {
        this.code = code;
        updateVal();
    }
    private void updateVal(){
        int sum=1;
        val=0;
        for (Integer integer : code) {
            val=val+sum*integer;
            sum=2*sum;
        } 
    }
    public int getFunction() {
        
        return function();
    }

    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPair() {
        return pair;
    }

    public void setPair(int pair) {
        this.pair = pair;
    }

    public int getVal() {
        return val;
    }

    public void setVal(int val) {
        this.val = val;
    }

    @Override
    public String toString() {
        return "Row " +  + id + "{ code=" + code + ", val=" + val + ", function=" + getFunction() + ", pair=" + pair + '}';
    }

    
    
    
}
