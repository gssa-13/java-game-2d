package graficos;

public final class Sprite {
    private final int tamanno;

    private int x;
    private int y;

    public int[] pixeles;
    private final HojaSprites hoja;

    public Sprite(final int tamanno, final int columna, final int fila, final HojaSprites hoja) {
        this.tamanno = tamanno;
        this.hoja = hoja;
        pixeles = new int[this.tamanno * this.tamanno];

        this.x = columna * this.tamanno;
        this.y = fila * this.tamanno;

        for (int y = 0; y < this.tamanno; y++) {
            for (int x = 0; x < this.tamanno; x++) {
                pixeles[x + y * this.tamanno] = hoja.pixeles[(x+this.x) + (y+this.y) * hoja.obtenerAncho()];
            }
        }
    }
}
