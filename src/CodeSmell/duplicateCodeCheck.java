package CodeSmell;

import java.util.ArrayList;
import java.util.Collections;

import com.puppycrawl.tools.checkstyle.api.AbstractCheck;
import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;

public class duplicateCodeCheck extends AbstractCheck {

	public void visitToken(DetailAST ast)
	  {
			ArrayList<String> lines = new ArrayList<String>();
		
			DetailAST classDef = ast.findFirstToken(TokenTypes.CLASS_DEF);    
		    
			int countOfChildren = classDef.getChildCount();
			
			for (int i = 0; i < countOfChildren; i++)
			{
				DetailAST sibling = classDef.getNextSibling();
				if (sibling.getType() == TokenTypes.METHOD_DEF) {
					lines.add(sibling.getText());
				}
			}
			
			boolean hasDupes = hasDupes(lines);
			if (hasDupes)
				log(1, "duplicate code found");
	  }
	
	  public void setMaxMethods(int limit)
	  {
		  maxMethods = limit;
	  } 
	  
	  public void setMaxLineNumbers(int limit)
	  {
		  maxLineNumbers = limit;
	  }

	public boolean hasDupes(ArrayList<String> lines) {

		for (String line : lines) {
			line = line.trim();
			
			if (line.length() > 0 && line != "{" && line != "}")
			{
				if (Collections.frequency(lines, line) > 1)
					return true;
			}
		}
		
		return false;
	}

	@Override
	public int[] getAcceptableTokens() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int[] getDefaultTokens() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int[] getRequiredTokens() {
		// TODO Auto-generated method stub
		return null;
	}
}
