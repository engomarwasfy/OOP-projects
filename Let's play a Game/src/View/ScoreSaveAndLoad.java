package View;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import Controller.ID;
import Controller.ObjectsHandler;
import Model.BlueClown;
import Model.GreenClown;

public class ScoreSaveAndLoad {
	public void save(String dir) throws IOException{
		
		File fout = new File(dir + ".txt");
		FileOutputStream fo = new FileOutputStream(fout, false);
		OutputStreamWriter x = new OutputStreamWriter(fo);
		BufferedWriter bw = new BufferedWriter(x);
		bw.append(Integer.valueOf(ScoreData.getbScore()).toString());
		bw.newLine();
		bw.append(Integer.valueOf(ScoreData.getgScore()).toString());
		bw.newLine();
		bw.close();
		
	}
	public void load(String dir) throws IOException{
		FileInputStream fis = new FileInputStream(dir);

		// Construct BufferedReader from InputStreamReader
		InputStreamReader x = new InputStreamReader(fis);
		BufferedReader br = new BufferedReader(x);

		String line = null;
		Integer blue = new Integer(br.readLine());
		Integer green = new Integer(br.readLine());
		br.close();
	
		
	}

}
