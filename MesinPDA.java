
import java.util.List;
import java.util.LinkedList;
import java.util.ArrayList;
import java.util.Queue;


/**
 * Created by root on 27/03/16.
 */
public class MesinPDA {
    private String state;
    private String finalState;
    private List<Aturan> aturan = new ArrayList<Aturan>();
    private Stack dStack;
    private int kolomSekarang;
    private int barisSekarang;
    private char karakterSekarang;
    private String catatan;
    private String grammarError;

    public MesinPDA(){
    }

    private boolean cHuruf(char c){
        switch(c){
            case 'a': case 'b': case 'c': case 'd': case 'e':
            case 'f': case 'g': case 'h': case 'i': case 'j':
            case 'k': case 'l': case 'm': case 'n': case 'o':
            case 'p': case 'q': case 'r': case 's': case 't':
            case 'u': case 'v': case 'w': case 'x': case 'y':
            case 'z':
            case 'A': case 'B': case 'C': case 'D': case 'E':
            case 'F': case 'G': case 'H': case 'I': case 'J':
            case 'K': case 'L': case 'M': case 'N': case 'O':
            case 'P': case 'Q': case 'R': case 'S': case 'T':
            case 'U': case 'V': case 'W': case 'X': case 'Y':
            case 'Z':
                return true;
        }
        return false;
    }

    private boolean cAngka(char c){
        switch(c){
            case '1': case '2': case '3': case '4': case '5':
            case '6': case '7': case '8': case '9': case '0':
                return true;
        }

        return false;
    }

    private boolean cOperator (char c){
        switch(c){
            case '+': case '-': case '/': case '^': case '*':
                return true;
        }

        return false;
    }

    private boolean cLainnya (char c){
        switch(c){
            case '!': case '@': case '#': case '$': case '%':
            case '&': case '(': case ')': case '=': case '_':
                return true;
        }

        return false;
    }

    public String errorGrammar(){
        return grammarError;
    }


    public boolean cekGrammar(String S){
        int len = S.length();
        char statusAwal = '-';
        char statusAkhir = 'Z';
        char status = statusAwal;
        int kolom = 1;
        int baris = 1;
        String token = "";
        finalState = "";

        String[] dGrammar = new String[5];
        int i;
        char CC = ' ';
        for(i = 0; i < len; i++){
            CC = S.charAt(i);
            kolom++;

            if(CC == '/'){
                // mungkin komentar
                if(status == ('-'))
                    status = 'K';
                else

                // mungkin komentar
                if(status == ('Z'))
                    status = 'K';
                else

                // pasti komentar
                if(status == ('K'))
                    status = 'L';
                else

                // Komentar
                if(status == ('L'))
                    status = 'L';
                else
                    break;
            }else

            if(CC == '@'){
                // @ [status awal]
                if(status == ('-'))
                    status = 'A';
                else

                // Komentar
                if(status == ('L'))
                    status = 'L';
                else
                    break;
            }else

            if(CC == '.'){
                // [stack akhir] .
                if(status == ('X')){
                    // Push stack akhir ke array
                    dGrammar[4] = token;

                    // Push data token ke array aturan

                    aturan.add(new Aturan(dGrammar[0].charAt(0), dGrammar[1], dGrammar[2], dGrammar[3].charAt(0), dGrammar[4]));

                    // reset dGrammar
                    // otomatis

                    status = 'Z';
                }else

                // Simbol masukan (char)
                if(status == ('Z')){
                    token = "" + CC;
                    status = 'P';
                }else

                // Komentar
                if(status == ('L'))
                    status = 'L';
                else
                    break;
            }else

            if(CC == ' '){
                // @ [status awal] <spasi> [status akhir]
                if(status == ('B')){
                    this.state = token;
                    status = 'C';
                }else

                // Simbol masukan (char)
                if(status == ('Z')){
                    token = "" + CC;
                    status = 'P';
                }
                else

                // [Simbol masukan] <spasi> [Status saat ini]
                if(status == ('P'))
                    status = 'Q';
                else

                // [Status saat ini] <spasi> [Status tujuan] (beerasal dari curiga komentar)
                if(status == ('K'))
                    status = 'Q';
                else

                // [Status saat ini] <spasi> [Status tujuan]
                if(status == ('R'))
                    status = 'S';
                else

                // [Status tujuan] <spasi> [top stack]
                if(status == ('T'))
                    status = 'U';
                else

                // [top stack] <spasi> [stack akhir]
                if(status == ('V'))
                    status = 'W';
                else

                // [top stack] . <spasi*> \n <spasi*>
                if(status == ('Z'))
                    status = 'Z';
                else

                // Komentar
                if(status == ('L'))
                    status = 'L';
                else
                    break;
            }else

            if(cHuruf(CC) || cAngka(CC) || cLainnya(CC) || cOperator(CC)){
                // Status awal (char)
                if(status == ('A')){
                    token = "" + CC;
                    status = 'B';
                }else

                // (masih) status awal (string)
                if(status == ('B')){
                    token += CC;
                    status = 'B';
                }else

                // Status akhir (char)
                if(status == ('C')){
                    token = "" + CC;
                    status = 'D';
                }else

                // Status akhir (string)
                if(status == ('D')){
                    token += CC;
                    status = 'D';
                }else

                // Simbol masukan (char)
                if(status == ('Z')){
                    token = "" + CC;
                    status = 'P';
                }else

                // Status saat ini (char)
                if(status == ('Q')){
                    // Push simbol masukan ke array
                    dGrammar[0] = token;

                    token = "" + CC;
                    status = 'R';
                }else

                // (masih) status saat ini (string)
                if(status == ('R')){
                    token += CC;
                    status = 'R';
                }else

                // Status tujuan (char)
                if(status == ('S')){
                    // Push status saat ini ke array
                    dGrammar[1] = token;

                    token = "" + CC;
                    status = 'T';
                }else

                // (masih) status tujuan (string)
                if(status == ('T')){
                    token += CC;
                    status = 'T';
                }else

                // top stack (char)
                if(status == ('U')){
                    // Push status tujuan ke array
                    dGrammar[2] = token;

                    token = "" + CC;
                    status = 'V';
                }else

                // stack akhir (char)
                if(status == ('W')){
                    // Push top stack ke array
                    dGrammar[3] = token;

                    token = "" + CC;
                    status = 'X';
                }else

                // (masih) stack akhir (string)
                if(status == ('X')){
                    token += CC;
                    status = 'X';
                }else

                // Komentar
                if(status == ('L'))
                    status = 'L';
                else
                    break;
            }else

            if(CC == '\n'){
                kolom = 1;
                baris++;
                // @ [status awal] \n
                if(status == ('B')){
                    this.state = token;
                    status = 'Z';
                }else

                // @ [status awal] [status akhir] \n
                if(status == ('D')){
                    this.finalState = token;
                    status = 'Z';
                }else

                // [top stack] . \n
                if(status == ('Z'))
                    status = 'Z';
                else

                // Komentar selesai
                if(status == ('L'))
                    status = 'Z';
                else
                    break;
            }else{
                grammarError = "[Bahasa] Susunan bahasa salah, pada karakter (" + baris + ", " + kolom + ") '" + CC + "'";
                return false;
            }
        }// end for

        if(i == len && status == ('Z')){
            grammarError = "[Bahasa] Berhasil";
            return true;
        }

        grammarError = "[Bahasa] Susunan bahasa salah, pada karakter (" + baris + ", " + kolom + ") '" + CC + "'";
        return false;
    }

    public String parseDeterministic(String ekspresi){
        int i = -1;
        int len = ekspresi.length();
        dStack = new Stack(len + 1);
        dStack.push('#');
        kolomSekarang = 0;      //terdefinisi mulai dari 1
        barisSekarang = 1;      //terdefinisi mulai dari 1
        karakterSekarang = ' ';
        catatan = "--log--\n\n";

        boolean ilegal = true;
        boolean gagal = true;
        while(++i < len){
            char CC = ekspresi.charAt(i);

            if(CC == '\n'){
                CC = ' ';
                barisSekarang++;
                kolomSekarang = 0;
            }
            else if(CC == '\t'){
                CC = ' ';
            }

            kolomSekarang++;
            karakterSekarang = CC;

            catatan += "CC : " + CC;

            ilegal = true;
            gagal = true;
            int pjgAturan = aturan.size();
            for(int k = 0; k < pjgAturan; k++){

                if(CC == aturan.get(k).getInput()){

                    ilegal = false;
                    if(state.equals(aturan.get(k).getState()) && dStack.getTop() == aturan.get(k).getTopStack()){

                        gagal = false;
                        if(aturan.get(k).getPush().charAt(0) == '-'){
                            dStack.pop();
                        }else{
                            dStack.pop();
                            String temp = aturan.get(k).getPush();
                            int tempLen = temp.length();
                            for(int m = 0; m < tempLen; m++){
                                dStack.push(temp.charAt(m));
                            }
                        }

                        catatan += " | Status : " + state;
                        state = aturan.get(k).getTo();
                        catatan += " -> " + state;

                        break;
                    }
                }
            }

            catatan += " | Stack : " + dStack.ambilStack() + "\n";

            if(gagal){
                dStack.push('!');
                break;
            }

            if(ilegal){
                break;
            }
        }

        if(dStack.getTop() != '#' && finalState.isEmpty()){     //belum mencapai final state dan stack tidak kosong
            gagal = true;
        }

        if(ilegal){
            return "[Kalimat] Error: Karakter (" + barisSekarang + ", " + kolomSekarang + ") '" + karakterSekarang + "' tidak dikenal";
        }else{
            if(gagal){
                return "[Kalimat] Ditolak: Gagal pada karakter (" + barisSekarang + ", " + kolomSekarang + ") '" + karakterSekarang + "'";
            }else{
                if(!finalState.isEmpty()){
                    if(state.equals(finalState)){
                        return "Kalimat diterima"+aturan.size();
                    }else{
                        return "[Kalimat] Ditolak: Gagal pada karakter (" + barisSekarang + ", " + kolomSekarang + ") '" + karakterSekarang + "'";
                    }
                }else{
                    return "Kalimat diterima"+aturan.size();
                }
            }
        }
    }

//=========================================================================================================================================================

int limit = 10;   
ArrayList<Tree> order = new ArrayList<Tree>();
Queue<Tree> epsilon_order = new LinkedList<Tree>();

    public Tree nextSolve()
    {
        if(!order.isEmpty())
        {
            return order.remove(order.size() - 1);
        }
        else if(!epsilon_order.isEmpty())
        {
            return epsilon_order.remove();
        }
        else
        {
            return null;
        }
    }


    public void generateChild(Tree t, String ekspresi)
    {
        for(int in=0; in<aturan.size(); ++in)
        {
            if((t.getLevel()+1) < ekspresi.length())
            {
                char cc = ekspresi.charAt(t.getLevel()+1);
                t.addChild(cc, aturan.get(in), ekspresi.length());


                if(aturan.get(in).getInput() == '-')
                {
                    order.add(t.getChild(in));
                }
                else
                {
                    epsilon_order.add(t.getChild(in));
                }
                
            }
        }
    }

    public int solve(Tree t)
    {
        catatan += "=================\n";
        if(t.solvedCon() == -1)
        { 
            catatan += ("Level: "+t.getLevel()+"\n");
            catatan += (t.getCC()+"\n");
            catatan += (t.getState()+"\n");
            catatan += (t.getStack().getTop()+"\n");

            catatan += (t.getAturan().getInput()+"\n");
            catatan += (t.getAturan().getState()+"\n");
            catatan += (t.getAturan().getTopStack()+"\n");
            if(t.getCC() == t.getAturan().getInput() && t.getEpsilon() < limit){
                if((t.getState().equals(t.getAturan().getState())) && (t.getStack().getTop() == t.getAturan().getTopStack())) {
                    if(t.getAturan().getPush().charAt(0) == '-'){
                        t.getStack().pop();
                    }else{
                        t.getStack().pop();
                        String temp = t.getAturan().getPush();
                        int tempLen = temp.length();
                        for(int m = 0; m < tempLen; m++){
                            t.getStack().push(temp.charAt(m));
                        }
                    }

                    //catatan += " | Status : " + state;
                    t.setState(t.getAturan().getTo());
                    //catatan += " -> " + state;
                    t.setSolved(1);
                }
                else
                {
                    t.setSolved(0);
                }
            }
            else if(t.getAturan().getInput() == '-' && t.getEpsilon() < limit) //epsilon
            {
                if((t.getState().equals(t.getAturan().getState())) && (t.getStack().getTop() == t.getAturan().getTopStack())) {
                    if(t.getAturan().getPush().charAt(0) == '-'){
                        t.getStack().pop();
                    }else{
                        t.getStack().pop();
                        String temp = t.getAturan().getPush();
                        int tempLen = temp.length();
                        for(int m = 0; m < tempLen; m++){
                            t.getStack().push(temp.charAt(m));
                        }
                    }

                    t.setState(t.getAturan().getTo());
                    t.setSolved(1);
                }
                else
                {
                    t.setSolved(0);
                }
                t.epsilonIcr();
                t.setLevel(t.getLevel()-1);
            }
            else {
                t.setSolved(0);
            }
        }
        catatan += (""+t.solvedCon()+"\n");
        return t.solvedCon();
    }

    public String parseNonDeterministic(String ekspresi){
        dStack = new Stack(100);
        dStack.push('#');
        Tree t = new Tree(dStack, ' ', null, state);
        t.setLevel(-1);

        if(t.getLevel() == ekspresi.length())
        {
            if(t.solvedCon() == 1)
            {
                return "Kalimat diterima";
            }
        }
        
        generateChild(t, ekspresi);

        if(order.isEmpty() && epsilon_order.isEmpty()) //antrian tree kosong
        {
            return "Kalimat eror"+t.getLevel();
        }

        return parseNonDeterministic(ekspresi, nextSolve());
    }

    public String parseNonDeterministic(String ekspresi, Tree t){
        solve(t);
        if((t.getLevel()+1) == ekspresi.length()) //input habis
        {
            if(t.getState().equals(finalState)) //cek final-State
            {
                return "Kalimat diterima";
            }
            else if(t.getStack().getTop() == '#')     //cek stack kosong
            {
                return "Kalimat diterima";
            }
        }
        else if(t.solvedCon() == 1)
        {
            generateChild(t, ekspresi);
        }


        if(order.isEmpty() && epsilon_order.isEmpty()) //antrian tree kosong
        {
            return "Kalimat eror "+t.getLevel();
        }

        return parseNonDeterministic(ekspresi, nextSolve());
    }






//=========================================================================================================================================================
    public String getCatatan(){
        if(catatan.isEmpty())
            return "Kosong";

        return catatan;
    }
}
