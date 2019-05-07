import java.io.*;
import minipython.lexer.Lexer;
import minipython.parser.Parser;
import minipython.node.*;
import java.util.*;

public class ParserTest
{
  public static void main(String[] args)
  {

    try
    {
      Parser parser =
        new Parser(
        new Lexer(
        new PushbackReader(
        new FileReader(args[0]), 1024)));

     Hashtable symtable =  new Hashtable();

     Start ast = parser.parse();

	 // Create the first visitor
	 myvisitor visitor1 = new myvisitor(symtable);
	 
	 // Apply the first visitor
     ast.apply(visitor1);

	 // Create the second visitor
	 myvisitor2 visitor2 = new myvisitor2(symtable);
	 
	 // Apply the second visitor
	 ast.apply(visitor2);
	 
	 // Find how many errors from the two visitors
	 int errors = visitor1.getErrors() + visitor2.getErrors();
	 
	 if(errors!=0){
		// Errors have been found
		System.out.println("\nCompilation failed, "+ errors+" errors were found!");
	 }else{
		// Compilation successful
		System.out.println("\nCompilation was successful!");
	 }
	 
    }
    catch (Exception e)
    {
      System.err.println(e);
    }
  }
}

