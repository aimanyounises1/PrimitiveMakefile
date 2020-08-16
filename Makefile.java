import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.LinkedList;
import java.util.List;

public class Makefile {
	List <String> names;
	public Makefile(String [] Make) throws IOException {
	//buildCMakeFile(Make);
		names = new LinkedList<String>();
		for (int i = 0; i < Make.length; i++) {
			names.add(Make[i]);
		}
		
	}
	public void buildCMakeFile() throws IOException {
		File f = new File("Makefile");
		FileWriter fw = new  FileWriter(f);
		BufferedWriter bw = new BufferedWriter(fw);
		bw.write("#!make -f\r\n");
		bw.write("CC=gcc");
		bw.newLine();
		bw.write("all:");
		for (String string : names) {
			bw.write(string.substring(0, string.length() -2)+ " ");
		}
		for (int i = 0; i < names.size(); i++) {
			bw.newLine();
			bw.append(names.get(i).substring(0, names.get(i).length() -2)+":");
			bw.newLine();
			bw.append("\t");
			bw.append("$(CC) -o " + names.get(i).substring(0, names.get(i).length() -2) +" "+names.get(i));
		}
		bw.newLine();
		bw.append("clean");
		bw.newLine();
		bw.append("\t");
		bw.append("rm -rf *.o ");
		for (int i = 0; i < names.size(); i++) {
			bw.append(names.get(i).substring(0, names.get(i).length()-2)+ " ");
		}
		bw.close();
		fw.close();
	}
	public void buildClang9Makefile () throws IOException {
		File f = new File("Makefile");
		FileWriter fw = new  FileWriter(f);
		BufferedWriter bw = new BufferedWriter(fw);
		bw.write("#!make -f\r\n");
		bw.write("CC=clang++-9");
		bw.newLine();
		bw.write("all:");
		for (String string : names) {
			bw.write(string.substring(0, string.length() -2)+ " ");
		}
		for (int i = 0; i < names.size(); i++) {
			bw.newLine();
			bw.append(names.get(i).substring(0, names.get(i).length() -2)+":");
			bw.newLine();
			bw.append("\t");
			bw.append("$(CC) -o " + names.get(i).substring(0, names.get(i).length() -2) +" "+names.get(i));
		}
		bw.newLine();
		bw.append("clean");
		bw.newLine();
		bw.append("\t");
		bw.append("rm -rf *.o ");
		for (int i = 0; i < names.size(); i++) {
			bw.append(names.get(i).substring(0, names.get(i).length()-2)+ " ");
		}
		bw.close();
		fw.close();
		
	}

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		String [] prog = {"filter.c","main.c","HelloWorld.c"};
		Makefile a = new Makefile(prog);
		a.buildCMakeFile();
		
		
	}

}
