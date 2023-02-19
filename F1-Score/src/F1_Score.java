import java.util.ArrayList;
import java.util.List;

public class F1_Score {
    List<Integer> truePositives = new ArrayList<Integer>();
    
    double Recall;
    double Precision;
    List<Integer> FScore = new ArrayList<Integer>();
    double f1;
    double alpha = 0.3;
    float map;

    public F1_Score(List<Integer> truePositives) {
        this.truePositives = truePositives;
    }

    public void CalculateRecallAndPrecision(){
        int SumOfPositives = truePositives.stream().mapToInt(Integer::intValue).sum();
        double mapRes = 0;
        for(int i = 0; i < truePositives.size(); i++){
        
            if(truePositives.get(i) == 1){
                Recall++;
                Precision++;
                int incrementOfIndex = i+1;
                double precisionAtIndex = Precision / incrementOfIndex;
                mapRes+=precisionAtIndex;

            }
            int incrementOfIndex = i+1;
            double recallAtIndex = Recall / SumOfPositives;
            double precisionAtIndex = Precision / incrementOfIndex;


            double mapOfIndex= mapRes / SumOfPositives;

            f1 =  ((alpha + 1) * ((recallAtIndex * precisionAtIndex) / (alpha * precisionAtIndex + recallAtIndex)));

            System.out.printf("Recall = %f "
            + "Precision = %f "
            + "F1 = %f "
            + "MAP = %f"
            +"Für index = "+incrementOfIndex+"."+ '\n' , recallAtIndex, precisionAtIndex,f1,mapOfIndex );

        }
        Precision = Precision / truePositives.size();
        Recall = Recall / truePositives.stream().mapToInt(Integer::intValue).sum();
    }

    public void CalculateF1(){
        for(int i = 0; i < truePositives.size(); i++){
        }
    }

    public void CalculateMAP(){
        float recall = 0; 
        for(int i = 0; i < truePositives.size(); i++){
            if(truePositives.get(i) == 1){
                recall += truePositives.get(i);
                int temp = i +1;
                float res = recall / temp;
                map += res;
            }
        }
        map = map / truePositives.stream().mapToInt(Integer::intValue).sum();
    }

}