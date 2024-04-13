// Mariam Hussein A00039 Assignment 3

package application;
	
import java.util.Random;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		//tab pane
		TabPane game = new TabPane();
		
		//game for each tab
		game.getTabs().add(new Tab("GUESS THE NUMBER", new GuessTheNumber()));
		game.getTabs().add(new Tab("MATH QUIZ GAME", new MathQuizGame()));
	    
	    //scene
	    Scene scene = new Scene(game, 550, 350);

	    primaryStage.setScene(scene);
	    primaryStage.setTitle("MATH GAMES");

	    primaryStage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}

	//GUESS THE NUMBER
	class GuessTheNumber extends VBox {
		int counter = 0;
		int random = (int) (Math.random() * 101);
		public GuessTheNumber() {
			
			//explanation of game
			Text text = new Text("Guess the number between 1 and 100");
			text.setFont(Font.font("Futura", FontWeight.BOLD, 14));

			//texts to display hint and correct answer and wrong attempts
			Text hint = new Text();
			Text answer = new Text();
			Text wrongattempts = new Text();

			//buttons
			Button buttonEnter = new Button("Enter");
			Button buttonAnswer = new Button("Click to reveal answer");

			//textfield
			TextField textfield = new TextField();
			textfield.setMaxWidth(250);

			//vbox
			VBox layout = new VBox();
			layout.getChildren().addAll(text, textfield, hint, wrongattempts, buttonEnter, buttonAnswer, answer);

			//pop-up window
			GridPane popup = new GridPane();
	        Text question = new Text ("Do you want to continue or quit the game?");
	        question.setFont(Font.font("Futura", FontWeight.BOLD, 14));
	        popup.add(question, 0, 0);
	        
	        Button buttonContinue = new Button("Continue");
	        popup.add(buttonContinue, 0, 5);
	        Button buttonQuit = new Button("Quit");
	        popup.add(buttonQuit, 5, 5);
	        
			Scene scene1 = new Scene(popup, 350, 60);
			Stage window = new Stage();
			window.setTitle("GAME OVER!");
			window.setScene(scene1);
			
			//if enter is clicked 
			buttonEnter.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent e) {

					int a = Integer.parseInt(textfield.getText());

					if(a > random) {
						hint.setText("Too high!");
						counter++;
						wrongattempts.setText("Number of wrong attempts: " + counter);
					}
					else if(a < random) {
						hint.setText("Too low!");
						counter++;
						wrongattempts.setText("Number of wrong attempts: " + counter);

					}
					else {
						hint.setText("CONGRATULATIONS YOU WON!");
						window.show();
					}
				}

			});
			
			//if reveal answer is clicked 
			buttonAnswer.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent e) {
					answer.setText("The answer is: " + random);
					window.show();
				}

			});
			
			//if continue is clicked 
			buttonContinue.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent e) {
					window.close();
					textfield.clear();
					counter = 0;
					random = (int) (Math.random() * 101);
					hint.setText(null);
					answer.setText(null);
					wrongattempts.setText(null);
				}

			});
			
			//if quit is clicked 
			buttonQuit.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent e) {
					Platform.exit();
				}

			});
			
			getChildren().addAll(layout);
		}
	}	
	
	//MATH QUIZ GAME
	class MathQuizGame extends VBox {
		Random random = new Random();
		int advancedMinimum = 11;
		int advancedMaximum = 100;
		int incorrectAnswer1 = (int)(Math.random()*11);
		int incorrectAnswer2 = (int)(Math.random()*11);
		int incorrectAnswer3 = (int)(Math.random()*11);
		int a = (int)(Math.random()*11);
		int x = (int)(Math.random()*11);
		int b = a * x;
		public MathQuizGame() {
			
			//options menu
			Menu options = new Menu("Options");
			MenuItem newGame = new MenuItem("New Game");
			CheckMenuItem advancedGame = new CheckMenuItem("Advanced");
			MenuItem exit = new MenuItem("Exit");
			options.getItems().addAll(newGame, advancedGame, exit);
			
			//view menu
			Menu view = new Menu("View");
			
			Menu fontSize = new Menu("Font size");
			MenuItem small = new MenuItem("Small");
			MenuItem large = new MenuItem("Large");
			fontSize.getItems().addAll(small, large);
			
			Menu theme = new Menu("Theme");
			MenuItem red = new MenuItem("Red");
			MenuItem blue = new MenuItem("Blue");
			MenuItem black = new MenuItem("Black");
			theme.getItems().addAll(red, blue, black);
			
			view.getItems().addAll(fontSize, theme);
			
			//menu bar
			MenuBar menuBar = new MenuBar();
			menuBar.getMenus().addAll(options, view);
			
			//explanation of game
			Text text1 = new Text("Solve the presented equation, where a is between 0 and 10, and b is its multiple.");
			Text text2 = new Text("In the advanced version, a is between 11 and 100.");
			text1.setFont(Font.font("Futura", FontWeight.BOLD, 14));
			text2.setFont(Font.font("Futura", FontWeight.BOLD, 14));
			
			//buttons
			Button buttonEnter = new Button("Enter");
			Button buttonSubmit = new Button("Submit");

			//textfield
			Label label = new Label("Enter your username:");
			TextField textfield = new TextField();
			textfield.setMaxWidth(250);
			
			//question
			Text question = new Text("Find x of: " + a + "x = " + b);
			
			//welcome message and answer response
			Text welcome = new Text();
			Text response = new Text();
			
			//radio buttons
			RadioButton button1 = new RadioButton(incorrectAnswer1 + "");
			RadioButton button2 = new RadioButton(incorrectAnswer2 + "");
			RadioButton button3 = new RadioButton(x + "");
			RadioButton button4 = new RadioButton(incorrectAnswer3 + "");
			
			ToggleGroup toggle = new ToggleGroup();

			button1.setToggleGroup(toggle);
			button2.setToggleGroup(toggle);
			button3.setToggleGroup(toggle);
			button4.setToggleGroup(toggle);

			//vbox
			VBox layout = new VBox();
			layout.getChildren().addAll(menuBar, text1, text2, label, textfield, buttonEnter, welcome, question, button1, button2, button3, button4, buttonSubmit, response);
			
			//if enter is clicked 
			buttonEnter.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent e) {
					String username = String.valueOf(textfield.getText());
					welcome.setText("Welcome: " + username);
				}

			});
			
			//if reveal answer is clicked 
			buttonSubmit.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent e) {
					String username = String.valueOf(textfield.getText());
					if (button3.isSelected()) {
						response.setText("Correct, " + username);
						buttonSubmit.setDisable(true);
					}	   
					else {
						response.setText("Try again, " + username);
					}
				}

			});
			
			//if new game is clicked 
			newGame.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent e) {
					textfield.clear();
					button1.setSelected(false);
					button2.setSelected(false);
					button3.setSelected(false);
					button4.setSelected(false);
					buttonSubmit.setDisable(false);
					welcome.setText(null);
					response.setText(null);
					a = (int) (Math.random() * 11);
					x = (int) (Math.random() * 11);
					b = a * x;
					question.setText("Find x of: " + a + "x = " + b);
					incorrectAnswer1 = (int)(Math.random()*11);
					incorrectAnswer2 = (int)(Math.random()*11);
					incorrectAnswer3 = (int)(Math.random()*11);
					button1.setText(incorrectAnswer1 + "");
					button2.setText(incorrectAnswer2 + "");
					button3.setText(x + "");
					button4.setText(incorrectAnswer3 + "");
				}

			});
			
			//if advanced is clicked 
			advancedGame.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent e) {
					if (advancedGame.isSelected()) {
						textfield.clear();
						button1.setSelected(false);
						button2.setSelected(false);
						button3.setSelected(false);
						button4.setSelected(false);
						buttonSubmit.setDisable(false);
						welcome.setText(null);
						response.setText(null);
						a = random.nextInt(advancedMaximum - advancedMinimum) + advancedMinimum;
						x = random.nextInt(advancedMaximum - advancedMinimum) + advancedMinimum;
						b = a * x;
						question.setText("Find x of: " + a + "x = " + b);
						incorrectAnswer1 = random.nextInt(advancedMaximum - advancedMinimum) + advancedMinimum;
						incorrectAnswer2 = random.nextInt(advancedMaximum - advancedMinimum) + advancedMinimum;
						incorrectAnswer3 = random.nextInt(advancedMaximum - advancedMinimum) + advancedMinimum;
						button1.setText(incorrectAnswer1 + "");
						button2.setText(incorrectAnswer2 + "");
						button3.setText(x + "");
						button4.setText(incorrectAnswer3 + "");
					}
					else {
						textfield.clear();
						button1.setSelected(false);
						button2.setSelected(false);
						button3.setSelected(false);
						button4.setSelected(false);
						buttonSubmit.setDisable(false);
						welcome.setText(null);
						response.setText(null);
						a = (int) (Math.random() * 11);
						x = (int) (Math.random() * 11);
						b = a * x;
						question.setText("Find x of: " + a + "x = " + b);
						incorrectAnswer1 = (int)(Math.random()*11);
						incorrectAnswer2 = (int)(Math.random()*11);
						incorrectAnswer3 = (int)(Math.random()*11);
						button1.setText(incorrectAnswer1 + "");
						button2.setText(incorrectAnswer2 + "");
						button3.setText(x + "");
						button4.setText(incorrectAnswer3 + "");
					}
				}
			});
			
			//if exit is clicked 
			exit.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent e) {
					Platform.exit();
				}

			});
			
			//if small is clicked 
			small.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent e) {
					text1.setFont(Font.font("Futura", FontWeight.BOLD, 10));
					text2.setFont(Font.font("Futura", FontWeight.BOLD, 10));
					label.setFont(Font.font(null, null, null, 10));
					textfield.setFont(Font.font(null, null, null, 10));
					buttonEnter.setFont(Font.font(null, null, null, 10));
					welcome.setFont(Font.font(null, null, null, 10));
					question.setFont(Font.font(null, null, null, 10));
					button1.setFont(Font.font(null, null, null, 10));
					button2.setFont(Font.font(null, null, null, 10));
					button3.setFont(Font.font(null, null, null, 10));
					button4.setFont(Font.font(null, null, null, 10));
					buttonSubmit.setFont(Font.font(null, null, null, 10));
					response.setFont(Font.font(null, null, null, 10));
				}

			});
			
			//if large is clicked 
			large.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent e) {
					text1.setFont(Font.font("Futura", FontWeight.BOLD, 15));
					text2.setFont(Font.font("Futura", FontWeight.BOLD, 15));
					label.setFont(Font.font(null, null, null, 15));
					textfield.setFont(Font.font(null, null, null, 15));
					buttonEnter.setFont(Font.font(null, null, null, 15));
					welcome.setFont(Font.font(null, null, null, 15));
					question.setFont(Font.font(null, null, null, 15));
					button1.setFont(Font.font(null, null, null, 15));
					button2.setFont(Font.font(null, null, null, 15));
					button3.setFont(Font.font(null, null, null, 15));
					button4.setFont(Font.font(null, null, null, 15));
					buttonSubmit.setFont(Font.font(null, null, null, 15));
					response.setFont(Font.font(null, null, null, 15));
				}

			});
			
			//if red is clicked 
			red.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent e) {
					text1.setFill(Color.RED);
					text2.setFill(Color.RED);
					label.setTextFill(Color.RED);
					buttonEnter.setTextFill(Color.RED);
					welcome.setFill(Color.RED);
					question.setFill(Color.RED);
					button1.setTextFill(Color.RED);
					button2.setTextFill(Color.RED);
					button3.setTextFill(Color.RED);
					button4.setTextFill(Color.RED);
					buttonSubmit.setTextFill(Color.RED);
					response.setFill(Color.RED);
				}

			});
			
			//if blue is clicked 
			blue.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent e) {
					text1.setFill(Color.BLUE);
					text2.setFill(Color.BLUE);
					label.setTextFill(Color.BLUE);
					buttonEnter.setTextFill(Color.BLUE);
					welcome.setFill(Color.BLUE);
					question.setFill(Color.BLUE);
					button1.setTextFill(Color.BLUE);
					button2.setTextFill(Color.BLUE);
					button3.setTextFill(Color.BLUE);
					button4.setTextFill(Color.BLUE);
					buttonSubmit.setTextFill(Color.BLUE);
					response.setFill(Color.BLUE);
				}

			});
			
			//if black is clicked 
			black.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent e) {
					text1.setFill(Color.BLACK);
					text2.setFill(Color.BLACK);
					label.setTextFill(Color.BLACK);
					buttonEnter.setTextFill(Color.BLACK);
					welcome.setFill(Color.BLACK);
					question.setFill(Color.BLACK);
					button1.setTextFill(Color.BLACK);
					button2.setTextFill(Color.BLACK);
					button3.setTextFill(Color.BLACK);
					button4.setTextFill(Color.BLACK);
					buttonSubmit.setTextFill(Color.BLACK);
					response.setFill(Color.BLACK);
				}

			});
			
			getChildren().addAll(layout);
		}
	}	
