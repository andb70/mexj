package it.biasutti.mexj;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
/**
 * @author Andrea Biasutti
 *
 * La classe MexJ gestisce comunicazioni del tipo "Tweeter" e consente
 * agli utenti di registrarsi con un nome e pubblicare messaggi.
 * Il nome deve essere univoco
 * *
 * Ogni utente può smettere di seguire ciascuno degli utenti che sta seguendo

 * Ogni utente può seguire o essere seguito da altri utenti, in questo modo
 * ogni messaggio pubblicato da un utente X può essere ricevuto dagli N utenti
 * che lo seguno.
 *
 * Ogni utente X seguito può essere messo in mute da ogni singolo utente che lo
 * segue in modo che, dal messaggio successivo al mute, i messaggi ricevuti da X
 * vengano registrati ma non mostrati. Se di seguito X viene riesumato da mute
 * anche i messaggi precedenti risulteranno visibili. Il mute non è retroattivo
  *
 * in args arrivano dei files che rappresentano
 * il flusso delle operazioni svolte da utenti del servizio di messaggistica MexJ
 *
 * le operazioni possibili sono le seguenti:
 *
 * *****************************************
 * x = new user()
 * x.SIGN-UP(userName)  A   creazione di un utente con <userName>
 *
 * x = users(<username>)
 * x.RENAME(newName)    A   modifica il nome: anche i vecchi messaggi / hint_oldName
 *
 *         NB: non sono ammessi userName duplicati
 *
 * *****************************************
 *
 * x = users(<username X>)
 * y = users(<username Y>)
 *
 * x.FOLLOW(y)          A   l'utente x inizia ad osservare l'utente y
 * x.UNFOLLOW(y)        A   l'utente x smette di osservare l'utente y
 * x.MUTE(y)            A   l'utente x sospende l'ossevazione dell'utente y
 * x.UNMUTE(y)          A   l'utente x riprende l'ossevazione dell'utente y
 *                          il MUTE è reversibile: UNMUTE scopre anche i messaggi trascurati
 *
 * *****************************************
 *
 * x.PUBLISH(msg)       M   l'utente x pubblica un messaggio
 *
 * *****************************************
 *
 * x = users(<username>)
 * x.QUERY-RECEIVED(n)  R   recupero degli ultimi n messaggi destinati a x
 *
 * *****************************************
 *
 * x = users(<username>)
 * x.QUERY-SENT(n)      Q   recupero degli ultimi n messaggi pubblicati da x
 *
 * *****************************************
 */

public class MexJ {
    private Users _users;
    private ActionExecutor _exe;
    private ActionLogger<Action> _actions;

    MexJ() {
        _users = new Users();
        _exe = new ActionExecutor(_users);
        //_exe = new ActionTester(_users);
        _actions = new ActionLogger<Action>();
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

    public void process(String filename, Object... args) {
        Action a = processDeferred(filename.substring(0, 1), args);
        if (a == null) {
            return;
        }
        a.performBy(_exe);
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
            a.performBy(_exe);
        }
    }

    public void performAndDiscard() {
        Action a;
        while ( _actions.size()>0) {
            if (!_actions._items.get(0).performBy(_exe))
                //console.log("Action FAIL!");
            _actions._items.remove(0);
        }
    }

    public int actionCount() {
        return _actions.size();
    }
}
