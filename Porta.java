public class Porta {
    int posicaoX;
    int posicaoY;
    Character chave;
    boolean chamou;

    Porta(int x, int y, char chave){
        this.posicaoX = x;
        this.posicaoY = y;
        this.chave = chave;
        this.chamou = false;
    }

    public int getPosicaoX(){
        return this.posicaoX;
    }

    public int getPosicaoY(){
        return this.posicaoY;
    }

    public char getChave(){
        return this.chave;
    }

}
