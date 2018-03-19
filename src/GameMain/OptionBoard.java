package GameMain;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import GameMain.Options.OptionsEnum;
import SpriteClasses.ImageUtils;

@SuppressWarnings("serial")
public class OptionBoard  extends JPanel implements ActionListener, KeyListener {

    private GameView theView;
    private JTextField levelText;
    private JLabel labelStage;
    private JLabel labelMode;
    private JButton mode1Button;
    private JButton mode2Button;
    private JButton keyUp1Button;
    private JButton keyDown1Button;
    private JButton keyRight1Button;
    private JButton keyLeft1Button;
    private JButton keyFire1Button;
    private JButton keyUp2Button;
    private JButton keyDown2Button;
    private JButton keyRight2Button;
    private JButton keyLeft2Button;
    private JButton keyFire2Button;
    private JButton backButton;
    
    private OptionsEnum keyStatus;

    public OptionBoard(GameView theView) {
        this.theView = theView;
        this.setFocusable(true);
        theView.setForeground(Color.BLACK);
        this.setBackground(Color.BLACK);
        this.setForeground(Color.WHITE);
        this.setLayout(null);

        labelStage = new JLabel();
        labelStage.setText("Start from stage: ");
        this.add(labelStage);
        labelStage.setBounds((ImageUtils.getCurrent_form_width()) / 2 - getXScaled(154), 10, getXScaled(160), 30);
        labelStage.setForeground(Color.WHITE);
        
        levelText = new JTextField();
        levelText.setText(Integer.toString(Options.getInstance().getOption(OptionsEnum.start_level)));
        this.add(levelText);
        levelText.setBounds(getXScaled(280), 10, getXScaled(160), 30);
        levelText.addActionListener(this);
        levelText.setBackground(Color.gray);
        
        mode1Button = new JButton();
        mode1Button.setText("One player");
        this.add(mode1Button);
        mode1Button.setBounds((ImageUtils.getCurrent_form_width()) / 2 - getXScaled(154), 90, getXScaled(160), 30);
        mode1Button.addActionListener(this);
        buttonStyle(mode1Button);

        mode2Button = new JButton();
        mode2Button.setText("Two players");
        this.add(mode2Button);
        mode2Button.setBounds(getXScaled(280), 90, getXScaled(160), 30);
        mode2Button.addActionListener(this);
        buttonStyle(mode2Button);
        
        labelMode = new JLabel();
        if (Options.getInstance().getOption(OptionsEnum.mode) == 1) {
        	labelMode.setText(Integer.toString(Options.getInstance().getOption(OptionsEnum.mode)) + " player");	
        } else labelMode.setText(Integer.toString(Options.getInstance().getOption(OptionsEnum.mode)) + " players");
        this.add(labelMode);
        labelMode.setBounds(getXScaled(248), 50, getXScaled(80), 30);
        labelMode.setForeground(Color.WHITE);
        
        keyUp1Button = new JButton();
        keyUp1Button.setText("key up player 1");
        this.add(keyUp1Button);
        keyUp1Button.setBounds((ImageUtils.getCurrent_form_width()) / 2 - getXScaled(154), 130, getXScaled(160), 30);
        keyUp1Button.addActionListener(this);
        buttonStyle(keyUp1Button);

        keyDown1Button = new JButton();
        keyDown1Button.setText("key down player 1");
        this.add(keyDown1Button);
        keyDown1Button.setBounds(getXScaled(280), 130, getXScaled(160), 30);
        keyDown1Button.addActionListener(this);
        buttonStyle(keyDown1Button);

        
        keyRight1Button = new JButton();
        keyRight1Button.setText("key right player 1");
        this.add(keyRight1Button);
        keyRight1Button.setBounds(getXScaled(280), 170, getXScaled(160), 30);
        keyRight1Button.addActionListener(this);
        buttonStyle(keyRight1Button);
        
        keyLeft1Button = new JButton();
        keyLeft1Button.setText("key left player 1");
        this.add(keyLeft1Button);
        keyLeft1Button.setBounds((ImageUtils.getCurrent_form_width()) / 2 - getXScaled(154), 170, getXScaled(160), 30);
        keyLeft1Button.addActionListener(this);
        buttonStyle(keyLeft1Button);
        
        keyFire1Button = new JButton();
        keyFire1Button.setText("key fire player 1");
        this.add(keyFire1Button);
        keyFire1Button.setBounds((ImageUtils.getCurrent_form_width()) / 2 - getXScaled(154), 210, keyRight1Button.getWidth() + keyRight1Button.getX() - keyLeft1Button.getX(), 30);
        keyFire1Button.addActionListener(this);
        buttonStyle(keyFire1Button);
        
        keyUp2Button = new JButton();
        keyUp2Button.setText("key up player 2");
        this.add(keyUp2Button);
        keyUp2Button.setBounds((ImageUtils.getCurrent_form_width()) / 2 - getXScaled(154), 250, getXScaled(160), 30);
        keyUp2Button.addActionListener(this);
        buttonStyle(keyUp2Button);

        keyDown2Button = new JButton();
        keyDown2Button.setText("key down player 2");
        this.add(keyDown2Button);
        keyDown2Button.setBounds(getXScaled(280), 250, getXScaled(160), 30);
        keyDown2Button.addActionListener(this);
        buttonStyle(keyDown2Button);
        
        keyRight2Button = new JButton();
        keyRight2Button.setText("key right player 2");
        this.add(keyRight2Button);
        keyRight2Button.setBounds(getXScaled(280), 290, getXScaled(160), 30);
        keyRight2Button.addActionListener(this);
        buttonStyle(keyRight2Button);
        
        keyLeft2Button = new JButton();
        keyLeft2Button.setText("key left player 2");
        this.add(keyLeft2Button);
        keyLeft2Button.setBounds((ImageUtils.getCurrent_form_width()) / 2 - getXScaled(154), 290, getXScaled(160), 30);
        keyLeft2Button.addActionListener(this);
        buttonStyle(keyLeft2Button);
        
        keyFire2Button = new JButton();
        keyFire2Button.setText("key fire player 2");
        this.add(keyFire2Button);
        keyFire2Button.setBounds((ImageUtils.getCurrent_form_width()) / 2 - getXScaled(154), 330, keyRight2Button.getWidth() + keyRight2Button.getX() - keyLeft2Button.getX(), 30);
        keyFire2Button.addActionListener(this);
        buttonStyle(keyFire2Button);
        
        backButton = new JButton();
        backButton.setText("Main Menu");
        this.add(backButton);
        backButton.setBounds(getXScaled(280), 410, getXScaled(160), 30);
        backButton.addActionListener(this);
        buttonStyle(backButton);
        
        addKeyListener(new TAdapter ());
        
        this.addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent evt) {
                Component c = (Component)evt.getSource();
                ImageUtils.setNewFormSize(c.getSize().width, c.getSize().height);
                labelStage.setBounds((ImageUtils.getCurrent_form_width()) / 2 - getXScaled(154), 10, getXScaled(160), 30);
                levelText.setBounds(getXScaled(280), 10, getXScaled(160), 30);
                mode1Button.setBounds((ImageUtils.getCurrent_form_width()) / 2 - getXScaled(154), 90, getXScaled(160), 30);
                mode2Button.setBounds(getXScaled(280), 90, getXScaled(160), 30);
                labelMode.setBounds(getXScaled(248), 50, getXScaled(80), 30);
                keyUp1Button.setBounds((ImageUtils.getCurrent_form_width()) / 2 - getXScaled(154), 130, getXScaled(160), 30);
                keyDown1Button.setBounds(getXScaled(280), 130, getXScaled(160), 30);
                keyRight1Button.setBounds(getXScaled(280), 170, getXScaled(160), 30);
                keyLeft1Button.setBounds((ImageUtils.getCurrent_form_width()) / 2 - getXScaled(154), 170, getXScaled(160), 30);
                keyFire1Button.setBounds((ImageUtils.getCurrent_form_width()) / 2 - getXScaled(154), 210, keyRight1Button.getWidth() + keyRight1Button.getX() - keyLeft1Button.getX(), 30);
                keyUp2Button.setBounds((ImageUtils.getCurrent_form_width()) / 2 - getXScaled(154), 250, getXScaled(160), 30);
                keyDown2Button.setBounds(getXScaled(280), 250, getXScaled(160), 30);
                keyRight2Button.setBounds(getXScaled(280), 290, getXScaled(160), 30);
                keyLeft2Button.setBounds((ImageUtils.getCurrent_form_width()) / 2 - getXScaled(154), 290, getXScaled(160), 30);
                keyFire2Button.setBounds((ImageUtils.getCurrent_form_width()) / 2 - getXScaled(154), 330, keyRight1Button.getWidth() + keyRight1Button.getX() - keyLeft1Button.getX(), 30);
                backButton.setBounds(getXScaled(280), 410, getXScaled(160), 30);
            }
		}); 
    }
    
    private void lockButtons(boolean lock) {
    	  mode1Button.setEnabled(!lock);
    	  mode2Button.setEnabled(!lock);
    	  keyUp1Button.setEnabled(!lock);
    	  keyDown1Button.setEnabled(!lock);
    	  keyRight1Button.setEnabled(!lock);
    	  keyLeft1Button.setEnabled(!lock);
    	  keyFire1Button.setEnabled(!lock);
    	  keyUp2Button.setEnabled(!lock);
    	  keyDown2Button.setEnabled(!lock);
    	  keyRight2Button.setEnabled(!lock);
    	  keyLeft2Button.setEnabled(!lock);
    	  keyFire2Button.setEnabled(!lock);
    	  backButton.setEnabled(!lock);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == backButton || e.getSource() == levelText) {
        	String temp = levelText.getText();
        	Integer temp_ = Options.getInstance().getOption(OptionsEnum.start_level);
        	try {
        		temp_ = Integer.parseInt(temp);
        		if (temp_ < 1) temp_ = 1;
        		if (temp_ > 35) temp_ = 35;
        		Options.getInstance().setOption(OptionsEnum.start_level, temp_);
        	} catch (NumberFormatException ex) {
        	}
        	if (e.getSource() == backButton) {
            	Options.getInstance().saveToFile();
                Menu.loadMenu(theView);
        	}
        } else if (e.getSource() == keyFire2Button) {
        	this.keyStatus = OptionsEnum.key_fire_2;   
        	lockButtons(true);
        } else if (e.getSource() == keyFire1Button) {
        	this.keyStatus = OptionsEnum.key_fire_1;
        	lockButtons(true);
        }  else if (e.getSource() == keyUp1Button) {
        	this.keyStatus = OptionsEnum.key_up_1;
        	lockButtons(true);
        }  else if (e.getSource() == keyUp2Button) {
        	this.keyStatus = OptionsEnum.key_up_2;
        	lockButtons(true);
        }  else if (e.getSource() == keyDown1Button) {
        	this.keyStatus = OptionsEnum.key_down_1;
        	lockButtons(true);
        }  else if (e.getSource() == keyDown2Button) {
        	this.keyStatus = OptionsEnum.key_down_2;
        	lockButtons(true);
        }  else if (e.getSource() == keyRight1Button) {
        	this.keyStatus = OptionsEnum.key_right_1;
        	lockButtons(true);
        }  else if (e.getSource() == keyRight2Button) {
        	this.keyStatus = OptionsEnum.key_right_2;
        	lockButtons(true);
        }  else if (e.getSource() == keyLeft1Button) {
        	this.keyStatus = OptionsEnum.key_left_1;
        	lockButtons(true);
        }  else if (e.getSource() == keyLeft2Button) {
        	this.keyStatus = OptionsEnum.key_left_2;
        	lockButtons(true);
        }  else if (e.getSource() == mode1Button) {
        	this.labelMode.setText("1 player");
        	Options.getInstance().setOption(OptionsEnum.mode, 1);     	
        }  else if (e.getSource() == mode2Button) {
        	this.labelMode.setText("2 players");
        	Options.getInstance().setOption(OptionsEnum.mode, 2);     	
        }  
    }

    private void keyMethod(KeyEvent e) {
    	if (keyStatus == null) return;
    	Options.getInstance().setOption(keyStatus, e.getKeyCode());
    	keyStatus = null;
    	lockButtons(false);
    }

    @Override
    public void keyPressed(KeyEvent e) {
    	keyMethod(e);
    }


	@Override
	public void keyReleased(KeyEvent e) {
		keyMethod(e);
	}


	@Override
	public void keyTyped(KeyEvent e) {
		keyMethod(e);
	}
	
    private class TAdapter extends KeyAdapter {

        @Override
        public void keyReleased(KeyEvent e) {
        	keyMethod(e);
        }

        @Override
        public void keyPressed(KeyEvent e) {
        	keyMethod(e);
        }
    }
    
    private int getXScaled(int x) {
    	return (int)(ImageUtils.getCurrent_scale_factor() * x) + ImageUtils.getDraw_from_x();
    }

    
    private void buttonStyle(JButton button) {
    	button.setBackground(Color.gray);
    	button.setContentAreaFilled(false);
    	button.setOpaque(true);
    	button.setForeground(Color.WHITE);
    }

}
