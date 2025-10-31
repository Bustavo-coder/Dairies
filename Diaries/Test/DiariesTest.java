import DiariesExecptions.DairyNotFound;
import DiariesExecptions.DuplicateName;
import DiariesExecptions.InvalidPassword;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DiariesTest {
    private Diaries dairies;

    @BeforeEach
    void setUp() {
        dairies = new Diaries();
    }

    @Test
    @DisplayName("test that my dairies is empty")
    public void isEmpty(){
        assertTrue(dairies.isEmpty());
    }


    @Test
    @DisplayName("test that when i add to my dairies my dairies is not empty")
    public void test_Add(){
        dairies.add("UserName","Password");
        assertFalse(dairies.isEmpty());
    }

    @Test
    @DisplayName("test when i add two dairy to my dairies and i remove one my dairies is not empty")
    public void test_AddTwo_RemoveOne(){
        dairies.add("UserName1","Password1");
        dairies.add("UserName2","Password2");
        dairies.delete("UserName2","Password2");
        assertFalse(dairies.isEmpty());
    }
    @Test
    @DisplayName("test that when i add diary and i tried deleting with wrong password")
    public void test_DeleteWithWrongPassword(){
        dairies.add("UserName1","Password1");
        dairies.add("UserName2","Password2");
        assertFalse(dairies.isEmpty());
        assertThrows(InvalidPassword.class,()-> dairies.delete("UserName2","WrongPassword"));
    }
    @Test
    @DisplayName("test that when a addd two dairys i can get any of my dairies by pasing the username")
    public void test_findBy_UserName(){
        dairies.add("UserName1","Password1");
        dairies.add("UserName2","Password2");
        Diary dairy = dairies.findByUserName("UserName1");
        dairy.unlock("Password1");
        dairy.createEntry("My Boy Is A Good Boy","I Live On Candy");
        assertFalse(dairy.isEmpty());
    }

    @Test
    @DisplayName("test that when i  tried adding Duplicate Diaries")
    public void test_addingDuplicate(){
        dairies.add("UserName1","Password1");
        dairies.add("UserName2","Password2");
        assertThrows(DuplicateName.class, ()-> dairies.add("UserName1","Password1"));
    }
    @Test
    @DisplayName("test that when i tried deleting dairy with a wrong password")
    public void delete_Dairy(){
        dairies.add("UserName1","Password1");
        dairies.add("UserName2","Password2");
        assertThrows(InvalidPassword.class,()-> dairies.delete("UserName1","Wrong Password"));
    }
    @Test
    @DisplayName("test that when i tried deleting with a wrong dairy userName")
    public void delete_DiaryByWrongUser(){
        dairies.add("UserName1","Password1");
        dairies.add("UserName2","Password2");
        assertThrows(InvalidPassword.class,()-> dairies.delete("UserName1","Wrong Password"));
        assertThrows(DairyNotFound.class,()-> dairies.delete("wrong UserNAME","Password1"));
    }
    @Test
    @DisplayName("test that when i tried finding with a wrong username")
    public void find_withWrongUserNAME(){
        assertThrows(DairyNotFound.class,()-> dairies.findByUserName("UserName1"));
    }
}