/*  Authors:    Patsianotakis Charalampos cpatsianotakis@inf.uth.gr
*               Axelou Olympia            oaxelou@inf.uth.gr
*
*   swing class:
*   Includes main method of program. Includes also every method used for swing
*   and with user communication.
*   Extends JFrame and implements ActionListener.
* 
*/

package ce325.hw2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;
import javax.swing.filechooser.*;

public class swing extends JFrame implements ActionListener {
  public static final int WIDTH = 400;
  public static final int HEIGHT = 250;

  PPMImage ppmCurrImg = null;
  YUVImage yuvCurrImg = null;

  private JPanel ImgPanel;
  private JLabel ImgLabel;
  private JFileChooser fc;

  private JMenu FileMenu, SaveMenu, OpenMenu;
  private JMenu ActionsMenu, StackingAlg;

  private JMenuItem PPMFileOpen, YUVFileOpen, PPMFileSave, YUVFileSave;
  private JMenuItem Grayscale, IncreaseSize, DecreaseSize, RotClockWise;
  private JMenuItem EqualHist, SelectDir;

  public static void main(String args[]) {
    swing gui = new swing();
    gui.setVisible(true);
  }

  public boolean getPPMfromFile(File f){
    try{
      ppmCurrImg = new PPMImage(f);
      yuvCurrImg = new YUVImage(ppmCurrImg);

      return true;
    }catch(FileNotFoundException ex){
      JOptionPane.showMessageDialog(new JFrame(), "File not found exception",
                                    "PPMImage Error", JOptionPane.ERROR_MESSAGE);
      return false;
    }catch(UnsupportedFileFormatException ex){
      JOptionPane.showMessageDialog(new JFrame(), "Not ppm file",
                                    "PPMImage Error", JOptionPane.ERROR_MESSAGE);
      return false;
    }
  }

  public boolean getYUVfromFile(File f){
    try{
      yuvCurrImg = new YUVImage(f);
      ppmCurrImg = new PPMImage(yuvCurrImg);
      return true;
    }catch(FileNotFoundException ex){
      JOptionPane.showMessageDialog(new JFrame(), "File not found exception",
                                    "YUVImage Error", JOptionPane.ERROR_MESSAGE);
      return false;
    }catch(UnsupportedFileFormatException ex){
      return false;
    }
  }

  public void enableEditAndSave(){
    PPMFileSave.setEnabled(true);
    YUVFileSave.setEnabled(true);

    Grayscale.setEnabled(true);
    IncreaseSize.setEnabled(true);
    DecreaseSize.setEnabled(true);
    RotClockWise.setEnabled(true);
    EqualHist.setEnabled(true);
  }

  public void createFileMenu(){
    FileMenu = new JMenu("File");

    OpenMenu = new JMenu("Open");
    FileMenu.add(OpenMenu);

    PPMFileOpen = new JMenuItem("PPM File");
    PPMFileOpen.addActionListener(this);
    OpenMenu.add(PPMFileOpen);

    YUVFileOpen = new JMenuItem("YUV File");
    YUVFileOpen.addActionListener(this);
    OpenMenu.add(YUVFileOpen);

    SaveMenu = new JMenu("Save");
    FileMenu.add(SaveMenu);

    PPMFileSave = new JMenuItem("PPM File");
    PPMFileSave.addActionListener(this);
    PPMFileSave.setEnabled(false);
    SaveMenu.add(PPMFileSave);

    YUVFileSave = new JMenuItem("YUV File");
    YUVFileSave.addActionListener(this);
    YUVFileSave.setEnabled(false);
    SaveMenu.add(YUVFileSave);
  }

  public void createActionsMenu(){
    ActionsMenu = new JMenu("Actions");

    Grayscale = new JMenuItem("Grayscale");
    Grayscale.addActionListener(this);
    Grayscale.setEnabled(false);
    ActionsMenu.add(Grayscale);

    IncreaseSize = new JMenuItem("Increase Size");
    IncreaseSize.addActionListener(this);
    IncreaseSize.setEnabled(false);
    ActionsMenu.add(IncreaseSize);

    DecreaseSize = new JMenuItem("Decrease Size");
    DecreaseSize.addActionListener(this);
    DecreaseSize.setEnabled(false);
    ActionsMenu.add(DecreaseSize);

    RotClockWise = new JMenuItem("Rotate Clockwise");
    RotClockWise.addActionListener(this);
    RotClockWise.setEnabled(false);
    ActionsMenu.add(RotClockWise);

    EqualHist = new JMenuItem("Equalize Histogram");
    EqualHist.addActionListener(this);
    EqualHist.setEnabled(false);
    ActionsMenu.add(EqualHist);

    StackingAlg = new JMenu("Stacking Algorithm");
    ActionsMenu.add(StackingAlg);

    SelectDir = new JMenuItem("Select directory");
    SelectDir.addActionListener(this);
    StackingAlg.add(SelectDir);
  }

  public JMenuBar createJMenuBar(){
    JMenuBar bar = new JMenuBar();
    createFileMenu();
    bar.add(FileMenu);
    createActionsMenu();
    bar.add(ActionsMenu);
    return bar;
  }

  void changeBackground(){

    BufferedImage currBufferedImg;
    Icon currIcon;

    int height, width;

    boolean ppmWasNull = false;

    if(ppmCurrImg == null){
      ppmCurrImg = new PPMImage(yuvCurrImg);
      ppmWasNull = true;
    }

    height = ppmCurrImg.pixels.length;
    width  = ppmCurrImg.pixels[0].length;

    currBufferedImg = new BufferedImage(width, height, java.awt.image.BufferedImage.TYPE_INT_RGB);

    for(int i=0; i < height; i++){
      for(int j=0; j < width; j++){
        currBufferedImg.setRGB(j, i, ppmCurrImg.getPixelsArray()[i][j].getRGBValue());
      }
    }

    if(ppmWasNull)
      ppmCurrImg = null;

    currIcon = new ImageIcon(currBufferedImg);

    setSize(width, height);
    ImgLabel.setIcon(currIcon);

    enableEditAndSave();
  }

  public swing() {
    super("ce325 - hw2: Image Processing");
    setSize(WIDTH, HEIGHT);

    // fc = new JFileChooser(System.getProperty("."));
    // fc = new JFileChooser(System.getProperty("user.home") + "/Desktop/ImageProcessing");
    // FileNameExtensionFilter filter = new FileNameExtensionFilter("Image Files", "ppm", "yuv");
    // fc.setFileFilter(filter);

    ImgLabel = new JLabel();
    ImgLabel.setSize(WIDTH*2, HEIGHT*3);
    getContentPane().add(ImgLabel, BorderLayout.CENTER);

    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    setJMenuBar(createJMenuBar());
    pack();
  }

  public void actionPerformed(ActionEvent e) {
    String menuString = e.getActionCommand();

    if(e.getSource().equals(PPMFileOpen) ) {
      fc = new JFileChooser(System.getProperty("user.home") + "/Desktop/ImageProcessing");

      FileNameExtensionFilter filter = new FileNameExtensionFilter("ppm Files", "ppm");
      fc.setFileFilter(filter);
      int returnVal = fc.showOpenDialog(swing.this);
      if(returnVal == JFileChooser.APPROVE_OPTION){
        if(getPPMfromFile(fc.getSelectedFile()) == true){
          changeBackground();
        }
      } else if(returnVal == JFileChooser.CANCEL_OPTION){
        // File chooser open dialog cancelled by user
      } else{
        JOptionPane.showMessageDialog(new JFrame(), "Un error occured with file " +
                                                    "chooser.\nPlease try again.",
                                      "File Chooser", JOptionPane.ERROR_MESSAGE);
      }
      fc = null;
    }else if(e.getSource().equals(YUVFileOpen)){
      fc = new JFileChooser(System.getProperty("user.home") + "/Desktop/ImageProcessing");
      FileNameExtensionFilter filter = new FileNameExtensionFilter("yuv Files", "yuv");
      fc.setFileFilter(filter);
      int returnVal = fc.showOpenDialog(swing.this);
      if(returnVal == JFileChooser.APPROVE_OPTION){
        if(getYUVfromFile(fc.getSelectedFile()) == true){
          changeBackground();
        }
      } else if(returnVal == JFileChooser.CANCEL_OPTION){
        // File chooser open dialog cancelled by user
      } else{
        JOptionPane.showMessageDialog(new JFrame(), "Un error occured with file " +
                                                    "chooser.\nPlease try again.",
                                      "File Chooser", JOptionPane.ERROR_MESSAGE);
      }
      fc = null;
    }else if(e.getSource().equals(PPMFileSave)){
      if(ppmCurrImg != null){
        fc = new JFileChooser(System.getProperty("user.home") + "/Desktop/ImageProcessing");
        FileNameExtensionFilter filter = new FileNameExtensionFilter("ppm Files", "ppm");
        fc.setFileFilter(filter);
        int returnVal = fc.showSaveDialog(swing.this);
        if(returnVal == JFileChooser.APPROVE_OPTION){
          ppmCurrImg.toFile(fc.getSelectedFile());
        } else if(returnVal == JFileChooser.CANCEL_OPTION){
          // File chooser open dialog cancelled by user
        } else{
          JOptionPane.showMessageDialog(new JFrame(), "Un error occured with file " +
                                                      "chooser.\nPlease try again.",
                                        "File Chooser", JOptionPane.ERROR_MESSAGE);
        }
      }
      fc = null;
    }else if(e.getSource().equals(YUVFileSave)){
      if(yuvCurrImg != null){
        fc = new JFileChooser(System.getProperty("user.home") + "/Desktop/ImageProcessing");
        FileNameExtensionFilter filter = new FileNameExtensionFilter("yuv Files", "yuv");
        fc.setFileFilter(filter);
        int returnVal = fc.showSaveDialog(swing.this);
        if(returnVal == JFileChooser.APPROVE_OPTION){
          yuvCurrImg.toFile(fc.getSelectedFile());
        } else if(returnVal == JFileChooser.CANCEL_OPTION){
          // File chooser open dialog cancelled by user
        } else{
          JOptionPane.showMessageDialog(new JFrame(), "Un error occured with file " +
                                                      "chooser.\nPlease try again.",
                                        "File Chooser", JOptionPane.ERROR_MESSAGE);
        }
      }
      fc = null;
    }else if(e.getSource().equals(Grayscale)){
      if(ppmCurrImg != null){
        ppmCurrImg.grayscale();
        changeBackground();
        yuvCurrImg = new YUVImage(ppmCurrImg);
      }
    }else if(e.getSource().equals(IncreaseSize)){
      if(ppmCurrImg != null){
        ppmCurrImg.doublesize();
        changeBackground();
        yuvCurrImg = new YUVImage(ppmCurrImg);
      }
    }else if(e.getSource().equals(DecreaseSize)){
      if(ppmCurrImg != null){
        ppmCurrImg.halfsize();
        changeBackground();
        yuvCurrImg = new YUVImage(ppmCurrImg);
      }
    }else if(e.getSource().equals(RotClockWise)){
      if(ppmCurrImg != null){
        ppmCurrImg.rotateClockwise();
        changeBackground();
        yuvCurrImg = new YUVImage(ppmCurrImg);
      }
    }else if(e.getSource().equals(EqualHist)){
      if(yuvCurrImg != null){
        yuvCurrImg.equalize();
        ppmCurrImg = new PPMImage(yuvCurrImg);
        changeBackground();
      }
    }else if(e.getSource().equals(SelectDir)){
      fc = new JFileChooser(System.getProperty("user.home") + "/Desktop/ImageProcessing");
      fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
      FileNameExtensionFilter filter = new FileNameExtensionFilter("Directories", "*");
      fc.setFileFilter(filter);
      int returnVal = fc.showOpenDialog(swing.this);
      if(returnVal == JFileChooser.APPROVE_OPTION){
        try {
            PPMImageStacker stacker = new PPMImageStacker(fc.getSelectedFile());
            stacker.stack();
            ppmCurrImg = stacker.getStackedImage();
            changeBackground();
            enableEditAndSave();
            yuvCurrImg = new YUVImage(ppmCurrImg);
          } catch(FileNotFoundException ex) {
            JOptionPane.showMessageDialog(new JFrame(), ex.getMessage(),
                                  "PPMImageStacker", JOptionPane.ERROR_MESSAGE);
          } catch(UnsupportedFileFormatException ex) {
            JOptionPane.showMessageDialog(new JFrame(), ex.getMessage(),
                                  "PPMImageStacker", JOptionPane.ERROR_MESSAGE);
          }
      } else if(returnVal == JFileChooser.CANCEL_OPTION){
        // File chooser open dialog cancelled by user
      } else{
        JOptionPane.showMessageDialog(new JFrame(), "Error with file chooser",
                                      "Error", JOptionPane.ERROR_MESSAGE);
      }
      fc = null;
    }else{
      System.err.println("None of the above. Something is wrong!");
      System.exit(1);
    }
  }
}
