import java.util.*;

class MainActivity
{
    public static void main(String[] args)
    {
        
        //String aturan = "@A B\n1 A C # b.\n1 A B # b.\n1 B B b b.\n1 C C c c.\n1 C C b c.";
        String aturan = "@V\n- V Q # #.\n0 Q Q x -.\n1 Q Q x -.\n1 Q Q x xx.\n1 Q Q # #x.\n0 Q P x x.\n0 P Q # #.\n0 P P x -.\n1 P P x -.\n0 P P # #.\n1 P P # #.";
        //String aturan = "@q\n0 q q # #.\n1 q q # #.\n0 q p # #.\n1 p q # #.\n1 q p # #.";
        //String aturan = "@A C\n- A B # #.\n- B C # #.\n1 B B # #.\n0 B B # #.";
        String input = "1011";

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