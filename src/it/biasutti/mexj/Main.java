package it.biasutti.mexj;

import java.io.File;

public class Main {

    public static void main(String[] args) {
        MexJ mexJ = new MexJ(false);
        /*runTests1(mexJ);*/


        /*
            si assume che i nomi dei files arrivino nella forma @##.txt
            e che si trovino nella cartella data della user dir
         */
        String path = System.getProperty("user.dir") + "\\data\\%s";
        path = path.replaceAll("\\\\", "/");

        /*
        deferred = true per eseguire tutte le operazioni dopo averle caricate
        deferred = false per eseguire ogni azione appena dopo averla caricata
         */
        boolean deferred = true;
        for (String arg : args) {
            console.log(arg);
            mexJ.loadActionsFromFile(String.format(path, arg), deferred);
        }
        if (deferred) {
            mexJ.shuffle();
            mexJ.performAndDiscard();
        }

    }

    public static void runTests1(MexJ mexJ) {
        mexJ.signUp("Primo user");
        mexJ.signUp("secondo user").follow("Primo user");
        mexJ.getUsers("Primo user").publish("ciao secondo");

        mexJ.signUp("terzo user");
        mexJ.signUp("quarto user");
        mexJ.getUsers("terzo user").follow("Primo user");
        mexJ.getUsers("Primo user").follow("terzo user");
        mexJ.getUsers("secondo user").mute("Primo user");
        mexJ.getUsers("Primo user").publish("Benvenuti ai presenti");
        mexJ.getUsers("Primo user").publish("anche agli assenti");
        mexJ.getUsers("Primo user").publish("all'assenzio");
        console.log(mexJ.getUsers("secondo user").queryReceived(5));
        console.log(mexJ.getUsers("terzo user").queryReceived(5));

        mexJ.listUsersAndFollowers();
        mexJ.listUsersAndFollowees();
        mexJ.getUsers("secondo user").unmute("Primo user");
        mexJ.getUsers("Primo user").publish("e allo zio");

        mexJ.listUsersAndFollowers();
        mexJ.listUsersAndFollowees();
        console.log(mexJ.getUsers("Primo user").queryReceived(5));
        console.log(mexJ.getUsers("secondo user").queryReceived(5));
        console.log(mexJ.getUsers("terzo user").queryReceived(5));
        console.log(mexJ.getUsers("quarto user").queryReceived(5));
    }

    public static void runTests2(MexJ mexJ) {

        mexJ.processWithExceptions("A", "2018-05-30 11:47:03", "Alice", "SIGN-UP");
        mexJ.processWithExceptions("A", "2018-05-30 11:47:05", "Mario", "SIGN-UP");
        mexJ.processWithExceptions("A", "2018-05-30 11:47:09", "Carlo", "SIGN-UP");
        mexJ.processWithExceptions("A", "2018-05-30 11:47:07", "Luca", "SIGN-UP");

        console.log();
        mexJ.listUsers();
        console.log();
        mexJ.processWithExceptions("A", "2018-05-30 11:47:13", "Luca", "RENAME", "Alfonso");

        console.log();
        mexJ.listUsers();
        console.log();
        mexJ.processWithExceptions("A", "2018-05-30 11:47:07", "Luca", "SIGN-UP");

        console.log();
        mexJ.listUsers();
        console.log();

        mexJ.processWithExceptions("A", "2018-05-30 11:47:31", "Carlo", "FOLLOW", "Mario");
        mexJ.processWithExceptions("A", "2018-05-30 11:47:31", "Carlo", "FOLLOW", "Alice");
        mexJ.processWithExceptions("A", "2018-05-30 11:47:31", "Carlo", "FOLLOW", "Alice");
        mexJ.processWithExceptions("A", "2018-05-30 11:47:31", "Carlo", "FOLLOW", "Luca");
        mexJ.processWithExceptions("A", "2018-05-30 11:47:31", "Carlo", "FOLLOW", "Alfonso");
        mexJ.processWithExceptions("A", "2018-05-30 11:47:31", "Carlo", "FOLLOW", "Mario");
        mexJ.processWithExceptions("A", "2018-05-30 11:47:31", "Carlo", "FOLLOW", "Carlo");
        mexJ.processWithExceptions("A", "2018-05-30 11:47:31", "Alice", "FOLLOW", "Alice");
        mexJ.processWithExceptions("A", "2018-05-30 11:47:31", "Alice", "FOLLOW", "Luca");
        mexJ.processWithExceptions("A", "2018-05-30 11:47:31", "Alice", "FOLLOW", "Alfonso");
        mexJ.processWithExceptions("A", "2018-05-30 11:47:31", "Alice", "FOLLOW", "Mario");
        mexJ.processWithExceptions("A", "2018-05-30 11:47:31", "Alice", "FOLLOW", "Carlo");
        mexJ.processWithExceptions("A", "2018-05-30 11:47:31", "Mario", "FOLLOW", "Alfonso");
        mexJ.processWithExceptions("A", "2018-05-30 11:47:31", "Alfonso", "FOLLOW", "Alice");
        mexJ.processWithExceptions("A", "2018-05-30 11:47:31", "Alfonso", "FOLLOW", "Luca");
        mexJ.processWithExceptions("A", "2018-05-30 11:47:31", "Alfonso", "FOLLOW", "Alfonso");

        console.log();
        mexJ.listUsersAndFollowers();
        mexJ.listUsersAndFollowees();

        console.log("Unfollow");

        mexJ.processWithExceptions("A", "2018-05-30 11:47:31", "Carlo", "UNFOLLOW", "Mario");
        mexJ.processWithExceptions("A", "2018-05-30 11:47:31", "Carlo", "UNFOLLOW", "Alice");
        mexJ.processWithExceptions("A", "2018-05-30 11:47:31", "Carlo", "UNFOLLOW", "Alice");
        mexJ.processWithExceptions("A", "2018-05-30 11:47:31", "Carlo", "UNFOLLOW", "Luca");
        mexJ.processWithExceptions("A", "2018-05-30 11:47:31", "Carlo", "UNFOLLOW", "Alfonso");
        mexJ.processWithExceptions("A", "2018-05-30 11:47:31", "Carlo", "UNFOLLOW", "Mario");
        mexJ.processWithExceptions("A", "2018-05-30 11:47:31", "Carlo", "UNFOLLOW", "Carlo");
        mexJ.processWithExceptions("A", "2018-05-30 11:47:31", "Alice", "UNFOLLOW", "Alice");
        mexJ.processWithExceptions("A", "2018-05-30 11:47:31", "Alice", "UNFOLLOW", "Luca");
        mexJ.processWithExceptions("A", "2018-05-30 11:47:31", "Alice", "UNFOLLOW", "Alfonso");
        mexJ.processWithExceptions("A", "2018-05-30 11:47:31", "Alice", "UNFOLLOW", "Mario");
        mexJ.processWithExceptions("A", "2018-05-30 11:47:31", "Alice", "UNFOLLOW", "Carlo");
        mexJ.processWithExceptions("A", "2018-05-30 11:47:31", "Mario", "UNFOLLOW", "Alfonso");
        mexJ.processWithExceptions("A", "2018-05-30 11:47:31", "Alfonso", "UNFOLLOW", "Alice");
        mexJ.processWithExceptions("A", "2018-05-30 11:47:31", "Alfonso", "UNFOLLOW", "Luca");
        mexJ.processWithExceptions("A", "2018-05-30 11:47:31", "Alfonso", "UNFOLLOW", "Alfonso");

        console.log();
        mexJ.listUsersAndFollowers();
        mexJ.listUsersAndFollowees();

        mexJ.processWithExceptions("A", "2018-05-30 11:47:31", "Carlo", "FOLLOW", "Mario");
        mexJ.processWithExceptions("A", "2018-05-30 11:47:31", "Carlo", "FOLLOW", "Alice");
        mexJ.processWithExceptions("A", "2018-05-30 11:47:31", "Carlo", "FOLLOW", "Luca");
        mexJ.processWithExceptions("A", "2018-05-30 11:47:31", "Carlo", "FOLLOW", "Alfonso");
        mexJ.processWithExceptions("A", "2018-05-30 11:47:31", "Alice", "MUTE", "Carlo");
        mexJ.processWithExceptions("A", "2018-05-30 11:47:31", "Carlo", "MUTE", "Alfonso");
        mexJ.processWithExceptions("A", "2018-05-30 11:47:31", "Carlo", "MUTE", "Alice");
        console.log();
        mexJ.listUsersAndFollowers();
        mexJ.listUsersAndFollowees();
        console.log("Unmute");
        mexJ.processWithExceptions("A", "2018-05-30 11:47:31", "Alice", "UNMUTE", "Carlo");
        mexJ.processWithExceptions("A", "2018-05-30 11:47:31", "Carlo", "UNMUTE", "Alfonso");
        mexJ.processWithExceptions("A", "2018-05-30 11:47:31", "Carlo", "UNMUTE", "Alice");


        console.log();
        mexJ.listUsersAndFollowers();
        mexJ.listUsersAndFollowees();

 /*      console.log();

        // mexJ.processWithExceptions(	"M", 	"2018-05-30 12:26:12", 	"Alice", 	"Messaggio 00001"	);
        // mexJ.processWithExceptions(	"M", 	"2018-05-30 12:26:26", 	"Mario", 	"Messaggio 00008"	);
        mexJ.processWithExceptions(	"A", 	"2018-05-30 11:47:31", 	"Carlo", 	"MUTE", 	"Alice"	);
        // mexJ.processWithExceptions(	"M", 	"2018-05-30 12:26:24", 	"Alice", 	"Messaggio 00007"	);
        // mexJ.processWithExceptions(	"M", 	"2018-05-30 12:26:14", 	"Mario", 	"Messaggio 00002"	);
        // mexJ.processWithExceptions(	"A", 	"2018-05-30 11:47:31", 	"Carlo", 	"UNFOLLOW", 	"Mario"	);
        mexJ.processWithExceptions(	"A", 	"2018-05-30 11:47:31", 	"Carlo", 	"UNMUTE", 	"Alice"	);
        // mexJ.processWithExceptions(	"M", 	"2018-05-30 12:26:12", 	"Alice", 	"Messaggio 00001"	);
        // mexJ.processWithExceptions(	"M", 	"2018-05-30 12:26:36", 	"Mario", 	"Messaggio 00013"	);

        mexJ.processWithExceptions(	"A", 	"2018-05-30 11:47:11", 	"Carlo", 	"RENAME", 	"Luca"	);

        console.log();
        mexJ.listUsers();
        console.log();

        mexJ.processWithExceptions(	"A", 	"2018-05-30 11:47:15", 	"Alice", 	"FOLLOW", 	"Alfonso"	);
        mexJ.processWithExceptions(	"A", 	"2018-05-30 11:47:17", 	"Mario", 	"FOLLOW", 	"Alfonso"	);

        console.log();
        console.log();
        mexJ.processWithExceptions(	"A", 	"2018-05-30 11:47:23", 	"Alfonso", 	"FOLLOW", 	"Alice"	);
        mexJ.processWithExceptions(	"A", 	"2018-05-30 11:47:27", 	"Mario", 	"FOLLOW", 	"Alfonso"	);
        mexJ.processWithExceptions(	"M", 	"2018-05-30 12:26:14", 	"Mario", 	"Messaggio 00002"	);
        mexJ.processWithExceptions(	"M", 	"2018-05-30 12:26:16", 	"Luca", 	"Messaggio 00003"	);
        mexJ.processWithExceptions(	"M", 	"2018-05-30 12:26:18", 	"Carlo", 	"Messaggio 00004"	);
        mexJ.processWithExceptions(	"M", 	"2018-05-30 12:26:20", 	"Carlo", 	"Messaggio 00005"	);
        mexJ.processWithExceptions(	"M", 	"2018-05-30 12:26:22", 	"Carlo", 	"Messaggio 00006"	);
        mexJ.processWithExceptions(	"M", 	"2018-05-30 12:26:28", 	"Luca", 	"Messaggio 00009"	);
        mexJ.processWithExceptions(	"M", 	"2018-05-30 12:26:30", 	"Carlo", 	"Messaggio 00010"	);
        mexJ.processWithExceptions(	"M", 	"2018-05-30 12:26:24", 	"Alice", 	"Messaggio 00007"	);
        mexJ.processWithExceptions(	"M", 	"2018-05-30 12:26:26", 	"Mario", 	"Messaggio 00008"	);
        mexJ.processWithExceptions(	"M", 	"2018-05-30 12:26:28", 	"Luca", 	"Messaggio 00009"	);
        mexJ.processWithExceptions(	"M", 	"2018-05-30 12:26:30", 	"Carlo", 	"Messaggio 00010"	);
        mexJ.processWithExceptions(	"M", 	"2018-05-30 12:26:32", 	"Alfonso", 	"Messaggio 00011"	);
*/
        /*
         *************************************************
         */
        /*mexJ.processWithExceptions(	"A", 	"2018-05-30 11:47:21", 	"Carlo", 	"FOLLOW", 	"Mario"	);
        mexJ.processWithExceptions(	"A", 	"2018-05-30 11:47:19", 	"Luca", 	"FOLLOW", 	"Alfonso"	);
         mexJ.processWithExceptions(	"A", 	"2018-05-30 11:47:25", 	"Luca", 	"FOLLOW", 	"Alice"	);
        mexJ.processWithExceptions(	"A", 	"2018-05-30 11:47:29", 	"Luca", 	"FOLLOW", 	"Carlo"	);
*/
/*
       //mexJ.processWithExceptions(	"M", 	"2018-05-30 12:26:32", 	"Alfonso", 	"Messaggio 00011"	);
        mexJ.processWithExceptions(	"M", 	"2018-05-30 12:26:34", 	"Luca", 	"Messaggio 00012"	);
        mexJ.processWithExceptions(	"M", 	"2018-05-30 12:26:36", 	"Mario", 	"Messaggio 00013"	);
        mexJ.processWithExceptions(	"M", 	"2018-05-30 12:26:38", 	"Luca", 	"Messaggio 00014"	);
        mexJ.processWithExceptions(	"M", 	"2018-05-30 12:26:40", 	"Carlo", 	"Messaggio 00015"	);
        //mexJ.processWithExceptions(	"M", 	"2018-05-30 12:26:42", 	"Alfonso", 	"Messaggio 00016"	);
        mexJ.processWithExceptions(	"A", 	"2018-05-30 11:47:31", 	"Carlo", 	"UNMUTE", 	"Alice"	);
        console.log();
        console.log();
        mexJ.processWithExceptions(	"M", 	"2018-05-30 12:26:16", 	"Luca", 	"Messaggio 00003"	);
        mexJ.processWithExceptions(	"M", 	"2018-05-30 12:26:18", 	"Carlo", 	"Messaggio 00004"	);
        mexJ.processWithExceptions(	"M", 	"2018-05-30 12:26:20", 	"Carlo", 	"Messaggio 00005"	);
        mexJ.processWithExceptions(	"M", 	"2018-05-30 12:26:22", 	"Carlo", 	"Messaggio 00006"	);
        mexJ.processWithExceptions(	"M", 	"2018-05-30 12:26:34", 	"Luca", 	"Messaggio 00012"	);
        mexJ.processWithExceptions(	"M", 	"2018-05-30 12:26:38", 	"Luca", 	"Messaggio 00014"	);
        mexJ.processWithExceptions(	"M", 	"2018-05-30 12:26:40", 	"Carlo", 	"Messaggio 00015"	);
*/

/*mexJ.processWithExceptions(	"A", 	"2018-05-30 11:47:31", 	"Carlo", 	"FOLLOW", 	"Luca"	);
        mexJ.processWithExceptions(	"A", 	"2018-05-30 11:47:33", 	"Alfonso", 	"FOLLOW", 	"Luca"	);
 */
    }

    public static void runTests3(MexJ mexJ) {

/*        String path ="C:/Users/andb7/IdeaProjects/tissino/Esame finale/MexJ/data/data/%s";
        // mexJ.loadActionsFromFile(String.format(path, "A03.txt"));
        // mexJ.loadActionsFromFile(String.format(path, "A02.txt"));
        // mexJ.loadActionsFromFile(String.format(path, "M01.txt"));
        // mexJ.loadActionsFromFile(String.format(path, "Q01.txt"));
        // mexJ.loadActionsFromFile(String.format(path, "R02.txt"));
         mexJ.loadActionsFromFile(String.format(path, "A01.txt"));
        // mexJ.loadActionsFromFile(String.format(path, "M02.txt"));
        // mexJ.loadActionsFromFile(String.format(path, "M03.txt"));
        // mexJ.loadActionsFromFile(String.format(path, "Q01.txt"));
        //mexJ.loadActionsFromFile(String.format(path, "Q03.txt"));
        // mexJ.loadActionsFromFile(String.format(path, "R03.txt"));
        mexJ.shuffle();

        mexJ.loadActionsFromFile(String.format(path, "Q03.txt"));
        // mexJ.loadActionsFromFile(String.format(path, "R03.txt"));
        mexJ.performAndDiscard();
        console.log("Azioni rimase: %d",mexJ.actionCount());*/

    }
}