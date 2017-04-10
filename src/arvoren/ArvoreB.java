package arvoren;

import java.io.IOException;

public class ArvoreB {

    private int raiz;
    private Arquivo arquivoArvore;
    private int ordem;
    private int posicao;

    public void setRaiz(int r) {
        this.raiz = r;
    }

    public ArvoreB(int ordem, String nome) throws IOException {
        arquivoArvore = new Arquivo(nome);
        raiz = -1;        
        this.ordem = ordem;
    }

    private NodoAuxiliar localizaNodoR(int r, int chave) throws IOException {
        if (r > -1) {//  lendo o nodo
            NodoAuxiliar nAux = arquivoArvore.leitura(r, ordem);
            NodoArvoreB nodoB = nAux.getR();
            if (nodoB.getnSubArvores() == 0) {// nao tem filhos 
                return nAux;
            } else {
                for (int i = nodoB.getnChaves() - 1; i >= 0; i--) {
                    if (chave >= nodoB.getChaveI(i)) { // maior que a chave i esta no i+1
                        return localizaNodoR(nodoB.getFilhoI(i + 1), chave);
                    }
                }
                return localizaNodoR(nodoB.getFilhoI(0), chave);
            }
        }
        return null;
    }

    private NodoAuxiliar leNodo(int i) throws IOException {
        return arquivoArvore.leitura(i, ordem);
    }

    public boolean insereArvoreB(int chave) throws IOException {
        if (raiz == -1) {
            System.out.println("Pasou\n");
            NodoArvoreB nodo = new NodoArvoreB(ordem * 2);
            nodo.insereChave(chave);
            NodoAuxiliar nAux = new NodoAuxiliar(0, nodo);
            arquivoArvore.gravaArquivo(nAux, ordem);
            raiz = 0;
            return true;
        } else {
            if (pesquisaArvoreB(chave) == null) {
                System.out.println("nao existe");
                //  lendo nodo raiz  ??
                NodoAuxiliar nAux = localizaNodoR(raiz, chave);
                NodoArvoreB nodo = nAux.getR();
                if (nodo.getnChaves() == ordem * 2) { // tem que particionar
                    posicao++;
                    NodoArvoreB nodoEsquerdaNew = new NodoArvoreB(ordem *2);
                    NodoArvoreB nodoDireitaNew = new NodoArvoreB(ordem *2);
                    
                    int chave0 = nodo.getChaveI(0);
                    int chave1 = nodo.getChaveI(1);
                    int chaveQuebra = nodo.getChaveI(2);
                    int chave3 = nodo.getChaveI(3);
                    int chave4 = nodo.getChaveI(4);
                    
                    nodoEsquerdaNew.insereChave(chave0);
                    nodoEsquerdaNew.insereChave(chave1);
                    
                    nodoDireitaNew.insereChave(chave3);
                    nodoDireitaNew.insereChave(chave4);
                    NodoAuxiliar nAuxEsquerda = new NodoAuxiliar(posicao, nodoEsquerdaNew);
                    NodoAuxiliar nAuxDireita = new NodoAuxiliar(posicao, nodoDireitaNew);
                    arquivoArvore.gravaArquivo(nAuxEsquerda, ordem);
                    arquivoArvore.gravaArquivo(nAuxDireita, ordem);
                    raiz = 0;
                    
                    nodo.setNChaves(1);
                    nodo.setChaveI(0, chaveQuebra);
                    nodo.setFilhoI(0, chave0);
                    nodo.setFilhoI(1, chave1);
                    nodo.setFilhoI(2, chave3);
                    nodo.setFilhoI(3, chave4);
                    
                    arquivoArvore.gravaArquivo(nAux, ordem);
                    
                    //????????  gerar um novo array com a nova chave
                    // a posic�o de nAux fica a raiz e gera dois novos
                    
                    System.out.println("tem que particionar");
                } else {
                    if (nodo.insereChave(chave)) {
                        arquivoArvore.gravaArquivo(nAux, ordem);
                        System.out.println("Inseriu\n");
                        return true;
                    } else {
                        System.out.println("Nao inseriu\n");
                        return false;
                    }
                }

            } else {
                System.out.println("ja existe");
            }
        }
        return false;
    }

    public NodoArvoreB pesquisaArvoreB(int chave) throws IOException {
        NodoArvoreB r = pesquisaArvoreBR(raiz, chave);
        return r;
    }

    private NodoArvoreB pesquisaArvoreBR(int r, int chave) throws IOException {
        if (r > -1) {
            NodoAuxiliar nAux = arquivoArvore.leitura(r, 3);
            NodoArvoreB nodo = nAux.getR();
            for (int i = 0; i < nodo.getnChaves(); i++) {
                if (chave == nodo.getChaveI(i)) {
                    return nodo;
                } else {
                    if (chave < nodo.getChaveI(i)) // verificar se tem filhos.
                    {
                        if (nodo.getnSubArvores() > 0) {
                            return pesquisaArvoreBR(nodo.getFilhoI(i), chave);
                        } else {
                            return null;
                        }
                    }
                }
            }
            if (nodo.getnSubArvores() == 0) {
                return null;
            } else {
                return pesquisaArvoreBR(nodo.getFilhoI(nodo.getnChaves() + 1), chave);
            }
        } else {
            return null;
        }
    }

    public void imprime(NodoArvoreB r) {
        if (r != null) {
            System.out.println("Chaves ");
            for (int i = 0; i < r.getCapacidade(); i++) {
                System.out.print(r.getChaveI(i));
                System.out.print(",");
            }
            System.out.println("\nValores ");
            for (int i = 0; i < r.getnSubArvores(); i++) {
                System.out.print(r.getFilhoI(i));
                System.out.print(",");
            }
        }
    }

    public static void main(String[] args) throws IOException {
        ArvoreB a = new ArvoreB(2, "c:\\ArvoreNova.txt");
        a.insereArvoreB(65);
        a.insereArvoreB(95);
        a.insereArvoreB(68);
        a.insereArvoreB(97);
        a.insereArvoreB(66);
        a.insereArvoreB(110);
        a.insereArvoreB(111);
        
        a.imprime(a.leNodo(0).getR());

    }
}
