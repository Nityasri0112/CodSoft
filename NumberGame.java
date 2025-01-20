import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class NumberGame {
    private int numberToGuess;
    private int attempts;
    private int maxAttempts = 10;
    private int score = 0;

    private JFrame frame;
    private JTextField guessField;
    private JLabel messageLabel;
    private JLabel attemptsLabel;
    private JLabel scoreLabel;

    public NumberGame() {
        frame = new JFrame("Number Guessing Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300); 
        frame.setLayout(new GridLayout(7, 1)); 

        JLabel instructionLabel = new JLabel("Guess the number between 1 and 100:");
        instructionLabel.setHorizontalAlignment(SwingConstants.CENTER);
        instructionLabel.setForeground(new Color(128, 0, 128));

        guessField = new JTextField();
        JButton guessButton = new JButton("Submit Guess");
        guessButton.setBackground(new Color(0, 128, 0));
        guessButton.setForeground(Color.WHITE); 

        messageLabel = new JLabel("You have " + maxAttempts + " attempts.");
        messageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        messageLabel.setForeground(new Color(128, 0, 128)); 

        attemptsLabel = new JLabel("Attempts left: " + maxAttempts);
        attemptsLabel.setHorizontalAlignment(SwingConstants.CENTER);
        attemptsLabel.setForeground(new Color(128, 0, 128)); 

        scoreLabel = new JLabel("Score: " + score);
        scoreLabel.setHorizontalAlignment(SwingConstants.CENTER);
        scoreLabel.setForeground(new Color(128, 0, 128)); 

        JButton newGameButton = new JButton("Start New Game");
        newGameButton.setBackground(new Color(0, 128, 0)); 
        newGameButton.setForeground(Color.WHITE); 

        frame.add(instructionLabel);
        frame.add(guessField);
        frame.add(guessButton);
        frame.add(messageLabel);
        frame.add(attemptsLabel);
        frame.add(scoreLabel);
        frame.add(newGameButton); 

        guessButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleGuess();
            }
        });

       
        newGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetGame();
                guessButton.setEnabled(true); 
            }
        });

        frame.setVisible(true);
        resetGame(); 
    }

    private void handleGuess() {
        try {
            int userGuess = Integer.parseInt(guessField.getText());
            attempts++;

            if (userGuess == numberToGuess) {
                messageLabel.setText("Congratulations! You guessed the number.");
                score += (maxAttempts - attempts + 1);
                scoreLabel.setText("Score: " + score);
                disableInput();
            } else if (userGuess > numberToGuess) {
                messageLabel.setText("Too high! Try again.");
            } else {
                messageLabel.setText("Too low! Try again.");
            }

            attemptsLabel.setText("Attempts left: " + (maxAttempts - attempts));

            if (attempts >= maxAttempts) {
                messageLabel.setText("Out of attempts! The number was " + numberToGuess + ".");
                disableInput();
            }
        } catch (NumberFormatException ex) {
            messageLabel.setText("Please enter a valid number.");
        }
    }

    private void resetGame() {
        Random random = new Random();
        numberToGuess = random.nextInt(100) + 1;
        attempts = 0;

        if (guessField != null) {
            guessField.setText("");
            guessField.setEnabled(true);
        }
        messageLabel.setText("You have " + maxAttempts + " attempts.");
        attemptsLabel.setText("Attempts left: " + maxAttempts);
    }

    private void disableInput() {
        if (guessField != null) {
            guessField.setEnabled(false);
        }
    }

    public static void main(String[] args) {
        new NumberGame();
    }
}



