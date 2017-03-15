package training_college.util;

import org.springframework.stereotype.Component;

/**
 * Created by ss14 on 2017/3/15.
 */
@Component
public class DisCntHelper {

    private double [] disCntArray= {1,1,0.9,0.8,0.7,0.6 };
    private double [] levelArray = {500,1000,2000,5000};

    public int getLevelByScore(int score){

        int level =1;
        for(int i=0;i<levelArray.length&&score>=levelArray[i];i++){
            level++;
        }
        return level;
    }

    public double getDisCntByLevel(int level ){

        if(level<1 ){
            return disCntArray[0];
        }else if(level>disCntArray.length-1){
            return disCntArray[disCntArray.length-1];
        }

        return  disCntArray[level];
    }

}
