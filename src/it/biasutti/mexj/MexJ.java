package it.biasutti.mexj;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class MexJ {
    private Users _users;

    MexJ() {
        _users = new Users();
    }
    public void loadActionsFromFile(String fileName){
        console.log(fileName);
        try {
            BufferedReader br = new BufferedReader(new FileReader(fileName));

            String line = br.readLine();

            while (line != null) {
                String[] args = line.split("\t");
                process(getFileName(fileName), args);
                line = br.readLine();
            }
        }
        catch (FileNotFoundException e) {
            console.log("File not found", e);
        }
        catch (IOException e) {
            console.log("Input/Output Error", e);
        }
        catch (ArrayIndexOutOfBoundsException e) {
            console.log("Missing file name on command line", e);
        }
        catch (Exception e) {
            console.log("Generic Exception" , e);
        }
    }
private String getFileName(String path){
        String[] f = path.split("/");
        if (f.length==0){
           console.log("nome di file non valido");
           return "";
        }
        return f[f.length-1];
}
    public void process(String filename, Object... args) {
        Action a = new Action(filename, args);
        if (!a.isValid()) {
            console.log("Azione non valida");
            return;
        }
        switch (a.getCmd()){
            case SIGN_UP:
                console.log("Sign up dell'utente %s, utenti: %d", a.getUserName(), _users.size());
                _users.signUp(a.getUserName());
                console.log("Sign up completo");
                break;
            case RENAME:
                console.log("Rename dell'utente %s in %s, utenti: %d", a.getUserName(), a.getDest(), _users.size());
                _users.getUser(a.getUserName()).renameAs(a.getDest());
                console.log("Rename completo");
                break;
            case FOLLOW:
                console.log("%s follow %s", a.getUserName(), a.getDest());
                _users.getUser(a.getUserName()).follow(a.getDest());
                console.log("Follow completo");
                break;
            case UNFOLLOW:
                console.log("%s unfollow %s", a.getUserName(), a.getDest());
                _users.getUser(a.getUserName()).unfollow(a.getDest());
                console.log("Unfollow completo");
                break;
            case MUTE:
                console.log("%s mute %s", a.getUserName(), a.getDest());
                _users.getUser(a.getUserName()).mute(a.getDest());
                console.log("Mute completo");
                break;
            case UNMUTE:
                console.log("%s unmute %s", a.getUserName(), a.getDest());
                _users.getUser(a.getUserName()).unmute(a.getDest());
                console.log("Unmute completo");
                break;
            case MESSAGE:
                console.log("l'utente %s pubblica %s", a.getUserName(), a.getMessage());
                _users.getUser(a.getUserName()).publish(a.getMessage());
                console.log("Message completo");
                break;
            case QUERYFROM:
                console.log("Richiesta di %d messaggi ricevuti da %s", a.getCount(), a.getUserName());
                console.log(_users.getUser(a.getUserName()).queryReceived(a.getCount()));
                console.log("Richiesta Messagi completo");
                break;
            case QUERYTO:
                console.log("Richiesta di %d messaggi inviati a %s", a.getCount(), a.getUserName());
                console.log(_users.getUser(a.getUserName()).querySent(a.getCount()));
                console.log("Richiesta Messagi completo");
                break;
        }
    }
}
