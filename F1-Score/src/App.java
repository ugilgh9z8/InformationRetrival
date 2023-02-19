import java.util.ArrayList;
import java.util.*;

//// 1. Get the R/NR List
//// 2. Calculate the Precision
//// 3. Calculate the Recall
//// 4. Calculate the F-Score

public class App {
    public static void main(String[] args) throws Exception {

        System.out.println(generateQuery("NOT ( ( feel AND ( NOT life ) ) OR ( feel AND ( NOT tomorrow ) ) ) "));
        
        List<String> doc_list = new ArrayList<String>();
        doc_list.add("Be who you are and say what you feel, because those who mind don't matter, and those who matter don't mind.");
        doc_list.add("I've learned that people will forget what you said, people will forget what you did, but people will never forget how you made them feel.");
        doc_list.add("In three words I can sum up everything I've learned about life: it goes on");
        doc_list.add("There are only two ways to live your life. One is as though nothing is a miracle. The other is as though everything is a miracle");
        doc_list.add("Live as if you were to die tomorrow. Learn as if you were to live forever");

        // NOT ceasar AND amused  == if( (!doc.contains("ceasar") & doc.contains("amused"))){}
        // NOT (brutus AND noble) == if( !(doc.contains("brutus") & doc.contains("noble"))){}

        int doc_count = 0;
        for(String doc: doc_list){
            doc_count++;
            if( ! ((doc.contains("feel") && (! doc.contains("life") ))|| (doc.contains("feel") && (! doc.contains("tomorrow") ))) ){
                System.out.println("d"+doc_count + " " + doc);
            }
        }


        
        List<Integer> r_nr_list = Collections.unmodifiableList(
              Arrays.asList(1,1,0,1,1,1,1,0,1,0)); 

        F1_Score f1 = new F1_Score(r_nr_list);
        f1.alpha=0.3;
        f1.CalculateRecallAndPrecision();
        f1.CalculateMAP(); 
        float map = f1.map;
        System.out.println("Map = "+map);
        System.out.println("F1 = "+f1.f1);
    }

    public static String generateQuery(String query)
    {
        String[] query_list = query.split(" ");
        String new_query = "";
        for(String word: query_list){
            if(word.contains("NOT")){
                new_query += "! ";
            }
            else if(word.contains("OR")){
                new_query += "|| ";
            }

            else if(word.contains("AND")){
                new_query += "&& ";
            }
            else if(word.contains("(")){
                new_query += "(";
            }
            else if(word.contains(")")){
                new_query += ")";
            }
            else{
                new_query += "doc.contains(\"" + word + "\") ";
            }
        }
        return new_query;
    }
}
