import java.io.*;

public class ParserPrefix {
    private Lexer lex;
    private BufferedReader pbr;
    private Token look;

    public ParserPrefix(Lexer l, BufferedReader br) {
        lex = l;
        pbr = br;
        move();
    }

    void move() {
        look = lex.lexical_scan(pbr);
        System.out.println("token = " + look);
    }

    void error(String s) {
	throw new Error("near line " + lex.line + ": " + s);
    }

    void match(int t) {
	if (look.tag == t) {
	    if (look.tag != Tag.EOF) move();
	} else error("syntax error");
    }

    public void start() {
    // ... completare ...
    stat();
	match(Tag.EOF);
	// ... completare ...
    }

    //sono tutti da completare ,ricordati che tutti gli operatori sono in notazione prefissa
    public void statlist(){
        
    }

    public void statlistp(){

    }

    public void stat(){

    }

    public void statp(){

    }

    public void elseopt(){

    }

    public void bexpr(){

    }


    public void bexrprp(){

    }


    public void exprlist(){

    }


    public void exprlistp(){

    }
		
    public static void main(String[] args) {
        Lexer lex = new Lexer();
        String path = "...path..."; // il percorso del file da leggere
        try {
            BufferedReader br = new BufferedReader(new FileReader(path));
            Parser parser = new Parser(lex, br);
            parser.start();
            System.out.println("Input OK");
            br.close();
        } catch (IOException e) {e.printStackTrace();}
    }
}