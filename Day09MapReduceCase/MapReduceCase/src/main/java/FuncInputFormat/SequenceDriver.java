package FuncInputFormat;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.BytesWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.SequenceFileOutputFormat;

import java.io.IOException;
import java.util.Date;

public class SequenceDriver {
	public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
		// 1.��ȡjob��Ϣ
		Configuration conf = new Configuration();
		Job job = Job.getInstance(conf);

		// 2.��ȡjar��
		job.setJarByClass(SequenceDriver.class);

		// 3.��ȡ�Զ����mapper��reducer��
		job.setMapperClass(SequenceFileMapper.class);
		job.setReducerClass(SequenceFileReducer.class);

		//�����Զ����ȡ��ʽ
		job.setInputFormatClass(FuncFileInputFormat.class);
		//����Ĭ�ϵ������ʽ���п��Բ�����
		//job.setOutputFormatClass(SequenceFileOutputFormat.class);

		// 4.����map�������������
		job.setMapOutputKeyClass(Text.class);
		job.setMapOutputValueClass(BytesWritable.class);

		// 5.����reduce������������ͣ����յ��������ͣ�
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(BytesWritable.class);

		// 6.����������ڵ�·���봦���Ľ��·��
		FileInputFormat.setInputPaths(job, new Path("Day09MapReduce�Ż�����\\MapReduceCase\\src\\main\\resources\\IFin"));
		FileOutputFormat.setOutputPath(job, new Path("Day09MapReduce�Ż�����\\MapReduceCase\\src\\main\\resources\\" + (new Date()).getTime() + "_IFout"));

		// 7.�ύ����
		boolean rs = job.waitForCompletion(true);
		System.out.println(rs ? 0 : 1);
	}
}
