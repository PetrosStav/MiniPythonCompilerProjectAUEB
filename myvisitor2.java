import minipython.analysis.*;
import minipython.node.*;
import java.util.*;

public class myvisitor2 extends DepthFirstAdapter 
{
	private Hashtable symtable;	

	private Hashtable symtable2 = new Hashtable();

	// Number of errors found
	private int errors = 0;
	
	myvisitor2(Hashtable symtable) 
	{
		this.symtable = symtable;
	}

	// Getter for errors
	public int getErrors(){
		return errors;
	}
	
	// This method returns the type of an expression by using recursion and prints type checking errors
    private PValue getTypeExpression(PExpression exp){
		
		// Check the type of the expression
		
        if(exp instanceof AAdditionExpression){

			// If a AAdditionExpression
		
			// Cast it
            AAdditionExpression exp2 = (AAdditionExpression)exp;

			// Call the method for E1 and E2 and get the types
            PValue e1 = getTypeExpression(exp2.getE1());
            PValue e2 = getTypeExpression(exp2.getE2());

			// Perform type checking for addition and return the value type of the operation or null if there was a type checking error
            if(e1!=null && e2!=null){
                if(e1 instanceof ANumberValue && e2 instanceof AStringLitValue){
                    System.out.println("Error :: Can't add "+e1.getClass().getSimpleName()+" with "+e2.getClass().getSimpleName()+".");
					// Increase error count
					errors+=1;
                }else if(e1 instanceof AStringLitValue && e2 instanceof ANumberValue){
                    System.out.println("Error :: Can't add "+e1.getClass().getSimpleName()+" with "+e2.getClass().getSimpleName()+".");
					// Increase error count
					errors+=1;
                }else if(e1 instanceof ANumberValue && e2 instanceof ANumberValue){
                    return e1;
                }else if(e1 instanceof AStringLitValue && e2 instanceof AStringLitValue){
                    return e1;
                }
            }

            return null;

        }else if(exp instanceof ASubtractionExpression){

			// If a ASubtractionExpression
		
			// Cast it
            ASubtractionExpression exp2 = (ASubtractionExpression)exp;
			
			// Call the method for E1 and E2 and get the types
            PValue e1 = getTypeExpression(exp2.getE1());
            PValue e2 = getTypeExpression(exp2.getE2());

			// Perform type checking for subtraction and return the value type of the operation or null if there was a type checking error
            if(e1!=null && e2!=null){
                if(e1 instanceof ANumberValue && e2 instanceof AStringLitValue){
                    System.out.println("Error :: Can't subtract "+e1.getClass().getSimpleName()+" from "+e2.getClass().getSimpleName()+".");
					// Increase error count
					errors+=1;
                }else if(e1 instanceof AStringLitValue && e2 instanceof ANumberValue){
                    System.out.println("Error :: Can't subtract "+e1.getClass().getSimpleName()+" from "+e2.getClass().getSimpleName()+".");
					// Increase error count
					errors+=1;
                }else if(e1 instanceof ANumberValue && e2 instanceof ANumberValue){
                    return e1;
                }else if(e1 instanceof AStringLitValue && e2 instanceof AStringLitValue){
                    System.out.println("Error :: Can't subtract "+e1.getClass().getSimpleName()+" from "+e2.getClass().getSimpleName()+".");
					// Increase error count
					errors+=1;
                }
            }
            return null;

        }else if(exp instanceof AMultiplicationExpression){
			
			// If a AMultiplicationExpression

			// Cast it
            AMultiplicationExpression exp2 = (AMultiplicationExpression)exp;
			
			// Call the method for E1 and E2 and get the types
            PValue e1 = getTypeExpression(exp2.getE1());
            PValue e2 = getTypeExpression(exp2.getE2());

			// Perform type checking for multiplication and return the value type of the operation or null if there was a type checking error
            if(e1!=null && e2!=null){
                if(e1 instanceof ANumberValue && e2 instanceof AStringLitValue){
                    return e2;
                }else if(e1 instanceof AStringLitValue && e2 instanceof ANumberValue){
                    return e1;
                }else if(e1 instanceof ANumberValue && e2 instanceof ANumberValue){
                    return e1;
                }else if(e1 instanceof AStringLitValue && e2 instanceof AStringLitValue){
                    System.out.println("Error :: Can't multiply "+e1.getClass().getSimpleName()+" with "+e2.getClass().getSimpleName()+".");
					// Increase error count
					errors+=1;
                }
            }
            return null;

        }else if(exp instanceof ADivisionExpression){

			// If a ADivisionExpression
		
			// Cast it
            ADivisionExpression exp2 = (ADivisionExpression)exp;
			
			// Call the method for E1 and E2 and get the types
            PValue e1 = getTypeExpression(exp2.getE1());
            PValue e2 = getTypeExpression(exp2.getE2());

			// Perform type checking for division and return the value type of the operation or null if there was a type checking error
            if(e1!=null && e2!=null){
                if(e1 instanceof ANumberValue && e2 instanceof AStringLitValue){
                    System.out.println("Error :: Can't divide "+e1.getClass().getSimpleName()+" with "+e2.getClass().getSimpleName()+".");
					// Increase error count
					errors+=1;
                }else if(e1 instanceof AStringLitValue && e2 instanceof ANumberValue){
                    System.out.println("Error :: Can't divide "+e1.getClass().getSimpleName()+" with "+e2.getClass().getSimpleName()+".");
					// Increase error count
					errors+=1;
                }else if(e1 instanceof ANumberValue && e2 instanceof ANumberValue){
                    return e1;
                }else if(e1 instanceof AStringLitValue && e2 instanceof AStringLitValue){
                    System.out.println("Error :: Can't divide "+e1.getClass().getSimpleName()+" with "+e2.getClass().getSimpleName()+".");
					// Increase error count
					errors+=1;
                }
            }

            return null;

        }else if(exp instanceof APowExpression){

			// If a APowExpression
		
			// Cast it
            APowExpression exp2 = (APowExpression)exp;
			
			// Call the method for E1 and E2 and get the types
            PValue e1 = getTypeExpression(exp2.getE1());
            PValue e2 = getTypeExpression(exp2.getE2());

			// Perform type checking for the power operation and return the value type of the operation or null if there was a type checking error
            if(e1!=null && e2!=null){
                if(e1 instanceof ANumberValue && e2 instanceof AStringLitValue){
                    System.out.println("Error: Can't set a power of a " + e1.getClass().getSimpleName() +" to a " + e2.getClass().getSimpleName()+".");
					// Increase error count
					errors+=1;
                }else if(e1 instanceof AStringLitValue && e2 instanceof ANumberValue){
                    System.out.println("Error: Can't set a power of a " + e1.getClass().getSimpleName() +" to a " + e2.getClass().getSimpleName()+".");
					// Increase error count
					errors+=1;
                }else if(e1 instanceof ANumberValue && e2 instanceof ANumberValue){
                    return e1;
                }else if(e1 instanceof AStringLitValue && e2 instanceof AStringLitValue){
                    System.out.println("Error: Can't set a power of a " + e1.getClass().getSimpleName() +" to a " + e2.getClass().getSimpleName()+".");
					// Increase error count
					errors+=1;
                }
            }
            return null;

        }else if(exp instanceof AIdentifierExpression){

			// If a AIdentifierExpression
		
			// Cast it
            AIdentifierExpression exp2 = (AIdentifierExpression)exp;

			// Get the id name
            String id = exp2.getId().toString();

            // Check for local 'f' vars
			
			// We want to search if the identifier expression comes from a function
			
			// Create a Node reference to exp
            Node temp = exp;
            // Boolean if it is an expression from a function initialized to false
            boolean found = false;
			// Search until we reaach the Start Node
            while(!(temp instanceof Start)){
				// If it is an instance of a function
                if(temp instanceof AFunction){
					// Set boolean to true
                    found = true;
					// Break the loop
                    break;
                }
				// Go to the node's parent
                temp = temp.parent();
            }
			// If it comes from a function and symtable2 contains the local id (using 'f' postfix)
			// (we search symtable2 and not symtable because symtable has all the ids defined even in the next lines, as it is filled in visitor.java)
            if(found && symtable2.containsKey(id+"f")){
				// Cast it to a Node
                Node n = (Node)symtable2.get(id+"f");
				// If it is an instance of PExpression
                if(n instanceof PExpression){
					// Return the type of the expression
                    return getTypeExpression((PExpression)n);
                }
            }

            // If it is assigned get it's type
            if(symtable2.containsKey(id) && symtable2.get(id) instanceof AAssignStatement){
                AAssignStatement statement = (AAssignStatement)symtable2.get(id);
                return getTypeExpression(statement.getExpression());
            }

        }else if(exp instanceof AValueExpression){

			// If a AValueExpression
		
			// Cast it
            AValueExpression exp2 = (AValueExpression)exp;
			
			// If the value is ANumberValue or AStringLitValue then return a new object of that type accordingly
            if(exp2.getValue() instanceof ANumberValue) return new ANumberValue();
            else if(exp2.getValue() instanceof AStringLitValue) return new AStringLitValue();
			// Or else return null
            return null;

        }else if(exp instanceof AParExpression){

			// If a AParExpression
		
			// Cast it
            AParExpression exp2 = (AParExpression)exp;
			
			// Return the type of the expression in the parentheses
            return getTypeExpression(exp2.getExpression());

        }else if(exp instanceof AListExpression){

			// If a AListExpression
		
			// Cast it
            AListExpression exp2 = (AListExpression)exp;
			
            // Can't return a list value so return null
            return null;

        }else if(exp instanceof ALElementExpression){

			// If a ALElementExpression
		
			// Cast it
            ALElementExpression exp2 = (ALElementExpression)exp;
			
			// Return the type of the expression in the ALElementExpression
            return getTypeExpression(exp2.getExpression());

        }
        return null;
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
		if(!symtable2.containsKey(id.toString())){
			// It is undefined, so return it
			return id;
		}else{
			// The id is in the symbol table
			
			// Get the object bound to the id in the symbol table
			Object o = symtable2.get(id.toString());
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

	// Here we have a inAAssignStatement as in visitor.java, but we fill symtable2 instead of symtable
	// We do this because we need to know the ids that are defined at the time the methods are entered,
	// as in symtable they are all already defined, so we can't use it.
	// This is needed so that inAFunctionCall, getTypeExpression methods can work in some situations
    public void inAAssignStatement(AAssignStatement node){
		
		// Get id name
        String id_name = node.getId().toString();

		// Search for undefined ids in the righthand expression
		TId id = notDefined(node.getExpression());
		
		// If all ids are defined
        if(id==null){
			// Put the id name in the symtable2
            symtable2.put(id_name, node);
        }
    }

	// Find function calls that infer functions not defined -- we know as we have all of them in the symbol table
	//  or if they infer in non function variables in the symbol table -- ex. x = 1 ; y = x(100)
	//  or if the function is defined, but with another number of arguments
	public void inAFunctionCall(AFunctionCall node){
		
		// Get the line from the node's id
		int line = ((TId) node.getId()).getLine();
		
		// Get the function call's name
		String fname = node.getId().toString();
		
		// If the symtable doesn't contain the function name
		if(!symtable.containsKey(fname)){
			// Print error
			System.out.println("FunctionCallError :: Line " + line + " :: Function " + fname + "is not defined.");
			// Increase error count
			errors+=1;
		}else{
			// If the symtable contains the function name but it is not a function
			if(!(symtable.get(fname) instanceof AFunction)){
				// Print error
				System.out.println("FunctionCallError :: Line " + line + " :: Id " + fname + "is not a function.");
				// Increase error count
				errors+=1;
			}else{
				
				// It is a function, let's check the arguments
				
				// Initialize argument count to 0
				int argCount = 0;
				
				// Find how many arguments the function call has
				
				// If it has arguments
				if(node.getArglist().toArray().length>0){
					// It sure has one argument
					AArglist arglist = (AArglist)node.getArglist().toArray()[0];
					// Increase the argument count by 1
					argCount += 1;
					// Add the count of the rest arguments
					argCount += arglist.getMoreExp().toArray().length;					
				}
				
				// Get the object from the symtable and cast it to a function
				AFunction func = (AFunction)symtable.get(fname);
				
				// Initialize the symtable function's arguments and the default value index to 0
				int argCountF = 0;
				int def_index = 0;
				
				// If it has arguments
				if(func.getArgument().toArray().length>0){
					// It sure has one argument
					AArgument arg = (AArgument)func.getArgument().toArray()[0];
					// Increase the argument count by 1
					argCountF += 1;
					
					// Check if it has default value and if it does set the default value index to 1
					if(arg.getArgSetValue().toArray().length>0) def_index = 1;
					
					// Add the count of the rest arguments
					Object[] argflist = arg.getMoreArgs().toArray();
					argCountF += argflist.length;
					
					// Find in which index the first default value is
					// IMPORTANT : When a default value has been placed in python, all the rest must be defaults
					// Initialize an index to 0
					int idx = 0;
					// For every object in the symtable's rest argument list
					for(Object o : argflist){
						// Increase the index by 1 
						idx += 1;
						// Cast the object to AMoreArgs
						AMoreArgs oarg = (AMoreArgs)o;
						// If it has a default value
						if(oarg.getArgSetValue().toArray().length>0) {
							// Set the default value index to idx
							def_index = idx;
							// We found the first default value so break the loop
							break;
						}
					}
					// Check if the node and the symtable function have different argument count
					if(argCount < argCountF - def_index || argCount > argCountF){
						// Print error
						System.out.println("FunctionCallError :: Line " + line + " :: Function " + fname + "does not have "+ argCount +" arguments.");
						// Increase error count
						errors+=1;
					}
					
					
				}else{
					// If it doesnt have arguments
					if(argCount!=0){
						// Print error
						System.out.println("FunctionCallError :: Line " + line + " :: Function " + fname + "does not have "+ argCount +" arguments.");
						// Increase error count
						errors+=1;
					}

				}
			}

            // Put the local variables of the function called by the function call in the symtable2, so that they can be used in the getTypeExpression function correctly
			// We need this for the type checking when calling a function call
			// ex. def foo(a,b):
			//        return a + b
			//     
			//     print 'hello' + foo(1,2)
			//
			// so that it knows that foo(1,2) returns a number and it can't be added to a string

			// Get arglist from node
            Object[] arguments = node.getArglist().toArray();

			// If it has arguments
            if(arguments.length > 0){
                // Get the first argument
                AArglist arglist = (AArglist)arguments[0];

                // Get node function's name
                fname = node.getId().toString();
				// Get object from symtable using fname
                Object objf = symtable.get(fname);
				// If object is a function
                if(objf instanceof  AFunction) {
					// Cast it to a function
                    AFunction func = (AFunction)objf;

					// If function is null end the method
                    if (func == null) return;

                    // Find all argument ids
					
					// Create a list of ids
                    List<TId> arg_ids = new ArrayList<>();
                    // If the symtable function has arguments
                    if (func.getArgument().toArray().length > 0) {
						// Get the first argument
                        AArgument arg = (AArgument) func.getArgument().toArray()[0];

                        // Add the first argument to the list
                        arg_ids.add(arg.getId());

                        // Check for the rest arguments
                        Object[] argflist = arg.getMoreArgs().toArray();
						
                        // Add the rest arguments to the list
                        for (Object o : argflist) {
                            AMoreArgs oarg = (AMoreArgs) o;
                            TId tid = oarg.getId();
                            arg_ids.add(tid);
                        }
                    }

                    // Set an index for the arguments and initialize it to 0
                    int idx = 0;

                    // Get the first expression
                    PExpression exp = (PExpression) arglist.getExpression();

                    // Values are put with a 'f' post fix (for function) -- they are replaced if already exist
					// We use symtable2 instead of symtable, because we want to put the local variables until the line that the method is run
					//  and symtable has all the ids as it is filled in visitor.java, so that way we prevent a mixup of the variables

					// Put the first argument (using a postfix 'f' to indicate it is a local variable) with it's expression in the symtable2 and increase the index by 1
                    symtable2.put(arg_ids.get(idx++).toString() + "f", exp);

                    // For the rest expressions

					// Get them as an array of objects
                    Object[] moreExp = arglist.getMoreExp().toArray();

					// For each object in the array
                    for (Object o : moreExp) {
						// Cast it to AMoreExp and get it's expression
                        PExpression e = ((AMoreExp) o).getExpression();
						// If the index doesn't exceed the argument ids list size
                        if (idx + 1 <= arg_ids.size()) {
							// Put the argument(using a postfix 'f' to indicate it is a local variable) with it's expression in the symtable2 and increase the index by 1
                            symtable2.put(arg_ids.get(idx++).toString() + "f", e);
                        }

                    }

                }

            }

		}		
		
	}

	// Get the value type from a function call
	// We need this for the type checking when calling a function call
	// ex. def foo(a,b):
	//        return a + b
	//     
	//     print 'hello' + foo(1,2)
	//
	// so that it knows that foo(1,2) returns a number and it can't be added to a string
	public void outAFuncCallExpression(AFuncCallExpression node){

		// Get function call from node
		AFunctionCall fc = (AFunctionCall)node.getFunctionCall();
	
		// Get the function's name
		String fname = fc.getId().toString();

		// If the function's name is in the symtable and the object is a function
		if(symtable.get(fname) != null && symtable.get(fname) instanceof AFunction){

			// Get and cast the object to a function
			AFunction func = (AFunction)symtable.get(fname);

			// Get the function's statement
			PStatement statement = func.getStatement();

			// If the statement is a return statement
			if(statement instanceof AReturnStatement){

				// Cast it
				AReturnStatement rstate = (AReturnStatement)statement;

				// Get the statement's expression
				PExpression exp = rstate.getExpression();
				
				// Get the expression's type
				PValue type = (PValue) getTypeExpression(exp);

				// If the type isn't null
				if(type!=null){
					// Add the node to the out hashtable with the expression's type
					setOut(node, type);
				}

			}

		}

	}
	
	// This method is needed when a functioncall is called not in an expression for type checking
	//  for example in Example 10 in minipythonexample_ours.py
	public void outAFunctionCall(AFunctionCall node){
	
		// If the node's parent is a AFuncCallExpression then it will go to the above method, so end the method to prevent duplicate messages
		if(node.parent() instanceof AFuncCallExpression) return;
	
		// Get the function's name
		String fname = node.getId().toString();

		// If the function's name is in the symtable and the object is a function
		if(symtable.get(fname) != null && symtable.get(fname) instanceof AFunction){

			// Get and cast the object to a function
			AFunction func = (AFunction)symtable.get(fname);

			// Get the function's statement
			PStatement statement = func.getStatement();

			// If the statement is a return statement
			if(statement instanceof AReturnStatement){

				// Cast it
				AReturnStatement rstate = (AReturnStatement)statement;

				// Get the statement's expression
				PExpression exp = rstate.getExpression();
				
				// Get the expression's type
				PValue type = (PValue) getTypeExpression(exp);

				// If the type isn't null
				if(type!=null){
					// Add the node to the out hashtable with the expression's type
					setOut(node, type);
				}

			}

		}

	}
	
	// In order to some examples working, like Test 5 in minipythonexample_given.py:
	// def add(x,y):
	//	return "hello world"
	//   print add(2,1)+2
	// where we want the return value type of a function call, we need the out type checks in visitor2 instead of visitor
	// (this is because the function may be defined after the function call, which shouldn't be an error)
	
	// Out -- used for type checking
	// Whenever we are getting from symtable we want to know the ids that have been assigned throughout the file
	// and whenever we are getting from symtable2 we want to know only the ids until the line the running method is at
	
	public void outANumberValue(ANumberValue node){
		// Add the node to the out hashtable as a ANumberValue object
		setOut(node, new ANumberValue());
	}
	
	public void outAStringLitValue(AStringLitValue node){
		// Add the node to the out hashtable as a AStringLitValue object
		setOut(node, new AStringLitValue());
	}
	
	public void outAFuncCallValue(AFuncCallValue node){
		// We can't get the value from this, as we don't have classes
		// to do something like: x.add(a) or y.execute() etc.
	}
	
	public void outAValueExpression(AValueExpression node){
		// Get the value type from the node's PValue, using the out hashtable
		PValue type = (PValue)getOut(node.getValue());
		// Add the node to the out hashtable as the previous PValue type (node's child type)
		setOut(node, type);
	}
	
	// Get the value type from an id
	public void outAIdentifierExpression(AIdentifierExpression node){
		
		// Get the id's name
		String id = node.getId().toString();
		
		// If it is assigned get it's type
		if(symtable2.containsKey(id) && symtable2.get(id) instanceof AAssignStatement){
			// Get the assignment statement from the symtable
			AAssignStatement statement = (AAssignStatement)symtable2.get(id);
			// Get the value type from the statement's expression, using the out hashtable
			PValue type = (PValue)getOut(statement.getExpression());
			// Add the node to the out hashtable as the PValue type from the expression
			setOut(node, type);
		}
		
	}
	
	public void outAListExpression(AListExpression node){
		// Do we suppose that a list has specific type of values?
		// If yes then the list has a specific type eg. numbers list
		// Else it has its unique list type
		
		// Here we suppose the second one, so we add the node to the out hashtable as a AListExpression object
		setOut(node, new AListExpression());
	}
	
	public void outALElementExpression(ALElementExpression node){
		// Get the id from the node
		String id = node.getId().toString();
		
		// Get the element requested number : 0 is the first element
		// We can't find which number unless we put it in the AST Tree
		// If we put it we can get it from the expression
		// We put 0 as default
		int el = 0;
		
		if(el<0){
			System.out.println("Error :: Array out of bounds : " + el);
			// Increase error count
			errors+=1;
			return;
		}
		
		// If the id is defined in the symbol table 2 and it is a ListExpression
		if(symtable2.containsKey(id) && symtable2.get(id) instanceof AListExpression){
			// Cast it
			AListExpression list = (AListExpression)symtable2.get(id);
			// If it is the first element of the list
			if(el == 0){
				// Get the value type from the list element
				PValue type = list.getValue();
				// Add the node to the out hashtable, as the above value type
				setOut(node, type);
			}else{
				// If it is not the first element
				// Get all the values as an array
				Object[] morevals = list.getMoreValues().toArray();
				// Get the length of the array
				int len = morevals.length;
				// If the element requested is bigger than the length of the array print error
				if(el-1 >= len){
					System.out.println("Error :: Array out of bounds : " + el);
					// Increase error count
					errors+=1;
				}
				else{
					// Else get the AMoreValues requested from the array and cast it
					AMoreValues mv = (AMoreValues)morevals[el];
					// Get the value type from it
					PValue type = (PValue)mv.getValue();
					// Add the node to the out hashtable as the above value type
					setOut(node,type);
				}
			}
		}
		
	}
	
	public void outAParExpression(AParExpression node){
		// Get the value type from the node's expression, using the out hashtable
		PValue type = (PValue) getOut(node.getExpression());
		// Add the node to the out hashtable as the expression's PValue type
		setOut(node, type);
	}
	
	// In the below we have to check if we have a list expression instead of a value
	
	public void outAAdditionExpression(AAdditionExpression node){
		
		// Here we do the type checking for adding
		
		// Get the values from E1 and E2 using the out hashtable
		PValue e1 = (PValue) getOut(node.getE1());
		PValue e2 = (PValue) getOut(node.getE2());

		// Check the cases where adding is permited and add the node to the outhashtable using the according value type
		// or print an error instead, if the operation is not permitted
		if(e1 instanceof ANumberValue && e2 instanceof AStringLitValue){
			System.out.println("Error :: Can't add "+e1.getClass().getSimpleName()+" with "+e2.getClass().getSimpleName()+".");
			// Increase error count
			errors+=1;
		}else if(e1 instanceof AStringLitValue && e2 instanceof ANumberValue){
			System.out.println("Error :: Can't add "+e1.getClass().getSimpleName()+" with "+e2.getClass().getSimpleName()+".");
			// Increase error count
			errors+=1;
		}else if(e1 instanceof ANumberValue && e2 instanceof ANumberValue){
			setOut(node, new ANumberValue());
		}else if(e1 instanceof AStringLitValue && e2 instanceof AStringLitValue){
			setOut(node, new AStringLitValue());
		}
		
	}
	
	public void outASubtractionExpression(ASubtractionExpression node){
		
		// Here we do the type checking for subtracting
		
		// Get the values from E1 and E2 using the out hashtable
		PValue e1 = (PValue) getOut(node.getE1());
		PValue e2 = (PValue) getOut(node.getE2());
		
		// Check the cases where subtracting is permited and add the node to the outhashtable using the according value type
		// or print an error instead, if the operation is not permitted
		if(e1 instanceof ANumberValue && e2 instanceof AStringLitValue){
			System.out.println("Error :: Can't subtract "+e1.getClass().getSimpleName()+" from "+e2.getClass().getSimpleName()+".");
			// Increase error count
			errors+=1;
		}else if(e1 instanceof AStringLitValue && e2 instanceof ANumberValue){
			System.out.println("Error :: Can't subtract "+e1.getClass().getSimpleName()+" from "+e2.getClass().getSimpleName()+".");
			// Increase error count
			errors+=1;
		}else if(e1 instanceof ANumberValue && e2 instanceof ANumberValue){
			setOut(node, new ANumberValue());
		}else if(e1 instanceof AStringLitValue && e2 instanceof AStringLitValue){
			System.out.println("Error :: Can't subtract "+e1.getClass().getSimpleName()+" from "+e2.getClass().getSimpleName()+".");
			// Increase error count
			errors+=1;
		}
		
	}
	
	public void outAMultiplicationExpression(AMultiplicationExpression node){
		
		// Here we do the type checking for multiplying
		
		// Get the values from E1 and E2 using the out hashtable
		PValue e1 = (PValue) getOut(node.getE1());
		PValue e2 = (PValue) getOut(node.getE2());
		
		// Check the cases where multiplying is permited and add the node to the outhashtable using the according value type
		// or print an error instead, if the operation is not permitted
		if(e1 instanceof ANumberValue && e2 instanceof AStringLitValue){
			setOut(node, new AStringLitValue());
		}else if(e1 instanceof AStringLitValue && e2 instanceof ANumberValue){
			setOut(node, new AStringLitValue());
		}else if(e1 instanceof ANumberValue && e2 instanceof ANumberValue){
			setOut(node, new ANumberValue());
		}else if(e1 instanceof AStringLitValue && e2 instanceof AStringLitValue){
			System.out.println("Error :: Can't multiply "+e1.getClass().getSimpleName()+" with "+e2.getClass().getSimpleName()+".");
			// Increase error count
			errors+=1;
		}
		
	}
	
	public void outADivisionExpression(ADivisionExpression node){
		
		// Here we do the type checking for dividing
		
		// Get the values from E1 and E2 using the out hashtable
		PValue e1 = (PValue) getOut(node.getE1());
		PValue e2 = (PValue) getOut(node.getE2());
		
		// Check the cases where dividing is permited and add the node to the outhashtable using the according value type
		// or print an error instead, if the operation is not permitted
		if(e1 instanceof ANumberValue && e2 instanceof AStringLitValue){
			System.out.println("Error :: Can't divide "+e1.getClass().getSimpleName()+" with "+e2.getClass().getSimpleName()+".");
			// Increase error count
			errors+=1;
		}else if(e1 instanceof AStringLitValue && e2 instanceof ANumberValue){
			System.out.println("Error :: Can't divide "+e1.getClass().getSimpleName()+" with "+e2.getClass().getSimpleName()+".");
			// Increase error count
			errors+=1;
		}else if(e1 instanceof ANumberValue && e2 instanceof ANumberValue){
			setOut(node, new ANumberValue());
		}else if(e1 instanceof AStringLitValue && e2 instanceof AStringLitValue){
			System.out.println("Error :: Can't divide "+e1.getClass().getSimpleName()+" with "+e2.getClass().getSimpleName()+".");
			// Increase error count
			errors+=1;
		}
		
	}
	
	public void outAPowExpression(APowExpression node){
		
		// Here we do the type checking for the power
		
		// Get the values from E1 and E2 using the out hashtable
		PValue e1 = (PValue) getOut(node.getE1());
		PValue e2 = (PValue) getOut(node.getE2());
		
		// Check the cases where the power operation is permited and add the node to the outhashtable using the according value type
		// or print an error instead, if the operation is not permitted
		if(e1 instanceof ANumberValue && e2 instanceof AStringLitValue){
			System.out.println("Error :: Can't set a power of a " + e1.getClass().getSimpleName() +" to a " + e2.getClass().getSimpleName()+".");
			// Increase error count
			errors+=1;
		}else if(e1 instanceof AStringLitValue && e2 instanceof ANumberValue){
			System.out.println("Error :: Can't set a power of a " + e1.getClass().getSimpleName() +" to a " + e2.getClass().getSimpleName()+".");
			// Increase error count
			errors+=1;
		}else if(e1 instanceof ANumberValue && e2 instanceof ANumberValue){
			setOut(node, new ANumberValue());
		}else if(e1 instanceof AStringLitValue && e2 instanceof AStringLitValue){
			System.out.println("Error :: Can't set a power of a " + e1.getClass().getSimpleName() +" to a " + e2.getClass().getSimpleName()+".");
			// Increase error count
			errors+=1;
		}
		
	}

	
	
	

}
