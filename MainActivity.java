class MainActivity
{
    public static void main(String[] args)
    {
        String aturan = "@A C\n1 A B # b.\n1 A C # c.\n1 B B b b.\n1 C C c c.";
        String input = "11";

        MesinPDA mesin = new MesinPDA();

        if(mesin.cekGrammar(aturan))
        {
            System.out.println(mesin.parseDeterministic(input));
            System.out.println(mesin.getCatatan());
        }else{
            System.out.println(mesin.errorGrammar());
        }

        if(mesin.cekGrammar(aturan))
        {
            System.out.println(mesin.parseNonDeterministic(input));
            System.out.println(mesin.getCatatan());
        }else{
            System.out.println(mesin.errorGrammar());
        }

    }
}