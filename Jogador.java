public class Jogador {
    int posicaoX;
    int posicaoY;
    int casinhasAndadas;

    Jogador(int x, int y){
        this.posicaoX = x;
        this.posicaoY = y;
        this.casinhasAndadas = 0;
    }

    public int getPosicaoX(){
        return this.posicaoX;
    }

    public int getPosicaoY(){
        return this.posicaoY;
    }

    public void setCasinhasAndadas(int count){
        this.casinhasAndadas = count;
    }
}
