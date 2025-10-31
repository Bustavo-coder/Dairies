import DiariesExecptions.EmptyDiaries;
import DiariesExecptions.EntryNotFound;
import DiariesExecptions.InvalidPassword;
import DiariesExecptions.LockedDiary;

import java.util.ArrayList;
import java.util.List;

public class Diary {
    private boolean isLocked = true;
    private int size;
    private String password = "1";
    private List<Entry> entries = new ArrayList<>();
    private String name;

    public Diary(String name,String password){
        this.password = password;
        this.name = name;
    }
    public String getName() {
        return name;
    }

    public boolean isLocked() {
        return  isLocked;
    }

    public void unlock(String password) {
        validatePassword(password);
        isLocked = false;
    }

    public void lock() {
        isLocked = true;
    }
    public boolean isEmpty(){
        return size == 0;
    }

    public int createEntry(String tittle, String body) {
        validateLocked();
        int id = generateId();
        Entry entry = new Entry(id,tittle,body);
        entries.add(entry);
        size++;
        return id;
    }

    public void deleteEntry(int id) {
        validateLocked();
        if(isEmpty()) throw new EmptyDiaries("Dairies Is Currently Empty");
        validateID(id);
        entries.remove(id-1);
        size--;
    }

    public Entry findEntryById(int id){
        validateLocked();
        validateID(id);
        return entries.get(id - 1);
    }

    public void updateEntry(int id, String title, String body) {
        validateLocked();
        validateID(id);
        entries.get(id-1).setTitle(title);
        entries.get(id-1).setBody(body);
    }

    private void validateLocked(){
        if(isLocked) throw new LockedDiary("Can Not Add Entry To Dairies");
    }

    private void validatePassword(String password){
        if(!this.password.equals(password)) throw new InvalidPassword("Invalid Password");
    }
    private int generateId(){
        return size + 1;
    }
    private void validateID(int id){
        if(id > size || id < 0) throw new EntryNotFound("Entry Is Not Available");
    }


}
