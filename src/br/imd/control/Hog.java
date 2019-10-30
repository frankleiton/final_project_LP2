package br.imd.control;

import java.util.List;

import org.opencv.core.Mat;
import org.opencv.core.MatOfFloat;
import org.opencv.core.Size;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.HOGDescriptor;

public class Hog {
	
	public double[] resizeImgame(String image_url) 
	{
		HOGDescriptor hog = new HOGDescriptor();
		Mat img = new Mat();
		MatOfFloat features = new MatOfFloat();
		
		img = Imgcodecs.imread(image_url, Imgcodecs.IMREAD_GRAYSCALE);
		Imgproc.resize(img, img, new Size(64,128), 0.5, 0.5, Imgproc.INTER_LINEAR);
		hog.compute(img,features);
		List<Float> arrayOfFeatures = features.toList();
		
		double[] result = new double[1000];
		
		for (int i = 0; i < result.length; i++) {
			result[i] = arrayOfFeatures.get(i);
		}
		
		return result;
	}
}
