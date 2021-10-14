/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package recipe;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author tmontanez
 */
public class Recipe {
    public String nameTM;
    public String[] ingredientsTM;
    public int prepTimeTM;
    public int cookTimeTM;
    public int totalTimeTM;
    public int servingsTM;
    public String cuisineTM;
    public String courseTM;
    public String dietTM;
    public String instructionsTM;

    public void display()
    {
        System.out.println("\nName: " + this.nameTM);
        System.out.println("Ingredients:");
        for(String ingreTM : ingredientsTM)
            System.out.println(ingreTM);
        System.out.println("Prep Time: " + this.prepTimeTM);
        System.out.println("Cook Time: " + this.cookTimeTM);
        System.out.println("Servings: " + this.servingsTM);
    }

    public boolean ingredientsAvailable(ArrayList<String> data)
    {
        boolean foundTM = false;
        for(String check : data)
        {
            foundTM = false;
            for(String ingreTM : ingredientsTM)
            {
                if (check.equalsIgnoreCase(ingreTM))
                {
                    foundTM = true;
                    break;
                }
            }
            if(!foundTM)
                break;
        }
        return foundTM;
    }

    public static void main(String[] args) {
        try
        {
            BufferedReader csvReader = new BufferedReader(new FileReader("/Users/tmontanez/Downloads/IndianFoodDatasetCSVEdited.csv"));
            try
            {
                String rowTM = csvReader.readLine();
                ArrayList<Recipe> recipesTM = new ArrayList<>();
                while((rowTM = csvReader.readLine()) != null)
                {
                    String[] dataTM = rowTM.split(",");
                    if(dataTM.length == 13)
                    {
                        Recipe recipe = new Recipe();
                        recipe.nameTM = dataTM[1];
                        recipe.ingredientsTM = dataTM[3].split(";");
                        recipe.prepTimeTM = Integer.parseInt(dataTM[5]);
                        recipe.cookTimeTM = Integer.parseInt(dataTM[6]);
                        recipe.totalTimeTM = Integer.parseInt(dataTM[7]);
                        recipe.servingsTM = Integer.parseInt(dataTM[8]);
                        recipe.cuisineTM = dataTM[9];
                        recipe.courseTM = dataTM[10];
                        recipe.dietTM = dataTM[11];
                        recipe.instructionsTM = dataTM[12];
                        recipesTM.add(recipe);
                    }
                }
                Scanner inputTM = new Scanner(System.in);
                System.out.print("Hello \nPlease enter your name: ");
                String userNameTM = inputTM.nextLine();
                while (userNameTM.isEmpty())
                {
                    System.out.print("Invalid name. Enter again: ");
                    userNameTM = inputTM.nextLine();
                }
                String choiceTM = "";
                char chTM = 'a';
                while(chTM != 'h')
                {
                    inputTM = new Scanner(System.in);
                    System.out.println(
                            "\na. Search for a Recipe by Name\n" +
                                    "b. Search for a Recipe by Ingredients\n" +
                                    "c. Search for a Recipe by Preparation Time\n" +
                                    "d. Search for a Recipe by Cook Time\n" +
                                    "e. Search for a Recipe by Cuisine\n" +
                                    "f. Search for a Recipe by Course\n" +
                                    "g. Search for a Recipe by Diet\n" +
                                    "h. Exit\n");
                    do {
                        System.out.print("Please pick a letter for your choice: \n");
                        choiceTM = inputTM.nextLine();
                        if(choiceTM.length() != 1)
                            System.out.print("Invalid Input. Enter again: \n");
                        else
                        {
                            chTM = choiceTM.toLowerCase().charAt(0);
                            if(chTM < 'a' || chTM > 'h')
                                System.out.print("Invalid entry. Please try again: \n");
                        }
                    }while(choiceTM.length() != 1 || chTM < 'a' || chTM > 'h');
                    boolean foundTM = false;
                    if(chTM == 'a')
                    {
                        System.out.print("Enter a Recipe Name to search: \n");
                        choiceTM = inputTM.nextLine();
                        for(Recipe listTM : recipesTM)
                        {
                            if(listTM.nameTM.contains(choiceTM))
                            {
                                listTM.display();
                                foundTM = true;
                            }
                        }
                    }
                    else if(chTM == 'b')
                    {
                        int countTM = 0;
                        System.out.print("Enter number of ingredients to check (1-3): \n");
                        do {
                            countTM = inputTM.nextInt();
                        }while(countTM <=0 || countTM > 3);
                        ArrayList<String> ingredientsTM = new ArrayList<>();
                        inputTM.nextLine();
                        for(int i=1; i <= countTM; i++)
                        {
                            System.out.print("Enter ingredient " + i + ": ");
                            choiceTM = inputTM.nextLine();
                            while(choiceTM.isEmpty())
                            {
                                System.out.println("Invalid input. Enter again: \n");
                                choiceTM = inputTM.nextLine();
                            }
                            ingredientsTM.add(choiceTM);
                        }
                        for(Recipe listTM : recipesTM)
                        {
                            if(listTM.ingredientsAvailable(ingredientsTM))
                            {
                                listTM.display();
                                foundTM = true;
                            }
                        }
                    }
                    else if(chTM == 'c')
                    {
                        int prepTimeTM = 0;
                        System.out.print("Enter Prep Time in minutes: \n");
                        prepTimeTM = inputTM.nextInt();
                        inputTM.nextLine();
                        while(prepTimeTM <= 0)
                        {
                            System.out.print("Time has to be in valid format (only numbers). Please try again: \n");
                            prepTimeTM = inputTM.nextInt();
                        }
                        for(Recipe listTM : recipesTM)
                        {
                            if(listTM.prepTimeTM == prepTimeTM)
                            {
                                listTM.display();
                                foundTM = true;
                            }
                        }
                    }
                    else if(chTM == 'd')
                    {
                        int cookTimeTM = 0;
                        System.out.print("Enter Cook Time in minutes: \n");
                        cookTimeTM = inputTM.nextInt();
                        inputTM.nextLine();
                        while(cookTimeTM <= 0)
                        {
                            System.out.print("Time has to be in valid format (only numbers). Please try again: \n");
                            cookTimeTM = inputTM.nextInt();
                        }
                        for(Recipe listTM : recipesTM)
                        {
                            if(listTM.cookTimeTM == cookTimeTM)
                            {
                                listTM.display();
                                foundTM = true;
                            }
                        }
                    }
                    else if(chTM == 'e')
                    {
                        System.out.print("Enter cuisine: \n");
                        choiceTM = inputTM.nextLine();
                        while(choiceTM.isEmpty())
                        {
                            System.out.print("Invalid input. Enter again: \n");
                            choiceTM = inputTM.nextLine();
                        }
                        for(Recipe listTM : recipesTM)
                        {
                            if(listTM.cuisineTM.equalsIgnoreCase(choiceTM))
                            {
                                listTM.display();
                                foundTM = true;
                            }
                        }
                    }
                    else if(chTM == 'f')
                    {
                        System.out.print("Enter course: \n");
                        choiceTM = inputTM.nextLine();
                        while(choiceTM.isEmpty())
                        {
                            System.out.print("Invalid input. Enter again: \n");
                            choiceTM = inputTM.nextLine();
                        }
                        for(Recipe listTM : recipesTM)
                        {
                            if(listTM.courseTM.equalsIgnoreCase(choiceTM))
                            {
                                listTM.display();
                                foundTM = true;
                            }
                        }
                    }
                    else if(chTM == 'g')
                    {
                        System.out.println("----------------------");
                        System.out.println("a. Diabetic");
                        System.out.println("b. High Protein");
                        System.out.println("c. Vegetarian");
                        System.out.println("----------------------\n");
                        System.out.print("Your choice: ");
                        choiceTM = inputTM.nextLine();
                        while (!choiceTM.equalsIgnoreCase("a") &&
                               !choiceTM.equalsIgnoreCase("b") &&
                               !choiceTM.equalsIgnoreCase("c"))
                        {
                            System.out.print("Invalid Choice. Enter again: \n");
                            choiceTM = inputTM.nextLine();
                        }
                        if(choiceTM.equalsIgnoreCase("a"))
                            choiceTM = "diabetic";
                        else if(choiceTM.equalsIgnoreCase("b"))
                            choiceTM = "high protein";
                        else if(choiceTM.equalsIgnoreCase("c"))
                            choiceTM = "vegetarian";
                        for(Recipe listTM : recipesTM)
                        {
                            if(listTM.dietTM.equalsIgnoreCase(choiceTM))
                            {
                                listTM.display();
                                foundTM = true;
                            }
                        }
                    }
                    if(!foundTM)
                        System.out.println("Sorry " + userNameTM + ", No Recipes found\n");
                    System.out.print("Continue to Menu (input 'y') or Exit (input 'n')? \n");
                    choiceTM = inputTM.nextLine();
                    while (!choiceTM.equalsIgnoreCase("y") && !choiceTM.equalsIgnoreCase("n"))
                    {
                        System.out.print("Invalid Input. Enter again: \n");
                        choiceTM = inputTM.nextLine();
                    }
                    if(choiceTM.equalsIgnoreCase("n"))
                        chTM = 'h';
                }
            }
            catch(Exception ex)
            {
                ex.printStackTrace();
            }
        }
        catch(Exception e)
        {
            System.out.println("Unable to open file or file don't exist\n");
        }
    }
}
