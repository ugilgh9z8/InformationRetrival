import java.util.ArrayList;
import java.util.*;

//// 1. Get the R/NR List
//// 2. Calculate the Precision
//// 3. Calculate the Recall
//// 4. Calculate the F-Score

public class App {
    public static void main(String[] args) throws Exception {

        List<Integer> r_nr_list = Collections.unmodifiableList(
              Arrays.asList(1,1,0,1,1,1,1,0,1,0)); 

        F1_Score f1 = new F1_Score(r_nr_list);
        f1.alpha=0.7;
        f1.CalculateRecallAndPrecision();
        f1.CalculateMAP(); 
        float map = f1.map;
        System.out.println("Map = "+map);
        System.out.println("F1 = "+f1.f1);

    }
}
