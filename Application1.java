import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;


public class Application1 {

    static int totalCasinhas = 0;
    static ArrayList<Jogador> jogadores = new ArrayList();
    static ArrayList<Character> chaves = new ArrayList();
    static ArrayList<Jogador> posicoesAndadas = new ArrayList();
    static Hashtable<Character,Porta> portasHash = new Hashtable<>();
    static SeparateChainingHashST<Character,Porta> portasHash2 = new SeparateChainingHashST<>();

    static ArrayList<Porta> portas = new ArrayList();

    public static void main(String[] args) throws FileNotFoundException, IOException {
        Path path = Path.of("casos-cohen/caso052.txt");
        boolean exists = Files.exists(path);
        if(exists){
            URI uri = path.toUri();
            File file = new File(uri);
            readFile(file);
        }else{
            System.out.println("Erro");
        }
        
    }


    public static void readFile(File strFile) throws FileNotFoundException, IOException{
       
            //Temos de trabalhar para descobrir a largura da matriz, agora coloquei a altura como largura.
           
            Posicao[][] matriz = carregaMatriz(strFile);
            for (Jogador jogador : jogadores) {
                ArrayList<Porta> portasJogador = new ArrayList();
                verificaPontinho(matriz, jogador.getPosicaoX(), jogador.getPosicaoY());
                boolean aux = true;
                while(aux){
                    aux = false;
                    for(Porta porta: portas){
                        if(verificaPorta(matriz, porta)){
                            if(!porta.chamou){
                                System.out.println("Encontrou a porta: " + porta.chave);
                                for(Character chave: chaves){
                                    if(chave.equals(porta.chave)){
                                        porta.chamou = true;
                                        verificaPontinho(matriz, porta.posicaoX, porta.posicaoY);
                                        portasJogador.add(porta);
                                        aux = true;
                                    }
                        
                            }
                        }
                        }
                    }
                }
                

                // for (Character character : chaves) {
                //     System.out.print(chaves.size());
                //     for(Porta porta : portasHash.values()){
                //         if(portasHash.containsKey(character)){
                //             //System.out.println(portasHash.get(character));
                //             //System.out.println("Encontrou a chave: "+ character + " para a porta: " + portasHash.get(character).chave);
                //             matriz[portasHash.get(character).posicaoX][portasHash.get(character).posicaoY].setMarcado(false);
                //             verificaPontinho(matriz, portasHash.get(character).posicaoX, portasHash.get(character).posicaoY);
                //         }
                //     }
                // }

                System.out.println("Jogador: " + matriz[jogador.posicaoX][jogador.posicaoY].getCaracter() 
                + "; Total de Casinhas: " + totalCasinhas);

                //Seta as posições caminhadas para não visitadas(falso) e as portas abertas para true; 
                for(Jogador posicao : posicoesAndadas){
                    if(matriz[posicao.getPosicaoX()][posicao.getPosicaoY()].caracter>=65 
                    && matriz[posicao.getPosicaoX()][posicao.getPosicaoY()].caracter<=90){
                       // matriz[posicao.getPosicaoX()][posicao.getPosicaoY()].setMarcado(true);
                    }else{
                        matriz[posicao.posicaoX][posicao.posicaoY].setMarcado(false);
                    }
                    
                }   

                for(Porta porta : portas){
                    porta.chamou = false;
                }

                System.out.print("Portas: ");
                for(Porta porta: portasJogador){
                    System.out.print(porta.chave + ",");
                }
                System.out.print("\nChaves: ");
                for (Character character : chaves) {
                    System.out.print(character + ",");
                }
                System.out.println();
                //System.out.println(chave);
                //Limpa o histórico do jogador
                totalCasinhas = 0;
                chaves.clear();
                posicoesAndadas.clear();
                //portas.clear();
                //portasHash.clear();

            }

            //System.out.println("Total de Portas: " + portas.size());
            //System.out.println("Total de Chaves: " + posicoesAndadas.size());
        
    }

    public static void verificaPontinho(Posicao[][] matriz, int i , int j) {
        
        //if(matriz[i][j].caracter>=65 && matriz[i][j].caracter<=90){
        //    portasHash.put(matriz[i][j].caracter, new Porta(i,j,matriz[i][j].caracter));
        //    portas.add(new Porta(i,j,matriz[i][j].caracter));xs
        //}

        if(!matriz[i][j].getMarcado() && matriz[i][j].getNavegavel()){
            if(matriz[i][j].caracter>=97 && matriz[i][j].caracter<=122){
                int caracterValue = matriz[i][j].caracter - 32;
                chaves.add((char)caracterValue);
                //System.out.println((char)caracterValue);
            }

            totalCasinhas++;
            matriz[i][j].setMarcado(true);
            posicoesAndadas.add(new Jogador(i,j));
            
            if(i<matriz.length-1){
                verificaPontinho(matriz,i+1,j);
            }
            if(i>0){
                verificaPontinho(matriz,i-1,j);
            }
            if(j<matriz[i].length){
                verificaPontinho(matriz,i,j+1);
            }
            if(j>0){
                verificaPontinho(matriz,i,j-1);
            }

        }
    }

    public static boolean verificaPorta(Posicao[][] matriz, Porta porta){
        
        if(matriz[porta.posicaoX][porta.posicaoY+1].getMarcado() || matriz[porta.posicaoX][porta.posicaoY-1].getMarcado()
        || matriz[porta.posicaoX+1][porta.posicaoY].getMarcado() || matriz[porta.posicaoX-1][porta.posicaoY].getMarcado()){
            return true;
        }
        return false;
        
    }

    public static Posicao[][] carregaMatriz(File strFile) throws FileNotFoundException, IOException{
        try(BufferedReader buffRead = new BufferedReader((new FileReader(strFile)))){
            //Temos de trabalhar para descobrir a largura da matriz, agora coloquei a altura como largura.
 
            int linha = 0;
            int coluna = 65;
            Posicao[][] matriz = new Posicao[coluna][coluna];

            while(buffRead.ready()) {

                String line = buffRead.readLine();
                
                for(int i = 0; i<line.length();i++){
                    if(line.charAt(i)=='#'){
                        matriz[linha][i] = new Posicao(line.charAt(i),false,false);
                    }else if(line.charAt(i)>=48 && line.charAt(i)<=57){
                        //jogador
                        jogadores.add(new Jogador(linha,i));
                        matriz[linha][i] = new Posicao(line.charAt(i),false,true);
                    }else if(line.charAt(i)>=65 && line.charAt(i)<=90){
                        //porta
                        matriz[linha][i] = new Posicao(line.charAt(i),true,true);
                        portas.add(new Porta(linha,i,line.charAt(i)));
                    }else{
                        matriz[linha][i] = new Posicao(line.charAt(i),false,true);
                    }
                    //System.out.print(matriz[linha][i].getCaracter());
                }
                linha++;
                //System.out.println();
                
            }
            
            return matriz;
        }
    }
}

