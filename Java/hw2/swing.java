import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;
import javax.swing.filechooser.*;


// Etoimos kwdikas gia to menu kai to background me thn eikona
// apo: MenuDemo.java, MenuLookDemo.java

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

  public void getPPMfromFile(File f){
    try{
      ppmCurrImg = new PPMImage(f);
      System.out.println("New ppm: " + ppmCurrImg.pixels.length + " x " + ppmCurrImg.pixels[0].length);
      // edw na kaloume kapoia sunarthsh pou na allazei thn eikona tou background
      yuvCurrImg = new YUVImage(ppmCurrImg);

    }catch(FileNotFoundException ex){
      System.err.println("File not found exception");
      System.exit(1);
    }catch(UnsupportedFileFormatException ex){
      System.err.println("Not ppm file");
      System.exit(1);
    }
  }

  public void getYUVfromFile(File f){
    try{
      yuvCurrImg = new YUVImage(f);
      ppmCurrImg = new PPMImage(yuvCurrImg);
      System.out.println("New yuv: " + yuvCurrImg.pixels.length + " x " + yuvCurrImg.pixels[0].length);
      // edw na kaloume kapoia sunarthsh pou na allazei thn eikona tou background
    }catch(FileNotFoundException ex){
      System.err.println("File not found exception");
      System.exit(1);
    }catch(UnsupportedFileFormatException ex){
      System.err.println("Not ppm file");
      System.exit(1);
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

  public swing() {
    super("ce325 - hw2: Image Processing");
    setSize(WIDTH, HEIGHT);

    fc = new JFileChooser(System.getProperty("."));
    FileNameExtensionFilter filter = new FileNameExtensionFilter("Image Files", "ppm", "yuv");
    fc.setFileFilter(filter);

    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    setJMenuBar(createJMenuBar());
    ImgLabel = new JLabel();
    pack();
  }

  public void actionPerformed(ActionEvent e) {
    String menuString = e.getActionCommand();

    if(e.getSource().equals(PPMFileOpen) ) {
      System.out.println("PPMFileOpen pressed!");
      FileNameExtensionFilter filter = new FileNameExtensionFilter("ppm Files", "ppm");
      fc.setFileFilter(filter);
      int returnVal = fc.showOpenDialog(swing.this);
      if(returnVal == JFileChooser.APPROVE_OPTION){
        //change background picture with fc.getSelectedFile()
        getPPMfromFile(fc.getSelectedFile());
        enableEditAndSave();
      } else if(returnVal == JFileChooser.CANCEL_OPTION){
        System.out.println("File chooser open dialog cancelled by user");
      } else{
        System.err.println("Error occured with file chooser!");
        System.exit(1);
      }
    }else if(e.getSource().equals(YUVFileOpen)){
      System.out.println("Pressed YUVFileOpen!");
      FileNameExtensionFilter filter = new FileNameExtensionFilter("yuv Files", "yuv");
      fc.setFileFilter(filter);
      int returnVal = fc.showOpenDialog(swing.this);
      if(returnVal == JFileChooser.APPROVE_OPTION){
        //change background picture with fc.getSelectedFile()
        getYUVfromFile(fc.getSelectedFile());
        enableEditAndSave();
      } else if(returnVal == JFileChooser.CANCEL_OPTION){
        System.out.println("File chooser open dialog cancelled by user");
      } else{
        System.err.println("Error occured with file chooser!");
        System.exit(1);
      }
    }else if(e.getSource().equals(PPMFileSave)){
      System.out.println("Pressed PPMFileSave!");

      if(ppmCurrImg != null){
        System.out.println("In save ppm!");
        FileNameExtensionFilter filter = new FileNameExtensionFilter("ppm Files", "ppm");
        fc.setFileFilter(filter);
        int returnVal = fc.showSaveDialog(swing.this);
        if(returnVal == JFileChooser.APPROVE_OPTION){
          //change background picture with fc.getSelectedFile()
          ppmCurrImg.toFile(fc.getSelectedFile());
        } else if(returnVal == JFileChooser.CANCEL_OPTION){
          System.out.println("File chooser open dialog cancelled by user");
        } else{
          System.err.println("Error occured with file chooser!");
          System.exit(1);
        }
      }
    }else if(e.getSource().equals(YUVFileSave)){
      System.out.println("Pressed YUVFileSave!");
    }else if(e.getSource().equals(Grayscale)){
      System.out.println("Pressed Grayscale!");

      if(ppmCurrImg != null){
        System.out.println("In grayscale!");
        ppmCurrImg.grayscale();
        // edw allagh tou background me thn eikona!
        yuvCurrImg = new YUVImage(ppmCurrImg);
      }
    }else if(e.getSource().equals(IncreaseSize)){
      System.out.println("Pressed Increase Size!");

      if(ppmCurrImg != null){
        System.out.println("In increase size!");
        ppmCurrImg.doublesize();
        // edw allagh tou background me thn eikona!
        yuvCurrImg = new YUVImage(ppmCurrImg);
      }
    }else if(e.getSource().equals(DecreaseSize)){
      System.out.println("Pressed Decrease Size!");

      if(ppmCurrImg != null){
        ppmCurrImg.halfsize();
        // edw allagh tou background me thn eikona!
        yuvCurrImg = new YUVImage(ppmCurrImg);
      }
    }else if(e.getSource().equals(RotClockWise)){
      System.out.println("Pressed Rotate Clockwise!");

      if(ppmCurrImg != null){
        ppmCurrImg.rotateClockwise();
        // edw allagh tou background me thn eikona!
        yuvCurrImg = new YUVImage(ppmCurrImg);
      }
    }else if(e.getSource().equals(EqualHist)){
      System.out.println("Pressed equlaize histogram!");

      if(yuvCurrImg != null){
        yuvCurrImg.equalize();
        ppmCurrImg = new PPMImage(yuvCurrImg);
        // edw allagh tou background me thn eikona!
      }
    }else if(e.getSource().equals(SelectDir)){
      System.out.println("Pressed select directory!");
      fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
      FileNameExtensionFilter filter = new FileNameExtensionFilter("Directories", "*");
      fc.setFileFilter(filter);
      int returnVal = fc.showOpenDialog(swing.this);
      if(returnVal == JFileChooser.APPROVE_OPTION){
        //change background picture with fc.getSelectedFile()
        try {
            PPMImageStacker stacker = new PPMImageStacker(fc.getSelectedFile());
            stacker.stack();
            ppmCurrImg = stacker.getStackedImage();
            // change background image;
            enableEditAndSave();
            yuvCurrImg = new YUVImage(ppmCurrImg);
          } catch(FileNotFoundException ex) {
            System.out.println("ERROR :"+ex.getMessage());
          } catch(UnsupportedFileFormatException ex) {
            System.out.println("ERROR :"+ex.getMessage());
          }
      } else if(returnVal == JFileChooser.CANCEL_OPTION){
        System.out.println("File chooser open dialog cancelled by user");
      } else{
        System.err.println("Error occured with file chooser!");
        System.exit(1);
      }
    }else{
      System.out.println("None of the above. Something is wrong!");
    }
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

    height = ppmCurrImg.getHeight();
    width  = ppmCurrImg.getWidth();

    currBufferedImg = new BufferedImage(width, height, TYPE_INT_RGB);

    for(int i=0; i < height; i++){
      for(int j=0; j < width; j++){
        currBufferedImg.setRGB(j, i, ppmCurrImg.getPixelsArray()[i][j].getRGBValue());
      }
    }

    if(ppmWasNull)
      ppmCurrImg = null;

    currIcon = new ImageIcon(currBufferedImg);

    ImgLabel.setIcon(currIcon);

    
  }



}
