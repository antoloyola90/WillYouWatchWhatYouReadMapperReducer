import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class MovieAdaptationsAnomalyReducer2 extends Reducer<Text, Text, Text, Text> {

  @Override
  public void reduce(Text key, Iterable<Text> values, Context context)
      throws IOException, InterruptedException {
	 int countM =0;
	 int countB =0;
	  for (Text value : values) {
		  String tmp = value.toString();
		  if (tmp.charAt(tmp.length()-1) == 'B'){
			  countB++;
			   }
		  else countM++;
	  }
	  if(countM > countB){
context.write(key, new Text("Movie"));
	}
	  else {
		  context.write(key, new Text("Book"));
	  }
	  }
  }
