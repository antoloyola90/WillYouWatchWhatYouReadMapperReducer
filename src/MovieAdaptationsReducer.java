import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class MovieAdaptationsReducer extends Reducer<Text, Text, Text, Text> {

  @Override
  public void reduce(Text key, Iterable<Text> values, Context context)
      throws IOException, InterruptedException {
	  Double temp;
	  Double rank = 0.0;
	  for (Text value : values) {
		 temp = Double.parseDouble(value.toString());
		  rank = rank + temp ;
	  }
context.write(key, new Text(rank+""));
  }
}