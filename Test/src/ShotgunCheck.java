

import com.puppycrawl.tools.checkstyle.api.AbstractCheck;
import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;

public class ShotgunCheck extends AbstractCheck
{
  private static final int DEFAULT_MAX = 3;
  private int max = DEFAULT_MAX;
  
  @Override
  public int[] getDefaultTokens()
  {
    return new int[]{TokenTypes.CLASS_DEF, TokenTypes.INTERFACE_DEF};
  }

  public void setMax(int limit)
  {
    this.max = limit;
  }
  
  
  @Override
  public void visitToken(DetailAST ast)
  {
    // find the OBJBLOCK node below the CLASS_DEF/INTERFACE_DEF
    DetailAST objBlock = ast.findFirstToken(TokenTypes.OBJBLOCK);

    // count the number of direct children of the OBJBLOCK
    // that are METHOD_CALLs
    int methodDefs = objBlock.getChildCount(TokenTypes.METHOD_CALL);
    // report violation if limit is reached
    if (methodDefs > this.max) {
      String message = "Potential Shotgun Surgery issue.";
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
