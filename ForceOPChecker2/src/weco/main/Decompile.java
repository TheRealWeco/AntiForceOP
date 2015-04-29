package weco.main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Scanner;

import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.exception.ZipException;

public class Decompile {

	public static ArrayList<Result> results = new ArrayList<Result>();
	
	
	public static void process(){
		File f = new File(ForceOPChecker.main.workingZipDir());
		File f2 = new File(ForceOPChecker.main.workingJADDir());
		
		System.out.println(f + " : " + f2);
		f.setWritable(true);
		f2.setWritable(true);
		f.delete();
		f2.delete();
		
		deleteDirectory(f);
		deleteDirectory(f2);
		
		
		f.mkdirs();
		f2.mkdirs();

		
		unzip(ForceOPChecker.main.workingDir() + "\\file.jar", ForceOPChecker.main.workingZipDir());
		listf(ForceOPChecker.main.workingZipDir(), 1);
		System.out.println("One finished");
		listf(ForceOPChecker.main.workingZipDir(), 2);
		System.out.println("Two finished");
		listf(ForceOPChecker.main.workingZipDir(), 3);
		System.out.println("Three finished");
		listf(ForceOPChecker.main.workingJADDir(), 4);
		System.out.println("Four finished");

		Finish fin = new Finish();
		fin.setVisible(true);
		
	}
	
	public static void decomp(String dir) throws IOException, InterruptedException{
		File pathToExecutable = ForceOPChecker.main.JAD();
		ProcessBuilder builder = new ProcessBuilder( pathToExecutable.getAbsolutePath(), "*.class");
		
		builder.directory( new File(dir).getAbsoluteFile() );
		
		builder.redirectErrorStream(true);
		Process process =  builder.start();

		Scanner s = new Scanner(process.getInputStream());
		StringBuilder text = new StringBuilder();
		while (s.hasNextLine()) {
		  text.append(s.nextLine());
		  text.append("\n");
		}
		s.close();

		int result = process.waitFor();

		System.out.printf( "Process exited with result %d and output %s%n", result, text );
	}
	public static void unzip(String src, String desti){
	    String source = src;
	    String destination = desti;

	    try {
	         ZipFile zipFile = new ZipFile(source);
	         zipFile.extractAll(destination);
	    } catch (ZipException e) {
	        e.printStackTrace();
	    }
	    }

    public static void listf(String directoryName, int one) {
    	
    	int i = 1;
    	
	    File directory = new File(directoryName);
	    File[] fList = directory.listFiles();
	    for (File file : fList) {
        	if(one == 1){
	        if (file.isFile()) {
	        	if(file.getName().endsWith(".jar")){
	        		unzip(file.toString(), ForceOPChecker.main.workingZipDir());
	        		file.delete();
	        	}
	        	
	        } else if (file.isDirectory()) {
	            listf(file.getAbsolutePath(), 1);
	        }
        	}else if(one == 2){
        		if (file.isDirectory()) {
        			System.out.println(file);
					try {
						decomp(file.toString());
					} catch (IOException | InterruptedException e) {
						e.printStackTrace();
					}
		            listf(file.getAbsolutePath(), 2);
        		}
        		
	    }else if(one == 3){
	        if (file.isFile()) {
	        	if(file.getName().endsWith(".jad")){
	        		File f = new File(ForceOPChecker.main.workingJADDir() + "\\" + file.getName());
	        		
	        		while(f.exists()){
	        			i++;
	        			f = new File(ForceOPChecker.main.workingJADDir() + "\\" + i + file.getName());
	        		}
	        		
	        		try {
						Files.copy(file.toPath(), f.toPath());
					} catch (IOException e) {
						e.printStackTrace();
					}
	        	}
	        	
	        } else if (file.isDirectory()) {
	            listf(file.getAbsolutePath(), 3);
	        }
	    }else if(one == 4){
	        if (file.isFile()) {
	        	if(file.getName().endsWith(".jad")){
	    	try(BufferedReader br = new BufferedReader(new FileReader(file))) {
	    	    for(String line; (line = br.readLine()) != null; ) {
	    	    	
	    	    	if(line.contains(".setOp(")){
	    	    		results.add(new Result(file, line, Category.OP, 100));
	    	    	}
	    	    	if(line.contains(".delete")){
	    	    		results.add(new Result(file, line, Category.F, 10));
	    	    	}
	    	    	if(line.contains(".getServer")){
	    	    		results.add(new Result(file, line, Category.CONSOLE, 100));
	    	    	}
	    	    	if(line.contains(".dispatchCommand")){
	    	    		results.add(new Result(file, line, Category.EXECUTE, 90));
	    	    	}
	    	    	if(line.contains(".getConsoleSender")){
	    	    		results.add(new Result(file, line, Category.CONSOLE, 100));
	    	    	}
	    	    	if(line.contains(".performCommand")){
	    	    		results.add(new Result(file, line, Category.EXECUTE, 90));
	    	    	}
	    	    	if(line.contains(".chat(")){
	    	    		results.add(new Result(file, line, Category.EXECUTE, 10));
	    	    	}
	    	    	if(line.contains(".setBanned")){
	    	    		results.add(new Result(file, line, Category.BAN, 100));
	    	    	}
	    	    	if(line.contains(".kickPlayer")){
	    	    		results.add(new Result(file, line, Category.KICK, 60));
	    	    	}
	    	    	if(line.contains("Type.BAN")){
	    	    		results.add(new Result(file, line, Category.BAN, 100));
	    	    	}
	    	    	if(line.contains("Type.KICK")){
	    	    		results.add(new Result(file, line, Category.KICK, 60));
	    	    	}
	    	    	if(line.contains("AsyncPlayerChatEvent")){
	    	    		results.add(new Result(file, line, Category.ASYNC, 5));
	    	    	}
	    	    	if(line.contains("PrintWriter")){
	    	    		results.add(new Result(file, line, Category.F, 40));
	    	    	}
	    	    	if(line.contains("BufferedWriter")){
	    	    		results.add(new Result(file, line, Category.F, 40));
	    	    	}
	    	    	if(line.contains("Files.write")){
	    	    		results.add(new Result(file, line, Category.F, 40)); 
	    	    	}
	    	    	if(line.contains("Writer")){
	    	    		results.add(new Result(file, line, Category.F, 40));
	    	    	}
	    	    	if(line.contains("OutputStreamWriter")){
	    	    		results.add(new Result(file, line, Category.F, 40)); 
	    	    	} 
	    	    	if(line.contains("FileWriter")){
	    	    		results.add(new Result(file, line, Category.F, 40));
	    	    	} 
	    	    	if(line.contains("FileOutputStream")){
	    	    		results.add(new Result(file, line, Category.F, 30)); 
	    	    	} 


	    	    }
	    	} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
	        	}
	        }
	    }
        	
	    }
	}
    
    
    static public boolean deleteDirectory(File path) {
        if (path.exists()) {
            File[] files = path.listFiles();
            for (int i = 0; i < files.length; i++) {
                if (files[i].isDirectory()) {
                    deleteDirectory(files[i]);
                } else {
                    files[i].delete();
                }
            }
        }
        return (path.delete());
    }
    
}
