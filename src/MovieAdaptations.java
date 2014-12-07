import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;


public class MovieAdaptations {

  public static void main(String[] args) throws Exception {
    /*
     * Validate that two arguments were passed from the command line.
     */
    if (args.length != 5) {
      System.out.printf("Usage: TweetWord <input dir> <output dir>\n");
      System.exit(-1);
    }

    /*
     * Instantiate a Job object for your job's configuration. 
     */
    Job job = new Job();
  Job job2 = new Job();
   Job job3 = new Job();
   Job job4 = new Job();
    
    //boolean success[]= new boolean[4];
    
    job.setJarByClass(MovieAdaptations.class);
    job.setJobName("Genre that works");
    FileInputFormat.addInputPath(job, new Path(args[0]));
    FileOutputFormat.setOutputPath(job, new Path(args[1]));
    job.setMapperClass(MovieAdaptationsMapper.class);
    job.setReducerClass(MovieAdaptationsReducer.class);
    job.setOutputKeyClass(Text.class);
    job.setOutputValueClass(Text.class);
    job.waitForCompletion(true);
    
    job2.setJarByClass(MovieAdaptations.class);
    job2.setJobName("genres with most anomalies");
    FileInputFormat.addInputPath(job2, new Path(args[0]));
    FileOutputFormat.setOutputPath(job2, new Path(args[2]));
    job2.setMapperClass(MovieAdaptationsMapperanomaly.class);
    job2.setReducerClass(MovieAdaptationsAnomalyreducer1.class);
    job2.setOutputKeyClass(Text.class);
    job2.setOutputValueClass(Text.class);
    job2.waitForCompletion(true);
    
    job3.setJarByClass(MovieAdaptations.class);
    job3.setJobName("books vs movies");
    FileInputFormat.addInputPath(job3, new Path(args[0]));
    FileOutputFormat.setOutputPath(job3, new Path(args[3]));
    job3.setMapperClass(MovieAdaptationsMapperAnomaly2.class);
    job3.setReducerClass(MovieAdaptationsAnomalyReducer3.class);
    job3.setOutputKeyClass(Text.class);
    job3.setOutputValueClass(Text.class);
    job3.waitForCompletion(true);
    
    job4.setJarByClass(MovieAdaptations.class);
    job4.setJobName("....");
    FileInputFormat.addInputPath(job4, new Path(args[0]));
    FileOutputFormat.setOutputPath(job4, new Path(args[4]));
    job4.setMapperClass(MovieAdaptationsMapperanomaly.class);
    job4.setReducerClass(MovieAdaptationsAnomalyReducer2.class);
    job4.setOutputKeyClass(Text.class);
    job4.setOutputValueClass(Text.class);
    job4.waitForCompletion(true);

    
   // boolean success = job4.waitForCompletion(true);
    //System.exit(success ? 0 : 1);
  }
 
}

