package CodeSmell;

import com.puppycrawl.tools.checkstyle.api.AbstractCheck;
import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;

public class MethodLimitCheck extends AbstractCheck
{
  private static final int DEFAULT_MAX_METHODS = 30;
  private static final int DEFAULT_MAX_IMPORTS = 20;
  private int max = DEFAULT_MAX_METHODS;
  private int maxImports = DEFAULT_MAX_IMPORTS;
  

  @Override
  public int[] getDefaultTokens()
  {
    return new int[]{TokenTypes.CLASS_DEF, TokenTypes.INTERFACE_DEF};
  }

  public void setMaxMethods(int limit)
  {
    max = limit;
  }
  
  public void setMaxImports(int limit)
  {
    maxImports = limit;
  }
  
    
  
  @Override
  public void visitToken(DetailAST ast)
  {
    // find the OBJBLOCK node below the CLASS_DEF/INTERFACE_DEF
    DetailAST objBlock = ast.findFirstToken(TokenTypes.OBJBLOCK);

    // count the number of direct children of the OBJBLOCK
    // that are METHOD_DEFS
    int methodDefs = objBlock.getChildCount(TokenTypes.METHOD_DEF);
    int imports = objBlock.getChildCount(TokenTypes.IMPORT);
    // report violation if limit is reached
    if (methodDefs > this.max && imports > this.maxImports) {
      String message = "potential Blob class issue. " + this.max + " classes are allowed and "+this.maxImports+" imports are allowed";
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
