import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Game extends JFrame {
    JPanel menu = new JPanel(new BorderLayout());
    int size;
    public Game(){
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension dm = toolkit.getScreenSize();
        setTitle("Pacman");
        setBounds(dm.width/2 - 600, dm.height/2 - 360, 1200, 720 );

        menu.setBackground(Color.black);

        JPanel btn = new JPanel(new GridBagLayout());
        btn.setBackground(Color.black);
        menu.add(getPacmanName(), BorderLayout.NORTH);
        add(menu);
        System.out.println(menu.getSize());
        GridBagConstraints gb = new GridBagConstraints();
        gb.fill = GridBagConstraints.HORIZONTAL;
        gb.weightx = 1;
        gb.weighty = 1;
        gb.insets = new Insets(10, 20, 10, 20);
        gb.gridx = 0;
        btn.add(getStartButton());
        gb.gridx = 1;
        btn.add(getHighScoreButton());
        gb.gridx = 2;
        btn.add(getExitButton());
        menu.add(btn);


    }
    JLabel getPacmanName(){
        JLabel name = new JLabel("PACMAN", JLabel.CENTER);
        name.setOpaque(true);
        name.setPreferredSize(new Dimension(200, 100));
        name.setBackground(Color.black);
        name.setForeground(Color.ORANGE);
        name.setFont(new Font("Serif", Font.BOLD, 36));
        return name;
    }
    JButton getStartButton(){
        JButton start = new JButton("Start");

//        start.setBorder(BorderFactory.createEmptyBorder(70, 140, 70, 140));
        start.setForeground(Color.ORANGE);
        start.setBorder(BorderFactory.createLineBorder(Color.GREEN, 3));
        start.setPreferredSize(new Dimension(140,70));
        start.setBackground(Color.BLACK);

        start.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String s = JOptionPane.showInputDialog(menu, "What size do you want to play?","Size pick", JOptionPane.QUESTION_MESSAGE);
                try{
                    if(Integer.parseInt(s) >= 10 && Integer.parseInt(s) <= 100){
                        System.out.println("ok");
                        size = Integer.parseInt(s);
                        setVisible(false);
                        SwingUtilities.invokeLater(() -> new GameTable(size));
                    }else{
                        System.out.println("not ok");
                    }
                }catch(NumberFormatException k){
                    System.out.println("Dolboeb?");
                }
            }
        });
        return start;
    }
    JButton getHighScoreButton(){
        JButton score = new JButton("High score");
//        score.setBorder(BorderFactory.createEmptyBorder(70, 140, 70, 140));
        score.setForeground(Color.ORANGE);
        score.setBorder(BorderFactory.createLineBorder(Color.GREEN, 3));
        score.setPreferredSize(new Dimension(140,70));
        score.setBackground(Color.BLACK);
        score.setSize(140,70);




        return score;
    }
    JButton getExitButton(){

        JButton exit = new JButton("Exit");
//        exit.setBorder(BorderFactory.createEmptyBorder(70, 140, 70, 140));
        exit.setBorder(BorderFactory.createLineBorder(Color.GREEN, 3));
        exit.setPreferredSize(new Dimension(140,70));
        exit.setBackground(Color.BLACK);
        exit.setForeground(Color.ORANGE);
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int b = JOptionPane.showConfirmDialog(menu, "Are you sure you want to leave?", "Leaving?", JOptionPane.YES_NO_OPTION);
                if(b == 0){
                    System.exit(0);
                }
            }
        });
        return exit;
    }

}
