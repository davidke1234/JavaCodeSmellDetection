package CodeSmell;

import com.puppycrawl.tools.checkstyle.api.AbstractCheck;
import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;

public class SwissArmyKnifeCheck extends AbstractCheck {
  private static final int DEFAULT_LINE_NUMBERS = 50;
  private static final int DEFAULT_MAX_METHODS = 5;
    
  private int maxMethods = DEFAULT_MAX_METHODS;
  private int maxLineNumbers = DEFAULT_LINE_NUMBERS;

  @Override
  public int[] getDefaultTokens() {
    return new int[]{TokenTypes.CLASS_DEF, TokenTypes.INTERFACE_DEF};
  }

  public void setMaxMethods(int limit){
	  maxMethods = limit;
  } 
  
  public void setMaxLineNumbers(int limit){
	  maxLineNumbers = limit;
  }
  
  @Override
  public void visitToken(DetailAST ast){
    // find the OBJBLOCK node below the CLASS_DEF/INTERFACE_DEF
    DetailAST objBlock = ast.findFirstToken(TokenTypes.OBJBLOCK);

    // count the number of direct children of the OBJBLOCK
    // that are METHOD_DEFS
    int methodDefCount = objBlock.getChildCount(TokenTypes.METHOD_DEF);

    // report violation if limit is reached
    if (methodDefCount > this.maxMethods ){
      String message = "SwissArmyKnife issue. " + this.maxMethods + " methods are allowed";
      log(ast.getLineNo(), message);
    }
    
    int lineNumbers = objBlock.getLineNo();
      
    // report violation if limit is reached
    if (lineNumbers > this.maxLineNumbers) {
      String message = "SwissArmyKnife issue. " + this.maxLineNumbers + " lines are allowed";
      log(ast.getLineNo(), message);
    } 
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