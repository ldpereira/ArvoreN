package arvoren;

public class NodoArvoreB {

    private int capacidade;
    private int[] filho;
    private int[] chave;
    private int nChaves;
    private int nSubArvores;

    public NodoArvoreB(int capacidade) {
        this.capacidade = capacidade;
        filho = new int[capacidade + 1];  // contem as referencias para os filhos, que serao nodos armazenados no arquivo
        this.chave = new int[capacidade];    // contem as chaves existente no nodo
        nChaves = 0;
        nSubArvores = 0;
    }

    public boolean insereChave(int chave) {
        if (nChaves < capacidade) // ainda tem lugar no nodo
        {
            for (int i = nChaves - 1; i >= 0; i--) {
                if (chave > this.chave[i]) {
                    this.chave[i + 1] = chave;
                    nChaves++;
                    return true;
                } else {
                    this.chave[i + 1] = this.chave[i];  // eh folha, nao tem filhos !!!
                }
            }
            this.chave[0] = chave;
            nChaves++;
            return true;
        } else {
            return false;
        }
    }

    public int getnSubArvores() {

        return nSubArvores;
    }

    public boolean setFilhoI(int i, int filho) {
        if (i < 0 || i > capacidade + 1) {
            return false;
        } else {
            this.filho[i] = filho;
            return true;
        }
    }

    public int getnChaves() {
        return nChaves;
    }

    public int getFilhoI(int i) {
        if (i < 0 || i > capacidade) {
            return -1;
        } else {
            return filho[i];
        }
    }

    public int getChaveI(int i) {
        if (i < 0 || i > capacidade - 1) {
            return -1;
        } else {
            return chave[i];
        }
    }

    public boolean setChaveI(int i, int chave) {
        if (i < 0 || i >= capacidade) {
            return false;
        } else {
            this.chave[i] = chave;
            return true;
        }
    }

    public int getCapacidade() {
        return capacidade;
    }

    public void setCapacidade(int capacidade) {
        this.capacidade = capacidade;
    }

    public void setNChaves(int chaves) {
        nChaves = chaves;
    }

    public void setNSubArvores(int subArvores) {
        nSubArvores = subArvores;
    }
}
