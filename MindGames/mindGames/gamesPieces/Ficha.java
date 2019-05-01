package mindGames.gamesPieces;

import mindGames.enums.ColorFicha;

public class Ficha {
	 
    private ColorFicha colorFicha;

    public Ficha(){
	
}
    public ColorFicha getColorFicha() {
        return colorFicha;
    }

    public void setColorFicha(ColorFicha colorFicha) {
        this.colorFicha = colorFicha;
    }

    public Ficha(ColorFicha colorFicha) {    
       this.colorFicha = colorFicha; 
    }   
}