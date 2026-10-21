package graficos;

public final class Pantalla {
    private final int ancho;
    private final int alto;

    public final int[] pixeles;

    // temporal
    private final static int LADO_SPRITE = 64;
    private final static int MASCARA_SPRITE = LADO_SPRITE-1;
    // fin temporal

    public Pantalla(final int ancho, final int alto) {
        this.ancho = ancho;
        this.alto = alto;

        pixeles = new int[ancho * alto];
    }

    public void limpiar() {
        // pinta todos los pixeles del array en negro
        for (int i = 0; i < pixeles.length; i++) {
            pixeles[i] = 0;
        }
    }

    public void mostrar(final int compensacionX, final int compensacionY) {
        for (int y = 0; y < alto; y++) {
            //dibujamos las lineas de arriba hacia abajo
            int posicionY = y + compensacionY;
            if (posicionY < 0 || posicionY >= alto) {
                // al llegar a este punto pasamos al siguiente nivel sin salirnos del tamanno del mapa
                continue;
            }

            for (int x = 0; x < ancho; x++) {
                //dibujamos las columnas de izquierda a derecha
                int posicionX = x + compensacionX;
                if (posicionX < 0 || posicionX >= ancho) {
                    continue;
                }

                // codigo para redibujar
                //temporal
                pixeles[posicionX + posicionY * ancho] = Sprite.asfalto.pixeles[(x & MASCARA_SPRITE) + (y & MASCARA_SPRITE) * LADO_SPRITE];
            }
        }
    }
}
