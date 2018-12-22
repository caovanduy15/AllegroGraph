package utilities;

import java.io.*;


public class ReadFile {
	
	public ReadFile() {
		super();
	}

	public String readFile(String file, int num) {
		String result = new String();
		int line = 0;
		try {
			
			BufferedReader br = new BufferedReader(new FileReader(file));
			while ( line != num) {
				result = br.readLine();
				line++;
			}
			br.close();
			
		} catch(Exception e){
			e.getMessage();
			e.printStackTrace();
		}
		return result;
	}
}
