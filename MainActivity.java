import java.util.*;

class MainActivity
{
    public static void main(String[] args)
    {
        String aturan = "@A B\n1 A C # b.\n1 A B # b.\n1 B B b b.\n1 C C c c.\n1 C C b c.";
        String input = "1111";

        MesinPDA mesin = new MesinPDA();
        Scanner in = new Scanner(System.in); 
        int opsi = in.nextInt();

        switch(opsi)
        {
            case 1:
            {
                if(mesin.cekGrammar(aturan))
                {
                    System.out.println(mesin.parseDeterministic(input));
                    System.out.println(mesin.getCatatan());
                }else{
                    System.out.println(mesin.errorGrammar());
                }
                break;
            }
            case 2:
            {
                if(mesin.cekGrammar(aturan))
                {
                    System.out.println(mesin.parseNonDeterministic(input));
                    System.out.println(mesin.getCatatan());
                }else{
                    System.out.println(mesin.errorGrammar());
                }
                break;
            }
        }
    }
}