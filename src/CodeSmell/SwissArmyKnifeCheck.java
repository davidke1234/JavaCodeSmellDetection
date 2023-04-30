package CodeSmell;

import java.io.File;
import java.util.SortedSet;

import com.puppycrawl.tools.checkstyle.api.AbstractCheck;
import com.puppycrawl.tools.checkstyle.api.CheckstyleException;
import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.FileSetCheck;
import com.puppycrawl.tools.checkstyle.api.FileText;
import com.puppycrawl.tools.checkstyle.api.MessageDispatcher;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;
import com.puppycrawl.tools.checkstyle.api.Violation;

public class SwissArmyKnifeCheck extends AbstractCheck implements FileSetCheck {
  private static final int DEFAULT_LINE_NUMBERS = 50;
  private static final int DEFAULT_MAX_METHODS = 5;
  private static final int DEFAULT_MAX_INTERFACES = 1;
    
  private int maxMethods = DEFAULT_MAX_METHODS;
  private int maxLines = DEFAULT_LINE_NUMBERS;
  private int maxInterfaces = DEFAULT_MAX_INTERFACES;

  @Override
  public int[] getDefaultTokens() {
    return new int[]{TokenTypes.CLASS_DEF, TokenTypes.INTERFACE_DEF};
  }
  
  public void setMaxMethods(int limit){
	maxMethods = limit;
  } 
  
  public void setMaxInterfaces(int limit){
	  maxInterfaces = limit;
  }
  
  public void setMaxLines(int limit){
	  maxLines = limit;
  }
  
  @Override
  public void visitToken(DetailAST ast){
	//seems to be called 1 time per method
    
	//check cyclomatic complexity
	//Done via config.xml
	    
    DetailAST objCompUnit = ast.findFirstToken(TokenTypes.COMPILATION_UNIT);

    //Check interface count
    if (objCompUnit != null)
    {
	    //Check interface count
	    int interfaceDefCount = objCompUnit.getChildCount(TokenTypes.INTERFACE_DEF);
	    if (interfaceDefCount > this.maxInterfaces) {
	    	 String message = "SwissArmyKnife issue. Too many interfaces: " + interfaceDefCount + " found, " + this.maxInterfaces + " interfaces are allowed.";
	    	 logMe(ast.getLineNo(), message);
	    }     
    }
    
    //find the OBJBLOCK node below the CLASS_DEF/INTERFACE_DEF
    //method count
    DetailAST objBlock = ast.findFirstToken(TokenTypes.OBJBLOCK);
    // count the number of direct children of the OBJBLOCK that are METHOD_DEFS
    if (objBlock != null)
    {
	    int methodDefCount = objBlock.getChildCount(TokenTypes.METHOD_DEF);
	
	    // report violation if limit is reached
	    if (methodDefCount > this.maxMethods ){
	      String message = "SwissArmyKnife issue.  Found " + methodDefCount + " methods, " + this.maxMethods + " methods are allowed";
	      logMe(ast.getLineNo(), message);
	    }  
    
	    //check number of lines
	    int lineNumbers = objBlock.getLineNo();
	      
	    // report violation if limit is reached
	    String message = checkViolationOfMaxLines(lineNumbers, this.maxLines);
	    logMe(ast.getLineNo(), message);
    }
    else
    	logMe(ast.getLineNo(), "The value of objblock is null");    
  }

  public String logMe(int logNo, String logItem) {
	  String message = "";
	  
	  if (logNo <= 0)
		  message = "Param logNo is invalid";
	  if (logItem.length() == 0)
		  message = "Param logItem is invalid";
	  
	  if (message.length() == 0)
	  {
		  try {
			  log(logNo, logItem); 
	      }
	      catch (Exception ex) {
	    	  //For unit tests
	    	  message="Failed to log: " + logNo + ", " + logItem + ", ";
	    	  System.out.print(message + ex.getMessage());
	      }
	  }
	  
	  return message;
  }
  
  public String checkViolationOfMaxLines(int lineNumbers, int maxLines) {
	  String message="";
	  
		if (lineNumbers > maxLines) {
			//message = "SwissArmyKnife issue. " + maxLines + " lines are allowed";
			message = "SwissArmyKnife issue.  Found " + lineNumbers + " lines, " + maxLines + " lines are allowed";
		} 
		else
		{
		   message = "No SwissArmyKnife issues with line numbers.";
		}
		
		return message;
    } 

	@Override
	public int[] getAcceptableTokens() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public int[] getRequiredTokens() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void beginProcessing(String arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void finishProcessing() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public SortedSet<Violation> process(File arg0, FileText arg1) throws CheckstyleException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setMessageDispatcher(MessageDispatcher arg0) {
		// TODO Auto-generated method stub
		
	}
 }