/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package algoritmosgeneticos.view.mediator;

import algoritmosgeneticos.controller.GeneticoController;
import algoritmosgeneticos.model.Row;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Manuel
 */
public class StepHomeMediator {
    private List<List<Row>> record;
    private List<Float> averages;
    private GeneticoController qc;
    private Integer times=0;

    public StepHomeMediator() {
        record=new ArrayList<List<Row>>();
        averages=new ArrayList<Float>();
        qc=new GeneticoController();
    }
    
    public void init(int times,int number) {
        this.times=times;
        qc.init(number);
    }

    /*
     * Algoritmo Genetico
     */
    public void ejecute(){
        averages.add(qc.average());
        record.add(qc.copyTable());
        while(times>0){
            qc.pairRandom();
            qc.selection();
            qc.pairRandom();
            qc.cross();
            averages.add(qc.average());
            record.add(qc.copyTable());
            times--;
        }
    }
    public List<Row> getTable(int i){
        return record.get(i);
    }
    public float getAverage(int i){
        return averages.get(i);
    }
     public GeneticoController getQc() {
        return qc;
    }

    public void setQc(GeneticoController qc) {
        this.qc = qc;
    }

    public List<List<Row>> getRecord() {
        return record;
    }

    public void setRecord(List<List<Row>> record) {
        this.record = record;
    }
    
}
