package arvoren;

import java.io.IOException;
import java.io.RandomAccessFile;

public class Arquivo {

    private String nomeArquivo;
    private long tamanhoArquivo;

    RandomAccessFile arvore;

    public Arquivo(String nomeArquivo) throws IOException {
        this.nomeArquivo = nomeArquivo;
        arvore = new RandomAccessFile(this.nomeArquivo, "rw");
        this.tamanhoArquivo = arvore.length();
        if (tamanhoArquivo == 0) {
            System.out.println("Arquivo vazio\n");
        } else {

            System.out.println("Tamanho arquivo = " + tamanhoArquivo);
        }
    }
//	public boolean abreArquivo(String nomeArquivo) throws IOException
//	{
//	
//	}

    public NodoAuxiliar leitura(int i, int ordem) throws IOException {// i eh o numero do nodos
        int tamanho = ordem * 2 * 2 * 4 + 16;
        int p = i * tamanho;
        NodoArvoreB nodoB = new NodoArvoreB(ordem * 2);
        NodoAuxiliar nAux = new NodoAuxiliar(i, nodoB);
        arvore.seek(p);
        int nCap = arvore.readInt();
        nodoB.setCapacidade(nCap);
        for (int j = 0; j < nCap + 1; j++) {
            nodoB.setFilhoI(j, arvore.readInt());
            System.out.println(" \n " + nodoB.getFilhoI(j));
        }
        for (int j = 0; j < nCap; j++) {
            nodoB.setChaveI(j, arvore.readInt());
            System.out.println(" \n " + nodoB.getChaveI(j));
        }
        nodoB.setNChaves(arvore.readInt());
        nodoB.setNSubArvores(arvore.readInt());

        return nAux;
    }

    public void gravaArquivo(NodoAuxiliar nodo, int ordem) throws IOException {
        int tamanho = ordem * 2 * 2 * 4 + 16;
        int p = nodo.getPosicao() * tamanho;
        NodoArvoreB nodoArvoreB = nodo.getR();
        arvore.seek(p);
        arvore.writeInt(nodoArvoreB.getCapacidade());
        for (int i = 0; i < nodoArvoreB.getCapacidade() + 1; i++) // numero de filho = capacidade + 1
        {
            arvore.writeInt(nodoArvoreB.getFilhoI(i));
        }

        for (int i = 0; i < nodoArvoreB.getCapacidade(); i++) {
            arvore.writeInt(nodoArvoreB.getChaveI(i));
        }

        arvore.writeInt(nodoArvoreB.getnChaves());
        arvore.writeInt(nodoArvoreB.getnSubArvores());
    }

}
