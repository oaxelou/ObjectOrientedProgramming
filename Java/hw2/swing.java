import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

// Etoimos kwdikas gia to menu kai to background me thn eikona
// apo: MenuDemo.java, MenuLookDemo.java

public class swing extends JFrame implements ActionListener {
  public static final int WIDTH = 400;
  public static final int HEIGHT = 250;

  PPMImage ppmCurrImg = null;
  YUVImage yuvCurrImg = null;

  private JPanel ImgPanel;
  private JLabel ImgLabel;

  private JMenu FileMenu, SaveMenu, OpenMenu;
  private JMenu ActionsMenu, StackingAlg;

  private JMenuItem PPMFileOpen, YUVFileOpen, PPMFileSave, YUVFileSave;
  private JMenuItem Grayscale, IncreaseSize, DecreaseSize, RotClockWise;
  private JMenuItem EqualHist, SelectDir;

  public static void main(String args[]) {
    swing gui = new swing();
    gui.setVisible(true);
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
    SaveMenu.add(PPMFileSave);

    YUVFileSave = new JMenuItem("YUV File");
    YUVFileSave.addActionListener(this);
    SaveMenu.add(YUVFileSave);
  }

  public void createActionsMenu(){
    ActionsMenu = new JMenu("Actions");

    Grayscale = new JMenuItem("Grayscale");
    Grayscale.addActionListener(this);
    ActionsMenu.add(Grayscale);

    IncreaseSize = new JMenuItem("Increase Size");
    IncreaseSize.addActionListener(this);
    ActionsMenu.add(IncreaseSize);

    DecreaseSize = new JMenuItem("Decrease Size");
    DecreaseSize.addActionListener(this);
    ActionsMenu.add(DecreaseSize);

    RotClockWise = new JMenuItem("Rotate Clockwise");
    RotClockWise.addActionListener(this);
    ActionsMenu.add(RotClockWise);

    EqualHist = new JMenuItem("Equalize Histogram");
    EqualHist.addActionListener(this);
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

    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    setJMenuBar(createJMenuBar());
    pack();
  }

  public void actionPerformed(ActionEvent e) {
    String menuString = e.getActionCommand();

    if(e.getSource().equals(PPMFileOpen) ) {
      System.out.println("PPMFileOpen pressed!");
    }else if(e.getSource().equals(YUVFileOpen)){
      System.out.println("Pressed YUVFileOpen!");
    }else if(e.getSource().equals(PPMFileSave)){
      System.out.println("Pressed PPMFileSave!");
    }else if(e.getSource().equals(YUVFileSave)){
      System.out.println("Pressed YUVFileSave!");
    }else if(e.getSource().equals(Grayscale)){
      System.out.println("Pressed Grayscale!");
    }else if(e.getSource().equals(IncreaseSize)){
      System.out.println("Pressed Increase Size!");
    }else if(e.getSource().equals(DecreaseSize)){
      System.out.println("Pressed Decrease Size!");
    }else if(e.getSource().equals(RotClockWise)){
      System.out.println("Pressed Rotate Clockwise!");
    }else if(e.getSource().equals(EqualHist)){
      System.out.println("Pressed equlaize histogram!");
    }else if(e.getSource().equals(SelectDir)){
      System.out.println("Pressed select directory!");
    }else{
      System.out.println("None of the above. Something is wrong!");
    }
  }
}
