import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Lexer {

    public static int line = 1;
    private char peek = ' ';
    
    private void readch(BufferedReader br) {
        try {
            peek = (char) br.read();
        } catch (IOException exc) {
            peek = (char) -1; // ERROR
        }
    }

    public Token lexical_scan(BufferedReader br) {
        while (peek == ' ' || peek == '\t' || peek == '\n'  || peek == '\r') {
            if (peek == '\n') line++;
            readch(br);
        }
        
        switch (peek) {
            case '!':
                peek = ' ';
                return Token.not;

    // ... gestire i casi di (, ), {, }, +, -, *, /, =, ; ... //
            case '(':
                peek = ' ';
                return Token.lpt;

            case ')':
            peek = ' ';
            return Token.rpt;

            case '{':
            peek = ' ';
            return Token.lpg;

            case '}':
            peek = ' ';
            return Token.rpg;

            case '+':
            peek = ' ';
            return Token.plus;

            case '-':
            peek = ' ';
            return Token.minus;

            case '*':
            peek = ' ';
            return Token.mult;

            case '/':
            peek = ' ';
            return Token.div;

            case ';':
            peek = ' ';
            return Token.semicolon;

            case '&':
                readch(br);
                if (peek == '&') {
                    peek = ' ';
                    return Word.and;
                } else {
                    System.err.println("Erroneous character"
                            + " after & : "  + peek );
                    return null;
                }

    // ... gestire i casi di ||, <, >, <=, >=, ==, <> ... //
            case '|':
            readch(br);
            if(peek == '|'){
                peek = ' ';
                return Word.or;
            }
            else{
                System.err.println("Error");
                return null;
            }

            case '<':
            readch(br);
            if(peek == '='){
                peek = ' ';
                return Word.le;
            }
            else if(peek == '>'){
                peek = ' ';
                return Word.ne;
            }
            else{
                peek = ' ';
                return Word.lt;
            }

            case '>':
            readch(br);
            if(peek == '='){
                peek = ' ';
                return Word.ge;
            }
            else{
                peek = ' ';
                return Word.gt;
            }

            case '=':
            readch(br);
            if(peek == '='){
                peek = ' ';
                return Word.eq;
            }
            else{
                peek = ' ';
                return Token.assign;
            }
          
            case (char)-1:
                return new Token(Tag.EOF);

            default:
                if (Character.isLetter(peek)) {
                    String parsed = "";
                    while(peek>='a' && peek<='z'){
                        parsed = parsed + peek;
                        peek = ' ';
                    }
                    if(parsed == "while"){
                        return Word.whiletok;
                    }
                    else if(parsed == "then"){
                        return Word.then;
                    }
                    else if(parsed == "else"){
                        return Word.elsetok;
                    }
                    else if(parsed == "do"){
                        return Word.dotok;
                    }
                    else if(parsed == "print"){
                        return Word.print;
                    }
                    else if(parsed == "read"){
                        return Word.print;
                    }
                    else if(parsed == "when"){
                        return Word.when;
                    }
                } else if (Character.isDigit(peek)) {
                    String number = "";
                    while(peek>='0' && peek<='9' || peek == '.' || peek == ','){
                        number = number + peek;
                        peek = ' ';
                    }
                    System.out.println("parsed  " + number);
                    return T

                } else {
                        System.err.println("Erroneous character: " 
                                + peek );
                        return null;
                }
         }
    }
		
    public static void main(String[] args) {
        Lexer lex = new Lexer();
        String path = "./try"; // il percorso del file da leggere
        try {
            BufferedReader br = new BufferedReader(new FileReader(path));
            Token tok;
            do {
                tok = lex.lexical_scan(br);
                System.out.println("Scan: " + tok);
            } while (tok.tag != Tag.EOF);
            br.close();
        } catch (IOException e) {e.printStackTrace();}    
    }

}