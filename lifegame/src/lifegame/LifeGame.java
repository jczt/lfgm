package lifegame;

import javax.swing.JFrame;

class LifeGame extends JFrame {

  LifeGame() {
    this.setSize(750, 650);
    this.setTitle("…˙√¸”Œœ∑");
    WorldMap worldmap = new WorldMap();
    this.add(worldmap);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setLocationRelativeTo(null);
    this.setResizable(false);
  }

  public static void main(String[] args) {
    LifeGame game = new LifeGame();
    game.setVisible(true);
  }

}
