package assn07;

import java.util.List;
import java.util.Set;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;

public class PasswordManager<K,V> implements Map<K,V> {
    private static final String MASTER_PASSWORD = "password123";
    private Account[] _passwords;

    public PasswordManager() {
        _passwords = new Account[50];
    }
    // TODO: put
    @Override
    public void put(K key, V value) {
        if(key == null){
            //if there's no password duplicates, just update website
            return;
        }
        int ind = Math.abs(key.hashCode() % _passwords.length);
        Account newAcc = new Account(key,value);

        if(_passwords[ind] == null){
            //if a list does not exist at the password location
            _passwords[ind] = newAcc; //create the list with the new account there for future chaining
        }
        else{
            //yay a list already exists
            Account oldAcc = _passwords[ind];  //gotta add to head of list
            while(oldAcc != null){
                if(oldAcc.getWebsite() == key){ //website alr there
                    oldAcc.setPassword(value); //update its password
                    System.out.println("New password added");
                    return;
                }
                oldAcc = oldAcc.getNext();
            }
            //website doesnt exist and list is not null
            oldAcc = _passwords[ind]; //return to top of list
            _passwords[ind] = newAcc; //replace with new acc
            newAcc.setNext(oldAcc); //update chain by changing pointers
        }
        System.out.println("New password added");
    }

    // TODO: get
    @Override
    public V get(K key) {
        if(key == null){
            System.out.println("Account does not exist");
            //acc provided is null
            return null;
        }
        V finalVal = null;
        int ind =Math.abs(key.hashCode() % _passwords.length); //where was it inserted
        Account oldAcc = _passwords[ind];
        while(oldAcc != null){
            if(oldAcc.getWebsite() == key){
                finalVal = (V) oldAcc.getPassword();
                //if this is the website we need then get password and break
                break;
            }
            oldAcc = oldAcc.getNext(); //havent found it yet :(
        }
        if(finalVal == null){
            System.out.println("Account does not exist");
            return null;
        }
        System.out.println(finalVal);
        return finalVal;
    }

    // TODO: remove
    @Override
    public V remove(K key) {
        if(key==null){
            System.out.println("Account not found");
            return null;
        }
        int ind = Math.abs(key.hashCode() % _passwords.length);
        Account oldAcc = _passwords[ind];
        while(oldAcc != null){
            if(oldAcc.getWebsite() == key){
                //is this the website we need?
                Account temp = oldAcc;
                if(oldAcc == _passwords[ind]){
                    //first and only in list
                    _passwords[ind] = oldAcc.getNext();
                    oldAcc = null;
                    System.out.println("Account removed");
                    return (V) temp.getPassword();
                } else{
                    //need to go thru list
                    Account findMemo = _passwords[ind];
                    while(findMemo.getNext()!=null){
                        if(findMemo.getNext() == oldAcc){
                            //go down to the one we need to delete, and change pointers
                            findMemo.setNext(oldAcc.getNext());
                            break;
                        }
                        findMemo = findMemo.getNext();
                    }
                }
                oldAcc = null;
                System.out.println("Account removed");
                return (V) temp.getPassword();
            }
            oldAcc = oldAcc.getNext(); //not there yet :(
        }
        System.out.println("Account not found");
        return null; //not found
    }

    // TODO: checkDuplicate

    @Override
    public List<K> checkDuplicate(V value) {
        List<K> dupList = new ArrayList<>();
        //create a new list to check for dups
        for (Account k: _passwords){
            Account temp = k;
            if(temp!=null){
                //if there is a list
                if(temp.getPassword() == value){
                    dupList.add((K) temp.getWebsite());
                }
                while(temp.getNext()!=null){
                    //go thru list
                    temp = temp.getNext();
                    if(temp.getPassword()==value){
                        dupList.add((K) temp.getWebsite());
                    }
                }
            }
        }
        if(dupList.size()!=0){
            System.out.println("Websites using the password:");
            for(K key: dupList){
                System.out.println(key);
            }
        } else{
            System.out.println("None found");
        }
        return dupList;
    }

    // TODO: keySet
    @Override
    public Set<K> keySet() {
        Set<K> keySet = new HashSet<K>();
        for (Account k: _passwords){
            Account temp = k;
            if(temp!=null){
                //if there is a list
                keySet.add((K) temp.getWebsite());
                while(temp.getNext()!=null){ //go thru list
                    temp = temp.getNext();
                    keySet.add((K) temp.getWebsite());
                }
            }
        }
        if(keySet.size()!=0){
            System.out.println("Accounts:");
            for(K key: keySet){
                System.out.println(key);
            }
        }
        return keySet;
    }

    /*
    Generates random password of input length
     */
    @Override
    public String generateRandomPassword(int length) {
        int leftLimit = 48; // numeral '0'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = length;
        Random random = new Random();

        String generatedString = random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
        System.out.println(generatedString);
        return generatedString;
    }

    // TODO: size
    @Override
    public int size() {
        int size = 0;
        for (Account k: _passwords){
            Account temp = k;
            if(temp!=null){
                //if the list exists
                size++;
                //add the first account
                while(temp.getNext()!=null){ //go thru list
                    size++;
                    temp = temp.getNext();
                }
            }
        }
        return size;
    }

    // TODO: checkMasterPassword
    @Override
    public boolean checkMasterPassword(String enteredPassword) {
        return enteredPassword.equals(this.MASTER_PASSWORD);
    }

    /*
    Used for testing, do not change
     */
    public Account[] getPasswords() {
        return _passwords;
    }
}

