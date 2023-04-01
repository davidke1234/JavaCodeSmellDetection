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

		//DetailAST classDef = ast.findFirstToken(TokenTypes.CLASS_DEF);
		//int countOfChildren = classDef.getChildCount();

		// find the OBJBLOCK node below the CLASS_DEF/INTERFACE_DEF
	    DetailAST objBlock = ast.findFirstToken(TokenTypes.OBJBLOCK);

	    // count the number of direct children of the OBJBLOCK
	    // that are METHOD_DEFS
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
			log(1, "duplicate code found: " + lines.size());
		else
			log(1, "No duplicate code found: " + lines.size());
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

	/*
	 * Sample
	 * https://github.com/sevntu-checkstyle/sevntu.checkstyle/tree/master/sevntu-
	 * checks/src/main/java/com/github/sevntu/checkstyle/checks/coding
	 * 
	 * @Override public void visitToken(DetailAST ast) { final int type =
	 * ast.getType(); final DetailAST parent = ast.getParent();
	 * 
	 * // shouldn't process assign in annotation pairs if (type != TokenTypes.ASSIGN
	 * || parent.getType() != TokenTypes.ANNOTATION_MEMBER_VALUE_PAIR) { final
	 * boolean surrounded = isSurrounded(ast);
	 * 
	 * // An identifier surrounded by parentheses. if (surrounded && type ==
	 * TokenTypes.IDENT) { parentToSkip = ast.getParent(); log(ast, MSG_KEY_IDENT,
	 * ast.getText()); } // A literal (numeric or string) surrounded by parentheses.
	 * else if (surrounded && inTokenList(type, LITERALS)) { parentToSkip =
	 * ast.getParent(); if (type == TokenTypes.STRING_LITERAL) { log(ast,
	 * MSG_KEY_STRING, chopString(ast.getText())); } else { log(ast,
	 * MSG_KEY_LITERAL, ast.getText()); } } // The rhs of an assignment surrounded
	 * by parentheses. else if (inTokenList(type, ASSIGNMENTS)) { assignDepth++;
	 * final DetailAST last = ast.getLastChild(); if (last.getType() ==
	 * TokenTypes.RPAREN) { final DetailAST subtree =
	 * ast.getFirstChild().getNextSibling() .getNextSibling(); final int subtreeType
	 * = subtree.getType(); if (!ignoreCalculationOfBooleanVariables ||
	 * !inTokenList( subtreeType, EQUALS)) { log(ast, MSG_KEY_ASSIGN); } } } } }
	 */
	/*
	 * Sample
	 * 
	 * @Override public void visitToken(DetailAST ast) { final DetailAST
	 * expressionAst = ast.findFirstToken(TokenTypes.EXPR);
	 * 
	 * switch (ast.getType()) { case TokenTypes.LITERAL_RETURN: if
	 * (!isEmptyReturn(ast)) { final DetailAST inversionAst =
	 * getInversion(expressionAst);
	 * 
	 * if (isAvoidableInversion(inversionAst)) { log(inversionAst); } } break;
	 * 
	 * case TokenTypes.LITERAL_WHILE: case TokenTypes.LITERAL_DO: case
	 * TokenTypes.LITERAL_IF: final DetailAST invertedAst =
	 * getInversion(expressionAst); if (isAvoidableInversion(invertedAst)) {
	 * log(invertedAst); } break;
	 * 
	 * case TokenTypes.FOR_CONDITION: if (!isEmptyForCondition(ast)) { final
	 * DetailAST inversionAst = getInversion(expressionAst);
	 * 
	 * if (isAvoidableInversion(inversionAst)) { log(inversionAst); } } break;
	 * 
	 * default: SevntuUtil.reportInvalidToken(ast.getType()); break; } }
	 */

}
