import java.io.IOException;
import java.util.StringTokenizer;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class MovieAdaptationsMapperanomaly extends
		Mapper<LongWritable, Text, Text, Text> {

	@Override
	public void map(LongWritable key, Text value, Context context)
			throws IOException, InterruptedException {
		try {
			String line = value.toString();

			System.out.println(line);
			String ImdbRating = line.substring(line.indexOf("redoneMovieRating"));
			  ImdbRating = ImdbRating.split(",")[0];
			  ImdbRating = ImdbRating.split(":")[1].trim();
			  ImdbRating = ImdbRating.split("[(]")[0];
			  //ImdbRating = ImdbRating.substring(1, ImdbRating.length()-1);
			  System.out.println(ImdbRating);
			//  Thread.sleep(5000);
			Float Imdb = Float.parseFloat(ImdbRating);

			String Genre = line.substring(line.indexOf("Genre"));
			  Genre = Genre.split(",")[0];
			  Genre = Genre.split(":")[1].trim();
			  System.out.println(Genre);
			  Genre = Genre.replace("###"," ");

			String bookRating = line.substring(line.indexOf("redoneBookRating"));
			bookRating = bookRating.split(",")[0];
			bookRating = bookRating.split(":")[1].trim();
			bookRating = bookRating.split("[(]")[0];
			
			//bookRating = bookRating.substring(1, bookRating.length());
			Float book = Float.parseFloat(bookRating);
			System.out.println(bookRating);

			StringTokenizer tokenizer = new StringTokenizer(Genre);
			int count = tokenizer.countTokens();
			System.out.println(Genre);
			System.out.println(count);

			while (tokenizer.hasMoreTokens()) {
				String word = tokenizer.nextToken();

				if (Imdb - book >= 3.0) {
					System.out.println(word + " "+1 + " M");
					context.write(new Text(word), new Text(1 + " M"));
				}

				else if (book - Imdb >= 3.0) {
					System.out.println(word +1 + " B");
					context.write(new Text(word), new Text(1 + " B"));

				}

			}

		} catch (Exception e) {

		}
	}
}
