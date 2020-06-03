package io.interfiber.Pizza.compiler;
import java.io.*;
import java.util.*;

import io.interfiber.Pizza.coreFunctions.*;
import io.interfiber.Pizza.coreFunctions.Math;
import io.interfiber.Pizza.coreFunctions.System;
import io.interfiber.Pizza.lang.VarNullException;
import io.interfiber.Pizza.utils.*;

public class compiler {
	public static void compile(String inputFile, boolean compiledFirst) throws IOException, NoSuchElementException{
		// Get file data
		File input = new File(inputFile);
		Scanner reader = new Scanner(input);
		String out = null;
		while (reader.hasNext()) {
			out = reader.next();
			if (out.contains("Console.pushString")) {
				String message = reader.nextLine().replace("\"", "").trim();
				try {
					Screen.printString(message);
				}catch(FileNotFoundException e){
					VarNullException.catchError(message.replace(".:", "").trim());
				}
			}
			if (out.contains("var")) {
				Variable.create(reader.next(), reader.nextLine());
			}
			if (out.contains("exit")) {
				exit.terminateProcess(0);
			}
			if (out.contains("function")) {
				String name = reader.next();
				String key = reader.next();
				String code = reader.nextLine();
				tmp.createTmp();
				File f = new File(tmp.getTmpDir() + "pizza/" + name + ".pizzafunc");
				FileWriter fw = new FileWriter(f);
				if (key.contains("{")) {
					while (!code.contains("}")) {
						fw.write(code);
						code = reader.nextLine().concat("\n");
					}
				}
				fw.close();
			}
			if (out.contains("funcrun")) {
				String funcName = reader.next() + ".pizzafunc";
				Function.runFuncFile(tmp.getTmpDir() + "/pizza/" + funcName);
			}
			if (out.contains("get")) {
				Math.get(reader.next(), reader.next(), reader.next(), reader.next());
			}
			if(out.contains("loop")){
				Loop.forLoop(reader.nextInt(), reader.next());
			}
			if(out.contains("exec")){
				System.executeCommand(reader.nextLine().replace("\"", "").trim());
			}

		}

	}
	}
