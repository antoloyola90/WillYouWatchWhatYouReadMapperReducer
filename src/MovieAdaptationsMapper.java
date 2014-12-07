import java.io.IOException;
import java.util.StringTokenizer;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class MovieAdaptationsMapper extends Mapper<LongWritable, Text, Text, Text> {
	
	

  @Override
  public void map(LongWritable key, Text value, Context context)
      throws IOException, InterruptedException {
	  try{
	  String line = value.toString();
	 
	  System.out.println(line);
	  String ImdbRating = line.substring(line.indexOf("redoneMovieRating"));
	  ImdbRating = ImdbRating.split(",")[0];
	  ImdbRating = ImdbRating.split(":")[1].trim();
	  ImdbRating = ImdbRating.split("[(]")[0];
	  //ImdbRating = ImdbRating.substring(1, ImdbRating.length()-1);
	  System.out.println(ImdbRating);
	//  Thread.sleep(5000);
	  
	  String Genre = line.substring(line.indexOf("Genre"));
	  Genre = Genre.split(",")[0];
	  Genre = Genre.split(":")[1].trim();
	  System.out.println(Genre);
	  Genre = Genre.replace("###"," ");
	  
	  StringTokenizer tokenizer=new StringTokenizer(Genre);
	  int count=tokenizer.countTokens();
	  System.out.println(Genre);
	  System.out.println(count);
	  
	  float rank = 0;
	   
	 if((Float.parseFloat(ImdbRating))>=7)
	 {
		 rank = (float) 1/ (float) count;
		 System.out.println(rank);
	 }
	  
	 while(tokenizer.hasMoreTokens())
	  {
		  String word=tokenizer.nextToken();
		 
		  
			  context.write(new Text(word), new Text(rank +""));
			  System.out.println(word + " "+ rank);
			 
		  	  }
	  
	  
	 
			  
		  
	  }catch(Exception e) {
		  
	  }
  }
}
