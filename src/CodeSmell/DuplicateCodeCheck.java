package CodeSmell;

import java.util.ArrayList;
import java.util.Collections;

import com.puppycrawl.tools.checkstyle.api.AbstractCheck;
import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;

public class DuplicateCodeCheck extends AbstractCheck {
	
	private static final int DEFAULT_MAX_DUPLICATELINES = 1;
	private int maxDupes = DEFAULT_MAX_DUPLICATELINES;
	  
	@Override
	public int[] getDefaultTokens() {
	  return new int[]{TokenTypes.CLASS_DEF, TokenTypes.INTERFACE_DEF};
	}

	public void setMaxDups(int limit){
		this.maxDupes = limit;
	} 
	
    @Override
	public void visitToken(DetailAST ast){
		ArrayList<String> lines = new ArrayList<String>();
	
		DetailAST classDef = ast.findFirstToken(TokenTypes.CLASS_DEF);    
	    
		int countOfChildren = classDef.getChildCount();
		
		for (int i = 0; i < countOfChildren; i++){
			DetailAST sibling = classDef.getNextSibling();
			if (sibling.getType() == TokenTypes.METHOD_DEF) {
				lines.add(sibling.getText());
			}
		}
		
		boolean hasDupes = hasDupes(lines);
		if (hasDupes)
			log(1, "duplicate code found");
	}
	
	public boolean hasDupes(ArrayList<String> lines){

		for (String line : lines) {
			line = line.trim();
			
			if (line.length() > 0 && line != "{" && line != "}")
			{
				//TODO: grab trees of diff methods?
				if (Collections.frequency(lines, line) > this.maxDupes)
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
	public int[] getRequiredTokens() {
		// TODO Auto-generated method stub
		return null;
	}


}
