package it.biasutti.mexj;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * @author Andrea Biasutti
 * <p>
 * La classe MexJ gestisce comunicazioni del tipo "Tweeter" e consente
 * agli utenti di registrarsi con un nome e pubblicare messaggi.
 * Il nome deve essere univoco
 * *
 * Ogni utente può smettere di seguire ciascuno degli utenti che sta seguendo
 * <p>
 * Ogni utente può seguire o essere seguito da altri utenti, in questo modo
 * ogni messaggio pubblicato da un utente X può essere ricevuto dagli N utenti
 * che lo seguno.
 * <p>
 * Ogni utente X seguito può essere messo in mute da ogni singolo utente che lo
 * segue in modo che, dal messaggio successivo al mute, i messaggi ricevuti da X
 * vengano registrati ma non mostrati. Se di seguito X viene riesumato da mute
 * anche i messaggi precedenti risulteranno visibili. Il mute non è retroattivo
 * <p>
 * in args arrivano dei files che rappresentano
 * il flusso delle operazioni svolte da utenti del servizio di messaggistica MexJ
 * <p>
 * le operazioni possibili sono le seguenti:
 * <p>
 * *****************************************
 * x = new user()
 * x.SIGN-UP(userName)  A   creazione di un utente con <userName>
 * <p>
 * x = users(<username>)
 * x.RENAME(newName)    A   modifica il nome: anche i vecchi messaggi / hint_oldName
 * <p>
 * NB: non sono ammessi userName duplicati
 * <p>
 * *****************************************
 * <p>
 * x = users(<username X>)
 * y = users(<username Y>)
 * <p>
 * x.FOLLOW(y)          A   l'utente x inizia ad osservare l'utente y
 * x.UNFOLLOW(y)        A   l'utente x smette di osservare l'utente y
 * x.MUTE(y)            A   l'utente x sospende l'ossevazione dell'utente y
 * x.UNMUTE(y)          A   l'utente x riprende l'ossevazione dell'utente y
 * il MUTE è reversibile: UNMUTE scopre anche i messaggi trascurati
 * <p>
 * *****************************************
 * <p>
 * x.PUBLISH(msg)       M   l'utente x pubblica un messaggio
 * <p>
 * *****************************************
 * <p>
 * x = users(<username>)
 * x.QUERY-RECEIVED(n)  R   recupero degli ultimi n messaggi destinati a x
 * <p>
 * *****************************************
 * <p>
 * x = users(<username>)
 * x.QUERY-SENT(n)      Q   recupero degli ultimi n messaggi pubblicati da x
 * <p>
 * *****************************************
 */

public class MexJ {
    private Users _users;
    private ActionExecutor _exe;
    private ActionLogger<Action> _actions;
    private Broker<IUser> _msgBoard;

    MexJ(boolean test) {
        _msgBoard = new Broker<IUser>();
        _users = new Users(_msgBoard);
        if (test) {
            _exe = new ActionTester(_users);
        } else {
            _exe = new ActionExecutor(_users);
        }

        _actions = new ActionLogger<Action>();
    }
    public IUser signUp(String userName) {
        return _users.signUp(userName);
    }
    public IUser getUsers(String username) {
        return _users.getUser(username);
    }
    public void loadActionsFromFile(String fileName) {
        this.loadActionsFromFile(fileName, true);
    }

    public void loadActionsFromFile(String fileName, boolean deferred) {

        console.log(fileName);
        try {
            BufferedReader br = new BufferedReader(new FileReader(fileName));

            String line = br.readLine();
            if (deferred) {
                while (line != null) {
                    String[] args = line.split("\t");
                    processDeferred(Util.getFileName(fileName), args);
                    line = br.readLine();
                }
            } else {
                while (line != null) {
                    String[] args = line.split("\t");
                    process(Util.getFileName(fileName), args);
                    line = br.readLine();
                }
            }
        } catch (FileNotFoundException e) {
            console.log("File not found", e);
        } catch (IOException e) {
            console.log("Input/Output Error", e);
        } catch (ArrayIndexOutOfBoundsException e) {
            console.log("Missing file name on command line", e);
        } catch (Exception e) {
            console.log("Generic Exception", e);
        }
    }

    public void listUsers() {
        console.log("List of users:\n%s", _users.listUsers());
    }
    public void listUsersAndFollowers() {
        console.log("List of users and followers:\n%s", _users.listUsersAndFollowers());
    }
    public void listUsersAndFollowees() {
        console.log("List of users and followees:\n%s", _users.listUsersAndFollowees());
    }
    public void listFollowers(String name) {
        IUser u = _users.getUser(name);
        if (u== null){
            console.log("%s is not a user", name);
            return;
        }
        String s = _users.listFollowers(u);
        console.log("%s is followed by :\n%s",u.getName(), s);
    }

    public void listFollowees(String name) {
        IUser u = _users.getUser(name);
        if (u== null){
            console.log("%s is not a user", name);
            return;
        }
        String s = _users.listFollowees(u);
        console.log("%s is following:\n%s",u.getName(), s);
    }
    public void processWithExceptions(String filename, Object... args) {
        try {
            process(filename, args);
        } catch (Exception e) {
            console.log("        MexJ.Process", e);
        }
    }


    public void process(String filename, Object... args) throws Exception {
        Action a = processDeferred(filename.substring(0, 1), args);
        if (a == null) {
            return;
        }
        console.log("result: %b", a.performBy(_exe));
    }

    public Action processDeferred(String filename, Object... args) {
        Action a = new Action(filename.substring(0, 1), args);
        if (!a.isValid()) {
            console.log("Azione non valida");
            return null;
        }
        _actions.add(a);
        return a;
    }

    public void shuffle() {
        _actions.shuffle();
    }

    public void perform() {
        for (Action a : _actions.items()
                ) {
            try {
                a.performBy(_exe);
            } catch (Exception e) {
                console.log("perform action", e);

            }
        }
    }

    public void performAndDiscard() {
        while (_actions.size() > 0) {
            try {
                Action a = _actions._items.get(0);
                a.performBy(_exe);
            } catch (Exception e) {
                console.log("perform action", e);
            }
            _actions._items.remove(0);
        }
    }

    public int actionCount() {
        return _actions.size();
    }
}
