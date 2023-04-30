package CodeSmell;

import java.util.ArrayList;
import java.util.Collections;

import com.puppycrawl.tools.checkstyle.api.AbstractCheck;
import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;

public class DuplicateCodeCheck extends AbstractCheck {

	private static final int DEFAULT_MAX_DUPLICATELINES = 1;
	private int max = DEFAULT_MAX_DUPLICATELINES;

	@Override
	public int[] getDefaultTokens() {
		return new int[] { TokenTypes.CLASS_DEF, TokenTypes.INTERFACE_DEF };
	}

	public void setMax(int limit) {
		max = limit;
	}

	@Override
	public void visitToken(DetailAST ast) {
		ArrayList<String> lines = new ArrayList<String>();

		// find the OBJBLOCK node below the CLASS_DEF/INTERFACE_DEF
	    DetailAST objBlock = ast.findFirstToken(TokenTypes.OBJBLOCK);

	    // count the number of direct children of the OBJBLOCK that are METHOD_DEFS
	    int countOfChildren = objBlock.getChildCount(TokenTypes.METHOD_DEF);
	    
		for (int i = 0; i < countOfChildren; i++) {
			DetailAST sibling = objBlock.findFirstToken(i);
			if (sibling == null)
				continue;
			if (sibling.getType() == TokenTypes.METHOD_DEF) {
				lines.add(sibling.getText());
			}
		}

		boolean hasDupes = hasDupes(lines);
		if (hasDupes)
			logMe(1, "duplicate code found: " + lines.size());
		else
			logMe(1, "No duplicate code found: " + lines.size());
	}

	public boolean hasDupes(ArrayList<String> lines) {

		if (lines.size() > 0)
		{
			for (String line : lines) {
				line = line.trim();
	
				if (line.length() > 0 && line != "{" && line != "}") {
					// TODO: grab trees of diff methods?
					if (Collections.frequency(lines, line) > this.max)
						return true;
				}
			}
		}

		return false;
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
