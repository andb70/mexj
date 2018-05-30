package it.biasutti.mexj;

public class Main {

    public static void main(String[] args) {


        MexJ mexJ = new MexJ();
        String path ="C:/Users/andb7/IdeaProjects/tissino/Esame finale/MexJ/data/data/%s";
        // mexJ.loadActionsFromFile(String.format(path, "A03.txt"));
        // mexJ.loadActionsFromFile(String.format(path, "A02.txt"));
        // mexJ.loadActionsFromFile(String.format(path, "M01.txt"));
        // mexJ.loadActionsFromFile(String.format(path, "Q01.txt"));
        // mexJ.loadActionsFromFile(String.format(path, "R02.txt"));
         mexJ.loadActionsFromFile(String.format(path, "A01.txt"));
        // mexJ.loadActionsFromFile(String.format(path, "M02.txt"));
        // mexJ.loadActionsFromFile(String.format(path, "M03.txt"));
        // mexJ.loadActionsFromFile(String.format(path, "Q01.txt"));
        mexJ.loadActionsFromFile(String.format(path, "Q03.txt"));
        // mexJ.loadActionsFromFile(String.format(path, "R03.txt"));
        mexJ.shuffle();

        mexJ.loadActionsFromFile(String.format(path, "Q03.txt"));
        // mexJ.loadActionsFromFile(String.format(path, "R03.txt"));
        mexJ.performAndDiscard();
        console.log("Azioni rimase: %d",mexJ.actionCount());
    }
    public static void runTests() {
        Users mexJ = new Users();

        // quanti utenti contiene?
        console.log("Ora ci sono %s utenti", mexJ.size());

        console.log("Aggiungo Alice");
        IUser a = mexJ.signUp("Alice");
        console.log("Ora ci sono %s utenti", mexJ.size());
        console.log("%s è nel gruppo", a.getName());
        console.log();

        console.log("Aggiungo Marta");
        IUser m = mexJ.signUp("Marta");
        console.log("Ora ci sono %s utenti", mexJ.size());
        console.log("%s è nel gruppo", m.getName());
        console.log();

        console.log("Aggiungo Ugo");
        IUser u = mexJ.signUp("Ugo");
        console.log("Ora ci sono %s utenti", mexJ.size());
        console.log("%s è nel gruppo", u.getName());
        console.log();

        console.log("Ugo cambia nome in Giorgio");
        u.renameAs("Giorgio");
        console.log("Ugo adesso si chiama %s", u.getName());
        console.log();
/*
        console.log("Genero un errore con Ugo (non esiste più");
        u = mexJ.getUser("Ugo");
        console.log("Genero un errore cambiando nome a Ugo");
        u.renameAs("Giorgio");
        console.log(">>> fine errore con Ugo");


        console.log(">>> genero un errore rinominando Giorgio in Marta (esiste già");
        u = mexJ.getUser("Giorgio");
        console.log("u si chiama %s", u.getName());
        u.renameAs("Marta");
        console.log("  Giorgio adesso si chiama " + u.getName());
        console.log(">>> fine errore con Giorgio");
        console.log();

        console.log("Genero un errore Alice.follow(marco)");
        mexJ.getUser("Alice").follow("marco");
        console.log();

        console.log("Genero un errore ciccio.follow(marco)");
        mexJ.getUser("ciccio").follow("marco");
        console.log();

        console.log("Creo un Alice.follow(Giorgio)");
        mexJ.getUser("Alice").follow("Giorgio");
        console.log();

        console.log("Elimino un Alice.follow(Giorgio)");
        mexJ.getUser("Alice").unfollow("Giorgio");
        console.log();

        console.log("Creo Marta.follow(Alice)");
        m.follow(a.getName());

        console.log("Creo Giorgio.follow(Alice)");
        u.follow(a.getName());
*/
        console.log();
        console.log("Riprovo Marta.follow(Alice)");
        m.follow(a);

        console.log("riprovo Giorgio.follow(Alice)");
        u.follow(a);

        console.log();
        console.log("Alice pubblica \"ciao da a\"");
        a.publish("ciao da a");

        console.log();
        console.log("Creo Giorgio.follow(Marta)");
        u.follow(m);

       /* console.log();
        console.log("Elimino Marta.follow(Alice)");
        m.unfollow(a.getName());

        console.log("elimino Giorgio.follow(Alice)");
        u.unfollow(a);*/

        console.log();
        console.log("Alice pubblica \"ciao 2 da a\"");
        a.publish("ciao 2 da a");

        console.log();
        console.log("Marta pubblica \"ciao da m\"");
        m.publish("ciao da m");

        console.log();
        console.log("Giorgio pubblica \"ciao da g\"");
        u.publish("ciao da g");


        console.log();
        console.log();
        console.log("Giorgio mute Alice");
        u.mute(a);


        console.log();
        console.log("Alice pubblica \"ciao 3 da a\"");
        a.publish("ciao 3 da a");

        console.log();
        console.log("Marta pubblica \"ciao 3 da m\"");
        m.publish("ciao 3 da m");

        console.log();
        console.log("Giorgio pubblica \"ciao 3 da g\"");
        u.publish("ciao 3 da g");
        console.log();
        console.log("ricevuti da %s", u.getName());
        console.log(u.queryReceived(4));
        console.log();
        console.log("spediti da %s", u.getName());
        console.log(u.querySent(4));
        console.log();
        console.log();
        console.log("ricevuti da %s", a.getName());
        console.log(a.queryReceived(4));
        console.log();
        console.log("spediti da %s", a.getName());
        console.log(a.querySent(4));
        console.log();
        console.log();
        console.log("ricevuti da %s", m.getName());
        console.log(m.queryReceived(4));
        console.log();
        console.log("spediti da %s", m.getName());
        console.log(m.querySent(4));
        console.log();
    }
}