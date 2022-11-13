package functions;

import java.util.Arrays;

public class Functions {
    private String str;

    public Functions(String str) {
        this.str = str;
    }

    public char defineArg(){
        char[] c = str.toCharArray();
        int i = 0;
        while(c[i] == ' '){
            i++;
        }
        return c[i];
    }

    public double calculate(double arg) {
        Parser parser = new Parser();
        char[] c = str.toCharArray();
        int i = 0;
        while(c[i] != '='){
            i++;
        }
        char[] c1 = Arrays.copyOfRange(c, i + 1, c.length);
        return parser.calculate(new String(c1), arg);
    }

}
