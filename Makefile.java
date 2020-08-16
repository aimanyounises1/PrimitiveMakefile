import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.LinkedList;
import java.util.List;

public class Makefile {
	List <String> names;
	List<String> headers;
	public Makefile(String [] Make) throws IOException {
	//buildCMakeFile(Make);
		names = new LinkedList<String>();
		headers = new LinkedList<String>();
		for (int i = 0; i < Make.length; i++) {
			String s = Make[i];
			boolean Cfile = s.charAt(s.length()-1) == 'h' ;
			if(!Cfile) {
			 names.add(Make[i]);
			}
		}
		for (int i = 0; i < Make.length; i++) {
			String s = Make[i];
			if(s.contains(".h"))
			headers.add(Make[i]);
		}
		
	}
	public void buildCMakeFile() throws IOException {
		File f = new File("Makefile");
		FileWriter fw = new  FileWriter(f);
		BufferedWriter bw = new BufferedWriter(fw);
		System.out.println(headers);
		System.out.println(names);
		bw.write("#!make -f\r\n");
		bw.write("CC=gcc");
		bw.newLine();
		bw.write("all:");
		for (String string : names) {
			bw.write(string.substring(0, string.length() -2)+ " ");
		}
		for (int i = 0; i < names.size(); i++) {
			String s = names.get(i);
			bw.newLine();
			bw.append(names.get(i).substring(0, names.get(i).length() -2)+":");
			bw.newLine();
			bw.append("\t");
			String ss = "";
			for (int j = 0; j < names.size(); j++) {
				String s1 = names.get(i).substring(0, names.get(i).length()-2);
				s1 = s1 + ".h";
				ss = headers.contains(s1) != false ?  s1 : "";
			//	System.out.println(ss);
				if(ss.length() > 0) break;
			}
			bw.append("$(CC) -o " + names.get(i).substring(0, names.get(i).length() -2) + " "
			+names.get(i)+" "+ ss);
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
		bw.write("CXXFLAGS=-std=c++2a\r\n");
		bw.newLine();
		bw.write("all:");
		for (String string : names) {
			bw.write(string.substring(0, string.length() -4)+ " ");
		}
		bw.newLine();
		for (int i = 0; i < names.size(); i++) {
			bw.newLine();
			bw.append(names.get(i).substring(0, names.get(i).length() -4)+":");
			bw.newLine();
			bw.append("\t");
			bw.append("$(CC) $(CXXFLAGS) -o " + names.get(i).substring(0, names.get(i).length() -4) +" "+names.get(i));
		}
		bw.newLine();
		bw.append("clean");
		bw.newLine();	
		bw.append("\t");
		bw.append("rm -rf *.o ");
		for (int i = 0; i < names.size(); i++) {
			bw.append(names.get(i).substring(0, names.get(i).length()-4)+ " ");
		}
		bw.close();
		fw.close();
		
	}

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		String [] prog = {"filter.c","main.c", "filter.h"};
		Makefile a = new Makefile(prog);
		a.buildCMakeFile();
		
		
	}

}
