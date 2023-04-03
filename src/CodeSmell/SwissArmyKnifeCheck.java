package CodeSmell;

import com.puppycrawl.tools.checkstyle.api.AbstractCheck;
import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;

public class SwissArmyKnifeCheck extends AbstractCheck {
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
	  
	//log(1, "ast count" + ast.getChildCount());
	  
    DetailAST objCompUnit = ast.findFirstToken(TokenTypes.COMPILATION_UNIT);

    //Check interface count
    if (objCompUnit != null)
    {
	    //Check interface count
	    int interfaceDefCount = objCompUnit.getChildCount(TokenTypes.INTERFACE_DEF);
	    if (interfaceDefCount > this.maxInterfaces) {
	    	 String message = "SwissArmyKnife issue. Too many interfaces: " + interfaceDefCount + " found, " + this.maxInterfaces + " interfaces are allowed.";
	         log(ast.getLineNo(), message);
	    }     
    }
    
    //find the OBJBLOCK node below the CLASS_DEF/INTERFACE_DEF
    //method count
    DetailAST objBlock = ast.findFirstToken(TokenTypes.OBJBLOCK);
    // count the number of direct children of the OBJBLOCK
    // that are METHOD_DEFS
    if (objBlock != null)
    {
	    int methodDefCount = objBlock.getChildCount(TokenTypes.METHOD_DEF);
	
	    // report violation if limit is reached
	    if (methodDefCount > this.maxMethods ){
	      String message = "SwissArmyKnife issue. " + this.maxMethods + " methods are allowed";
	      log(ast.getLineNo(), message);
	    }
    
    
	    //check number of lines
	    int lineNumbers = objBlock.getLineNo();
	      
	    // report violation if limit is reached
	    String message = checkViolationOfMaxLines(lineNumbers, this.maxLines);
	    log(ast.getLineNo(), message);
    }
    else
    	log(ast.getLineNo(), "objblock is null");
    
    
  }

  public String checkViolationOfMaxLines(int lineNumbers, int maxLines) {
	  String message="";
	  
		if (lineNumbers > maxLines) {
		   message = "SwissArmyKnife issue. " + maxLines + " lines are allowed";
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
 }