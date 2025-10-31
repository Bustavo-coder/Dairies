import DiariesExecptions.DairyNotFound;
import DiariesExecptions.DuplicateName;

import java.util.HashMap;
import java.util.List;

public class Diaries {
    private int size;
    private final HashMap <String,Diary> dairies = new HashMap<String,Diary>();

    public boolean isEmpty() {
        return size == 0;
    }

    public void add(String userName, String password) {
        validateUserName(userName);
       Diary dairy  = new Diary(userName,password);
       dairies.put(userName,dairy);
        size++;
    }

    public void delete(String userName, String password) {
        isAvailable(userName);
        dairies.get(userName).unlock(password);
        dairies.remove(userName);
        size--;
    }

    public Diary findByUserName(String username) {
        isAvailable(username);
        return dairies.get(username);
    }
    private void validateUserName(String userName){
        if(dairies.containsKey(userName)) throw new DuplicateName("Dairy Existed Already");
    }
    private void isAvailable(String userName){
        if(!dairies.containsKey(userName)) throw new DairyNotFound("Dairy Does Not Exist");
    }




}
