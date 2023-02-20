package CodeSmell;

import java.util.ArrayList;
import java.util.Collections;

import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;

public class duplicateCodeCheck {

	public void GetDuplicateCode(DetailAST ast)
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
	  }

	private boolean hasDupes(ArrayList<String> lines) {

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



}
