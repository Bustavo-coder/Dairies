import DiariesExecptions.DiariesExceptions;

import javax.swing.*;
import java.util.Scanner;

public class Driver {
    public static Diaries diaries = new Diaries();
    static void main() {
        gotoMainMenu();
    }
    private static Diary currentDairyOpened;

    public static void print(String prompt){
        System.out.println(prompt);
    }
    public static String input(String prompt){
        return JOptionPane.showInputDialog(prompt);
    }
    public static String userPrompt(){
        return """
                1.>> Create a diary
                2.>>> Delete a Diary
                3.>>> Unlock a diary
                4.>>> Lock a diary
                0.>>>> Exit
               """;
    }


    public static void createDairy(){
        String userName = input("Enter Diary Name");
        String password = input("Enter Your Password");
        try{
            diaries.add(userName,password);
            print("Created Successfully");
        }catch (DiariesExceptions e){
            print(e.getMessage());
        }finally {
            gotoMainMenu();
            print("Invalid Password");
        }
    }


    public static void unlockDiary(){
        String userName = input("Enter Diary Name");
        String password = input("Enter Your Password");
        try{
           currentDairyOpened = diaries.findByUserName(userName);
            currentDairyOpened.unlock(password);
            gotoSubMenu(currentDairyOpened);
        } catch ( DiariesExceptions e) {
            print(e.getMessage());
        }
        finally {
            gotoMainMenu();
            print("Successfully");
        }
    }


    public static void lockDiary(){
        String userName = input("Enter Diary Name");
        try{
            Diary diary = diaries.findByUserName(userName);
            diary.lock();
        } catch ( DiariesExceptions e) {
            print(e.getMessage());
        }
        finally {
            gotoMainMenu();
            print("Successfully");
        }

    }

    public static void deleteDairy(){
        String userName = input("Enter Your UserName");
        String password = input("Enter Your Password");
        try{
            diaries.delete(userName,password);
            print("Successfully Deleted");
        }
        catch (DiariesExceptions e){
            print(e.getMessage());
        }
        finally {
            gotoMainMenu();
        }
    }


    public static void addEntry(Diary dairy){
        String tittle = input("Enter the title of the entry");
        String body = input("Enter Entry Body");
        try {
            int id = dairy.createEntry(tittle,body);
            print("The Entry id is " + id);
            print("Entry Successfully Created");
        }
        catch (DiariesExceptions e){
            print(e.getMessage());
        }
        finally {
            gotoSubMenu(currentDairyOpened);
        }
    }



    public static void deleteEntry(Diary dairy){
        int id = Integer.parseInt(input("Enter Entry Id"));

        try {
            dairy.deleteEntry(id);
            print("Deleted Successfully");
        }catch (DiariesExceptions e){
            print(e.getMessage());
        }
        finally {
            gotoSubMenu(currentDairyOpened);
        }
    }


    public static void updateEntry(Diary diary){
        int id = Integer.parseInt(input("Enter Entry Id"));
        String tittle = input("Enter the title of the entry");
        String body = input("Enter Entry Body");
        try {
            diary.updateEntry(id, tittle, body);
            print("Succesfully Updated");
        }catch (DiariesExceptions e){
            print(e.getMessage());
        }finally {
            gotoSubMenu(currentDairyOpened);
        }
    }

    public static void getEntryById(Diary diary){
        int id = Integer.parseInt(input("Enter Entry Id"));
        try{
            print(diary.findEntryById(id).toString());
        }catch (DiariesExceptions e){
            print(e.getMessage());
        }finally {
            gotoSubMenu(currentDairyOpened);
        }
    }


    public static String subMenu(){
        return """
                    1.<< Add Entry.
                    2.<< Update Entry.
                    3.<< Delete Entry.
                    4.<< Find entry by id
                    0.<< Back To Main Menu
                    """;
    }

    public static void gotoMainMenu(){
        String prompt = input(userPrompt());
        switch (prompt){
            case "1" -> createDairy();
            case "2" -> deleteDairy();
            case "3" -> unlockDiary();
            case  "4" -> lockDiary();
            case "0" -> System.exit(0);
            default -> gotoMainMenu();
        }
    }

    public static void gotoSubMenu(Diary dairy){
        String prompt = input(subMenu());
        switch (prompt){
            case "1" -> addEntry(dairy);
            case "2" -> updateEntry(dairy);
            case "3" -> deleteEntry(dairy);
            case "4" -> getEntryById(dairy);
            case "0" -> print("Bye Bye ");
            default ->  gotoSubMenu(currentDairyOpened);
        }

    }


}
