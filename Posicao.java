public class Posicao {

    Character caracter;
    boolean marcado;
    boolean navegavel;
    
    Posicao(char caracter, boolean marcado, boolean navegavel){
        this.caracter = caracter;
        this.marcado = marcado;
        this.navegavel = navegavel;
    }

    public void setMarcado(boolean bool){
        this.marcado = bool;
    }

    public boolean getMarcado(){
        return this.marcado;
    }

    public char getCaracter(){
        return this.caracter;
    }
    public boolean getNavegavel(){
        return this.navegavel;
    }


}
