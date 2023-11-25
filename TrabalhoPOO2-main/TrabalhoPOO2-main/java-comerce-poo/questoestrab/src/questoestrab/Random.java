package questoestrab;
import java.text.DecimalFormat; 

public class Random {

    public static void main(String[] args) {
    	DecimalFormat df = new DecimalFormat("#,###.00");
    	for (int i = 0; i < 10; i++) {
	      System.out.println(df.format(Math.random()*100));
	    }
    }
}
