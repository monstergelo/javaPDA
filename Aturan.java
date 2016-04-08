

/**
 * Created by root on 27/03/16.
 */
public class Aturan {
    private char input;
    private String state;
    private String to;
    private char topStack;
    private String push;

    public Aturan(char input, String state, String to, char topStack, String push){
        this.input = input;
        this.state = state;
        this.to = to;
        this.topStack = topStack;
        this.push = push;
    }


    // GETTER
    public char getInput(){
        return input;
    }
    public String getState(){
        return state;
    }
    public String getTo(){
        return to;
    }
    public char getTopStack(){
        return topStack;
    }
    public String getPush(){
        return push;
    }

    // SETTER
    public void setInput(char input){
        this.input = input;
    }
    public void setState(String state){
        this.state = state;
    }
    public void setTo(String to){
        this.to = to;
    }
    public void setTopStack(char topStack){
        this.topStack = topStack;
    }
    public void setPush(String push){
        this.push = push;
    }
}
