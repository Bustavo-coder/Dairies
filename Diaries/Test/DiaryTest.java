import DiariesExecptions.EmptyDiaries;
import DiariesExecptions.EntryNotFound;
import DiariesExecptions.InvalidPassword;
import DiariesExecptions.LockedDiary;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DiaryTest {
    private Diary dairy;
    @BeforeEach
    public void setup(){
        dairy = new Diary("FirstName","1234");
    }

    @Test
    @DisplayName("test that my diary is not yet Opened")
    public void test_isClosed(){
        assertTrue(dairy.isLocked());
    }


    @Test
    @DisplayName("test that when i can Open My Diary")
    public void open_Diary(){
        dairy.unlock("1234");
        assertFalse(dairy.isLocked());
    }

    @Test
    @DisplayName("test that when i locked my diary it Locked")
    public void lock_Dary(){
        dairy.lock();
        assertTrue(dairy.isLocked());
    }


    @Test
    @DisplayName("test that my dairy has no entries")
    public void test_Empty_Dairy(){
        assertTrue(dairy.isEmpty());
    }


    @Test
    @DisplayName("test that when i  create entry to my diaries is not more empty")
    public void createEntries(){
        dairy.unlock("1234");
        dairy.createEntry("My New Year Resolution","1.Buy Two Lamboghini\n Buy Four Buildings Is Sambisa");
        assertFalse(dairy.isEmpty());
    }


    @Test
    @DisplayName("test that when my dairy is locked i can not add Entry")
    public void test_locked_Entries(){
        assertThrows(LockedDiary.class , ()-> dairy.createEntry("My 2025 Year Resolution","1.Become President Of Nigeria\n Make Nigeria Great Again"));
    }


    @Test
    @DisplayName("test that i can create mutiple entries to my dairy")
    public  void create_mutipleEntries(){
        dairy.unlock("1234");
        dairy.createEntry("My 2025 Year Resolution","1.Become President Of Nigeria\n Make Nigeria Great Again");
        dairy.createEntry("My New Year Resolution","1.Buy Two Lamboghini\n Buy Four Buildings Is Sambisa");
        assertFalse(dairy.isEmpty());
    }


    @Test
    @DisplayName("test that i passed in a wrong password to unlock my Dairy it throw InvalidPassword Exceptions")
    public void unlock_WithWrongPassword(){
        assertThrows(InvalidPassword.class , ()-> dairy.unlock("wrong password"));
    }

    @Test
    @DisplayName("test that when i create an entries and delete all entries created my dairy is empty")
    public void delete_Entries(){
        dairy.unlock("1234");
        dairy.createEntry("My 2025 Year Resolution","1.Become President Of Nigeria\n Make Nigeria Great Again");
        int id = 1;
        dairy.deleteEntry(id);
        assertTrue(dairy.isEmpty());
    }


    @Test
    @DisplayName("test that when i create mutiple entries and i delete the second one my diriaes is not empty ")
    public void delete_entries(){
        dairy.unlock("1234");
        dairy.createEntry("My 2025 Year Resolution","1.Become President Of Nigeria\n Make Nigeria Great Again");
        dairy.createEntry("My New Year Resolution","1.Buy Two Lamboghini\n Buy Four Buildings Is Sambisa");
        dairy.deleteEntry(2);
        assertFalse(dairy.isEmpty());
    }


    @Test
    @DisplayName("test that when my dairy is locked i can not delete my entries")
    public void delete_whenLocked(){
        dairy.unlock("1234");
        dairy.createEntry("My 2025 Year Resolution","1.Become President Of Nigeria\n Make Nigeria Great Again");
        dairy.createEntry("My New Year Resolution","1.Buy Two Lamboghini\n Buy Four Buildings Is Sambisa");
        dairy.lock();
        assertThrows(LockedDiary.class, ()-> dairy.deleteEntry(1));
    }


    @Test
    @DisplayName("test that when my dairy is Empty i cannot delete")
    public void delete_EmptyDairy(){
        dairy.unlock("1234");
        assertThrows(EmptyDiaries.class,()->dairy.deleteEntry(3));
    }

    @Test
  @DisplayName("test that when i create entries and i delete my entry i cannot find My deleted entry again")
    public void findEntries_ById(){
     dairy.unlock("1234");
     dairy.createEntry("My 2025 Year Resolution","1.Become President Of Nigeria\n Make Nigeria Great Again");
     dairy.createEntry("My New Year Resolution","1.Buy Two Lamboghini\n Buy Four Buildings Is Sambisa");
     dairy.deleteEntry(2);
     assertThrows(EntryNotFound.class, ()->dairy.findEntryById(2));
}
    @Test
    @DisplayName("test that i cannot find an entry from my dairy if my dairy is closed")
    public void test_FindClosedDiary(){
        dairy.unlock("1234");
        dairy.createEntry("My 2025 Year Resolution","1.Become President Of Nigeria\n Make Nigeria Great Again");
        dairy.createEntry("My New Year Resolution","1.Buy Two Lamboghini\n Buy Four Buildings Is Sambisa");
        dairy.lock();
        assertThrows(LockedDiary.class,()-> dairy.findEntryById(2));

    }

    @Test
    @DisplayName("test that when i find the correct entry")
    public void test_Entry(){
        dairy.unlock("1234");
        dairy.createEntry("My 2025 Year Resolution","1.Become President Of Nigeria\n Make Nigeria Great Again");
        dairy.createEntry("My New Year Resolution","1.Buy Two Lamboghini\n Buy Four Buildings Is Sambisa");
        assertEquals(dairy.findEntryById(2).getTitle(),"My New Year Resolution");
    }

    @Test
    @DisplayName("test that i can update my old entries by passing in the id")
    public void test_Update(){
        dairy.unlock("1234");
        int id1 = dairy.createEntry("My 2025 Year Resolution","1.Become President Of Nigeria\n Make Nigeria Great Again");
       int id2 =  dairy.createEntry("My New Year Resolution","1.Buy Two Lamboghini\n Buy Four Buildings Is Sambisa");
        dairy.updateEntry(id1,"Lion King","I am a King in my Kingdom");
        Entry entry = dairy.findEntryById(id1);
        assertEquals("Lion King",
                entry.getTitle());
    }

}