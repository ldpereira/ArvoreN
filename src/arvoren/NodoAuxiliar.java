package arvoren;

public class NodoAuxiliar {

    private int posicao; // posicao no arquivo
    private NodoArvoreB r;

    public NodoAuxiliar(int p, NodoArvoreB r) {
        posicao = p;
        this.r = r;
    }

    public int getPosicao() {
        return posicao;
    }

    public void setPosicao(int posicao) {
        this.posicao = posicao;
    }

    public NodoArvoreB getR() {
        return r;
    }

    public void setR(NodoArvoreB r) {
        this.r = r;
    }
}
