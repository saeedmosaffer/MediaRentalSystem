package application;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;


public class Main extends Application implements EventHandler<ActionEvent> {

	static ArrayList<Customer> customer1 = new ArrayList<Customer>();
	static ArrayList<Media> media1 = new ArrayList<Media>();
	static MediaRental MR = new MediaRental(customer1, media1);
	
	@Override
	public void start(Stage primaryStage) {
		try {
			GridPane root = new GridPane();
			BorderPane Root = new BorderPane();
			Root.setTop(root);

			root.setPadding(new Insets(15, 15, 15, 15));
			root.setVgap(10);
			root.setHgap(10);

			Button customer = new Button("Customer");
			customer.setPrefSize(100, 40);
			Button media = new Button("Media");
			media.setPrefSize(100, 40);
			Button rent = new Button("Rent");
			rent.setPrefSize(100, 40);

			customer.setOnAction((ActionEvent a) -> {
				customer();
			});

			media.setOnAction((ActionEvent b) -> {
				media();
			});

			rent.setOnAction((ActionEvent c) -> {
				rent();
			});

			DropShadow shadow = new DropShadow();
			customer.addEventHandler(MouseEvent.MOUSE_ENTERED, (MouseEvent e) -> {
				customer.setEffect(shadow);
			});
			media.addEventHandler(MouseEvent.MOUSE_ENTERED, (MouseEvent e) -> {
				media.setEffect(shadow);
			});
			rent.addEventHandler(MouseEvent.MOUSE_ENTERED, (MouseEvent e) -> {
				rent.setEffect(shadow);
			});

			customer.addEventHandler(MouseEvent.MOUSE_EXITED, (MouseEvent e) -> {
				customer.setEffect(null);
			});
			media.addEventHandler(MouseEvent.MOUSE_EXITED, (MouseEvent e) -> {
				media.setEffect(null);
			});
			rent.addEventHandler(MouseEvent.MOUSE_EXITED, (MouseEvent e) -> {
				rent.setEffect(null);
			});

			Label title = new Label("Rental Media System");

			Image image = new Image("https://img.icons8.com/cute-clipart/344/movie.png");
			ImageView imageView = new ImageView();
			imageView.setFitHeight(200);
			imageView.setFitWidth(200);
			imageView.setImage(image);
			Root.setCenter(imageView);
			Button photo = new Button("", imageView);
			photo.setPrefSize(300, 100);
			Root.setCenter(imageView);
			
			photo.addEventHandler(MouseEvent.MOUSE_ENTERED, (MouseEvent e) -> {
				photo.setEffect(shadow);
			});
			photo.addEventHandler(MouseEvent.MOUSE_EXITED, (MouseEvent e) -> {
				photo.setEffect(null);
			});
			photo.setOnAction(dkk -> {
				primaryStage.close();
			});

			VBox vb = new VBox();
			vb.getChildren().addAll(title, photo);
			vb.setSpacing(25);

			root.add(customer, 0, 11);
			root.add(media, 0, 12);
			root.add(rent, 0, 13);
			root.add(photo, 4, 12);
			root.add(title, 5, 14);

			Scene scene = new Scene(Root, 650, 500, Color.BEIGE);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			// primaryStage.setMaximized(true);
			primaryStage.setTitle("The main facade");
			primaryStage.setScene(scene);
			primaryStage.show();

		} catch (Exception ER) {
			ER.printStackTrace();
		}
	}

	public static void customer() {
		Stage stage1 = new Stage();
		stage1.initModality(Modality.APPLICATION_MODAL);
		stage1.setTitle("Customer Stage");
		stage1.setMinWidth(250);
		// stage1.setMaximized(true);
		GridPane root1 = new GridPane();
		Group group = new Group();
		VBox v = new VBox();

		Button Add1 = new Button("Add new Customer");
		Add1.setStyle("-fx-background-radius:20.0 10.0;");
		Add1.setMaxSize(250, 400);
		Add1.setOnAction(e -> {
			AddNewCustomer();
		});
		Button Delete1 = new Button("Delete Customer");
		Delete1.setStyle("-fx-background-radius:20.0 10.0;");
		Delete1.setMaxSize(250, 400);
		Delete1.setOnAction(e -> {
			DeleteCustomer();
		});
		Button Update1 = new Button("Update Information about Customer");
		Update1.setStyle("-fx-background-radius:20.0 10.0;");
		Update1.setMaxSize(250, 400);
		Update1.setOnAction(e -> {
			UpdateInformationAboutCustomer();
		});
		Button Search1 = new Button("Search a Customer by id");
		Search1.setStyle("-fx-background-radius:20.0 10.0;");
		Search1.setMaxSize(250, 400);
		Search1.setOnAction(e -> {
			SearchACustomerByID();
		});
		Button Return1 = new Button("Return to main page");
		Return1.setStyle("-fx-background-radius:20.0 10.0;");
		Return1.setMaxSize(250, 400);
		Return1.setOnAction(e -> {
			stage1.close();
		});

		Add1.setId("Add");
		Delete1.setId("Delete");
		Update1.setId("Update");
		Search1.setId("Search");
		Return1.setId("Return");
		v.setId("root");

		v.setPadding(new Insets(50, 50, 50, 50));
		v.setSpacing(10);
		v.setAlignment(Pos.CENTER);
		v.getChildren().addAll(Add1, Delete1, Update1, Search1, Return1);

		Scene scene = new Scene(v, 650, 500);
		stage1.setScene(scene);
		stage1.show();

	}

	private static void SearchACustomerByID() {
		// TODO Auto-generated method stub
		Stage stage2 = new Stage();
		stage2.initModality(Modality.APPLICATION_MODAL);
		stage2.setTitle("Customer Stage");
		stage2.setMinWidth(250);
		// stage2.setMaximized(true);
		GridPane root2 = new GridPane();
		BorderPane Root2 = new BorderPane();
		Root2.setTop(root2);
		root2.setPadding(new Insets(15, 15, 15, 15));
		root2.setVgap(10);
		root2.setHgap(10);

		Label ID2 = new Label("Enter the Customer ID");
		TextField CustomerID2 = new TextField();
		ID2.setStyle("-fx-font-weight:bold;");
		HBox hb10 = new HBox();
		hb10.getChildren().addAll(ID2, CustomerID2);
		hb10.setSpacing(25);

		Label Name2 = new Label("The Customer Name is :");
		TextField CustomerName2 = new TextField();
		Name2.setStyle("-fx-font-weight:bold;");
		HBox hb20 = new HBox();
		hb20.getChildren().addAll(Name2, CustomerName2);
		hb20.setSpacing(25);

		Label Address2 = new Label("The Customer Adress is :");
		TextField CustomerAddress2 = new TextField();
		Address2.setStyle("-fx-font-weight:bold;");
		HBox hb30 = new HBox();
		hb30.getChildren().addAll(Address2, CustomerAddress2);
		hb30.setSpacing(25);

		Label plan2 = new Label("The Customer Plan is :");
		TextField CustomerPlan2 = new TextField();
		plan2.setStyle("-fx-font-weight:bold;");
		HBox hb50 = new HBox();
		hb50.getChildren().addAll(plan2, CustomerPlan2);
		hb50.setSpacing(25);

		Label Mobile2 = new Label("The Customer Mobile is :");
		TextField CustomerMobile2 = new TextField();
		Mobile2.setStyle("-fx-font-weight:bold;");
		HBox hb40 = new HBox();
		hb40.getChildren().addAll(Mobile2, CustomerMobile2);
		hb40.setSpacing(25);

		Image image2 = new Image("https://img.icons8.com/nolan/344/circled-left-2.png");
		ImageView imageView2 = new ImageView();
		imageView2.setImage(image2);
		Root2.setCenter(imageView2);
		Button back1 = new Button("Back", imageView2);
		back1.setStyle("-fx-background-color:transparent;");
		imageView2.setFitHeight(40);
		imageView2.setFitWidth(40);
		back1.setOnAction(d -> {
			stage2.close();
		});

		Image image3 = new Image(
				"https://img.icons8.com/external-icongeek26-outline-colour-icongeek26/344/external-search-project-work-icongeek26-outline-colour-icongeek26.png");
		ImageView imageView3 = new ImageView();
		imageView3.setImage(image3);
		Root2.setCenter(imageView3);
		Button search1 = new Button("Search", imageView3);
		search1.setStyle("-fx-background-color:transparent;");
		imageView3.setFitHeight(40);
		imageView3.setFitWidth(40);
		Label Response2 = new Label();
		search1.setOnAction(f -> {
			for (int i = 0; i < MR.getCustomer().size(); i++) {
				if (MR.getCustomer().get(i).getID().equalsIgnoreCase(CustomerID2.getText())) {
					CustomerName2.setText(MR.getCustomer().get(i).getName());
					CustomerAddress2.setText(MR.getCustomer().get(i).getAddress());
					CustomerPlan2.setText(MR.getCustomer().get(i).getPlan());
					CustomerMobile2.setText(MR.getCustomer().get(i).getMobileNumber());

					if (CustomerID2.getText() != null && !CustomerID2.getText().isEmpty()) {
						Response2.setText(CustomerID2.getText() + " " + "Customer founded !");
					} else {
						Response2.setText("you have missing fields ! try again.");
					}
				}
			}
		});

		DropShadow shadow2 = new DropShadow();
		search1.addEventHandler(MouseEvent.MOUSE_ENTERED, (MouseEvent e) -> {
			search1.setEffect(shadow2);
		});

		back1.addEventHandler(MouseEvent.MOUSE_ENTERED, (MouseEvent e) -> {
			back1.setEffect(shadow2);
		});

		search1.addEventHandler(MouseEvent.MOUSE_EXITED, (MouseEvent e) -> {
			search1.setEffect(null);
		});

		back1.addEventHandler(MouseEvent.MOUSE_EXITED, (MouseEvent e) -> {
			back1.setEffect(null);
		});

		root2.add(ID2, 0, 1);
		root2.add(CustomerID2, 1, 1);
		root2.add(Name2, 0, 2);
		root2.add(CustomerName2, 1, 2);
		root2.add(Address2, 0, 3);
		root2.add(CustomerAddress2, 1, 3);
		root2.add(plan2, 0, 4);
		root2.add(CustomerPlan2, 1, 4);
		root2.add(Mobile2, 0, 5);
		root2.add(CustomerMobile2, 1, 5);
		root2.add(Response2, 0, 6);

		HBox hBox78 = new HBox();
		hBox78.getChildren().addAll(search1, back1);
		hBox78.setAlignment(Pos.CENTER);
		hBox78.setSpacing(25);
		Root2.setBottom(hBox78);

		Scene scene = new Scene(Root2, 650, 500);
		stage2.setTitle("Search a Customer By ID");
		stage2.setScene(scene);
		stage2.show();
	}

	private static void UpdateInformationAboutCustomer() {
		// TODO Auto-generated method stub
		Stage stage3 = new Stage();
		stage3.initModality(Modality.APPLICATION_MODAL);
		stage3.setTitle("Customer Stage");
		stage3.setMinWidth(250);
		// stage3.setMaximized(true);
		GridPane root3 = new GridPane();
		BorderPane Root3 = new BorderPane();
		Root3.setTop(root3);
		root3.setPadding(new Insets(15, 15, 15, 15));
		root3.setVgap(10);
		root3.setHgap(10);

		Label ID3 = new Label("Enter the Customer ID");
		TextField CustomerID3 = new TextField();
		ID3.setStyle("-fx-font-weight:bold;");
		HBox hb00 = new HBox();
		hb00.getChildren().addAll(ID3, CustomerID3);
		hb00.setSpacing(25);

		Label Name3 = new Label("Enter the Customer Name");
		TextField CustomerName3 = new TextField();
		Name3.setStyle("-fx-font-weight:bold;");
		HBox hb01 = new HBox();
		hb01.getChildren().addAll(Name3, CustomerName3);
		hb01.setSpacing(25);

		Label Address3 = new Label("Enter the Customer Adress");
		TextField CustomerAddress3 = new TextField();
		Address3.setStyle("-fx-font-weight:bold;");
		HBox hb11 = new HBox();
		hb11.getChildren().addAll(Address3, CustomerAddress3);
		hb11.setSpacing(25);

		Label Plan3 = new Label("Enter the Plan ");
		// final ComboBox<String> CustomerPlan3 = new ComboBox<>();
		TextField CustomerPlan3 = new TextField();
//		 CustomerPlan3.getItems().addAll("LIMITED", "UNLIMITED");
//		 CustomerPlan3.setValue("The rate is : ");
		Plan3.setStyle("-fx-text-fill:#0076a3;");
		Plan3.setStyle("-fx-font-size:16.0px;");
		Plan3.setStyle("-fx-font-weight:bold;");
		HBox hb110 = new HBox();
		hb110.getChildren().addAll(Plan3, CustomerPlan3);
		hb110.setSpacing(25);

		Label Mobile3 = new Label("Enter the Customer Mobile");
		TextField CustomerMobile3 = new TextField();
		Mobile3.setStyle("-fx-font-weight:bold;");
		HBox hb10 = new HBox();
		hb10.getChildren().addAll(Mobile3, CustomerMobile3);
		hb10.setSpacing(25);

		Image image4 = new Image("https://img.icons8.com/clouds/344/back.png");
		ImageView imageView4 = new ImageView();
		imageView4.setImage(image4);
		Root3.setCenter(imageView4);
		Button back3 = new Button("Back", imageView4);
		back3.setStyle("-fx-background-color:transparent;");
		imageView4.setFitHeight(40);
		imageView4.setFitWidth(40);
		back3.setOnAction(f -> {
			stage3.close();
		});

		Label Response3 = new Label();

		Image image5 = new Image("https://img.icons8.com/clouds/344/available-updates.png");
		ImageView imageView5 = new ImageView();
		imageView5.setImage(image5);
		Root3.setCenter(imageView5);
		Button update3 = new Button("Update", imageView5);
		update3.setStyle("-fx-background-color:transparent;");
		imageView5.setFitHeight(40);
		imageView5.setFitWidth(40);
		update3.setOnAction(g -> {
			for (int i = 0; i < MR.getCustomer().size(); i++) {
				if (MR.getCustomer().get(i).getID().equalsIgnoreCase(CustomerID3.getText())) {
					MR.getCustomer().get(i).setName(CustomerName3.getText());
					MR.getCustomer().get(i).setAddress(CustomerAddress3.getText());
					MR.getCustomer().get(i).setPlan(CustomerPlan3.getText());
					MR.getCustomer().get(i).setMobileNumber(CustomerMobile3.getText());

					if (CustomerID3.getText() != null && !CustomerID3.getText().isEmpty()
							&& CustomerName3.getText() != null && !CustomerName3.getText().isEmpty()
							&& CustomerAddress3.getText() != null && !CustomerAddress3.getText().isEmpty()
							&& CustomerMobile3.getText() != null && !CustomerMobile3.getText().isEmpty()) {
						Response3.setText(" The Customer " + CustomerName3.getText() + " "
								+ " information has been updated successfully !");
					} else {
						Response3.setText("you have missing fields ! try again.");
					}

				}
			}
		});

		Image image38 = new Image(
				"https://img.icons8.com/external-icongeek26-outline-colour-icongeek26/344/external-search-project-work-icongeek26-outline-colour-icongeek26.png");
		ImageView imageView38 = new ImageView();
		imageView38.setImage(image38);
		Root3.setCenter(imageView38);
		Button search11 = new Button("Search", imageView38);
		search11.setStyle("-fx-background-color:transparent;");
		imageView38.setFitHeight(40);
		imageView38.setFitWidth(40);
		Label Response25 = new Label();
		search11.setOnAction(f -> {
			for (int i = 0; i < MR.getCustomer().size(); i++) {
				if (MR.getCustomer().get(i).getID().equalsIgnoreCase(CustomerID3.getText())) {
					CustomerName3.setText(MR.getCustomer().get(i).getName());
					CustomerAddress3.setText(MR.getCustomer().get(i).getAddress());
					CustomerPlan3.setText(MR.getCustomer().get(i).getPlan());
					CustomerMobile3.setText(MR.getCustomer().get(i).getMobileNumber());

					if (CustomerID3.getText() != null && !CustomerID3.getText().isEmpty()) {
						Response25.setText(CustomerID3.getText() + " " + "Customer founded !");
					} else {
						Response25.setText("you have missing fields ! try again.");
					}
				}
			}
		});

		DropShadow shadow3 = new DropShadow();
		update3.addEventHandler(MouseEvent.MOUSE_ENTERED, (MouseEvent e) -> {
			update3.setEffect(shadow3);
		});

		back3.addEventHandler(MouseEvent.MOUSE_ENTERED, (MouseEvent e) -> {
			back3.setEffect(shadow3);
		});
		search11.addEventHandler(MouseEvent.MOUSE_ENTERED, (MouseEvent e) -> {
			search11.setEffect(shadow3);
		});

		update3.addEventHandler(MouseEvent.MOUSE_EXITED, (MouseEvent e) -> {
			update3.setEffect(null);
		});

		back3.addEventHandler(MouseEvent.MOUSE_EXITED, (MouseEvent e) -> {
			back3.setEffect(null);
		});
		search11.addEventHandler(MouseEvent.MOUSE_EXITED, (MouseEvent e) -> {
			search11.setEffect(null);
		});

		root3.add(ID3, 0, 1);
		root3.add(CustomerID3, 1, 1);
		root3.add(Name3, 0, 2);
		root3.add(CustomerName3, 1, 2);
		root3.add(Address3, 0, 3);
		root3.add(CustomerAddress3, 1, 3);
		root3.add(Plan3, 0, 4);
		root3.add(CustomerPlan3, 1, 4);
		root3.add(Mobile3, 0, 5);
		root3.add(CustomerMobile3, 1, 5);
		root3.add(Response3, 0, 6);

		HBox hBox2353 = new HBox();
		hBox2353.getChildren().addAll(update3, search11, back3);
		hBox2353.setAlignment(Pos.CENTER);
		hBox2353.setSpacing(25);
		Root3.setBottom(hBox2353);

		Scene scene = new Scene(Root3, 650, 500);
		stage3.setTitle("Update Information about Customer");
		stage3.setScene(scene);
		stage3.show();
	}

	private static void DeleteCustomer() {
		// TODO Auto-generated method stub
		Stage stage4 = new Stage();
		stage4.initModality(Modality.APPLICATION_MODAL);
		stage4.setTitle("Customer Stage");
		stage4.setMinWidth(250);
		// stage4.setMaximized(true);
		GridPane root4 = new GridPane();
		BorderPane Root4 = new BorderPane();
		Root4.setTop(root4);
		root4.setPadding(new Insets(15, 15, 15, 15));
		root4.setVgap(10);
		root4.setHgap(10);

		Label ID4 = new Label("Enter the Customer ID");
		TextField CustomerID4 = new TextField();
		ID4.setStyle("-fx-font-weight:bold;");
		HBox hb = new HBox();
		hb.getChildren().addAll(ID4, CustomerID4);
		hb.setSpacing(25);

		Label Name4 = new Label("The Customer Name IS :");
		TextField CustomerName4 = new TextField();
		Name4.setStyle("-fx-font-weight:bold;");
		HBox hb2475374 = new HBox();
		hb2475374.getChildren().addAll(Name4, CustomerName4);
		hb2475374.setSpacing(25);

		Label Address4 = new Label("The Customer Adress is :");
		TextField CustomerAddress4 = new TextField();
		Address4.setStyle("-fx-font-weight:bold;");
		HBox hb3756373 = new HBox();
		hb3756373.getChildren().addAll(Address4, CustomerAddress4);
		hb3756373.setSpacing(25);

		Label plan4 = new Label("The Customer Plan is :");
		TextField CustomerPlan4 = new TextField();
		plan4.setStyle("-fx-font-weight:bold;");
		HBox hb5753 = new HBox();
		hb5753.getChildren().addAll(plan4, CustomerPlan4);
		hb5753.setSpacing(25);

		Label Mobile4 = new Label("The Customer Mobile is :");
		TextField CustomerMobile4 = new TextField();
		Mobile4.setStyle("-fx-font-weight:bold;");
		HBox hb4165489 = new HBox();
		hb4165489.getChildren().addAll(Mobile4, CustomerMobile4);
		hb4165489.setSpacing(25);

		Label Response4 = new Label();
		Label Response44 = new Label();

		Image image6 = new Image(
				"https://img.icons8.com/external-kiranshastry-gradient-kiranshastry/344/external-delete-miscellaneous-kiranshastry-gradient-kiranshastry.png",
				40, 40, false, false);
		ImageView imageView6 = new ImageView();
		imageView6.setImage(image6);
		Root4.setCenter(imageView6);
		Button delete4 = new Button("Delete", imageView6);
		delete4.setStyle("-fx-background-color:transparent;");
		delete4.setOnAction(d -> {
			for (int i = 0; i < MR.getCustomer().size(); i++) {
				if (MR.getCustomer().get(i).getID().equalsIgnoreCase(CustomerID4.getText())) {
					MR.getCustomer().remove(MR.getCustomer().get(i));

					if (CustomerID4.getText() != null && !CustomerID4.getText().isEmpty()
							&& CustomerName4.getText() != null && !CustomerName4.getText().isEmpty()
							&& CustomerAddress4.getText() != null && !CustomerAddress4.getText().isEmpty()
							&& CustomerMobile4.getText() != null && !CustomerMobile4.getText().isEmpty()) {
						Response4.setText(
								" The Customer " + CustomerName4.getText() + " " + "has been deleted successfully !");
					} else {
						Response4.setText("you have missing fields ! try again.");
					}
				}
			}
		});

		Image image7 = new Image("https://img.icons8.com/clouds/344/back.png");
		ImageView imageView7 = new ImageView();
		imageView7.setImage(image7);
		Root4.setCenter(imageView7);
		Button back4 = new Button("Back", imageView7);
		back4.setStyle("-fx-background-color:transparent;");
		imageView7.setFitHeight(40);
		imageView7.setFitWidth(40);
		back4.setOnAction(c -> {
			stage4.close();
		});

		Image image8 = new Image(
				"https://img.icons8.com/external-kiranshastry-gradient-kiranshastry/344/external-find-hotel-kiranshastry-gradient-kiranshastry.png");
		ImageView imageView8 = new ImageView();
		imageView8.setImage(image8);
		Root4.setCenter(imageView8);
		Button find4 = new Button("Find", imageView8);
		find4.setStyle("-fx-background-color:transparent;");
		imageView8.setFitHeight(40);
		imageView8.setFitWidth(40);
		find4.setOnAction(g -> {
			for (int i = 0; i < MR.getCustomer().size(); i++) {
				if (MR.getCustomer().get(i).getID().equalsIgnoreCase(CustomerID4.getText())) {
					CustomerName4.setText(MR.getCustomer().get(i).getName());
					CustomerAddress4.setText(MR.getCustomer().get(i).getAddress());
					CustomerPlan4.setText(MR.getCustomer().get(i).getPlan());
					CustomerMobile4.setText(MR.getCustomer().get(i).getMobileNumber());

					if (CustomerID4.getText() != null && !CustomerID4.getText().isEmpty()) {
						Response4.setText(CustomerID4.getText() + " " + "Customer founded !");
					} else {
						Response4.setText("you have missing fields ! try again.");
					}
				}
			}
		});

		DropShadow shadow4 = new DropShadow();
		delete4.addEventHandler(MouseEvent.MOUSE_ENTERED, (MouseEvent ea) -> {
			delete4.setEffect(shadow4);
		});
		find4.addEventHandler(MouseEvent.MOUSE_ENTERED, (MouseEvent eb) -> {
			find4.setEffect(shadow4);
		});
		back4.addEventHandler(MouseEvent.MOUSE_ENTERED, (MouseEvent ec) -> {
			back4.setEffect(shadow4);
		});

		delete4.addEventHandler(MouseEvent.MOUSE_EXITED, (MouseEvent ed) -> {
			delete4.setEffect(null);
		});
		find4.addEventHandler(MouseEvent.MOUSE_EXITED, (MouseEvent ee) -> {
			find4.setEffect(null);
		});
		back4.addEventHandler(MouseEvent.MOUSE_EXITED, (MouseEvent ef) -> {
			back4.setEffect(null);
		});

		root4.add(ID4, 0, 1);
		root4.add(CustomerID4, 1, 1);
		root4.add(Name4, 0, 2);
		root4.add(CustomerName4, 1, 2);
		root4.add(Address4, 0, 3);
		root4.add(CustomerAddress4, 1, 3);
		root4.add(plan4, 0, 4);
		root4.add(CustomerPlan4, 1, 4);
		root4.add(Mobile4, 0, 5);
		root4.add(CustomerMobile4, 1, 5);
		root4.add(Response4, 0, 6);
		root4.add(Response44, 0, 7);

		HBox hBox = new HBox();
		hBox.getChildren().addAll(delete4, find4, back4);
		hBox.setAlignment(Pos.CENTER);
		hBox.setSpacing(25);
		Root4.setBottom(hBox);

		Scene scene = new Scene(Root4, 650, 500);
		stage4.setTitle("Delete Customer");
		stage4.setScene(scene);
		stage4.show();
	}

	private static void AddNewCustomer() {
		// TODO Auto-generated method stub
		Stage stage5 = new Stage();
		stage5.initModality(Modality.APPLICATION_MODAL);
		stage5.setTitle("Customer Stage");
		stage5.setMinWidth(250);
		// stage5.setMaximized(true);
		GridPane root5 = new GridPane();
		BorderPane Root5 = new BorderPane();
		Root5.setTop(root5);
		root5.setPadding(new Insets(15, 15, 15, 15));
		root5.setVgap(10);
		root5.setHgap(10);

		Label ID5 = new Label("Enter the Customer ID");
		TextField CustomerID5 = new TextField();
		ID5.setStyle("-fx-font-weight:bold;");
		HBox hb4737 = new HBox();
		hb4737.getChildren().addAll(ID5, CustomerID5);
		hb4737.setSpacing(25);

		Label Name5 = new Label("Enter the Customer Name");
		TextField CustomerName5 = new TextField();
		Name5.setStyle("-fx-font-weight:bold;");
		HBox hb24573 = new HBox();
		hb24573.getChildren().addAll(Name5, CustomerName5);
		hb24573.setSpacing(25);
		CustomerName5.setDisable(true);
		CustomerID5.setOnKeyTyped(j -> {
			CustomerName5.setDisable(false);
		});

		Label Address5 = new Label("Enter the Customer Adress");
		TextField CustomerAddress5 = new TextField();
		Address5.setStyle("-fx-font-weight:bold;");
		HBox hb378697 = new HBox();
		hb378697.getChildren().addAll(Address5, CustomerAddress5);
		hb378697.setSpacing(25);
		CustomerAddress5.setDisable(true);
		CustomerName5.setOnKeyTyped(k -> {
			CustomerAddress5.setDisable(false);
		});

//		Label Plan5 = new Label("Enter the Plan ");
//		final ComboBox<String> CustomerPlan5 = new ComboBox<>();
//		CustomerPlan5.getItems().addAll("LIMITED", "UNLIMITED");
//		CustomerPlan5.setValue("The plan is : ");
//		Plan5.setStyle("-fx-text-fill:#0076a3;");
//		Plan5.setStyle("-fx-font-size:16.0px;");
//		Plan5.setStyle("-fx-font-weight:bold;");

		Label Plan5 = new Label("Enter the Plan ");
		TextField CustomerPlan5 = new TextField();
		Plan5.setStyle("-fx-text-fill:#0076a3;");
		Plan5.setStyle("-fx-font-size:16.0px;");
		Plan5.setStyle("-fx-font-weight:bold;");
		HBox hb1100 = new HBox();
		hb1100.getChildren().addAll(Plan5, CustomerPlan5);
		hb1100.setSpacing(25);
		CustomerPlan5.setDisable(true);
		CustomerAddress5.setOnKeyTyped(l -> {
			CustomerPlan5.setDisable(false);
		});

		Label Mobile5 = new Label("Enter the Customer Mobile");
		TextField CustomerMobile5 = new TextField();
		Mobile5.setStyle("-fx-font-weight:bold;");
		HBox hb4453 = new HBox();
		hb4453.getChildren().addAll(Mobile5, CustomerMobile5);
		hb4453.setSpacing(25);
		CustomerMobile5.setDisable(true);
		CustomerPlan5.setOnKeyTyped(m -> {
			CustomerMobile5.setDisable(false);
		});

		Label Response5 = new Label();
		Image image9 = new Image("https://img.icons8.com/office/344/add.png", 40, 40, false, false);
		ImageView imageView9 = new ImageView();
		imageView9.setImage(image9);
		Root5.setCenter(imageView9);
		Button add5 = new Button("Add", imageView9);
		add5.setStyle("-fx-background-color:transparent;");
		add5.setOnAction(n -> {
			MR.addCustomer(CustomerID5.getText(), CustomerName5.getText(), CustomerAddress5.getText(),
					CustomerPlan5.getText(), CustomerMobile5.getText());

			if (CustomerID5.getText() != null && !CustomerID5.getText().isEmpty() && CustomerName5.getText() != null
					&& !CustomerName5.getText().isEmpty() && CustomerAddress5.getText() != null
					&& !CustomerAddress5.getText().isEmpty() && CustomerMobile5.getText() != null
					&& !CustomerMobile5.getText().isEmpty()) {
				Response5.setText(CustomerName5.getText() + " " + "Registration completed !");
			} else {
				Response5.setText("you have missing fields ! try again.");
			}
		});

		Image image10 = new Image("https://img.icons8.com/clouds/344/back.png");
		ImageView imageView10 = new ImageView();
		imageView10.setImage(image10);
		Root5.setCenter(imageView10);
		Button back5 = new Button("Back", imageView10);
		back5.setStyle("-fx-background-color:transparent;");
		imageView10.setFitHeight(40);
		imageView10.setFitWidth(40);
		back5.setOnAction(o -> {
			stage5.close();
		});

		DropShadow shadow5 = new DropShadow();
		add5.addEventHandler(MouseEvent.MOUSE_ENTERED, (MouseEvent eg) -> {
			add5.setEffect(shadow5);
		});
		back5.addEventHandler(MouseEvent.MOUSE_ENTERED, (MouseEvent eh) -> {
			back5.setEffect(shadow5);
		});

		add5.addEventHandler(MouseEvent.MOUSE_EXITED, (MouseEvent ei) -> {
			add5.setEffect(null);
		});
		back5.addEventHandler(MouseEvent.MOUSE_EXITED, (MouseEvent ej) -> {
			back5.setEffect(null);
		});

		root5.add(ID5, 0, 1);
		root5.add(CustomerID5, 1, 1);
		root5.add(Name5, 0, 2);
		root5.add(CustomerName5, 1, 2);
		root5.add(Address5, 0, 3);
		root5.add(CustomerAddress5, 1, 3);
		root5.add(Plan5, 0, 4);
		root5.add(CustomerPlan5, 1, 4);
		root5.add(Mobile5, 0, 5);
		root5.add(CustomerMobile5, 1, 5);
		root5.add(Response5, 0, 6);

		HBox hBox02 = new HBox();
		hBox02.getChildren().addAll(add5, back5);
		hBox02.setAlignment(Pos.CENTER);
		hBox02.setSpacing(25);
		Root5.setBottom(hBox02);

		Scene scene = new Scene(Root5, 650, 500);
		stage5.setTitle("Add new Customer");
		stage5.setScene(scene);
		stage5.show();
	}

	public static void media() {
		Stage stage6 = new Stage();
		stage6.initModality(Modality.APPLICATION_MODAL);
		stage6.setTitle("Media Stage");
		stage6.setMinWidth(250);
		// stage1.setMaximized(true);
		GridPane root6 = new GridPane();
		Group group6 = new Group();
		VBox v6 = new VBox();

		Button Add6 = new Button("Add new Media");
		Add6.setStyle("-fx-background-radius:100.0 30.0;");
		Add6.setMaxSize(250, 400);
		Add6.setOnAction(q -> {
			AddNewMedia();
		});
		Button Delete6 = new Button("Delete Media");
		Delete6.setStyle("-fx-background-radius:100.0 30.0;");
		Delete6.setMaxSize(250, 400);
		Delete6.setOnAction(r -> {
			DeleteMedia();
		});
		Button Update6 = new Button("Update Information about Media");
		Update6.setStyle("-fx-background-radius:100.0 30.0;");
		Update6.setMaxSize(250, 400);
		Update6.setOnAction(s -> {
			UpdateInformationAboutMedia();
		});
		Button Search6 = new Button("Search a Media by code");
		Search6.setStyle("-fx-background-radius:100.0 30.0;");
		Search6.setMaxSize(250, 400);
		Search6.setOnAction(t -> {
			SearchAMediaByCode();
		});

		Button Print6 = new Button("Print all Media information");
		Print6.setStyle("-fx-background-radius:100.0 30.0;");
		Print6.setMaxSize(250, 400);
		Print6.setOnAction(u -> {
			PrintAllMediaInformation();
		});

		Button Return6 = new Button("Return to main page");
		Return6.setStyle("-fx-background-radius:100.0 30.0;");
		Return6.setMaxSize(250, 400);
		Return6.setOnAction(v -> {
			stage6.close();
		});

		Add6.setId("Add");
		Delete6.setId("Delete");
		Update6.setId("Update");
		Search6.setId("Search");
		Return6.setId("Return");
		Print6.setId("Print");
		v6.setId("root");

		v6.setPadding(new Insets(50, 50, 50, 50));
		v6.setSpacing(10);
		v6.setAlignment(Pos.CENTER);
		v6.getChildren().addAll(Add6, Delete6, Update6, Search6, Print6, Return6);

		// root.getChildren().addAll(v);
		Scene scene = new Scene(v6, 650, 500);
		stage6.setScene(scene);
		stage6.show();
	}

	private static void PrintAllMediaInformation() {
		// TODO Auto-generated method stub
		Stage stage7 = new Stage();
		stage7.initModality(Modality.APPLICATION_MODAL);
		stage7.setTitle("Customer Stage");
		stage7.setMinWidth(250);
		// stage7.setMaximized(true);
		GridPane root7 = new GridPane();
		BorderPane Root7 = new BorderPane();
		Root7.setTop(root7);
		root7.setPadding(new Insets(15, 15, 15, 15));
		root7.setVgap(10);
		root7.setHgap(10);

		Label print7 = new Label("The all media information is : ");
		print7.setStyle("-fx-font-weight:bold;");

		TextArea PrintData7 = new TextArea();
		PrintData7.setStyle("-fx-border: gone;");
		PrintData7.setStyle("-fx-background-color: #63cdda;");

		Image image11 = new Image(
				"https://img.icons8.com/external-icongeek26-outline-colour-icongeek26/344/external-print-photography-icongeek26-outline-colour-icongeek26.png",
				40, 40, false, false);
		ImageView imageView11 = new ImageView();
		imageView11.setImage(image11);
		Root7.setCenter(imageView11);
		Button print07 = new Button("Print", imageView11);
		print07.setStyle("-fx-background-color:transparent;");
		print07.setOnAction(w -> {
			PrintData7.setText(MR.getAllMediaInfo());

		});

		Image image12 = new Image("https://img.icons8.com/plasticine/344/back.png");
		ImageView imageView12 = new ImageView();
		imageView12.setImage(image12);
		Root7.setCenter(imageView12);
		Button back7 = new Button("Back", imageView12);
		back7.setStyle("-fx-background-color:transparent;");
		imageView12.setFitHeight(40);
		imageView12.setFitWidth(40);
		back7.setOnAction(x -> {
			stage7.close();
		});

		DropShadow shadow7 = new DropShadow();
		print07.addEventHandler(MouseEvent.MOUSE_ENTERED, (MouseEvent ek) -> {
			print07.setEffect(shadow7);
		});

		back7.addEventHandler(MouseEvent.MOUSE_ENTERED, (MouseEvent el) -> {
			back7.setEffect(shadow7);
		});

		print07.addEventHandler(MouseEvent.MOUSE_EXITED, (MouseEvent em) -> {
			print07.setEffect(null);
		});

		back7.addEventHandler(MouseEvent.MOUSE_EXITED, (MouseEvent en) -> {
			back7.setEffect(null);
		});

		root7.add(print7, 0, 1);
		root7.add(PrintData7, 0, 2);

		HBox hBox7 = new HBox();
		hBox7.getChildren().addAll(print07, back7);
		hBox7.setAlignment(Pos.CENTER);
		hBox7.setSpacing(25);
		Root7.setBottom(hBox7);

		Scene scene = new Scene(Root7, 650, 500);
		stage7.setTitle("Print All Media Information");
		stage7.setScene(scene);
		stage7.show();
	}

	private static void SearchAMediaByCode() {
		// TODO Auto-generated method stub
		Stage stage8 = new Stage();
		stage8.initModality(Modality.APPLICATION_MODAL);
		stage8.setMinWidth(250);
		// stage8.setMaximized(true);
		GridPane root8 = new GridPane();
		BorderPane Root8 = new BorderPane();
		Root8.setTop(root8);
		root8.setPadding(new Insets(15, 15, 15, 15));
		root8.setVgap(10);
		root8.setHgap(10);

		final ComboBox<String> Combo_Box1681871 = new ComboBox<>();
		Combo_Box1681871.getItems().addAll("Game", "Album", "Movie");
		Combo_Box1681871.setValue("Media type");
		Label label18 = new Label("The media is: ");
		label18.setStyle("-fx-text-fill:#0076a3;");
		label18.setStyle("-fx-font-size:16.0px;");
		label18.setStyle("-fx-font-weight:bold;");
		root8.add(label18, 0, 0);
		root8.add(Combo_Box1681871, 1, 0);
		Combo_Box1681871.setOnAction(z -> {
			if (Combo_Box1681871.getValue().equals("Game")) {
				SearchGame();
			}

			else if (Combo_Box1681871.getValue().equals("Album")) {
				SearchAlbum();
			}

			else if (Combo_Box1681871.getValue().equals("Movie")) {
				SearchMovie();
			}

		});

		Image image13 = new Image("https://img.icons8.com/nolan/344/circled-left-2.png");
		ImageView imageView13 = new ImageView();
		imageView13.setImage(image13);
		Root8.setCenter(imageView13);
		Button back8 = new Button("Back", imageView13);
		back8.setStyle("-fx-background-color:transparent;");
		imageView13.setFitHeight(40);
		imageView13.setFitWidth(40);
		back8.setOnAction(y -> {
			stage8.close();
		});

		DropShadow shadow = new DropShadow();
		back8.addEventHandler(MouseEvent.MOUSE_ENTERED, (MouseEvent e) -> {
			back8.setEffect(shadow);
		});

		back8.addEventHandler(MouseEvent.MOUSE_EXITED, (MouseEvent e) -> {
			back8.setEffect(null);
		});

		HBox hBox8 = new HBox();
		hBox8.getChildren().addAll(back8);
		hBox8.setAlignment(Pos.CENTER);
		hBox8.setSpacing(25);
		Root8.setBottom(hBox8);

		Scene scene = new Scene(Root8, 650, 500);
		stage8.setTitle("Search a Media By Code");
		stage8.setScene(scene);
		stage8.show();
	}

	private static void SearchMovie() {
		// TODO Auto-generated method stub
		Stage stage9 = new Stage();
		stage9.initModality(Modality.APPLICATION_MODAL);
		stage9.setMinWidth(250);
		// stage9.setMaximized(true);
		GridPane root9 = new GridPane();
		BorderPane Root9 = new BorderPane();
		Root9.setTop(root9);
		root9.setPadding(new Insets(15, 15, 15, 15));
		root9.setVgap(10);
		root9.setHgap(10);

		Label Code9 = new Label("Enter the Media Code");
		TextField MediaCode9 = new TextField();
		Code9.setStyle("-fx-font-weight:bold;");
		HBox hb29 = new HBox();
		hb29.getChildren().addAll(Code9, MediaCode9);
		hb29.setSpacing(25);

		Label Title9 = new Label("Enter the Media title");
		TextField MediaTitle9 = new TextField();
		Title9.setStyle("-fx-font-weight:bold;");
		HBox hb9 = new HBox();
		hb9.getChildren().addAll(Title9, MediaTitle9);
		hb9.setSpacing(25);

		Label Copies9 = new Label("Enter the number of copies available");
		TextField MediaCopies9 = new TextField();
		Copies9.setStyle("-fx-font-weight:bold;");
		HBox hb39 = new HBox();
		hb39.getChildren().addAll(Copies9, MediaCopies9);
		hb39.setSpacing(25);

		Label rate9 = new Label("The Rating is :");
		TextField rateText9 = new TextField();
		Code9.setStyle("-fx-font-weight:bold;");
		HBox hb19 = new HBox();
		hb19.getChildren().addAll(rate9, rateText9);
		hb19.setSpacing(25);
		rate9.setStyle("-fx-font-weight:bold;");

		Image image14 = new Image("https://img.icons8.com/nolan/344/circled-left-2.png");
		ImageView imageView14 = new ImageView();
		imageView14.setImage(image14);
		Root9.setCenter(imageView14);
		Button back9 = new Button("Back", imageView14);
		back9.setStyle("-fx-background-color:transparent;");
		imageView14.setFitHeight(40);
		imageView14.setFitWidth(40);
		back9.setOnAction(a1 -> {
			stage9.close();
		});

		Label Response00092 = new Label();

		Image image15 = new Image(
				"https://img.icons8.com/external-icongeek26-outline-colour-icongeek26/344/external-search-project-work-icongeek26-outline-colour-icongeek26.png");
		ImageView imageView15 = new ImageView();
		imageView15.setImage(image15);
		Root9.setCenter(imageView15);
		Button search9 = new Button("Search", imageView15);
		search9.setStyle("-fx-background-color:transparent;");
		imageView15.setFitHeight(40);
		imageView15.setFitWidth(40);
		search9.setOnAction(b1 -> {
			for (int i = 0; i < MR.getMedia().size(); i++) {
				if (MR.getMedia().get(i).getCode().equalsIgnoreCase(MediaCode9.getText())) {
					MediaTitle9.setText(MR.getMedia().get(i).getTitle());
					MediaCopies9.setText(String.valueOf(MR.getMedia().get(i).getNumber_of_copies_available()));
					rateText9.setText(((Movie) MR.getMedia().get(i)).getRate());
				}
			}

			if (MediaCode9.getText() != null && !MediaCode9.getText().isEmpty()) {
				Response00092.setText(MediaTitle9.getText() + " " + "Media founded !");
			} else {
				Response00092.setText("Please enter the media code you want to found and delete .");
			}

		});

		DropShadow shadow9 = new DropShadow();
		search9.addEventHandler(MouseEvent.MOUSE_ENTERED, (MouseEvent es) -> {
			search9.setEffect(shadow9);
		});

		back9.addEventHandler(MouseEvent.MOUSE_ENTERED, (MouseEvent et) -> {
			back9.setEffect(shadow9);
		});

		search9.addEventHandler(MouseEvent.MOUSE_EXITED, (MouseEvent eu) -> {
			search9.setEffect(null);
		});

		back9.addEventHandler(MouseEvent.MOUSE_EXITED, (MouseEvent ev) -> {
			back9.setEffect(null);
		});

		root9.add(Code9, 0, 1);
		root9.add(MediaCode9, 1, 1);
		root9.add(Title9, 0, 2);
		root9.add(MediaTitle9, 1, 2);
		root9.add(Copies9, 0, 3);
		root9.add(MediaCopies9, 1, 3);
		root9.add(rate9, 0, 4);
		root9.add(rateText9, 1, 4);

		HBox hBox9999 = new HBox();
		hBox9999.getChildren().addAll(search9, back9);
		hBox9999.setAlignment(Pos.CENTER);
		hBox9999.setSpacing(25);
		Root9.setBottom(hBox9999);

		Scene scene = new Scene(Root9, 650, 500);
		stage9.setTitle("Search a Movie By Code");
		stage9.setScene(scene);
		stage9.show();
	}

	private static void SearchAlbum() {
		// TODO Auto-generated method stub
		Stage stage10 = new Stage();
		stage10.initModality(Modality.APPLICATION_MODAL);
		stage10.setTitle("Customer Stage");
		stage10.setMinWidth(250);
		// stage10.setMaximized(true);
		GridPane root10 = new GridPane();
		BorderPane Root10 = new BorderPane();
		Root10.setTop(root10);
		root10.setPadding(new Insets(15, 15, 15, 15));
		root10.setVgap(10);
		root10.setHgap(10);

		Label Code10 = new Label("Enter the Media Code");
		TextField MediaCode10 = new TextField();
		Code10.setStyle("-fx-font-weight:bold;");
		HBox hb210 = new HBox();
		hb210.getChildren().addAll(Code10, MediaCode10);
		hb210.setSpacing(25);

		Label Title10 = new Label("Enter the Media title");
		TextField MediaTitle10 = new TextField();
		Title10.setStyle("-fx-font-weight:bold;");
		HBox hb10 = new HBox();
		hb10.getChildren().addAll(Title10, MediaTitle10);
		hb10.setSpacing(25);

		Label Copies10 = new Label("Enter the number of copies available");
		TextField MediaCopies10 = new TextField();
		Copies10.setStyle("-fx-font-weight:bold;");
		HBox hb310 = new HBox();
		hb310.getChildren().addAll(Copies10, MediaCopies10);
		hb310.setSpacing(25);

		Label artist10 = new Label("Enter the Artist ");
		TextField artistText10 = new TextField();
		Code10.setStyle("-fx-font-weight:bold;");
		HBox hb510 = new HBox();
		hb510.getChildren().addAll(artist10, artistText10);
		hb510.setSpacing(25);
		artist10.setStyle("-fx-font-weight:bold;");

		Label song10 = new Label("Enter the Song ");
		TextField songText10 = new TextField();
		Code10.setStyle("-fx-font-weight:bold;");
		HBox hb610 = new HBox();
		hb610.getChildren().addAll(song10, songText10);
		hb610.setSpacing(25);
		song10.setStyle("-fx-font-weight:bold;");

		Image image16 = new Image("https://img.icons8.com/nolan/344/circled-left-2.png");
		ImageView imageView16 = new ImageView();
		imageView16.setImage(image16);
		Root10.setCenter(imageView16);
		Button back10 = new Button("Back", imageView16);
		back10.setStyle("-fx-background-color:transparent;");
		imageView16.setFitHeight(40);
		imageView16.setFitWidth(40);
		back10.setOnAction(c1 -> {
			stage10.close();
		});

		Label Response00010 = new Label();

		Image image17 = new Image(
				"https://img.icons8.com/external-icongeek26-outline-colour-icongeek26/344/external-search-project-work-icongeek26-outline-colour-icongeek26.png");
		ImageView imageView17 = new ImageView();
		imageView17.setImage(image17);
		Root10.setCenter(imageView17);
		Button search10 = new Button("Search", imageView17);
		search10.setStyle("-fx-background-color:transparent;");
		imageView17.setFitHeight(40);
		imageView17.setFitWidth(40);
		search10.setOnAction(d1 -> {
			for (int i = 0; i < MR.getMedia().size(); i++) {
				if (MR.getMedia().get(i).getCode().equalsIgnoreCase(MediaCode10.getText())) {
					MediaTitle10.setText(MR.getMedia().get(i).getTitle());
					MediaCopies10.setText(String.valueOf(MR.getMedia().get(i).getNumber_of_copies_available()));
					artistText10.setText(((Album) MR.getMedia().get(i)).getArtist());
					songText10.setText(((Album) MR.getMedia().get(i)).getSongs());
				}
			}

			if (MediaCode10.getText() != null && !MediaCode10.getText().isEmpty()) {
				Response00010.setText(MediaTitle10.getText() + " " + "Media founded !");
			} else {
				Response00010.setText("Please enter the media code you want to found and delete .");
			}

		});

		DropShadow shadow10 = new DropShadow();
		search10.addEventHandler(MouseEvent.MOUSE_ENTERED, (MouseEvent eo) -> {
			search10.setEffect(shadow10);
		});

		back10.addEventHandler(MouseEvent.MOUSE_ENTERED, (MouseEvent ep) -> {
			back10.setEffect(shadow10);
		});

		search10.addEventHandler(MouseEvent.MOUSE_EXITED, (MouseEvent eq) -> {
			search10.setEffect(null);
		});

		back10.addEventHandler(MouseEvent.MOUSE_EXITED, (MouseEvent er) -> {
			back10.setEffect(null);
		});

		root10.add(Code10, 0, 1);
		root10.add(MediaCode10, 1, 1);
		root10.add(Title10, 0, 2);
		root10.add(MediaTitle10, 1, 2);
		root10.add(Copies10, 0, 3);
		root10.add(MediaCopies10, 1, 3);
		root10.add(artist10, 0, 4);
		root10.add(artistText10, 1, 4);
		root10.add(song10, 0, 5);
		root10.add(songText10, 1, 5);

		HBox hBox10 = new HBox();
		hBox10.getChildren().addAll(search10, back10);
		hBox10.setAlignment(Pos.CENTER);
		hBox10.setSpacing(25);
		Root10.setBottom(hBox10);

		Scene scene = new Scene(Root10, 650, 500);
		stage10.setTitle("Search a Album By Code");
		stage10.setScene(scene);
		stage10.show();
	}

	private static void SearchGame() {
		// TODO Auto-generated method stub
		Stage stage11 = new Stage();
		stage11.initModality(Modality.APPLICATION_MODAL);
		stage11.setMinWidth(250);
		// stage1.setMaximized(true);
		GridPane root11 = new GridPane();
		BorderPane Root11 = new BorderPane();
		Root11.setTop(root11);
		root11.setPadding(new Insets(15, 15, 15, 15));
		root11.setVgap(10);
		root11.setHgap(10);

		Label Code11 = new Label("Enter the Media Code");
		TextField MediaCode11 = new TextField();
		Code11.setStyle("-fx-font-weight:bold;");
		HBox hb211 = new HBox();
		hb211.getChildren().addAll(Code11, MediaCode11);
		hb211.setSpacing(25);

		Label Title11 = new Label("Enter the Media title");
		TextField MediaTitle11 = new TextField();
		Title11.setStyle("-fx-font-weight:bold;");
		HBox hb11 = new HBox();
		hb11.getChildren().addAll(Title11, MediaTitle11);
		hb11.setSpacing(25);

		Label Copies11 = new Label("Enter the number of copies available");
		TextField MediaCopies11 = new TextField();
		Copies11.setStyle("-fx-font-weight:bold;");
		HBox hb311 = new HBox();
		hb311.getChildren().addAll(Copies11, MediaCopies11);
		hb311.setSpacing(25);

		Label weight11 = new Label("Enter the Weight (in grams)");
		TextField weightText11 = new TextField();
		Code11.setStyle("-fx-font-weight:bold;");
		HBox hb411 = new HBox();
		hb411.getChildren().addAll(weight11, weightText11);
		hb411.setSpacing(25);
		weight11.setStyle("-fx-font-weight:bold;");

		Image image18 = new Image("https://img.icons8.com/nolan/344/circled-left-2.png");
		ImageView imageView18 = new ImageView();
		imageView18.setImage(image18);
		Root11.setCenter(imageView18);
		Button back11 = new Button("Back", imageView18);
		back11.setStyle("-fx-background-color:transparent;");
		imageView18.setFitHeight(40);
		imageView18.setFitWidth(40);
		back11.setOnAction(f1 -> {
			stage11.close();
		});

		Label Response00011 = new Label();

		Image image19 = new Image(
				"https://img.icons8.com/external-icongeek26-outline-colour-icongeek26/344/external-search-project-work-icongeek26-outline-colour-icongeek26.png");
		ImageView imageView19 = new ImageView();
		imageView19.setImage(image19);
		Root11.setCenter(imageView19);
		Button search11 = new Button("Search", imageView19);
		search11.setStyle("-fx-background-color:transparent;");
		imageView19.setFitHeight(40);
		imageView19.setFitWidth(40);
		search11.setOnAction(g1 -> {
			for (int i = 0; i < MR.getMedia().size(); i++) {
				if (MR.getMedia().get(i).getCode().equalsIgnoreCase(MediaCode11.getText())) {
					MediaTitle11.setText(MR.getMedia().get(i).getTitle());
					MediaCopies11.setText(String.valueOf(MR.getMedia().get(i).getNumber_of_copies_available()));
					weightText11.setText(String.valueOf(((Game) MR.getMedia().get(i)).getWeight()));
				}
			}

			if (MediaCode11.getText() != null && !MediaCode11.getText().isEmpty()) {
				Response00011.setText(MediaTitle11.getText() + " " + "Media founded ! (❁´◡`❁) ");
			} else {
				Response00011.setText("Please enter the media code you want to found and delete . (●'◡'●) ");
			}

		});

		DropShadow shadow11 = new DropShadow();
		search11.addEventHandler(MouseEvent.MOUSE_ENTERED, (MouseEvent ew) -> {
			search11.setEffect(shadow11);
		});

		back11.addEventHandler(MouseEvent.MOUSE_ENTERED, (MouseEvent ex) -> {
			back11.setEffect(shadow11);
		});

		search11.addEventHandler(MouseEvent.MOUSE_EXITED, (MouseEvent ey) -> {
			search11.setEffect(null);
		});

		back11.addEventHandler(MouseEvent.MOUSE_EXITED, (MouseEvent ez) -> {
			back11.setEffect(null);
		});

		root11.add(Code11, 0, 1);
		root11.add(MediaCode11, 1, 1);
		root11.add(Title11, 0, 2);
		root11.add(MediaTitle11, 1, 2);
		root11.add(Copies11, 0, 3);
		root11.add(MediaCopies11, 1, 3);
		root11.add(weight11, 0, 4);
		root11.add(weightText11, 1, 4);
		root11.add(Response00011, 0, 5);

		HBox hBox11 = new HBox();
		hBox11.getChildren().addAll(search11, back11);
		hBox11.setAlignment(Pos.CENTER);
		hBox11.setSpacing(25);
		Root11.setBottom(hBox11);

		Scene scene = new Scene(Root11, 650, 500);
		stage11.setTitle("Search a Game By Code");
		stage11.setScene(scene);
		stage11.show();
	}

	private static void UpdateInformationAboutMedia() {
		// TODO Auto-generated method stub
		Stage stage12 = new Stage();
		stage12.initModality(Modality.APPLICATION_MODAL);
		stage12.setMinWidth(250);
		// stage12.setMaximized(true);
		GridPane root12 = new GridPane();
		BorderPane Root12 = new BorderPane();
		Root12.setTop(root12);
		root12.setPadding(new Insets(15, 15, 15, 15));
		root12.setVgap(10);
		root12.setHgap(10);

		final ComboBox<String> Combo_Box1212 = new ComboBox<>();
		Combo_Box1212.getItems().addAll("Game", "Album", "Movie");
		Combo_Box1212.setValue("Media type");
		Label label112 = new Label("The media is: ");
		label112.setStyle("-fx-text-fill:#0076a3;");
		label112.setStyle("-fx-font-size:16.0px;");
		label112.setStyle("-fx-font-weight:bold;");
		root12.add(label112, 0, 0);
		root12.add(Combo_Box1212, 1, 0);
		Combo_Box1212.setOnAction(h1 -> {
			if (Combo_Box1212.getValue().equals("Game")) {
				UpdateGame();
			}

			else if (Combo_Box1212.getValue().equals("Album")) {
				UpdateAlbum();
			}

			else if (Combo_Box1212.getValue().equals("Movie")) {
				UpdateMovie();
			}

		});

		Image image212 = new Image("https://img.icons8.com/clouds/344/back.png");
		ImageView imageView212 = new ImageView();
		imageView212.setImage(image212);
		Root12.setCenter(imageView212);
		Button back12 = new Button("Back", imageView212);
		back12.setStyle("-fx-background-color:transparent;");
		imageView212.setFitHeight(40);
		imageView212.setFitWidth(40);
		back12.setOnAction(i1 -> {
			stage12.close();
		});

		DropShadow shadow12 = new DropShadow();

		back12.addEventHandler(MouseEvent.MOUSE_ENTERED, (MouseEvent e454654) -> {
			back12.setEffect(shadow12);
		});

		back12.addEventHandler(MouseEvent.MOUSE_EXITED, (MouseEvent e472543) -> {
			back12.setEffect(null);
		});

		HBox hBox12 = new HBox();
		hBox12.getChildren().addAll(back12);
		hBox12.setAlignment(Pos.CENTER);
		hBox12.setSpacing(25);
		Root12.setBottom(hBox12);

		Scene scene = new Scene(Root12, 650, 500);
		stage12.setTitle("Update Information About Media");
		stage12.setScene(scene);
		stage12.show();
	}

	private static void UpdateMovie() {
		// TODO Auto-generated method stub
		Stage stage13 = new Stage();
		stage13.initModality(Modality.APPLICATION_MODAL);
		stage13.setTitle("Update Movie Stage");
		stage13.setMinWidth(250);
		// stage13.setMaximized(true);
		GridPane root13 = new GridPane();
		BorderPane Root13 = new BorderPane();
		Root13.setTop(root13);
		root13.setPadding(new Insets(15, 15, 15, 15));
		root13.setVgap(10);
		root13.setHgap(10);

		Label Code13 = new Label("Enter the Movie Code");
		TextField MediaCode13 = new TextField();
		Code13.setStyle("-fx-font-weight:bold;");
		HBox hb213 = new HBox();
		hb213.getChildren().addAll(Code13, MediaCode13);
		hb213.setSpacing(25);

		Label Title13 = new Label("Enter the Movie title");
		TextField MediaTitle13 = new TextField();
		Title13.setStyle("-fx-font-weight:bold;");
		HBox hb13 = new HBox();
		hb13.getChildren().addAll(Title13, MediaTitle13);
		hb13.setSpacing(25);

		Label Copies13 = new Label("Enter the number of copies available");
		TextField MediaCopies13 = new TextField();
		Copies13.setStyle("-fx-font-weight:bold;");
		HBox hb313 = new HBox();
		hb313.getChildren().addAll(Copies13, MediaCopies13);
		hb313.setSpacing(25);

//		Label rate = new Label("Enter the Rating ");
//		final ComboBox<String> rating = new ComboBox<>();
//		rating.getItems().addAll("AC", "DR", "HR");
//		rating.setValue("The rate is : ");
//		rate.setStyle("-fx-text-fill:#0076a3;");
//		rate.setStyle("-fx-font-size:16.0px;");
//		rate.setStyle("-fx-font-weight:bold;");

		Label rate13 = new Label("Enter the number of copies available");
		TextField rating13 = new TextField("AC, HR, DR");
		Copies13.setStyle("-fx-font-weight:bold;");
		HBox hb3813 = new HBox();
		hb3813.getChildren().addAll(rate13, rating13);
		hb3813.setSpacing(25);
		rate13.setStyle("-fx-text-fill:#0076a3;");
		rate13.setStyle("-fx-font-size:16.0px;");
		rate13.setStyle("-fx-font-weight:bold;");

		Image image313 = new Image("https://img.icons8.com/clouds/344/available-updates.png");
		ImageView imageView313 = new ImageView();
		imageView313.setImage(image313);
		Root13.setCenter(imageView313);
		Button update13 = new Button("Update", imageView313);
		update13.setStyle("-fx-background-color:transparent;");
		imageView313.setFitHeight(40);
		imageView313.setFitWidth(40);
		Label Response13 = new Label();
		update13.setOnAction(j1 -> {
			for (int i = 0; i < MR.getMedia().size(); i++) {
				if (MR.getMedia().get(i).getCode().equalsIgnoreCase(MediaCode13.getText())) {
					MR.getMedia().get(i).setTitle(MediaTitle13.getText());
					MR.getMedia().get(i).setNumber_of_copies_available(Integer.parseInt(MediaCopies13.getText()));
					((Movie) MR.getMedia().get(i)).setRate(rating13.getText());
				}
			}

			if (MediaTitle13.getText() != null && !MediaTitle13.getText().isEmpty() && MediaCode13.getText() != null
					&& !MediaCode13.getText().isEmpty() && MediaCopies13.getText() != null
					&& !MediaCopies13.getText().isEmpty() && rating13.getText() != null
					&& !rating13.getText().isEmpty()) {
				Response13.setText(MediaTitle13.getText() + " " + "Updated successfully !");
			} else {
				Response13.setText("you have missing fields ! try again.");
			}

		});

		Image image213 = new Image("https://img.icons8.com/clouds/344/back.png");
		ImageView imageView213 = new ImageView();
		imageView213.setImage(image213);
		Root13.setCenter(imageView213);
		Button back13 = new Button("Back", imageView213);
		back13.setStyle("-fx-background-color:transparent;");
		imageView213.setFitHeight(40);
		imageView213.setFitWidth(40);
		back13.setOnAction(k1 -> {
			stage13.close();
		});

		Image image15AE = new Image(
				"https://img.icons8.com/external-icongeek26-outline-colour-icongeek26/344/external-search-project-work-icongeek26-outline-colour-icongeek26.png");
		ImageView imageView15AE = new ImageView();
		imageView15AE.setImage(image15AE);
		Root13.setCenter(imageView15AE);
		Button search13 = new Button("Search", imageView15AE);
		search13.setStyle("-fx-background-color:transparent;");
		imageView15AE.setFitHeight(40);
		imageView15AE.setFitWidth(40);
		search13.setOnAction(b102 -> {
			for (int i = 0; i < MR.getMedia().size(); i++) {
				if (MR.getMedia().get(i).getCode().equalsIgnoreCase(MediaCode13.getText())) {
					MediaTitle13.setText(MR.getMedia().get(i).getTitle());
					MediaCopies13.setText(String.valueOf(MR.getMedia().get(i).getNumber_of_copies_available()));
					rating13.setText(((Movie) MR.getMedia().get(i)).getRate());
				}
			}

			if (MediaCode13.getText() != null && !MediaCode13.getText().isEmpty()) {
				Response13.setText(MediaTitle13.getText() + " " + "Media founded !");
			} else {
				Response13.setText("Please enter the media code you want to found and delete .");
			}

		});

		DropShadow shadow13 = new DropShadow();
		update13.addEventHandler(MouseEvent.MOUSE_ENTERED, (MouseEvent e77) -> {
			update13.setEffect(shadow13);
		});
		back13.addEventHandler(MouseEvent.MOUSE_ENTERED, (MouseEvent e777) -> {
			back13.setEffect(shadow13);
		});
		search13.addEventHandler(MouseEvent.MOUSE_ENTERED, (MouseEvent e777444) -> {
			search13.setEffect(shadow13);
		});

		update13.addEventHandler(MouseEvent.MOUSE_EXITED, (MouseEvent e7777) -> {
			update13.setEffect(null);
		});
		back13.addEventHandler(MouseEvent.MOUSE_EXITED, (MouseEvent e77777) -> {
			back13.setEffect(null);
		});
		search13.addEventHandler(MouseEvent.MOUSE_EXITED, (MouseEvent e777774) -> {
			search13.setEffect(null);
		});

		root13.add(Code13, 0, 2);
		root13.add(MediaCode13, 1, 2);
		root13.add(Title13, 0, 3);
		root13.add(MediaTitle13, 1, 3);
		root13.add(Copies13, 0, 4);
		root13.add(MediaCopies13, 1, 4);
		root13.add(rate13, 0, 5);
		root13.add(rating13, 1, 5);
		root13.add(Response13, 0, 6);

		HBox hBox = new HBox();
		hBox.getChildren().addAll(update13, search13, back13);
		hBox.setAlignment(Pos.CENTER);
		hBox.setSpacing(25);
		Root13.setBottom(hBox);

		Scene scene = new Scene(Root13, 650, 500);
		stage13.setScene(scene);
		stage13.show();
	}

	private static void UpdateAlbum() {
		// TODO Auto-generated method stub
		Stage stage14 = new Stage();
		stage14.initModality(Modality.APPLICATION_MODAL);
		stage14.setMinWidth(250);
		// stage14.setMaximized(true);
		GridPane root14 = new GridPane();
		BorderPane Root14 = new BorderPane();
		Root14.setTop(root14);
		root14.setPadding(new Insets(15, 15, 15, 15));
		root14.setVgap(10);
		root14.setHgap(10);

		Label Code14 = new Label("Enter the Album Code");
		TextField MediaCode14 = new TextField();
		Code14.setStyle("-fx-font-weight:bold;");
		HBox hb214 = new HBox();
		hb214.getChildren().addAll(Code14, MediaCode14);
		hb214.setSpacing(25);

		Label Title14 = new Label("Enter the Album title");
		TextField MediaTitle14 = new TextField();
		Title14.setStyle("-fx-font-weight:bold;");
		HBox hb14 = new HBox();
		hb14.getChildren().addAll(Title14, MediaTitle14);
		hb14.setSpacing(25);

		Label Copies14 = new Label("Enter the number of copies available");
		TextField MediaCopies14 = new TextField();
		Copies14.setStyle("-fx-font-weight:bold;");
		HBox hb314 = new HBox();
		hb314.getChildren().addAll(Copies14, MediaCopies14);
		hb314.setSpacing(25);

		Label artist14 = new Label("Enter the Artist ");
		TextField artistText14 = new TextField();
		Code14.setStyle("-fx-font-weight:bold;");
		HBox hb514 = new HBox();
		hb514.getChildren().addAll(artist14, artistText14);
		hb514.setSpacing(25);
		artist14.setStyle("-fx-font-weight:bold;");

		Label song14 = new Label("Enter the Song ");
		TextField songText14 = new TextField();
		Code14.setStyle("-fx-font-weight:bold;");
		HBox hb614 = new HBox();
		hb614.getChildren().addAll(song14, songText14);
		hb614.setSpacing(25);
		song14.setStyle("-fx-font-weight:bold;");

		Image image314 = new Image("https://img.icons8.com/clouds/344/available-updates.png");
		ImageView imageView314 = new ImageView();
		imageView314.setImage(image314);
		Root14.setCenter(imageView314);
		Button update14 = new Button("Update", imageView314);
		update14.setStyle("-fx-background-color:transparent;");
		imageView314.setFitHeight(40);
		imageView314.setFitWidth(40);
		Label Response9914 = new Label();
		update14.setOnAction(l1 -> {
			for (int i = 0; i < MR.getMedia().size(); i++) {
				if (MR.getMedia().get(i).getCode().equalsIgnoreCase(MediaCode14.getText())) {
					MR.getMedia().get(i).setTitle(MediaTitle14.getText());
					MR.getMedia().get(i).setNumber_of_copies_available(Integer.parseInt(MediaCopies14.getText()));
					((Album) MR.getMedia().get(i)).setArtist(artistText14.getText());
					((Album) MR.getMedia().get(i)).setSongs(songText14.getText());
				}
			}

			if (MediaTitle14.getText() != null && !MediaTitle14.getText().isEmpty() && MediaCode14.getText() != null
					&& !MediaCode14.getText().isEmpty() && MediaCopies14.getText() != null
					&& !MediaCopies14.getText().isEmpty() && artistText14.getText() != null
					&& !artistText14.getText().isEmpty() && songText14.getText() != null
					&& !songText14.getText().isEmpty()) {
				Response9914.setText(MediaTitle14.getText() + " " + "Updated successfully !");
			} else {
				Response9914.setText("you have missing fields ! try again.");
			}

		});

		Image image214 = new Image("https://img.icons8.com/clouds/344/back.png");
		ImageView imageView214 = new ImageView();
		imageView214.setImage(image214);
		Root14.setCenter(imageView214);
		Button back14 = new Button("Back", imageView214);
		back14.setStyle("-fx-background-color:transparent;");
		imageView214.setFitHeight(40);
		imageView214.setFitWidth(40);
		back14.setOnAction(m1 -> {
			stage14.close();
		});

		Image image176 = new Image(
				"https://img.icons8.com/external-icongeek26-outline-colour-icongeek26/344/external-search-project-work-icongeek26-outline-colour-icongeek26.png");
		ImageView imageView175 = new ImageView();
		imageView175.setImage(image176);
		Root14.setCenter(imageView175);
		Button search14 = new Button("Search", imageView175);
		search14.setStyle("-fx-background-color:transparent;");
		imageView175.setFitHeight(40);
		imageView175.setFitWidth(40);
		search14.setOnAction(d1441 -> {
			for (int i = 0; i < MR.getMedia().size(); i++) {
				if (MR.getMedia().get(i).getCode().equalsIgnoreCase(MediaCode14.getText())) {
					MediaTitle14.setText(MR.getMedia().get(i).getTitle());
					MediaCopies14.setText(String.valueOf(MR.getMedia().get(i).getNumber_of_copies_available()));
					artistText14.setText(((Album) MR.getMedia().get(i)).getArtist());
					songText14.setText(((Album) MR.getMedia().get(i)).getSongs());
				}
			}

			if (MediaCode14.getText() != null && !MediaCode14.getText().isEmpty()) {
				Response9914.setText(MediaTitle14.getText() + " " + "Media founded !");
			} else {
				Response9914.setText("Please enter the media code you want to found and delete .");
			}

		});

		DropShadow shadow14 = new DropShadow();
		update14.addEventHandler(MouseEvent.MOUSE_ENTERED, (MouseEvent e777777) -> {
			update14.setEffect(shadow14);
		});
		back14.addEventHandler(MouseEvent.MOUSE_ENTERED, (MouseEvent e7777777) -> {
			back14.setEffect(shadow14);
		});
		search14.addEventHandler(MouseEvent.MOUSE_ENTERED, (MouseEvent e77777477) -> {
			search14.setEffect(shadow14);
		});
		update14.addEventHandler(MouseEvent.MOUSE_EXITED, (MouseEvent e77777777) -> {
			update14.setEffect(null);
		});
		back14.addEventHandler(MouseEvent.MOUSE_EXITED, (MouseEvent e777777777) -> {
			back14.setEffect(null);
		});
		search14.addEventHandler(MouseEvent.MOUSE_EXITED, (MouseEvent e7777777477) -> {
			search14.setEffect(null);
		});

		root14.add(Code14, 0, 2);
		root14.add(MediaCode14, 1, 2);
		root14.add(Title14, 0, 3);
		root14.add(MediaTitle14, 1, 3);
		root14.add(Copies14, 0, 4);
		root14.add(MediaCopies14, 1, 4);
		root14.add(artist14, 0, 5);
		root14.add(artistText14, 1, 5);
		root14.add(song14, 0, 6);
		root14.add(songText14, 1, 6);
		root14.add(Response9914, 0, 7);

		HBox hBox14 = new HBox();
		hBox14.getChildren().addAll(update14, search14, back14);
		hBox14.setAlignment(Pos.CENTER);
		hBox14.setSpacing(25);
		Root14.setBottom(hBox14);

		Scene scene = new Scene(Root14, 650, 500);
		stage14.setTitle("Update Album Stage");
		stage14.setScene(scene);
		stage14.show();
	}

	private static void UpdateGame() {
		// TODO Auto-generated method stub
		Stage stage15 = new Stage();
		stage15.initModality(Modality.APPLICATION_MODAL);
		stage15.setTitle("Update Game Stage");
		stage15.setMinWidth(250);
		// stage15.setMaximized(true);
		GridPane root15 = new GridPane();
		BorderPane Root15 = new BorderPane();
		Root15.setTop(root15);
		root15.setPadding(new Insets(15, 15, 15, 15));
		root15.setVgap(10);
		root15.setHgap(10);

		Label Code15 = new Label("Enter the Game Code");
		TextField MediaCode15 = new TextField();
		Code15.setStyle("-fx-font-weight:bold;");
		HBox hb215 = new HBox();
		hb215.getChildren().addAll(Code15, MediaCode15);
		hb215.setSpacing(25);

		Label Title15 = new Label("Enter the Game title");
		TextField MediaTitle15 = new TextField();
		Title15.setStyle("-fx-font-weight:bold;");
		HBox hb15 = new HBox();
		hb15.getChildren().addAll(Title15, MediaTitle15);
		hb15.setSpacing(25);

		Label Copies15 = new Label("Enter the number of copies available");
		TextField MediaCopies15 = new TextField();
		Copies15.setStyle("-fx-font-weight:bold;");
		HBox hb315 = new HBox();
		hb315.getChildren().addAll(Copies15, MediaCopies15);
		hb315.setSpacing(25);

		Label weight15 = new Label("Enter the Weight (in grams)");
		TextField weightText15 = new TextField();
		Code15.setStyle("-fx-font-weight:bold;");
		HBox hb415 = new HBox();
		hb415.getChildren().addAll(weight15, weightText15);
		hb415.setSpacing(25);
		weight15.setStyle("-fx-font-weight:bold;");

		Image image315 = new Image("https://img.icons8.com/clouds/344/available-updates.png");
		ImageView imageView315 = new ImageView();
		imageView315.setImage(image315);
		Root15.setCenter(imageView315);
		Button update15 = new Button("Update", imageView315);
		update15.setStyle("-fx-background-color:transparent;");
		imageView315.setFitHeight(40);
		imageView315.setFitWidth(40);
		Label Response8815 = new Label();
		update15.setOnAction(n1 -> {
			for (int i = 0; i < MR.getMedia().size(); i++) {
				if (MR.getMedia().get(i).getCode().equalsIgnoreCase(MediaCode15.getText())) {
					MR.getMedia().get(i).setTitle(MediaTitle15.getText());
					MR.getMedia().get(i).setNumber_of_copies_available(Integer.parseInt(MediaCopies15.getText()));
					((Game) MR.getMedia().get(i)).setWeight(Double.parseDouble(weightText15.getText()));
				}
			}

			if (MediaTitle15.getText() != null && !MediaTitle15.getText().isEmpty() && MediaCode15.getText() != null
					&& !MediaCode15.getText().isEmpty() && MediaCopies15.getText() != null
					&& !MediaCopies15.getText().isEmpty() && weightText15.getText() != null
					&& !weightText15.getText().isEmpty()) {
				Response8815.setText(MediaTitle15.getText() + " " + "Updated successfully !");
			} else {
				Response8815.setText("you have missing fields ! try again.");
			}

		});

		Image image215 = new Image("https://img.icons8.com/clouds/344/back.png");
		ImageView imageView215 = new ImageView();
		imageView215.setImage(image215);
		Root15.setCenter(imageView215);
		Button back15 = new Button("Back", imageView215);
		back15.setStyle("-fx-background-color:transparent;");
		imageView215.setFitHeight(40);
		imageView215.setFitWidth(40);
		back15.setOnAction(o1 -> {
			stage15.close();
		});

		Image image193 = new Image(
				"https://img.icons8.com/external-icongeek26-outline-colour-icongeek26/344/external-search-project-work-icongeek26-outline-colour-icongeek26.png");
		ImageView imageView193 = new ImageView();
		imageView193.setImage(image193);
		Root15.setCenter(imageView193);
		Button search15 = new Button("Search", imageView193);
		search15.setStyle("-fx-background-color:transparent;");
		imageView193.setFitHeight(40);
		imageView193.setFitWidth(40);
		search15.setOnAction(g1e -> {
			for (int i = 0; i < MR.getMedia().size(); i++) {
				if (MR.getMedia().get(i).getCode().equalsIgnoreCase(MediaCode15.getText())) {
					MediaTitle15.setText(MR.getMedia().get(i).getTitle());
					MediaCopies15.setText(String.valueOf(MR.getMedia().get(i).getNumber_of_copies_available()));
					weightText15.setText(String.valueOf(((Game) MR.getMedia().get(i)).getWeight()));
				}
			}

			if (MediaCode15.getText() != null && !MediaCode15.getText().isEmpty()) {
				Response8815.setText(MediaTitle15.getText() + " " + "Media founded ! (❁´◡`❁) ");
			} else {
				Response8815.setText("Please enter the media code you want to found and delete . (●'◡'●) ");
			}

		});

		DropShadow shadow15 = new DropShadow();
		update15.addEventHandler(MouseEvent.MOUSE_ENTERED, (MouseEvent e11) -> {
			update15.setEffect(shadow15);
		});
		back15.addEventHandler(MouseEvent.MOUSE_ENTERED, (MouseEvent e111) -> {
			back15.setEffect(shadow15);
		});
		search15.addEventHandler(MouseEvent.MOUSE_ENTERED, (MouseEvent ewf) -> {
			search15.setEffect(shadow15);
		});
		update15.addEventHandler(MouseEvent.MOUSE_EXITED, (MouseEvent e1111) -> {
			update15.setEffect(null);
		});
		back15.addEventHandler(MouseEvent.MOUSE_EXITED, (MouseEvent e11111) -> {
			back15.setEffect(null);
		});
		search15.addEventHandler(MouseEvent.MOUSE_EXITED, (MouseEvent eew) -> {
			search15.setEffect(null);
		});

		root15.add(Code15, 0, 2);
		root15.add(MediaCode15, 1, 2);
		root15.add(Title15, 0, 3);
		root15.add(MediaTitle15, 1, 3);
		root15.add(Copies15, 0, 4);
		root15.add(MediaCopies15, 1, 4);
		root15.add(weight15, 0, 5);
		root15.add(weightText15, 1, 5);
		root15.add(Response8815, 0, 6);

		HBox hBox15 = new HBox();
		hBox15.getChildren().addAll(update15, search15, back15);
		hBox15.setAlignment(Pos.CENTER);
		hBox15.setSpacing(25);
		Root15.setBottom(hBox15);

		Scene scene = new Scene(Root15, 650, 500);
		stage15.setScene(scene);
		stage15.show();
	}

	private static void DeleteMedia() {
		// TODO Auto-generated method stub
		Stage stage16 = new Stage();
		stage16.initModality(Modality.APPLICATION_MODAL);
		stage16.setTitle("Customer Stage");
		stage16.setMinWidth(250);
		// stage16.setMaximized(true);
		GridPane root16 = new GridPane();
		BorderPane Root16 = new BorderPane();
		Root16.setTop(root16);
		root16.setPadding(new Insets(15, 15, 15, 15));
		root16.setVgap(10);
		root16.setHgap(10);

		final ComboBox<String> Combo_Box1616 = new ComboBox<>();
		Combo_Box1616.getItems().addAll("Game", "Album", "Movie");
		Combo_Box1616.setValue("Media type");
		Label label116 = new Label("The media is: ");
		label116.setStyle("-fx-text-fill:#0076a3;");
		label116.setStyle("-fx-font-size:16.0px;");
		label116.setStyle("-fx-font-weight:bold;");
		root16.add(label116, 0, 0);
		root16.add(Combo_Box1616, 1, 0);
		Combo_Box1616.setOnAction(p17 -> {
			if (Combo_Box1616.getValue().equals("Game")) {
				DeleteGame();
			}

			else if (Combo_Box1616.getValue().equals("Album")) {
				DeleteAlbum();
			}

			else if (Combo_Box1616.getValue().equals("Movie")) {
				DeleteMovie();
			}

		});

		Image image216 = new Image("https://img.icons8.com/nolan/344/back.png");
		ImageView imageView216 = new ImageView();
		imageView216.setImage(image216);
		Root16.setCenter(imageView216);
		Button back16 = new Button("Back", imageView216);
		back16.setStyle("-fx-background-color:transparent;");
		imageView216.setFitHeight(40);
		imageView216.setFitWidth(40);
		back16.setOnAction(q1 -> {
			stage16.close();
		});

		DropShadow shadow16 = new DropShadow();
		back16.addEventHandler(MouseEvent.MOUSE_ENTERED, (MouseEvent e111111) -> {
			back16.setEffect(shadow16);
		});

		back16.addEventHandler(MouseEvent.MOUSE_EXITED, (MouseEvent e1111111) -> {
			back16.setEffect(null);
		});

		HBox hBox16 = new HBox();
		hBox16.getChildren().addAll(back16);
		hBox16.setAlignment(Pos.CENTER);
		hBox16.setSpacing(25);
		Root16.setBottom(hBox16);

		Scene scene = new Scene(Root16, 650, 500);
		stage16.setTitle("Delete Media");
		stage16.setScene(scene);
		stage16.show();
	}

	private static void DeleteMovie() {
		// TODO Auto-generated method stub
		Stage stage17 = new Stage();
		stage17.initModality(Modality.APPLICATION_MODAL);
		stage17.setTitle("Delete Media Stage");
		stage17.setMinWidth(250);
		// stage17.setMaximized(true);
		GridPane root17 = new GridPane();
		BorderPane Root17 = new BorderPane();
		Root17.setTop(root17);
		root17.setPadding(new Insets(15, 15, 15, 15));
		root17.setVgap(10);
		root17.setHgap(10);

		Label Code17 = new Label("Enter the Media Code");
		TextField MediaCode17 = new TextField();
		Code17.setStyle("-fx-font-weight:bold;");
		HBox hb217 = new HBox();
		hb217.getChildren().addAll(Code17, MediaCode17);
		hb217.setSpacing(25);

		Label Title17 = new Label("The Media title is :");
		TextField MediaTitle17 = new TextField();
		Title17.setStyle("-fx-font-weight:bold;");
		HBox hb17 = new HBox();
		hb17.getChildren().addAll(Title17, MediaTitle17);
		hb17.setSpacing(25);

		Label Copies17 = new Label("The number of copies available is :");
		TextField MediaCopies17 = new TextField();
		Copies17.setStyle("-fx-font-weight:bold;");
		HBox hb317 = new HBox();
		hb317.getChildren().addAll(Copies17, MediaCopies17);
		hb317.setSpacing(25);

		Label rate17 = new Label("The Rating is :");
		TextField rateText17 = new TextField();
		Code17.setStyle("-fx-font-weight:bold;");
		HBox hb117 = new HBox();
		hb117.getChildren().addAll(rate17, rateText17);
		hb117.setSpacing(25);
		rate17.setStyle("-fx-font-weight:bold;");

		Label Response7817 = new Label();

		Image image117 = new Image(
				"https://img.icons8.com/external-kiranshastry-gradient-kiranshastry/344/external-delete-miscellaneous-kiranshastry-gradient-kiranshastry.png",
				40, 40, false, false);
		ImageView imageView117 = new ImageView();
		imageView117.setImage(image117);
		Root17.setCenter(imageView117);
		Button delete17 = new Button("Delete", imageView117);
		delete17.setStyle("-fx-background-color:transparent;");
		delete17.setOnAction(r1 -> {
			for (int i = 0; i < MR.getMedia().size(); i++) {
				if (MR.getMedia().get(i).getCode().equalsIgnoreCase(MediaCode17.getText())) {
					MR.getMedia().remove(MR.getMedia().get(i));
				}
			}

			if (MediaCode17.getText() != null && !MediaCode17.getText().isEmpty() && MediaTitle17.getText() != null
					&& !MediaTitle17.getText().isEmpty() && MediaCopies17.getText() != null
					&& !MediaCopies17.getText().isEmpty() && rateText17.getText() != null
					&& !rateText17.getText().isEmpty()) {
				Response7817
						.setText(" The Customer " + MediaTitle17.getText() + " " + "has been deleted successfully !");
			} else {
				Response7817.setText("you have missing fields ! try again.");
			}
		});

		Image image217 = new Image("https://img.icons8.com/nolan/344/back.png");
		ImageView imageView217 = new ImageView();
		imageView217.setImage(image217);
		Root17.setCenter(imageView217);
		Button back17 = new Button("Back", imageView217);
		back17.setStyle("-fx-background-color:transparent;");
		imageView217.setFitHeight(40);
		imageView217.setFitWidth(40);
		back17.setOnAction(s1 -> {
			stage17.close();
		});

		Image image317 = new Image(
				"https://img.icons8.com/external-kiranshastry-gradient-kiranshastry/344/external-find-hotel-kiranshastry-gradient-kiranshastry.png");
		ImageView imageView317 = new ImageView();
		imageView317.setImage(image317);
		Root17.setCenter(imageView317);
		Button find17 = new Button("Find", imageView317);
		find17.setStyle("-fx-background-color:transparent;");
		imageView317.setFitHeight(40);
		imageView317.setFitWidth(40);
		find17.setOnAction(t1 -> {
			for (int i = 0; i < MR.getMedia().size(); i++) {
				if (MR.getMedia().get(i).getCode().equalsIgnoreCase(MediaCode17.getText())) {
					MediaTitle17.setText(MR.getMedia().get(i).getTitle());
					MediaCopies17.setText(String.valueOf(MR.getMedia().get(i).getNumber_of_copies_available()));
					rateText17.setText(((Movie) MR.getMedia().get(i)).getRate());
				}
			}
			if (MediaCode17.getText() != null && !MediaCode17.getText().isEmpty()) {
				Response7817.setText(MediaTitle17.getText() + " " + "Media founded !");
			} else {
				Response7817.setText("Please enter the media code you want to found and delete .");
			}
		});

		DropShadow shadow17 = new DropShadow();
		delete17.addEventHandler(MouseEvent.MOUSE_ENTERED, (MouseEvent e22) -> {
			delete17.setEffect(shadow17);
		});
		find17.addEventHandler(MouseEvent.MOUSE_ENTERED, (MouseEvent e222) -> {
			find17.setEffect(shadow17);
		});
		back17.addEventHandler(MouseEvent.MOUSE_ENTERED, (MouseEvent e2222) -> {
			back17.setEffect(shadow17);
		});

		delete17.addEventHandler(MouseEvent.MOUSE_EXITED, (MouseEvent e22222) -> {
			delete17.setEffect(null);
		});
		find17.addEventHandler(MouseEvent.MOUSE_EXITED, (MouseEvent e222222) -> {
			find17.setEffect(null);
		});
		back17.addEventHandler(MouseEvent.MOUSE_EXITED, (MouseEvent e2222222) -> {
			back17.setEffect(null);
		});

		root17.add(Code17, 0, 1);
		root17.add(MediaCode17, 1, 1);
		root17.add(Title17, 0, 2);
		root17.add(MediaTitle17, 1, 2);
		root17.add(Copies17, 0, 3);
		root17.add(MediaCopies17, 1, 3);
		root17.add(rate17, 0, 4);
		root17.add(rateText17, 1, 4);
		root17.add(Response7817, 0, 5);

		HBox hBox = new HBox();
		hBox.getChildren().addAll(find17, delete17, back17);
		hBox.setAlignment(Pos.CENTER);
		hBox.setSpacing(25);
		Root17.setBottom(hBox);

		Scene scene = new Scene(Root17, 650, 500);
		stage17.setTitle("Delete Movie");
		stage17.setScene(scene);
		stage17.show();

	}

	private static void DeleteAlbum() {
		// TODO Auto-generated method stub
		Stage stage18 = new Stage();
		stage18.initModality(Modality.APPLICATION_MODAL);
		stage18.setMinWidth(250);
		// stage18.setMaximized(true);
		GridPane root18 = new GridPane();
		BorderPane Root18 = new BorderPane();
		Root18.setTop(root18);
		root18.setPadding(new Insets(15, 15, 15, 15));
		root18.setVgap(10);
		root18.setHgap(10);

		Label Code18 = new Label("Enter the Media Code");
		TextField MediaCode18 = new TextField();
		Code18.setStyle("-fx-font-weight:bold;");
		HBox hb218 = new HBox();
		hb218.getChildren().addAll(Code18, MediaCode18);
		hb218.setSpacing(25);

		Label Title18 = new Label("The Media title is :");
		TextField MediaTitle18 = new TextField();
		Title18.setStyle("-fx-font-weight:bold;");
		HBox hb18 = new HBox();
		hb18.getChildren().addAll(Title18, MediaTitle18);
		hb18.setSpacing(25);

		Label Copies18 = new Label("The number of copies available is :");
		TextField MediaCopies18 = new TextField();
		Copies18.setStyle("-fx-font-weight:bold;");
		HBox hb318 = new HBox();
		hb318.getChildren().addAll(Copies18, MediaCopies18);
		hb318.setSpacing(25);

		Label artist18 = new Label("The Artist is :");
		TextField artistText18 = new TextField();
		Code18.setStyle("-fx-font-weight:bold;");
		HBox hb518 = new HBox();
		hb518.getChildren().addAll(artist18, artistText18);
		hb518.setSpacing(25);
		artist18.setStyle("-fx-font-weight:bold;");

		Label song18 = new Label("The Song is :");
		TextField songText18 = new TextField();
		Code18.setStyle("-fx-font-weight:bold;");
		HBox hb618 = new HBox();
		hb618.getChildren().addAll(song18, songText18);
		hb618.setSpacing(25);
		song18.setStyle("-fx-font-weight:bold;");

		Label Response181 = new Label();

		Image image118 = new Image(
				"https://img.icons8.com/external-kiranshastry-gradient-kiranshastry/344/external-delete-miscellaneous-kiranshastry-gradient-kiranshastry.png",
				40, 40, false, false);
		ImageView imageView118 = new ImageView();
		imageView118.setImage(image118);
		Root18.setCenter(imageView118);
		Button delete18 = new Button("Delete", imageView118);
		delete18.setStyle("-fx-background-color:transparent;");
		delete18.setOnAction(u1 -> {
			for (int i = 0; i < MR.getMedia().size(); i++) {
				if (MR.getMedia().get(i).getCode().equalsIgnoreCase(MediaCode18.getText())) {
					MR.getMedia().remove(MR.getMedia().get(i));
				}
			}

			if (MediaCode18.getText() != null && !MediaCode18.getText().isEmpty() && MediaTitle18.getText() != null
					&& !MediaTitle18.getText().isEmpty() && MediaCopies18.getText() != null
					&& !MediaCopies18.getText().isEmpty() && artistText18.getText() != null
					&& !artistText18.getText().isEmpty() && songText18.getText() != null
					&& !songText18.getText().isEmpty()) {
				Response181
						.setText(" The Customer " + MediaTitle18.getText() + " " + "has been deleted successfully !");
			} else {
				Response181.setText("you have missing fields ! try again.");
			}
		});

		Image image218 = new Image("https://img.icons8.com/nolan/344/back.png");
		ImageView imageView218 = new ImageView();
		imageView218.setImage(image218);
		Root18.setCenter(imageView218);
		Button back18 = new Button("Back", imageView218);
		back18.setStyle("-fx-background-color:transparent;");
		imageView218.setFitHeight(40);
		imageView218.setFitWidth(40);
		back18.setOnAction(v1 -> {
			stage18.close();
		});

		Image image318 = new Image(
				"https://img.icons8.com/external-kiranshastry-gradient-kiranshastry/344/external-find-hotel-kiranshastry-gradient-kiranshastry.png");
		ImageView imageView318 = new ImageView();
		imageView318.setImage(image318);
		Root18.setCenter(imageView318);
		Button find18 = new Button("Find", imageView318);
		find18.setStyle("-fx-background-color:transparent;");
		imageView318.setFitHeight(40);
		imageView318.setFitWidth(40);
		find18.setOnAction(w1 -> {
			for (int i = 0; i < MR.getMedia().size(); i++) {
				if (MR.getMedia().get(i).getCode().equalsIgnoreCase(MediaCode18.getText())) {
					MediaTitle18.setText(MR.getMedia().get(i).getTitle());
					MediaCopies18.setText(String.valueOf(MR.getMedia().get(i).getNumber_of_copies_available()));
					artistText18.setText(((Album) MR.getMedia().get(i)).getArtist());
					songText18.setText(((Album) MR.getMedia().get(i)).getSongs());
				}
			}
			if (MediaCode18.getText() != null && !MediaCode18.getText().isEmpty()) {
				Response181.setText(MediaTitle18.getText() + " " + "Media founded !");
			} else {
				Response181.setText("Please enter the media code you want to found and delete .");
			}
		});

		DropShadow shadow18 = new DropShadow();
		delete18.addEventHandler(MouseEvent.MOUSE_ENTERED, (MouseEvent efd) -> {
			delete18.setEffect(shadow18);
		});
		find18.addEventHandler(MouseEvent.MOUSE_ENTERED, (MouseEvent ehdf) -> {
			find18.setEffect(shadow18);
		});
		back18.addEventHandler(MouseEvent.MOUSE_ENTERED, (MouseEvent esfh) -> {
			back18.setEffect(shadow18);
		});

		delete18.addEventHandler(MouseEvent.MOUSE_EXITED, (MouseEvent eHsf) -> {
			delete18.setEffect(null);
		});
		find18.addEventHandler(MouseEvent.MOUSE_EXITED, (MouseEvent esfh) -> {
			find18.setEffect(null);
		});
		back18.addEventHandler(MouseEvent.MOUSE_EXITED, (MouseEvent eFSH) -> {
			back18.setEffect(null);
		});

		root18.add(Code18, 0, 1);
		root18.add(MediaCode18, 1, 1);
		root18.add(Title18, 0, 2);
		root18.add(MediaTitle18, 1, 2);
		root18.add(Copies18, 0, 3);
		root18.add(MediaCopies18, 1, 3);
		root18.add(artist18, 0, 4);
		root18.add(artistText18, 1, 4);
		root18.add(song18, 0, 5);
		root18.add(songText18, 1, 5);
		root18.add(Response181, 0, 6);

		HBox hBox18 = new HBox();
		hBox18.getChildren().addAll(find18, delete18, back18);
		hBox18.setAlignment(Pos.CENTER);
		hBox18.setSpacing(25);
		Root18.setBottom(hBox18);

		Scene scene = new Scene(Root18, 650, 500);
		stage18.setTitle("Delete Album");
		stage18.setScene(scene);
		stage18.show();

	}

	private static void DeleteGame() {
		// TODO Auto-generated method stub
		Stage stage19 = new Stage();
		stage19.initModality(Modality.APPLICATION_MODAL);
		stage19.setTitle("Delete Media Stage");
		stage19.setMinWidth(250);
		// stage19.setMaximized(true);
		GridPane root19 = new GridPane();
		BorderPane Root19 = new BorderPane();
		Root19.setTop(root19);
		root19.setPadding(new Insets(15, 15, 15, 15));
		root19.setVgap(10);
		root19.setHgap(10);

		Label Code19 = new Label("Enter the Media Code");
		TextField MediaCode19 = new TextField();
		Code19.setStyle("-fx-font-weight:bold;");
		HBox hb219 = new HBox();
		hb219.getChildren().addAll(Code19, MediaCode19);
		hb219.setSpacing(25);

		Label Title19 = new Label("The Media title is :");
		TextField MediaTitle19 = new TextField();
		Title19.setStyle("-fx-font-weight:bold;");
		HBox hb19 = new HBox();
		hb19.getChildren().addAll(Title19, MediaTitle19);
		hb19.setSpacing(25);

		Label Copies19 = new Label("The number of copies available is :");
		TextField MediaCopies19 = new TextField();
		Copies19.setStyle("-fx-font-weight:bold;");
		HBox hb319 = new HBox();
		hb319.getChildren().addAll(Copies19, MediaCopies19);
		hb319.setSpacing(25);

		Label weight19 = new Label("The Weight (in grams) is :");
		TextField weightText19 = new TextField();
		Code19.setStyle("-fx-font-weight:bold;");
		HBox hb419 = new HBox();
		hb419.getChildren().addAll(weight19, weightText19);
		hb419.setSpacing(25);
		weight19.setStyle("-fx-font-weight:bold;");

		Label Response1919 = new Label();

		Image image119 = new Image(
				"https://img.icons8.com/external-kiranshastry-gradient-kiranshastry/344/external-delete-miscellaneous-kiranshastry-gradient-kiranshastry.png",
				40, 40, false, false);
		ImageView imageView119 = new ImageView();
		imageView119.setImage(image119);
		Root19.setCenter(imageView119);
		Button delete19 = new Button("Delete", imageView119);
		delete19.setStyle("-fx-background-color:transparent;");
		delete19.setOnAction(x1 -> {
			for (int i = 0; i < MR.getMedia().size(); i++) {
				if (MR.getMedia().get(i).getCode().equalsIgnoreCase(MediaCode19.getText())) {
					MR.getMedia().remove(MR.getMedia().get(i));
				}
			}

			if (MediaCode19.getText() != null && !MediaCode19.getText().isEmpty() && MediaTitle19.getText() != null
					&& !MediaTitle19.getText().isEmpty() && MediaCopies19.getText() != null
					&& !MediaCopies19.getText().isEmpty() && weightText19.getText() != null
					&& !weightText19.getText().isEmpty()) {
				Response1919
						.setText(" The Customer " + MediaTitle19.getText() + " " + "has been deleted successfully !");
			} else {
				Response1919.setText("you have missing fields ! try again.");
			}
		});

		Image image219 = new Image("https://img.icons8.com/nolan/344/back.png");
		ImageView imageView219 = new ImageView();
		imageView219.setImage(image219);
		Root19.setCenter(imageView219);
		Button back19 = new Button("Back", imageView219);
		back19.setStyle("-fx-background-color:transparent;");
		imageView219.setFitHeight(40);
		imageView219.setFitWidth(40);
		back19.setOnAction(y1 -> {
			stage19.close();
		});

		Image image319 = new Image(
				"https://img.icons8.com/external-kiranshastry-gradient-kiranshastry/344/external-find-hotel-kiranshastry-gradient-kiranshastry.png");
		ImageView imageView319 = new ImageView();
		imageView319.setImage(image319);
		Root19.setCenter(imageView319);
		Button find19 = new Button("Find", imageView319);
		find19.setStyle("-fx-background-color:transparent;");
		imageView319.setFitHeight(40);
		imageView319.setFitWidth(40);
		find19.setOnAction(z1 -> {
			for (int i = 0; i < MR.getMedia().size(); i++) {
				if (MR.getMedia().get(i).getCode().equalsIgnoreCase(MediaCode19.getText())) {
					MediaTitle19.setText(MR.getMedia().get(i).getTitle());
					MediaCopies19.setText(String.valueOf(MR.getMedia().get(i).getNumber_of_copies_available()));
					weightText19.setText(String.valueOf(((Game) MR.getMedia().get(i)).getWeight()));

				}
			}
			if (MediaCode19.getText() != null && !MediaCode19.getText().isEmpty()) {
				Response1919.setText(MediaTitle19.getText() + " " + "Media founded !");
			} else {
				Response1919.setText("Please enter the media code you want to found and delete .");
			}
		});

		DropShadow shadow19 = new DropShadow();
		delete19.addEventHandler(MouseEvent.MOUSE_ENTERED, (MouseEvent eJKJK) -> {
			delete19.setEffect(shadow19);
		});
		find19.addEventHandler(MouseEvent.MOUSE_ENTERED, (MouseEvent eTKJ) -> {
			find19.setEffect(shadow19);
		});
		back19.addEventHandler(MouseEvent.MOUSE_ENTERED, (MouseEvent eKTK) -> {
			back19.setEffect(shadow19);
		});

		delete19.addEventHandler(MouseEvent.MOUSE_EXITED, (MouseEvent eTTT) -> {
			delete19.setEffect(null);
		});
		find19.addEventHandler(MouseEvent.MOUSE_EXITED, (MouseEvent eUUK) -> {
			find19.setEffect(null);
		});
		back19.addEventHandler(MouseEvent.MOUSE_EXITED, (MouseEvent eKKKT) -> {
			back19.setEffect(null);
		});

		root19.add(Code19, 0, 1);
		root19.add(MediaCode19, 1, 1);
		root19.add(Title19, 0, 2);
		root19.add(MediaTitle19, 1, 2);
		root19.add(Copies19, 0, 3);
		root19.add(MediaCopies19, 1, 3);
		root19.add(weight19, 0, 4);
		root19.add(weightText19, 1, 4);
		root19.add(Response1919, 0, 5);

		HBox hBox19 = new HBox();
		hBox19.getChildren().addAll(find19, delete19, back19);
		hBox19.setAlignment(Pos.CENTER);
		hBox19.setSpacing(25);
		Root19.setBottom(hBox19);

		Scene scene = new Scene(Root19, 650, 500);
		stage19.setTitle("Delete Game");
		stage19.setScene(scene);
		stage19.show();

	}

	private static void AddNewMedia() {
		// TODO Auto-generated method stub
		Stage stage20 = new Stage();
		stage20.initModality(Modality.APPLICATION_MODAL);
		stage20.setTitle("Customer Stage");
		stage20.setMinWidth(250);
		// stage20.setMaximized(true);
		GridPane root20 = new GridPane();
		BorderPane Root20 = new BorderPane();
		Root20.setTop(root20);
		root20.setPadding(new Insets(15, 15, 15, 15));
		root20.setVgap(10);
		root20.setHgap(10);

		Image image20 = new Image("https://img.icons8.com/clouds/344/back.png");
		ImageView imageView20 = new ImageView();
		imageView20.setImage(image20);
		Root20.setCenter(imageView20);
		Button back20 = new Button("Back", imageView20);
		back20.setStyle("-fx-background-color:transparent;");
		imageView20.setFitHeight(40);
		imageView20.setFitWidth(40);
		back20.setOnAction(a2 -> {
			stage20.close();
		});

		final ComboBox<String> Combo_Box2020 = new ComboBox<>();
		Combo_Box2020.getItems().addAll("Game", "Album", "Movie");
		Combo_Box2020.setValue("Media type");
		Label label120 = new Label("The media is: ");
		label120.setStyle("-fx-text-fill:#0076a3;");
		label120.setStyle("-fx-font-size:16.0px;");
		label120.setStyle("-fx-font-weight:bold;");
		root20.add(label120, 0, 0);
		root20.add(Combo_Box2020, 1, 0);
		Combo_Box2020.setOnAction(b2 -> {
			if (Combo_Box2020.getValue().equals("Game")) {
				AddGame();
			}

			else if (Combo_Box2020.getValue().equals("Album")) {
				AddAlbum();
			}

			else if (Combo_Box2020.getValue().equals("Movie")) {
				AddMovie();
			}

		});

		DropShadow shadow20 = new DropShadow();
		back20.addEventHandler(MouseEvent.MOUSE_ENTERED, (MouseEvent evad) -> {
			back20.setEffect(shadow20);
		});

		back20.addEventHandler(MouseEvent.MOUSE_EXITED, (MouseEvent egqe) -> {
			back20.setEffect(null);
		});

		HBox hBox20 = new HBox();
		hBox20.getChildren().addAll(back20);
		hBox20.setAlignment(Pos.CENTER);
		hBox20.setSpacing(25);
		Root20.setBottom(hBox20);

		Scene scene = new Scene(Root20, 650, 500);
		stage20.setTitle("Add New Media");
		stage20.setScene(scene);
		stage20.show();
	}

	private static void AddMovie() {
		// TODO Auto-generated method stub
		Stage stage111 = new Stage();
		stage111.initModality(Modality.APPLICATION_MODAL);
		stage111.setTitle("Movie Stage");
		stage111.setMinWidth(250);
		// stage111.setMaximized(true);
		GridPane root111 = new GridPane();
		BorderPane Root111 = new BorderPane();
		Root111.setTop(root111);
		root111.setPadding(new Insets(15, 15, 15, 15));
		root111.setVgap(10);
		root111.setHgap(10);

		Label Code111 = new Label("Enter the Movie Code");
		TextField MediaCode111 = new TextField();
		Code111.setStyle("-fx-font-weight:bold;");
		HBox hb2111 = new HBox();
		hb2111.getChildren().addAll(Code111, MediaCode111);
		hb2111.setSpacing(25);

		Label Title111 = new Label("Enter the Movie title");
		TextField MediaTitle111 = new TextField();
		Title111.setStyle("-fx-font-weight:bold;");
		HBox hb111 = new HBox();
		hb111.getChildren().addAll(Title111, MediaTitle111);
		hb111.setSpacing(25);

		Label Copies111 = new Label("Enter the number of copies available");
		TextField MediaCopies111 = new TextField();
		Copies111.setStyle("-fx-font-weight:bold;");
		HBox hb3111 = new HBox();
		hb3111.getChildren().addAll(Copies111, MediaCopies111);
		hb3111.setSpacing(25);

//		Label rate = new Label("Enter the Rating ");
//		final ComboBox<String> rating = new ComboBox<>();
//		rating.getItems().addAll("AC", "DR", "HR");
//		rating.setValue("The rate is : ");

		Label rate111 = new Label("Enter the number of copies available");
		TextField rating111 = new TextField("AC, HR, DR");
		Copies111.setStyle("-fx-font-weight:bold;");
		HBox hb38111 = new HBox();
		hb38111.getChildren().addAll(rate111, rating111);
		hb38111.setSpacing(25);
		rate111.setStyle("-fx-text-fill:#0076a3;");
		rate111.setStyle("-fx-font-size:16.0px;");
		rate111.setStyle("-fx-font-weight:bold;");

		Label Response111 = new Label();

		Image image1111 = new Image("https://img.icons8.com/office/344/add.png", 40, 40, false, false);
		ImageView imageView1111 = new ImageView();
		imageView1111.setImage(image1111);
		Root111.setCenter(imageView1111);
		Button add111 = new Button("Add", imageView1111);
		add111.setStyle("-fx-background-color:transparent;");
		add111.setOnAction(c2 -> {
			MR.addMovie(MediaCode111.getText(), MediaTitle111.getText(), Integer.parseInt(MediaCopies111.getText()),
					rating111.getText());

			if (MediaTitle111.getText() != null && !MediaTitle111.getText().isEmpty() && MediaCode111.getText() != null
					&& !MediaCode111.getText().isEmpty() && MediaCopies111.getText() != null
					&& !MediaCopies111.getText().isEmpty()) {
				Response111.setText(MediaTitle111.getText() + " " + "Registration completed !");
			} else {
				Response111.setText("you have missing fields ! try again.");
			}
		});

		Image image2111 = new Image("https://img.icons8.com/clouds/344/back.png");
		ImageView imageView2111 = new ImageView();
		imageView2111.setImage(image2111);
		Root111.setCenter(imageView2111);
		Button back111 = new Button("Back", imageView2111);
		back111.setStyle("-fx-background-color:transparent;");
		imageView2111.setFitHeight(40);
		imageView2111.setFitWidth(40);
		back111.setOnAction(d2 -> {
			stage111.close();
		});

		DropShadow shadow111 = new DropShadow();
		add111.addEventHandler(MouseEvent.MOUSE_ENTERED, (MouseEvent eQQQQQ) -> {
			add111.setEffect(shadow111);
		});
		back111.addEventHandler(MouseEvent.MOUSE_ENTERED, (MouseEvent eWWWWW) -> {
			back111.setEffect(shadow111);
		});

		add111.addEventHandler(MouseEvent.MOUSE_EXITED, (MouseEvent eEEEEE) -> {
			add111.setEffect(null);
		});
		back111.addEventHandler(MouseEvent.MOUSE_EXITED, (MouseEvent eRRRRR) -> {
			back111.setEffect(null);
		});

		root111.add(Code111, 0, 2);
		root111.add(MediaCode111, 1, 2);
		root111.add(Title111, 0, 3);
		root111.add(MediaTitle111, 1, 3);
		root111.add(Copies111, 0, 4);
		root111.add(MediaCopies111, 1, 4);
		root111.add(rate111, 0, 5);
		root111.add(rating111, 1, 5);
		root111.add(Response111, 0, 6);

		HBox hBox111 = new HBox();
		hBox111.getChildren().addAll(add111, back111);
		hBox111.setAlignment(Pos.CENTER);
		hBox111.setSpacing(25);
		Root111.setBottom(hBox111);

		Scene scene = new Scene(Root111, 650, 500);
		stage111.setTitle("Add New Media");
		stage111.setScene(scene);
		stage111.show();
	}

	private static void AddAlbum() {
		// TODO Auto-generated method stub
		Stage stage21 = new Stage();
		stage21.initModality(Modality.APPLICATION_MODAL);
		stage21.setTitle("Album Stage");
		stage21.setMinWidth(250);
		// stage21.setMaximized(true);
		GridPane root21 = new GridPane();
		BorderPane Root21 = new BorderPane();
		Root21.setTop(root21);
		root21.setPadding(new Insets(15, 15, 15, 15));
		root21.setVgap(10);
		root21.setHgap(10);

		Label Code21 = new Label("Enter the Album Code");
		TextField MediaCode21 = new TextField();
		Code21.setStyle("-fx-font-weight:bold;");
		HBox hb221 = new HBox();
		hb221.getChildren().addAll(Code21, MediaCode21);
		hb221.setSpacing(25);

		Label Title21 = new Label("Enter the Album title");
		TextField MediaTitle21 = new TextField();
		Title21.setStyle("-fx-font-weight:bold;");
		HBox hb21 = new HBox();
		hb21.getChildren().addAll(Title21, MediaTitle21);
		hb21.setSpacing(25);

		Label Copies21 = new Label("Enter the number of copies available");
		TextField MediaCopies21 = new TextField();
		Copies21.setStyle("-fx-font-weight:bold;");
		HBox hb321 = new HBox();
		hb321.getChildren().addAll(Copies21, MediaCopies21);
		hb321.setSpacing(25);

		Label artist21 = new Label("Enter the Artist ");
		TextField artistText21 = new TextField();
		Code21.setStyle("-fx-font-weight:bold;");
		HBox hb521 = new HBox();
		hb521.getChildren().addAll(artist21, artistText21);
		hb521.setSpacing(25);
		artist21.setStyle("-fx-font-weight:bold;");

		Label song21 = new Label("Enter the Song ");
		TextField songText21 = new TextField();
		Code21.setStyle("-fx-font-weight:bold;");
		HBox hb621 = new HBox();
		hb621.getChildren().addAll(song21, songText21);
		hb621.setSpacing(25);
		song21.setStyle("-fx-font-weight:bold;");

		Label Response21 = new Label();

		Image image121 = new Image("https://img.icons8.com/office/344/add.png", 40, 40, false, false);
		ImageView imageView121 = new ImageView();
		imageView121.setImage(image121);
		Root21.setCenter(imageView121);
		Button add21 = new Button("Add", imageView121);
		add21.setStyle("-fx-background-color:transparent;");
		add21.setOnAction(e2 -> {
			MR.addAlbum(MediaCode21.getText(), MediaTitle21.getText(), Integer.parseInt(MediaCopies21.getText()),
					artistText21.getText(), songText21.getText());

			if (MediaTitle21.getText() != null && !MediaTitle21.getText().isEmpty() && MediaCode21.getText() != null
					&& !MediaCode21.getText().isEmpty() && MediaCopies21.getText() != null
					&& !MediaCopies21.getText().isEmpty() && artistText21.getText() != null
					&& !artistText21.getText().isEmpty() && songText21.getText() != null
					&& !songText21.getText().isEmpty()) {
				Response21.setText(MediaTitle21.getText() + " " + "Registration completed !");
			} else {
				Response21.setText("you have missing fields ! try again.");
			}
		});

		Image image221 = new Image("https://img.icons8.com/clouds/344/back.png");
		ImageView imageView221 = new ImageView();
		imageView221.setImage(image221);
		Root21.setCenter(imageView221);
		Button back21 = new Button("Back", imageView221);
		back21.setStyle("-fx-background-color:transparent;");
		imageView221.setFitHeight(40);
		imageView221.setFitWidth(40);
		back21.setOnAction(f2 -> {
			stage21.close();
		});

		DropShadow shadow21 = new DropShadow();
		add21.addEventHandler(MouseEvent.MOUSE_ENTERED, (MouseEvent eAAA) -> {
			add21.setEffect(shadow21);
		});
		back21.addEventHandler(MouseEvent.MOUSE_ENTERED, (MouseEvent eSSS) -> {
			back21.setEffect(shadow21);
		});

		add21.addEventHandler(MouseEvent.MOUSE_EXITED, (MouseEvent eDDD) -> {
			add21.setEffect(null);
		});
		back21.addEventHandler(MouseEvent.MOUSE_EXITED, (MouseEvent eFFFFFF) -> {
			back21.setEffect(null);
		});

		root21.add(Code21, 0, 2);
		root21.add(MediaCode21, 1, 2);
		root21.add(Title21, 0, 3);
		root21.add(MediaTitle21, 1, 3);
		root21.add(Copies21, 0, 4);
		root21.add(MediaCopies21, 1, 4);
		root21.add(artist21, 0, 5);
		root21.add(artistText21, 1, 5);
		root21.add(song21, 0, 6);
		root21.add(songText21, 1, 6);
		root21.add(Response21, 0, 7);

		HBox hBox21 = new HBox();
		hBox21.getChildren().addAll(add21, back21);
		hBox21.setAlignment(Pos.CENTER);
		hBox21.setSpacing(25);
		Root21.setBottom(hBox21);

		Scene scene = new Scene(Root21, 650, 500);
		stage21.setTitle("Add New Media");
		stage21.setScene(scene);
		stage21.show();
	}

	private static void AddGame() {
		// TODO Auto-generated method stub
		Stage stage22 = new Stage();
		stage22.initModality(Modality.APPLICATION_MODAL);
		stage22.setTitle("Game Stage");
		stage22.setMinWidth(250);
		// stage22.setMaximized(true);
		GridPane root22 = new GridPane();
		BorderPane Root22 = new BorderPane();
		Root22.setTop(root22);
		root22.setPadding(new Insets(15, 15, 15, 15));
		root22.setVgap(10);
		root22.setHgap(10);

		Label Code22 = new Label("Enter the Game Code");
		TextField MediaCode22 = new TextField();
		Code22.setStyle("-fx-font-weight:bold;");
		HBox hb222 = new HBox();
		hb222.getChildren().addAll(Code22, MediaCode22);
		hb222.setSpacing(25);

		Label Title22 = new Label("Enter the Game title");
		TextField MediaTitle22 = new TextField();
		Title22.setStyle("-fx-font-weight:bold;");
		HBox hb22 = new HBox();
		hb22.getChildren().addAll(Title22, MediaTitle22);
		hb22.setSpacing(25);

		Label Copies22 = new Label("Enter the number of copies available");
		TextField MediaCopies22 = new TextField();
		Copies22.setStyle("-fx-font-weight:bold;");
		HBox hb322 = new HBox();
		hb322.getChildren().addAll(Copies22, MediaCopies22);
		hb322.setSpacing(25);

		Label weight22 = new Label("Enter the Weight (in grams)");
		TextField weightText22 = new TextField();
		Code22.setStyle("-fx-font-weight:bold;");
		HBox hb422 = new HBox();
		hb422.getChildren().addAll(weight22, weightText22);
		hb422.setSpacing(25);
		weight22.setStyle("-fx-font-weight:bold;");

		Label Response22 = new Label();

		Image image122 = new Image("https://img.icons8.com/office/344/add.png", 40, 40, false, false);
		ImageView imageView122 = new ImageView();
		imageView122.setImage(image122);
		Root22.setCenter(imageView122);
		Button add22 = new Button("Add", imageView122);
		add22.setStyle("-fx-background-color:transparent;");
		add22.setOnAction(j2 -> {
			MR.addGame(MediaCode22.getText(), MediaTitle22.getText(), Integer.parseInt(MediaCopies22.getText()),
					Double.parseDouble(weightText22.getText()));

			if (MediaTitle22.getText() != null && !MediaTitle22.getText().isEmpty() && MediaCode22.getText() != null
					&& !MediaCode22.getText().isEmpty() && MediaCopies22.getText() != null
					&& !MediaCopies22.getText().isEmpty() && weightText22.getText() != null
					&& !weightText22.getText().isEmpty()) {
				Response22.setText(MediaTitle22.getText() + " " + "Registration completed !");
			} else {
				Response22.setText("you have missing fields ! try again.");
			}
		});

		Image image222 = new Image("https://img.icons8.com/clouds/344/back.png");
		ImageView imageView222 = new ImageView();
		imageView222.setImage(image222);
		Root22.setCenter(imageView222);
		Button back22 = new Button("Back", imageView222);
		back22.setStyle("-fx-background-color:transparent;");
		imageView222.setFitHeight(40);
		imageView222.setFitWidth(40);
		back22.setOnAction(h2 -> {
			stage22.close();
		});

		DropShadow shadow22 = new DropShadow();
		add22.addEventHandler(MouseEvent.MOUSE_ENTERED, (MouseEvent eHHH) -> {
			add22.setEffect(shadow22);
		});
		back22.addEventHandler(MouseEvent.MOUSE_ENTERED, (MouseEvent eHJJ) -> {
			back22.setEffect(shadow22);
		});

		add22.addEventHandler(MouseEvent.MOUSE_EXITED, (MouseEvent eJJJK) -> {
			add22.setEffect(null);
		});
		back22.addEventHandler(MouseEvent.MOUSE_EXITED, (MouseEvent eKHG) -> {
			back22.setEffect(null);
		});

		root22.add(Code22, 0, 2);
		root22.add(MediaCode22, 1, 2);
		root22.add(Title22, 0, 3);
		root22.add(MediaTitle22, 1, 3);
		root22.add(Copies22, 0, 4);
		root22.add(MediaCopies22, 1, 4);
		root22.add(weight22, 0, 5);
		root22.add(weightText22, 1, 5);
		root22.add(Response22, 0, 6);

		HBox hBox22 = new HBox();
		hBox22.getChildren().addAll(add22, back22);
		hBox22.setAlignment(Pos.CENTER);
		hBox22.setSpacing(25);
		Root22.setBottom(hBox22);

		Scene scene = new Scene(Root22, 650, 500);
		stage22.setTitle("Add New Media");
		stage22.setScene(scene);
		stage22.show();
	}

	public static void rent() {
		Stage st1 = new Stage();
		st1.initModality(Modality.APPLICATION_MODAL);
		st1.setMinWidth(250);
		// st1.setMaximized(true);
		GridPane rt1 = new GridPane();
		BorderPane Rt1 = new BorderPane();
		Rt1.setTop(rt1);
		rt1.setPadding(new Insets(15, 15, 15, 15));
		rt1.setVgap(10);
		rt1.setHgap(10);

		RadioButton rb1 = new RadioButton("Rent form");
		rb1.setOnAction(set1 -> {
			RentForm();
		});
		RadioButton rb2 = new RadioButton("Print the request interested media in the cart");
		rb2.setOnAction(set2 -> {
			PrintRequested();
		});
		RadioButton rb3 = new RadioButton("Print the rented media in the cart");
		rb3.setOnAction(set3 -> {
			PrintRented();
		});
		RadioButton rb4 = new RadioButton("Return Rented media");
		rb4.setOnAction(set4 -> {
			ReturnRented();
		});
		RadioButton rb5 = new RadioButton("Return to main page");
		rb5.setOnAction(set5 -> {
			st1.close();
		});

		rt1.add(rb1, 2, 1);
		rt1.add(rb2, 2, 2);
		rt1.add(rb3, 2, 3);
		rt1.add(rb4, 2, 4);
		rt1.add(rb5, 2, 5);

		Scene scene = new Scene(Rt1, 650, 500);
		st1.setTitle("The Rent Stage");
		st1.setScene(scene);
		st1.show();
	}

	private static void ReturnRented() {
		// TODO Auto-generated method stub
		Stage st5 = new Stage();
		st5.initModality(Modality.APPLICATION_MODAL);
		st5.setMinWidth(250);
		// st5.setMaximized(true);
		GridPane rt5 = new GridPane();
		BorderPane Rt5 = new BorderPane();
		Rt5.setTop(rt5);
		rt5.setPadding(new Insets(15, 15, 15, 15));
		rt5.setVgap(10);
		rt5.setHgap(10);

		Label idC = new Label("Customer ID : ");
		TextField CustomerIDC = new TextField();
		idC.setStyle("-fx-font-weight:bold;");
		HBox HB3 = new HBox();
		HB3.getChildren().addAll(idC, CustomerIDC);
		HB3.setSpacing(25);

		Label CodeC = new Label("Media Code : ");
		TextField MediaCodeC = new TextField();
		CodeC.setStyle("-fx-font-weight:bold;");
		HBox HB33 = new HBox();
		HB33.getChildren().addAll(CodeC, MediaCodeC);
		HB33.setSpacing(25);

//		Label ReturnThe1st = new Label("The rented media in the cart is : ");
//		ReturnThe1st.setStyle("-fx-font-weight:bold;");

//		Label ReturnThe2nd = new Label("The all media in the cart is : ");
//		ReturnThe2nd.setStyle("-fx-font-weight:bold;");

//		TextArea ReturnRented = new TextArea();
//		ReturnRented.setStyle("-fx-border: gone;");
//		ReturnRented.setStyle("-fx-background-color: #63cdda;");
//		ReturnRented.setPrefSize(450, 150);

//		TextArea ReturnAll = new TextArea();
//		ReturnAll.setStyle("-fx-border: gone;");
//		ReturnAll.setStyle("-fx-background-color: #63cdda;");
//		ReturnAll.setPrefSize(450, 150);

		Image rented_imageB = new Image("https://img.icons8.com/office/344/return-purchase.png", 40, 40, false, false);
		ImageView rented_imageViewB = new ImageView();
		rented_imageViewB.setImage(rented_imageB);
		Rt5.setCenter(rented_imageViewB);
		Button returnButton = new Button("Return", rented_imageViewB);
		returnButton.setStyle("-fx-background-color:transparent;");
		returnButton.setOnAction(bn1 -> {
			MR.returnMedia(CustomerIDC.getText(), MediaCodeC.getText());
		});

		Image back3_image = new Image("https://img.icons8.com/cute-clipart/344/return.png");
		ImageView back3_imageView = new ImageView();
		back3_imageView.setImage(back3_image);
		Rt5.setCenter(back3_imageView);
		Button Back3 = new Button("Back", back3_imageView);
		Back3.setStyle("-fx-background-color:transparent;");
		back3_imageView.setFitHeight(40);
		back3_imageView.setFitWidth(40);
		Back3.setOnAction(i2111 -> {
			st5.close();
		});

		DropShadow dropshadow2 = new DropShadow();
		returnButton.addEventHandler(MouseEvent.MOUSE_ENTERED, (MouseEvent eSKNF1) -> {
			returnButton.setEffect(dropshadow2);
		});
		Back3.addEventHandler(MouseEvent.MOUSE_ENTERED, (MouseEvent eKNHDO1) -> {
			Back3.setEffect(dropshadow2);
		});

		returnButton.addEventHandler(MouseEvent.MOUSE_EXITED, (MouseEvent ePOIUY1) -> {
			returnButton.setEffect(null);
		});
		Back3.addEventHandler(MouseEvent.MOUSE_EXITED, (MouseEvent eDSADD1) -> {
			Back3.setEffect(null);
		});

		rt5.add(idC, 0, 1);
		rt5.add(CustomerIDC, 1, 1);
		rt5.add(CodeC, 0, 2);
		rt5.add(MediaCodeC, 1, 2);
//		rt5.add(ReturnThe1st, 0, 3);
//		rt5.add(ReturnRented, 0, 4);
//		rt5.add(ReturnThe2nd, 0, 5);
//		rt5.add(ReturnAll, 0, 6);

		HBox hBoxC = new HBox();
		hBoxC.getChildren().addAll(returnButton, Back3);
		hBoxC.setAlignment(Pos.CENTER);
		hBoxC.setSpacing(50);
		Rt5.setBottom(hBoxC);

		Scene scene = new Scene(Rt5, 650, 500);
		st5.setTitle("The media in the cart Stage");
		st5.setScene(scene);
		st5.show();
	}

	private static void PrintRented() {
		// TODO Auto-generated method stub
		Stage st4 = new Stage();
		st4.initModality(Modality.APPLICATION_MODAL);
		st4.setMinWidth(250);
		// st4.setMaximized(true);
		GridPane rt4 = new GridPane();
		BorderPane Rt4 = new BorderPane();
		Rt4.setTop(rt4);
		rt4.setPadding(new Insets(15, 15, 15, 15));
		rt4.setVgap(10);
		rt4.setHgap(10);

		Label idB = new Label("Customer ID : ");
		TextField CustomerIDB = new TextField();
		idB.setStyle("-fx-font-weight:bold;");
		HBox HB2 = new HBox();
		HB2.getChildren().addAll(idB, CustomerIDB);
		HB2.setSpacing(25);

		Label printed2 = new Label("The rented media in the cart is : ");
		printed2.setStyle("-fx-font-weight:bold;");

		TextArea rentedPrint = new TextArea();
		rentedPrint.setStyle("-fx-border: gone;");
		rentedPrint.setStyle("-fx-background-color: #63cdda;");

		Image rented_image = new Image(
				"https://img.icons8.com/external-icongeek26-outline-colour-icongeek26/344/external-print-photography-icongeek26-outline-colour-icongeek26.png",
				40, 40, false, false);
		ImageView rented_imageView = new ImageView();
		rented_imageView.setImage(rented_image);
		Rt4.setCenter(rented_imageView);
		Button Add2 = new Button("Print", rented_imageView);
		Add2.setStyle("-fx-background-color:transparent;");
		Add2.setOnAction(bt11 -> {
			for (int i = 0; i < MR.getCustomer().size(); i++) {
				if (MR.getCustomer().get(i).getID().equals(CustomerIDB.getText())) {
					rentedPrint.setText(MR.getCustomer().get(i).rented.toString());
				}
			}
		});

		Image back2_image = new Image("https://img.icons8.com/clouds/344/back.png");
		ImageView back2_imageView = new ImageView();
		back2_imageView.setImage(back2_image);
		Rt4.setCenter(back2_imageView);
		Button Back2 = new Button("Back", back2_imageView);
		Back2.setStyle("-fx-background-color:transparent;");
		back2_imageView.setFitHeight(40);
		back2_imageView.setFitWidth(40);
		Back2.setOnAction(i2222 -> {
			st4.close();
		});

		DropShadow dropshadow2 = new DropShadow();
		Add2.addEventHandler(MouseEvent.MOUSE_ENTERED, (MouseEvent eSKNF2) -> {
			Add2.setEffect(dropshadow2);
		});
		Back2.addEventHandler(MouseEvent.MOUSE_ENTERED, (MouseEvent eKNHDO2) -> {
			Back2.setEffect(dropshadow2);
		});

		Add2.addEventHandler(MouseEvent.MOUSE_EXITED, (MouseEvent ePOIUY2) -> {
			Add2.setEffect(null);
		});
		Back2.addEventHandler(MouseEvent.MOUSE_EXITED, (MouseEvent eDSADD2) -> {
			Back2.setEffect(null);
		});

		rt4.add(idB, 0, 1);
		rt4.add(CustomerIDB, 1, 1);
		rt4.add(printed2, 0, 2);
		rt4.add(rentedPrint, 0, 3);

		HBox hBoxB = new HBox();
		hBoxB.getChildren().addAll(Add2, Back2);
		hBoxB.setAlignment(Pos.CENTER);
		hBoxB.setSpacing(50);
		Rt4.setBottom(hBoxB);

		Scene scene = new Scene(Rt4, 650, 500);
		st4.setTitle("The rented media Stage");
		st4.setScene(scene);
		st4.show();
	}

	private static void PrintRequested() {
		// TODO Auto-generated method stub
		Stage st3 = new Stage();
		st3.initModality(Modality.APPLICATION_MODAL);
		st3.setMinWidth(250);
		// st3.setMaximized(true);
		GridPane rt3 = new GridPane();
		BorderPane Rt3 = new BorderPane();
		Rt3.setTop(rt3);
		rt3.setPadding(new Insets(15, 15, 15, 15));
		rt3.setVgap(10);
		rt3.setHgap(10);

		Label idA = new Label("Customer ID : ");
		TextField CustomerIDA = new TextField();
		idA.setStyle("-fx-font-weight:bold;");
		HBox HB1 = new HBox();
		HB1.getChildren().addAll(idA, CustomerIDA);
		HB1.setSpacing(25);

		Label printed1 = new Label("The requested interested media in the cart is : ");
		printed1.setStyle("-fx-font-weight:bold;");

		TextArea requestPrint = new TextArea();
		requestPrint.setStyle("-fx-border: gone;");
		requestPrint.setStyle("-fx-background-color: #63cdda;");

		Image request_image = new Image(
				"https://img.icons8.com/external-icongeek26-outline-colour-icongeek26/344/external-print-photography-icongeek26-outline-colour-icongeek26.png",
				40, 40, false, false);
		ImageView request_imageView = new ImageView();
		request_imageView.setImage(request_image);
		Rt3.setCenter(request_imageView);
		Button Add1 = new Button("Print", request_imageView);
		Add1.setStyle("-fx-background-color:transparent;");
		Add1.setOnAction(bt12 -> {
			String mediaC = "";
			String info = "";
			for (int j = 0; j < MR.getCustomer().size(); j++) {

				if (MR.getCustomer().get(j).getID().equals(CustomerIDA.getText())) {
					for (int k = 0; k < MR.getCustomer().get(j).getIntrested_in_received().size(); k++) {
						mediaC = MR.getCustomer().get(j).getIntrested_in_received().get(k);
						for (int q = 0; q < MR.getMedia().size(); q++) {
							if (MR.getMedia().get(q).getCode().equals(mediaC)) {
								info += MR.getMedia().get(q).toString() + "\n";
							}
						}
					}
					requestPrint.setText(info);
				} else {
					requestPrint.setText("Wrong ID or customer have no media");
				}
			}
		});

		Image back1_image = new Image("https://img.icons8.com/clouds/344/back.png");
		ImageView back1_imageView = new ImageView();
		back1_imageView.setImage(back1_image);
		Rt3.setCenter(back1_imageView);
		Button Back1 = new Button("Back", back1_imageView);
		Back1.setStyle("-fx-background-color:transparent;");
		back1_imageView.setFitHeight(40);
		back1_imageView.setFitWidth(40);
		Back1.setOnAction(i2333 -> {
			st3.close();
		});

		DropShadow dropshadow1 = new DropShadow();
		Add1.addEventHandler(MouseEvent.MOUSE_ENTERED, (MouseEvent eSKNF3) -> {
			Add1.setEffect(dropshadow1);
		});
		Back1.addEventHandler(MouseEvent.MOUSE_ENTERED, (MouseEvent eKNHDO3) -> {
			Back1.setEffect(dropshadow1);
		});

		Add1.addEventHandler(MouseEvent.MOUSE_EXITED, (MouseEvent ePOIUY3) -> {
			Add1.setEffect(null);
		});
		Back1.addEventHandler(MouseEvent.MOUSE_EXITED, (MouseEvent eDSADD3) -> {
			Back1.setEffect(null);
		});

		rt3.add(idA, 0, 1);
		rt3.add(CustomerIDA, 1, 1);
		rt3.add(printed1, 0, 2);
		rt3.add(requestPrint, 0, 3);

		HBox hBoxA = new HBox();
		hBoxA.getChildren().addAll(Add1, Back1);
		hBoxA.setAlignment(Pos.CENTER);
		hBoxA.setSpacing(50);
		Rt3.setBottom(hBoxA);

		Scene scene = new Scene(Rt3, 650, 500);
		st3.setTitle("The requested interested media Stage");
		st3.setScene(scene);
		st3.show();
	}

	private static void RentForm() {
		// TODO Auto-generated method stub
		Stage st2 = new Stage();
		st2.initModality(Modality.APPLICATION_MODAL);
		st2.setTitle("Customer Stage");
		st2.setMinWidth(250);
		// stage1.setMaximized(true);
		GridPane rt2 = new GridPane();
		BorderPane Rt2 = new BorderPane();
		Rt2.setTop(rt2);
		rt2.setPadding(new Insets(15, 15, 15, 15));
		rt2.setVgap(10);
		rt2.setHgap(10);

		Label ID_ = new Label("Customer ID");
		TextField Customer_ID = new TextField();
		Customer_ID.setStyle("-fx-background-color: #ea8685;");
		ID_.setStyle("-fx-font-weight:bold;");
		HBox hb_ = new HBox();
		hb_.getChildren().addAll(ID_, Customer_ID);
		hb_.setSpacing(25);

		TextArea Display_Customer = new TextArea();
		Display_Customer.setPrefSize(400, 100);
		Display_Customer.setStyle("-fx-border: gone;");
		Display_Customer.setStyle("-fx-background-color: #63cdda;");

		Label Response50 = new Label();
		Button btnButton1 = new Button("Show the information, ya babe");
		btnButton1.setStyle("-fx-background-color: #ffcccc;");
		btnButton1.setStyle("-fx-background-radius:20.0 10.0;");
		btnButton1.setOnAction(btn1 -> {
			for (int i = 0; i < MR.getCustomer().size(); i++) {
				if (MR.getCustomer().get(i).getID().equals(Customer_ID.getText())) {
					Display_Customer.setText(MR.getCustomer().get(i).toString());
				}
			}

			if (Customer_ID.getText() != null && !Customer_ID.getText().isEmpty()) {
				Response50.setText("Customer information displayed successfully (●'◡'●) ");
			} else {
				Response50.setText("Please enter the ID to display the information (◔◡◔)");
			}

		});

		Label Code_ = new Label("Media Code");
		TextField Media_Code = new TextField();
		Media_Code.setStyle("-fx-background-color: #ea8685;");
		Code_.setStyle("-fx-font-weight:bold;");
		HBox hb_2 = new HBox();
		hb_2.getChildren().addAll(Code_, Media_Code);
		hb_2.setSpacing(25);

		TextArea Dispaly_Media = new TextArea();
		Dispaly_Media.setPrefSize(400, 100);
		Dispaly_Media.setStyle("-fx-border: gone;");
		Dispaly_Media.setStyle("-fx-background-color: #63cdda;");

		Label Response51 = new Label();
		Button btnButton2 = new Button("Show the information, ya babe");
		btnButton2.setStyle("-fx-background-color: #ffcccc;");
		btnButton2.setStyle("-fx-background-radius:20.0 10.0;");
		btnButton2.setOnAction(btn2 -> {
			for (int i = 0; i < MR.getMedia().size(); i++) {
				if (MR.getMedia().get(i).getCode().equals(Media_Code.getText())) {
					Dispaly_Media.setText(MR.getMedia().get(i).toString());
				}
			}

			if (Media_Code.getText() != null && !Media_Code.getText().isEmpty()) {
				Response51.setText("Media information displayed successfully (●'◡'●) ");
			} else {
				Response51.setText("Please enter the Code to display the information (◔◡◔)");
			}

		});

		Label Date_ = new Label("Rented Date : ");
		TextField Rentel_Date = new TextField();
		Rentel_Date.setStyle("-fx-background-color: #f3a683;");
		Date_.setStyle("-fx-font-weight:bold;");
		HBox hb_3 = new HBox();
		hb_3.getChildren().addAll(Date_, Rentel_Date);
		hb_3.setSpacing(25);

		Date now = new Date();
		SimpleDateFormat sf = new SimpleDateFormat("dd-MM-YYYY , hh:mm:ss , EE");
		Rentel_Date.setText(sf.format(now));
		// Rentel_Date.setText(sf.toString());
		Rentel_Date.setStyle("-fx-font-weight:bold;");

		Label ResponseA = new Label();
		Label ResponseB = new Label();

		Image image_1 = new Image("https://img.icons8.com/clouds/344/buy.png", 40, 40, false, false);
		ImageView imageView_1 = new ImageView();
		imageView_1.setImage(image_1);
		Rt2.setCenter(imageView_1);
		Button add_ = new Button("Add To Cart", imageView_1);
		add_.setStyle("-fx-background-color:transparent;");
		add_.setOnAction(btn299 -> {
			MR.addToCart(Customer_ID.getText(), Media_Code.getText());
			// ResponseA.setText("Added");
			if (Media_Code.getText() != null && !Media_Code.getText().isEmpty()) {
				ResponseA.setText("Added");
			} else {
				ResponseA.setText("Fill in");
			}
		});

		Image image_3 = new Image("https://img.icons8.com/clouds/344/process.png", 40, 40, false, false);
		ImageView imageView_3 = new ImageView();
		imageView_3.setImage(image_3);
		Rt2.setCenter(imageView_3);
		Button process_ = new Button("Process Cart", imageView_3);
		process_.setStyle("-fx-background-color:transparent;");
		process_.setOnAction(btn288 -> {
			MR.processRequests();
			ResponseB.setText("Processed");
		});

		Image image_2 = new Image("https://img.icons8.com/clouds/344/back.png");
		ImageView imageView_2 = new ImageView();
		imageView_2.setImage(image_2);
		Rt2.setCenter(imageView_2);
		Button back_ = new Button("Back", imageView_2);
		back_.setStyle("-fx-background-color:transparent;");
		imageView_2.setFitHeight(40);
		imageView_2.setFitWidth(40);
		back_.setOnAction(i2879 -> {
			st2.close();
		});

		DropShadow shadow_ = new DropShadow();
		add_.addEventHandler(MouseEvent.MOUSE_ENTERED, (MouseEvent eSKNF4) -> {
			add_.setEffect(shadow_);
		});
		process_.addEventHandler(MouseEvent.MOUSE_ENTERED, (MouseEvent eDWOIHD4) -> {
			process_.setEffect(shadow_);
		});
		back_.addEventHandler(MouseEvent.MOUSE_ENTERED, (MouseEvent eKNHDO4) -> {
			back_.setEffect(shadow_);
		});
		btnButton1.addEventHandler(MouseEvent.MOUSE_ENTERED, (MouseEvent eKNHDO45) -> {
			btnButton1.setEffect(shadow_);
		});
		btnButton2.addEventHandler(MouseEvent.MOUSE_ENTERED, (MouseEvent eKNHDO44) -> {
			btnButton2.setEffect(shadow_);
		});

		add_.addEventHandler(MouseEvent.MOUSE_EXITED, (MouseEvent ePOIUY4) -> {
			add_.setEffect(null);
		});
		process_.addEventHandler(MouseEvent.MOUSE_EXITED, (MouseEvent eASDS4) -> {
			process_.setEffect(null);
		});
		back_.addEventHandler(MouseEvent.MOUSE_EXITED, (MouseEvent eDSADD4) -> {
			back_.setEffect(null);
		});
		btnButton1.addEventHandler(MouseEvent.MOUSE_EXITED, (MouseEvent eDSADD45) -> {
			btnButton1.setEffect(null);
		});
		btnButton2.addEventHandler(MouseEvent.MOUSE_EXITED, (MouseEvent eDSADD44) -> {
			btnButton2.setEffect(null);
		});

		rt2.add(ID_, 0, 1);
		rt2.add(Customer_ID, 1, 1);
		rt2.add(btnButton1, 0, 2);
		rt2.add(Display_Customer, 1, 2);
		rt2.add(Response50, 1, 3);
		rt2.add(Code_, 0, 4);
		rt2.add(Media_Code, 1, 4);
		rt2.add(btnButton2, 0, 5);
		rt2.add(Dispaly_Media, 1, 5);
		rt2.add(Response51, 1, 6);
		rt2.add(Date_, 0, 7);
		rt2.add(Rentel_Date, 1, 7);

		HBox hBox_ = new HBox();
		hBox_.getChildren().addAll(add_, ResponseA, process_, ResponseB, back_);
		hBox_.setAlignment(Pos.CENTER);
		hBox_.setSpacing(50);
		Rt2.setBottom(hBox_);

		Scene scene = new Scene(Rt2, 650, 500);
		st2.setTitle("The Rent Stage");
		st2.setScene(scene);
		st2.show();
	}
	
	public static void SaveData() {
//		 Prints all the data to a file in-order to be read from when the program runs
//		 again
		File customerdataBase = new File("CustomerdataBase.txt");
		try {
			PrintWriter customerWriter = new PrintWriter(customerdataBase);
//			 Prints all customers information to a customerdataBase File
			for (int i = 0; i < MR.customer.size(); i++) {
				Customer customer = MR.customer.get(i);
				customerWriter.print(customer.getID() + ",");
				customerWriter.print(customer.getName() + ",");
				customerWriter.print(customer.getAddress() + ",");
				customerWriter.print(customer.getMobileNumber() + ",");
				customerWriter.print(customer.getPlan() + ",");
				customerWriter.println();
				for (int j = 0; j < customer.intrested_in_received.size(); j++) {
					customerWriter.print(customer.intrested_in_received.get(j) + "-");
				}
				customerWriter.println();
				for (int j = 0; j < customer.rented.size(); j++) {
					customerWriter.print(customer.rented.get(j) + "-");
				}
				customerWriter.println();
			}
			customerWriter.close();
		} catch (IOException e) {
			e.getStackTrace();
		}
//		 Prints media data to files of Album, Game and movie
		File albumDataBase = new File("AlbumDataBase.txt");
		File gameDataBase = new File("Game Data Base.txt");
		File movieDataBases = new File("Movie Data Base.txt");
		try {
			PrintWriter albumWriter = new PrintWriter(albumDataBase);
			PrintWriter GameWriter = new PrintWriter(gameDataBase);
			PrintWriter movieWriter = new PrintWriter(movieDataBases);
			for (int i = 0; i < MR.media.size(); i++) {
				Media media = MR.media.get(i);
				if (media instanceof Album) {
					Album album1 = (Album) media;
					albumWriter.print(album1.getCode() + "-");
					albumWriter.print(album1.getTitle() + "-");
					albumWriter.print(album1.getNumber_of_copies_available() + "-");
					albumWriter.print(album1.getArtist() + "-");
					albumWriter.print(album1.getSongs());
					albumWriter.println();
				} else if (media instanceof Movie) {
					Movie movie1 = (Movie) media;
					movieWriter.print(movie1.getCode() + ",");
					movieWriter.print(movie1.getTitle() + ",");
					movieWriter.print(movie1.getNumber_of_copies_available() + ",");
					movieWriter.print(movie1.getRate());
					movieWriter.println();
				} else if (media instanceof Game) {
					Game game1 = (Game) media;
					GameWriter.print(game1.getCode() + ",");
					GameWriter.print(game1.getTitle() + ",");
					GameWriter.print(game1.getNumber_of_copies_available() + ",");
					GameWriter.print(game1.getWeight());
					GameWriter.println();
				}

			}
			albumWriter.close();
			GameWriter.close();
			movieWriter.close();
		} catch (IOException e) {
			e.getStackTrace();
		}
	}
	public static void main(String[] args) throws Exception {
		launch(args);

//		File file = new File("C:\\Users\\pankaj\\Desktop\\test.txt");
//
//		BufferedReader br = new BufferedReader(new FileReader(file));
//
//		String st;
//
//		while ((st = br.readLine()) != null)
//
//			System.out.println(st);
//		
//		FileChooser fileChooser = new FileChooser();
//		FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
//		fileChooser.getExtensionFilters().add(extFilter);
//		File file = fileChooser.showSaveDialog(primaryStage);
		
//		PrintWriter fw = null;
//	    try {
//	        fw = new PrintWriter("users.txt");
//	        BufferedWriter bw = new BufferedWriter(fw);
//	   //     bw.write(tf1.getText());
//	        bw.newLine();
//	   //     bw.write(tf2.getText());
//	        bw.close();
//	    } catch (IOException e) {
//	        e.printStackTrace();
//	        fw.close();
//	    }

	}
	@Override
	public void handle(ActionEvent arg0) {
		// TODO Auto-generated method stub

	}
}
