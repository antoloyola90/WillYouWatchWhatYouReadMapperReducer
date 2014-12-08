import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class MovieAdaptationsReducer extends Reducer<Text, Text, Text, Text> {

	@Override
	public void reduce(Text key, Iterable<Text> values, Context context)
			throws IOException, InterruptedException {
		float temp;
		float rank = 0;
		long count = 0;
		for (Text value : values) {
			temp = Float.parseFloat(value.toString());
			rank = rank + temp;
			count++;
		}
		rank = rank/count;
		rank = rank * 100;
		context.write(key, new Text(rank + ""+" "+count));
	}
}