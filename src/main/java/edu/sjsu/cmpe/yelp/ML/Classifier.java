package edu.sjsu.cmpe.yelp.ML;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;

public class Classifier {

	public static void main(String[] args) throws Exception {

		BufferedReader negativeFile = new BufferedReader(
				new FileReader(
						"C:\\Users\\Savani Bharat\\Documents\\workspace-sts-3.3.0.RELEASE\\yelp\\src\\main\\java\\edu\\sjsu\\cmpe\\yelp\\ML\\rt-polarity.neg"));
		BufferedReader positiveFile = new BufferedReader(
				new FileReader(
						"C:\\Users\\Savani Bharat\\Documents\\workspace-sts-3.3.0.RELEASE\\yelp\\src\\main\\java\\edu\\sjsu\\cmpe\\yelp\\ML\\rt-polarity.pos"));

		HashMap<String,Integer> positiveWordsLabel =new HashMap<String,Integer>();
		
		HashMap<String, Integer> positiveWords = new HashMap<String, Integer>();
		String str = null;
		try{
		while ((str = positiveFile.readLine()) != null) {

			String newStr[] = str.split(" ");
			for (int i = 0; i < newStr.length; i++) {

				if (positiveWords.containsKey(newStr[i])){
					positiveWords.put(newStr[i],positiveWords.get(newStr[i]) + 1);
					
				}
				else{
					positiveWords.put(newStr[i], 1);
					positiveWordsLabel.put(newStr[i],-1);

				}
			}
		}
		HashMap<String,Integer> negativeWordsLabel =new HashMap<String,Integer>();
		HashMap<String, Integer> negativeWords = new HashMap<String, Integer>();
		int j = 0;
		while ((str = negativeFile.readLine()) != null) {

			String newStr[] = str.split(" ");
			for (int i = 0; i < newStr.length; i++) {

				if (negativeWords.containsKey(newStr[i])){
					negativeWords.put(newStr[i],negativeWords.get(newStr[i]) + 1);
				}
				else{
					negativeWords.put(newStr[i], 1);
					negativeWordsLabel.put(newStr[i],-1);
				}
			}
		}

		String line = "worth seeing worth listening";
		int error=0;
		String[] newStr = line.split(" ");
		float positiveCount = 0;
		float negativeCount = 0;
		float wordPositive=0f;
		float wordNegative=0f;
		int prediction=1;
		for (int k = 0; k < newStr.length; k++) {

			/*System.out.println("Positive for " + newStr[k] + " "
					+ positiveWords.get(newStr[k]));
			*/if (positiveWords.get(newStr[k]) == null)
				positiveCount += 1;
			else
				positiveCount += positiveWords.get(newStr[k])+1;

			/*System.out.println("Negative for " + newStr[k] + " "
					+ negativeWords.get(newStr[k]));
			*/if (negativeWords.get(newStr[k]) == null)
				negativeCount += 1;
			else
				negativeCount += negativeWords.get(newStr[k])+1;
			
			wordPositive+=positiveCount/(positiveCount+negativeCount);
			wordNegative+=negativeCount/(positiveCount+negativeCount);
			
			prediction=1;
			if(wordPositive<wordNegative)
				prediction=-1;
			
			/*if(prediction!=1){
				error++;
				System.out.println("in error  "+newStr[k]+" "+wordPositive+" "+wordNegative);
			}
			else{
				System.out.println(newStr[k]+" "+wordPositive+" "+wordNegative);
			}*/
			System.out.println(newStr[k]+" "+wordPositive+" "+wordNegative);
		}
		
		}
		finally{
			negativeFile.close();positiveFile.close();
		}
	}
}
