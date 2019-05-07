import minipython.analysis.*;
import minipython.node.*;
import java.util.*;

public class myvisitor extends DepthFirstAdapter 
{
	private Hashtable symtable;	

	// Number of errors found
	private int errors = 0;
	
	myvisitor(Hashtable symtable) 
	{
		this.symtable = symtable;
	}
	
	// Getter for errors
	public int getErrors(){
		return errors;
	}

	// Function notDefined is a recursive function, which given an Expression, TId, PValue or a PFunctionCall, it can find whether there is a id that is not defined.
	// If all the ids are defined, then it returns null
	// If there is an undefined id, it returns it (so it returns the first id that is not defined)
	
	private TId notDefined(PExpression exp){
		
		// Here we check the type of the PExpression using instanceof
		
		if(exp instanceof AAdditionExpression){
			
			// If it is a AAdditionExpression
			
			// Cast it
			AAdditionExpression exp2 = (AAdditionExpression)exp;
			// Search E1 for undefined ids
			TId id1 = notDefined(exp2.getE1());
			// Search E2 for undefined ids
			TId id2 = notDefined(exp2.getE2());
			// If there are undefined ids return the first undefined
			if(id1!=null) return id1;
			else if(id2!=null) return id2;
			// Else return null
			else return null;
			
		}else if(exp instanceof ASubtractionExpression){
			
			// If it is a ASubtractionExpression
			
			// Cast it
			ASubtractionExpression exp2 = (ASubtractionExpression)exp;
			// Search E1 for undefined ids
			TId id1 = notDefined(exp2.getE1());
			// Search E2 for undefined ids
			TId id2 = notDefined(exp2.getE2());
			// If there are undefined ids return the first undefined
			if(id1!=null) return id1;
			else if(id2!=null) return id2;
			// Else return null
			else return null;
			
		}else if(exp instanceof AMultiplicationExpression){
			
			// If it is a AMultiplicationExpression
			
			// Cast it
			AMultiplicationExpression exp2 = (AMultiplicationExpression)exp;
			// Search E1 for undefined ids
			TId id1 = notDefined(exp2.getE1());
			// Search E2 for undefined ids
			TId id2 = notDefined(exp2.getE2());
			// If there are undefined ids return the first undefined
			if(id1!=null) return id1;
			else if(id2!=null) return id2;
			// Else return null
			else return null;
			
		}else if(exp instanceof ADivisionExpression){
			
			// If it is a ADivisionExpression
			
			// Cast it
			ADivisionExpression exp2 = (ADivisionExpression)exp;
			// Search E1 for undefined ids
			TId id1 = notDefined(exp2.getE1());
			// Search E2 for undefined ids
			TId id2 = notDefined(exp2.getE2());
			// If there are undefined ids return the first undefined
			if(id1!=null) return id1;
			else if(id2!=null) return id2;
			// Else return null
			else return null;
			
		}else if(exp instanceof APowExpression){
			
			// If it is a APowExpression
			
			// Cast it
			APowExpression exp2 = (APowExpression)exp;
			// Search E1 for undefined ids
			TId id1 = notDefined(exp2.getE1());
			// Search E2 for undefined ids
			TId id2 = notDefined(exp2.getE2());
			// If there are undefined ids return the first undefined
			if(id1!=null) return id1;
			else if(id2!=null) return id2;
			// Else return null
			else return null;
			
		}else if(exp instanceof AIdentifierExpression){
			
			// If it is a AIdentifierExpression
			
			// Cast it
			AIdentifierExpression exp2 = (AIdentifierExpression)exp;
			// Search if the id of the expression is defined and return the answer
			return notDefined(exp2.getId());
			
		}else if(exp instanceof AValueExpression){
			
			// If it is a AValueExpression
			
			// Cast it
			AValueExpression exp2 = (AValueExpression)exp;
			// Search if the value has a undefined id and return the asnwer
			return notDefined(exp2.getValue());
			
		}else if(exp instanceof AFuncCallExpression){
			
			// If it is a AFuncCallExpression
			
			// Cast it
			AFuncCallExpression exp2 = (AFuncCallExpression)exp;
			// Search it for undefined ids and return the asnwer
			return notDefined(exp2.getFunctionCall());
			
		}else if(exp instanceof AParExpression){
			
			// If it is a AParExpression
			
			// Cast it
			AParExpression exp2 = (AParExpression)exp;
			// Search the expression in the parentheses for undefined ids and return the answer
			return notDefined(exp2.getExpression());
			
		}else if(exp instanceof AListExpression){

			// If it is a AListExpression
		
			// Cast it
			AListExpression exp2 = (AListExpression)exp;
			
			// Put all AMoreValue objects of the list expression in an array
			Object[] morevalsL = exp2.getMoreValues().toArray();
			
			// For every AMoreValue object
			for(Object mvs : morevalsL){
				// Cast it
				AMoreValues amv = (AMoreValues)mvs;
				// Search for undefined ids
				TId id = notDefined(amv.getValue());
				// Return the id if it isn't null
				if(id!=null) return id;
			}
			// Search for undefined ids from the first value of the lisr expression
			// and return the answer
			return notDefined(exp2.getValue());
			
		}else if(exp instanceof ALElementExpression){
			
			// If it is a ALElementExpression
			
			// Cast it
			ALElementExpression exp2 = (ALElementExpression)exp;
			// Search if the id of the ALElementExpression is defined
			TId id1 = notDefined(exp2.getId());
			// Search if the expression of the ALElementExpression has undefined ids
			TId id2 = notDefined(exp2.getExpression());
			// If there are undefined ids return the first
			if(id1!=null) return id1;
			else if(id2!=null) return id2;
			// Else return null
			else return null;
			
		}
		// If PExpression is of no known type return null
		return null;
	}

	private TId notDefined(TId id){
		
		// Here we check if the id given is undefined or not 
		
		// If the id is not in the symbol table
		if(!symtable.containsKey(id.toString())){
			// It is undefined, so return it
			return id;
		}else{
			// The id is in the symbol table
			
			// Get the object bound to the id in the symbol table
			Object o = symtable.get(id.toString());
			// If the object isn't an instance of a AAssignStatement or a AFunction
			if(!(o instanceof AAssignStatement) && !(o instanceof AFunction)){
				// It is defined from an unknown (for our purposes) command, so consider it undefined and return it
				return id;
			}else{
				// Else it is defined so return null
				return null;
			}
		}
	}

	private TId notDefined(PValue val){
		
		// Here we check the type of the PValue
		
		if(val instanceof AFuncCallValue){
			
			// If it is a AFuncCallValue
			
			// Cast it
			AFuncCallValue val2 = (AFuncCallValue)val;
			// Search if the func call value id is defined
			TId id1 = notDefined(val2.getId());
			// Search the function call for undefined ids
			TId id2 = notDefined(val2.getFunctionCall());
			// If there is an undefined id return it
			if(id1!=null) return id1;
			else if(id2!=null) return id2;
			// Else return null
			else return null;
			
		}else if(val instanceof ANumberValue){
			
			// If it is a ANumberValue it doesn't have ids so return null
			return null;
			
		}else if(val instanceof AStringLitValue){
			
			// If it is a AStringLitValue it doesn't have ids so return null
			return null;
			
		}
		
		// If the type of PValue is unknown return null
		return null;
	}

	private TId notDefined(PFunctionCall fcall){
		
		// Here we check in the function call has undefined ids
		
		// Check that the PFunctionCall is an instanceof AFunctionCall
		if(fcall instanceof AFunctionCall){
			// Cast it
			AFunctionCall fcall2 = (AFunctionCall)fcall;
			
			// If the functioncall has arguments
			if(fcall2.getArglist().toArray().length > 0){
				// Get the first argument
				Object[] objlist = fcall2.getArglist().toArray();
				AArglist arglist = (AArglist)objlist[0];
				// Search if the expression in the argument has undefined ids
				TId id1 = notDefined(arglist.getExpression());
				// If it has an undefined id return the id
				if(id1!=null) return id1;
				
				// Get the rest arguments (objects AMoreExp)
				Object[] moreexpsL = arglist.getMoreExp().toArray();
				// For every AMoreExp object
				for(Object o : moreexpsL){
					// Get the expression of the object
					PExpression e = ((AMoreExp)o).getExpression();
					// Search the expression for undefined ids
					TId id2 = notDefined(e);
					// If it has an undefined id return it
					if(id2!=null) return id2;
				}
				
			}
			// Search if the functioncall id is not defined and return the asnwer
			return notDefined(fcall2.getId());
			
		}
		// If it isn't an instanceof AFunctionCall return null
		return null;
	}

	// Find all not defined ids when entering an AIdentifierExpression
	public void inAIdentifierExpression(AIdentifierExpression node){
		// Get the id of the node
		TId id = node.getId();
		
		// Special Cases
		// Check until Start node
		Node temp = node;
		while(!(temp instanceof Start)){
			if(temp instanceof AFunction){
				// Special Case Function -- we check the local variables of the function
				
				AFunction temp2 = (AFunction)temp;
				
				// Find all argument ids
				// Initialize a set of ids
				Set<TId> arg_ids = new HashSet<>();
				// If it has arguments
				if(temp2.getArgument().toArray().length >0){
					// Get the first argument
					AArgument arg = (AArgument)temp2.getArgument().toArray()[0];
					
					// Add the first argument to the set
					arg_ids.add(arg.getId());
					
					// Check for the rest arguments
					Object[] argflist = arg.getMoreArgs().toArray();
					// Add the rest to the set
					for(Object o : argflist){
						AMoreArgs oarg = (AMoreArgs)o;
						TId tid = oarg.getId();
						arg_ids.add(tid);
					}	
				}
				// If the id is undefined
				if(notDefined(id) != null){
					// Check if it is a local variable
					
					// Initialize a boolean variable to false
					boolean found = false;
					// For every id in the argument ids
					for(TId i : arg_ids){
						// Check if it matches the id that is undefined
						if(i.toString().equals(id.toString())){
							// If it is then it is a local variable
							found = true;
							break;
						}
					}
					// If it isn't a local variable print error
					if(!found){
						// Get line from id
						int line = id.getLine();
						System.out.println("IdentifierExpressionError :: Line " + line + " :: Id " + id.toString() + "in function "+ temp2.getId().toString() +"is not defined.");
						// Increase error count
						errors+=1;
					}
				}
				// End the method
				return;
			}else if(temp instanceof AForStatement){
				// Special Case For Statement -- for el in a_list -> el is defined inside the for statement and we must check only for a_list
				
				// Cast it
				AForStatement temp2 = (AForStatement)temp;
				
				// Get the first id defined in the for statement
				String id1 = temp2.getId1().toString();
				
				// If the id is not defined globally and locally (in the for statement)
				if(notDefined(id)!=null && !id.toString().equals(id1)){
					// Get the line from the id
					int line = id.getLine();
					// Print error
					System.out.println("IdentifierExpressionError :: Line " + line + " :: Id " + id.toString() + "in for statement is not defined.");
					// Increase error count
					errors+=1;
				}
				// End the method
				return;
				
				
			}else{
				// Else go to the parent node
				temp = temp.parent();
			}
		}
		// It isn't a special case
		
		// Check if the id is undefined
		if(notDefined(id) != null){
			// If it is undefined
			// Get the line
			int line = id.getLine();
			// Print error
			System.out.println("IdentifierExpressionError :: Line " + line + " :: Id " + id.toString() + "is not defined.");
			// Increase error count
			errors+=1;
		}
	}
	
	// Add the id to the symtable in a assign statement
	public void inAAssignStatement(AAssignStatement node)
    {
		// Get the id's name
        String id_name = node.getId().toString();
		
		// Check if the expression in the right part of the assignment has undefined ids
		TId id = notDefined(node.getExpression());
		
		// If it doesn't
		if(id==null){
			// Add the id to the symbtable
			symtable.put(id_name, node);
		}	
		
    }
	
	// Check if the id in the right part of the for statement is undefined
	public void inAForStatement(AForStatement node){
		
		// Get the second id of the for statement
		TId id2 = node.getId2();
		
		// Get the line of the id
		int line = id2.getLine();

		// Check if it is defined
		if(notDefined(id2)!=null){
			// Print error if not defined
			System.out.println("ForStatementError :: Line " + line + " :: Id " + id2.toString() + "is not defined.");
			// Increase error count
			errors+=1;
		}
		
	}
	
	// Check if the id in a decrementstatement is undefined
	public void inADecrementStatement(ADecrementStatement node){
		// Get the id
		TId id = node.getId();
		// Get the line of the id
		int line = id.getLine();
		// Check if it is undefined
		if(notDefined(id)!=null){
			// Print error if not defined
			System.out.println("DecrementStatementError :: Line " + line + " :: Id " + id.toString() + "is not defined.");
			// Increase error count
			errors+=1;
		}
		
	}
	
	// Check if the id in a decrementdivstatement is undefined
	public void inADecrementDivStatement(ADecrementDivStatement node){
		// Get the id
		TId id = node.getId();
		// Get the line of the id
		int line = id.getLine();
		// Check if it is undefined
		if(notDefined(id)!=null){
			// Print error if not defined
			System.out.println("DecrementDivStatementError :: Line " + line + " :: Id " + id.toString() + "is not defined.");
			// Increase error count
			errors+=1;
		}
	}
	
	// Check if the id in a assignelementstatement is undefined and whether the assigned expression is a list
	public void inAAssignElementStatement(AAssignElementStatement node){
		// Get the id
		TId id = node.getId();
		// Get the line of the id
		int line = id.getLine();
		// Check if it is undefined
		if(notDefined(id)!=null){
			// Print error if not defined
			System.out.println("AssignElementStatementError :: Line " + line + " :: Id " + id.toString() + "is not defined.");
			// Increase error count
			errors+=1;
		}else{
			// Check that the assign statement is a list

			// Get the assignstatement from the symbol table
			AAssignStatement stat = (AAssignStatement)symtable.get(id.toString());
			// Check if the statement's expression is a list expression
			if(!(stat.getExpression() instanceof AListExpression)){
				// If it isn't then print error
				System.out.println("AssignElementStatementError :: Line " + line + " :: Id " + id.toString() + "is not a list.");
				// Increase error count
				errors+=1;
			}
		}
	}
	
	// Find Functions with duplicate arguments, functions already defined with same arguments (and the case with the default values -- overrides ),
	// 	functions that have arguments with non default values after arguments with default values,
	//	functions that have return statements with ids that are not defined AND not in the argument list
	// Else put function name in symbol table
	// foo(a,a) is error -- duplicate arguments
	public void inAFunction(AFunction node)
    {
		// Get the function's name
        String fName = node.getId().toString();
		
		// Get the line of the function
		int line = ((TId) node.getId()).getLine();
		
		// Find all argument ids -- meanwhile check that there are no duplicate args
		Set<TId> arg_ids = new HashSet<>();
		// If it has arguments
		if(node.getArgument().toArray().length >0){
			// Get the first argument
			AArgument arg = (AArgument)node.getArgument().toArray()[0];
			
			// Add the first argument to the hashset
			arg_ids.add(arg.getId());
			
			// Check for the rest
			// IMPORTANT : When a default value has been placed in python, all the rest must be defaults
			Object[] argflist = arg.getMoreArgs().toArray();
			
			// For every argument
			for(Object o : argflist){
				// Cast it to AMoreArgs
				AMoreArgs oarg = (AMoreArgs)o;
				
				// Find if it doesn't contain already the argument
				
				// Get the id from the current argument
				TId tid = oarg.getId();
				// Initialize a boolean to false
				boolean foundD = false;
				// For every id in the hashset
				for(TId i : arg_ids){
					// If the id from the current argument is the same as the id
					if(tid.toString().equals(i.toString())){
						// Already contains the argument, break the loop and set foundD to true (for found duplicate)
						foundD = true;
						break;
					}
				}
				// If it doesn't then add it to the set
				if(!foundD){
					arg_ids.add(oarg.getId());
				}else{
					// Else we have foudn a duplicate argument, so print error
					System.out.println("FunctionError :: Line " + line + " :: Function " + fName + "has duplicate argument: " + oarg.getId() + ".");
					// Increase error count
					errors+=1;
				}
			}
			
		}
				
		// If the function name is contained in the symbol table and the object is an instance of a function
		if(symtable.containsKey(fName) && symtable.get(fName) instanceof AFunction){
			
			// We must check overrides with the same count of arguments
			
			// Cast it to a function
			AFunction func = (AFunction)symtable.get(fName);
			
			// Initialize the argument count and the default values index to 0
			int argCount = 0;
			int def_index = 0;
			
			// Find how many arguments the node has
			
			// If the node has arguments
			if(node.getArgument().toArray().length>0){
				
				// It sure has one argument (the first)
				AArgument arg = (AArgument)node.getArgument().toArray()[0];
				// Increase the argument count by 1
				argCount += 1;				
				
				// Check if it has default value and if it does set the default value index to 1
				if(arg.getArgSetValue().toArray().length>0) def_index = 1;
				
				// Add the count of the rest arguments
				Object[] argflist = arg.getMoreArgs().toArray();
				argCount += argflist.length;
				
				// Find in which index the first default value is
				// IMPORTANT : When a default value has been placed in python, all the rest must be defaults
				// Initialize an index to 0
				int idx = 0;
				// For every object in th erest arguments
				for(Object o : argflist){
					// Increase the index by 1
					idx += 1;
					// Cast the object to AMoreArgs
					AMoreArgs oarg = (AMoreArgs)o;
					// If it has a default value
					if(oarg.getArgSetValue().toArray().length>0) {
						// Set the default value index to idx
						def_index = idx;
						// We found the first default value index -- break the loop
						break;
					}
				}
			}
						
			// Initialize the symtable function's argument count and default value index to 0
			int argCountF = 0;
			int def_indexF = 0;
			
			// Find how many arguments the symtable function has
			if(func.getArgument().toArray().length>0){
				// It sure has one argument
				AArgument arg = (AArgument)func.getArgument().toArray()[0];
				// Increase the argument count by 1
				argCountF += 1;

				// Check if it has default value and if it does set default value index to 1
				if(arg.getArgSetValue().toArray().length>0) def_indexF = 1;
				
				// Add the count of the rest arguments
				Object[] argflist = arg.getMoreArgs().toArray();
				argCountF += argflist.length;
				
				// Find in which index the first default value is
				// IMPORTANT : When a default value has been placed in python, all the rest must be defaults
				// Initialize an index to 0
				int idx = 0;
				// For every object in the rest arguments
				for(Object o : argflist){
					// Increase the index by 1
					idx += 1;
					// Cast the object to AMoreArgs
					AMoreArgs oarg = (AMoreArgs)o;
					// If it has a devault value
					if(oarg.getArgSetValue().toArray().length>0) {
						// Set the default value index to idx
						def_indexF = idx;
						// We foudn the first default value index -- break the loop
						break;
					}
				}
				
			}
			
			// Now we know how many arguments each function has (the node and the symtable function) and where the default values start (index)
			// So we can check for errors
			
			if(argCount == argCountF) {
				System.out.println("FunctionError :: Line " + line + " :: Function " + fName + "is already defined with the same number of arguments.");
				// Increase error count
				errors+=1;
			}else if(argCount > argCountF && argCountF == def_index) {
				System.out.println("FunctionError :: Line " + line + " :: Function " + fName + "is already defined with less arguments. You must remove the arguments with default values.");
				// Increase error count
				errors+=1;
			}else if(argCount < argCountF && argCount == def_indexF){
				System.out.println("FunctionError :: Line " + line + " :: Function " + fName + "is already defined with more arguments, including arguments with default values. You must increase the number of arguments.");
				// Increase error count
				errors+=1;
			}
						
		}else{
			
			// The function name is not contained in the symbol table
			
			// Check that if a default value is set, all the others will be set as default
			// IMPORTANT : When a default value has been placed in python, all the rest must be defaults
			
			// If it has arguments
			if(node.getArgument().toArray().length >0){
				// Get the first argument
				AArgument arg = (AArgument)node.getArgument().toArray()[0];
				
				// Initialize a boolean for the search of a default value to false
				boolean foundDefault = false;
				
				// Check if the first arg has a default value and if it has make the boolean true
				if(arg.getArgSetValue().toArray().length>0) foundDefault = true;
				
				// Check for the rest
				Object[] argflist = arg.getMoreArgs().toArray();
				// For every object in the rest arguments
				for(Object o : argflist){
					// Cast the object to AMoreArgs
					AMoreArgs oarg = (AMoreArgs)o;
					// If it has a default value
					if(oarg.getArgSetValue().toArray().length>0) {
						// If we haven't found a default value then set the boolean to true
						if(!foundDefault) foundDefault = true;
					}else{
						// If we found a argument without a default value and we have already found a previous argument with a default value
						if(foundDefault){
							// Print error
							System.out.println("FunctionError :: Line " + line + " :: Function " + fName + "has arguments without default values after arguments with default values.");
							// Increase error count
							errors+=1;
							// End method
							return;
						}
						
					}
				}
				
			}
			
			// Everything is ok, put function in symbol table
			
			symtable.put(fName, node);
		}
		
    }
	
}
