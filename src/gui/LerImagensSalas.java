package gui;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

public class LerImagensSalas extends JPanel {
    private BufferedImage image;
    private String nomeImagem;

    public LerImagensSalas(String nomeImagem) {
    	try {                
    		image = ImageIO.read(new File("../resources/" + nomeImagem + ".png"));
    	} catch (IOException ex) {
    		System.err.println("O arquivo da imagem da sala não pode ser aberta!");
    	}
    }

    public LerImagensSalas(){
        
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        //*  Para centralizar
        Graphics2D g2d = (Graphics2D) g;
		//g2d.resize(this.getWidth(), this.getHeight());
        g2d.translate(this.getWidth() / 2, this.getHeight() / 2);
        g2d.translate(-image.getWidth(null) / 2, -image.getHeight(null) / 2);
        g2d.drawImage(image, 0, 0, null);
        //*/
        //g.drawImage(image, 0, 0, null); // see javadoc for more info on the parameters            
    }
}
