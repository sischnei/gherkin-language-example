package org.example;

import com.intellij.lexer.FlexLexer;
import com.intellij.psi.tree.IElementType;

import static com.intellij.psi.TokenType.BAD_CHARACTER;
import static com.intellij.psi.TokenType.WHITE_SPACE;
import static org.example.psi.CompositeStepsTypes.*;

%%

%class CompositeStepsLexer
%implements FlexLexer
%unicode
%function advance
%type IElementType
%eof{  return;
%eof}

NEWLINES=[\r\n]+
SPACES=[ ]+
WHITE_SPACE=\s+
COMPOSITE_KEYWORD="Composite:"
GIVEN_KEYWORD="Given"
WHEN_KEYWORD="When"
THEN_KEYWORD="Then"
AND_KEYWORD="And"
GWT_KEYWORDS_TOKEN={GIVEN_KEYWORD}|{WHEN_KEYWORD}|{THEN_KEYWORD}
GWTA_KEYWORDS_TOKEN={GWT_KEYWORDS_TOKEN}|{AND_KEYWORD}
WORD=[\S]+

%state COMPOSITE_DEFINITON
%state COMPOSED_DEFINITION
%state RETURN_VALUE_ASSIGNMENT_DEFINITON
%%
<YYINITIAL> {
  {WHITE_SPACE}                        { yybegin(YYINITIAL); return WHITE_SPACE; }
  {COMPOSITE_KEYWORD}                  { yybegin(YYINITIAL); return COMPOSITE_KEYWORD; }
  {GWT_KEYWORDS_TOKEN}                 { yybegin(COMPOSITE_DEFINITON); return GWT_KEYWORDS_TOKEN; }
  {WORD}                               { yybegin(YYINITIAL); return WORD; }
}

 <COMPOSITE_DEFINITON> {
  {SPACES}                              { yybegin(COMPOSITE_DEFINITON); return WHITE_SPACE; }
  {NEWLINES}                            { yybegin(COMPOSITE_DEFINITON); return NEWLINES; }
  {GWTA_KEYWORDS_TOKEN}                { yybegin(COMPOSED_DEFINITION); return GWTA_KEYWORDS_TOKEN; }
  {COMPOSITE_KEYWORD}                  { yybegin(YYINITIAL); return COMPOSITE_KEYWORD; }
  {WORD}                               { yybegin(COMPOSITE_DEFINITON); return WORD; }
 }

 <COMPOSED_DEFINITION> {
  {SPACES}                              { yybegin(COMPOSED_DEFINITION); return WHITE_SPACE; }
  {NEWLINES}                            { yybegin(COMPOSED_DEFINITION); return NEWLINES; }
  {GWTA_KEYWORDS_TOKEN}                { yybegin(COMPOSED_DEFINITION); return GWTA_KEYWORDS_TOKEN; }
  {COMPOSITE_KEYWORD}                  { yybegin(YYINITIAL); return COMPOSITE_KEYWORD; }
  {WORD}                               { yybegin(COMPOSED_DEFINITION); return WORD; }
 }


[^] { return BAD_CHARACTER; }
