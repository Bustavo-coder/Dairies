//import DiariesExecptions.DiariesExceptions;
//
////TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
//// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
//void main() {
//    public static void createDairy(){
//        String userName = input("Enter Diary Name");
//        String password = input("Enter Your Password");
//        print("Created Successfully");
//        try{
//            diaries.add(userName,password);
//        }catch (DiariesExceptions e){
//            print(e.getMessage());
//        }finally {
//            print("Invalid Password");
//        }
//
//    }
//
//    public static void deleteDairy(){
//        String userName = input("Enter Diary Name");
//        String password = input("Enter Your Password");
//        try{
//            diaries.delete(userName,password);
//            print("Deleted Successfully");
//        }catch (DiariesExceptions e){
//            print(e.getMessage());
//        }finally {
//            print("ok");
//        }
//    }
//
//    public static void lockDiary(){
//        String userName = input("Enter Diary Name");
//        String password = input("Enter Your Password");
//        try{
//            Diary dairy = diaries.findByUserName(userName);
//            dairy.lock();
//            print("Locked Succesfully");
//        }catch (DiariesExceptions e){
//            print(e.getMessage());
//        }
//        finally {
//            print("You are good To Go");
//        }
//    }
//
//    public static void unlockDiary(){
//        String userName = input("Enter Diary Name");
//        String password = input("Enter Your Password");
//        try{
//            Diary diary = diaries.findByUserName(userName);
//            diary.unlock(password);
//            print("Succesfully");
//        } catch (DiariesExceptions e) {
//            e.getMessage();
//        }finally {
//            print("Success");
//        }
//
//    }
///
//}
