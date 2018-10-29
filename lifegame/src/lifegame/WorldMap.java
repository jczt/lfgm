package lifegame;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;
import javax.swing.Timer;

public class WorldMap extends JPanel {
  private static final long serialVersionUID = 1L;

  private static final int width = 50;

  private static final int height = 50;

  private static final char World_Map_Dead = 'D';

  private static final char World_Map_Live = 'L';


  private final char[][] world =
      {{'D', 'D', 'D', 'D', 'D', 'D', 'D', 'D', 'D', 'D', 'D', 'D', 'D', 'D'},
          {'D', 'D', 'D', 'L', 'D', 'D', 'D', 'D', 'D', 'D', 'D', 'D', 'D', 'D'},
          {'D', 'D', 'D', 'L', 'D', 'D', 'D', 'D', 'D', 'D', 'D', 'D', 'D', 'D'},
          {'D', 'D', 'D', 'L', 'D', 'D', 'D', 'D', 'D', 'D', 'D', 'D', 'D', 'D'},
          {'D', 'D', 'D', 'L', 'D', 'D', 'D', 'D', 'D', 'D', 'D', 'D', 'D', 'D'},
          {'D', 'D', 'D', 'L', 'D', 'D', 'D', 'D', 'D', 'D', 'D', 'D', 'D', 'D'},
          {'D', 'D', 'D', 'L', 'D', 'D', 'D', 'D', 'D', 'D', 'D', 'D', 'D', 'D'},
          {'D', 'D', 'D', 'L', 'D', 'D', 'D', 'D', 'D', 'D', 'D', 'D', 'D', 'D'},
          {'D', 'D', 'D', 'L', 'D', 'D', 'D', 'D', 'D', 'D', 'D', 'D', 'D', 'D'},
          {'D', 'D', 'D', 'D', 'D', 'D', 'D', 'D', 'D', 'D', 'D', 'D', 'D', 'D'},
          {'D', 'D', 'D', 'D', 'D', 'D', 'D', 'D', 'D', 'D', 'D', 'D', 'D', 'D'},
          {'D', 'D', 'D', 'D', 'D', 'D', 'D', 'D', 'D', 'D', 'D', 'D', 'D', 'D'},};

  public char[][] nextStatus = new char[world.length][world[0].length];

  public char[][] tempStatus = new char[world.length][world[0].length];

  private Timer timer;

  private static final int DELAY_TIME = 1000;

  public WorldMap() {
    this.startAnimation();
  }

  // 画网格
  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    for (int i = 0; i < nextStatus.length; i++) {
      for (int j = 0; j < nextStatus[i].length; j++) {
        if (nextStatus[i][j] == World_Map_Live) {
          g.fillRect(j * width, i * height, width, height);
        } else {
          g.drawRect(j * width, i * height, width, height);
        }
      }
    }
  }

  private void copyWorldMap() {
    for (int row = 0; row < nextStatus.length; row++) {
      for (int col = 0; col < nextStatus[row].length; col++) {
        tempStatus[row][col] = nextStatus[row][col];
      }
    }
  }

  /**
   * change the status of cells
   */
  public void changeCellStatus() {
    for (int row = 0; row < nextStatus.length; row++) {
      for (int col = 0; col < nextStatus[row].length; col++) {

        switch (neighborsCount(row, col)) {
          case 0:
          case 1:
          case 4:
          case 5:
          case 6:
          case 7:
          case 8:
            nextStatus[row][col] = World_Map_Dead;
            break;
          case 2:
            nextStatus[row][col] = tempStatus[row][col];
            break;
          case 3:
            nextStatus[row][col] = World_Map_Live;
            break;
        }
      }
    }
    copyWorldMap();
  }

  /**
   * count the neighborhoods
   * 
   * @param row
   * @param col
   * @return count
   */
  public int neighborsCount(int row, int col) {
    int count = 0, r = 0, c = 0;

    for (r = row - 1; r <= row + 1; r++) {
      for (c = col - 1; c <= col + 1; c++) {
        if (r < 0 || r >= tempStatus.length || c < 0 || c >= tempStatus[0].length) {
          continue;
        }
        if (tempStatus[r][c] == World_Map_Live) {
          count++;
        }
      }
    }
    if (tempStatus[row][col] == World_Map_Live) {
      count--;
    }
    return count;
  }

  // 开始
  private void startAnimation() {
    for (int row = 0; row < world.length; row++) {
      for (int col = 0; col < world[0].length; col++) {
        nextStatus[row][col] = world[row][col];
        tempStatus[row][col] = world[row][col];
      }
    }
    // 创建计时器
    timer = new Timer(DELAY_TIME, new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        changeCellStatus();
        repaint();
      }
    });
    // 开启计时器
    timer.start();
  }
}
