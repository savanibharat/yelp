package edu.sjsu.cmpe.IR.chapter1;

import java.io.BufferedReader;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class InvertedIndex {

	public static void main(String[] args) throws Exception {

		File[] file = getFiles();
		if(file==null){
			System.out.println("No files in database");
			return;
		}
		HashMap<String, String> index = getKeyWordsFromFiles(file);
		if(index==null){
			System.out.println("Files dont have any content in it");
			return;
		}
		String str="NAME";
		String files=searchWord(index , str);

		if(files==null){
			
			System.out.println(str+" not found");
			return;
		}
		else{
			if(files.contains("|")){
				System.out.println(str+" found. Displaying files. . .");
				String newStr[]=files.split("\\|");
				
				for (int i = 0; i < newStr.length; i++) {
					System.out.print(newStr[i]+" ");
				}
			}
			else
				System.out.println(str+" found in "+files);
		}
	}

	public static File[] getFiles() {

		ArrayList<File> file = new ArrayList<File>();
		File folder = new File("Files");
		File[] listOfFile = folder.listFiles();

		
		
		/*for (int i = 0; i < listOfFile.length; i++) {
			System.out.print(listOfFile[i]+" ");
		}*/
		
		return listOfFile;
	}

	public static HashMap<String, String> getKeyWordsFromFiles(File[] file) throws IOException {

		HashMap<String, String> index = new HashMap<String, String>();
		if(file==null){
			System.out.println("null");
			return null;
		}
		int numFile=0;
		for (int i = 0; i < file.length; i++) {
			numFile++;
		}
		
		BufferedReader br = null;
		try{
		for (int i = 0; i < numFile; i++) {
				br = new BufferedReader(new FileReader(new File(file[i].toString())));
				String str = null;
				while ((str = br.readLine()) != null) {
					String newStr[] = str.split(" ");
					for (int j = 0; j < newStr.length; j++) {

						if (index.containsKey(newStr[j])) {
							String temp = index.get(newStr[j]);
							index.put(newStr[j],temp + "|" + file[i].getCanonicalPath());
						} else {
							index.put(newStr[j], file[i].getCanonicalPath());
							
						}
					}
				}
			}
			/*for (Map.Entry<String, String> entry : index.entrySet()) {

				System.out.print(entry+"  ");
			}*/
		} catch (IOException e) {
			e.printStackTrace();
			
		} finally {
			br.close();
		}
		return index;
	}
	
	public static String searchWord(HashMap<String, String> index, String str){
		
		if(index.containsKey(str))
			return index.get(str);
		return null;
	}
}
/*
 * 
 * is=doc1 
 * My=doc1|doc3 
 * NAME=doc2|doc3 
 * MY=doc2 
 * bharat=doc1 
 * BHARAT=doc3
 * IS=doc2|doc3 
 * Bharat=doc2 
 * SAVANI=doc3 
 * risk=doc4 
 * name=doc1 
 * savani=doc1|doc2
 * assessment=doc4
 */
