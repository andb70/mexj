package it.biasutti.mexj;

public class Main {

    public static void main(String[] args) {

        /**
         * @author Andrea Biasutti
         *
         * in args arrivano dei files che rappresentano
         * il flusso delle operazioni svolte da utenti del servizio MexJ
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
         * x.QUERY-TO(n)        Q   recupero degli ultimi n messaggi destinati a x
         *
         * *****************************************
         *
         * x = users(<username>)
         * x.QUERY-FROM(n)      U   recupero degli ultimi n messaggi pubblicati da x
         *
         * *****************************************
         * *****************************************
         * *****************************************
         *
         *          DEFINIZIONE DELLE CLASSI
         *
         * *****************************************
         *
         *
         */

        // oggetto padre
        MexJ mexJ = new MexJ();
        // contiene Users

        // quanti utenti contiene?
        console.log("Ora ci sono %s utenti", mexJ.users().count());

        User a = mexJ.users().signUp("Alice");
        console.log("Ora ci sono %s utenti", mexJ.users().count());
        console.log("%s è nel gruppo", a.getUserName());

        User m = mexJ.users().signUp("Marta");
        console.log("Ora ci sono %s utenti", mexJ.users().count());
        console.log("%s è nel gruppo", m.getUserName());

        User u = mexJ.users().signUp("Ugo");
        console.log("Ora ci sono %s utenti", mexJ.users().count());
        console.log("%s è nel gruppo", u.getUserName());

        u.renameAs("Giorgio");
        console.log("Ugo adesso si chiama %s", u.getUserName());

        console.log(">>> genero un errore con Ugo");
        u = mexJ.users("Ugo");

        mexJ.users("Ugo").renameAs("Giorgio");
        console.log(">>> fine errore con Ugo");
        console.log("Ora ci sono %s utenti", mexJ.users().count());


        console.log(">>> genero un errore con Giorgio");
        u = mexJ.users("Giorgio");
        console.log("  u si chiama %s", u.getUserName());
        mexJ.users("Giorgio").renameAs("Marta");
        console.log("  Giorgio adesso si chiama " + u.getUserName());
        console.log(">>> fine errore con Giorgio");
        console.log("Ora ci sono %s utenti", mexJ.users().count());


        console.log(">>> genero un errore Alice.follow(marco)");
        mexJ.users("Alice").follow("marco");

        console.log(">>> genero un errore ciccio.follow(marco)");
        mexJ.users("ciccio").follow("marco");

        console.log(">>> Creo un Alice.follow(Giorgio)");
        mexJ.users("Alice").follow("Giorgio");

        console.log(">>> Elimino un Alice.follow(Giorgio)");
        mexJ.users("Alice").unfollow("Giorgio");

        mexJ.users("Alice").publish("welcome to world");
        mexJ.users("Alice").mute("marco");
        mexJ.users("Alice").unmute("marco");

/*


        mexJ.users("Alice").query(3).to("marco").result();
        mexJ.users("Alice").query(3).from("marco").result();
*/


    }

}