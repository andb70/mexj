package it.biasutti.mexj;

public class Main {

    public static void main(String[] args) {


        MexJ mexJ = new MexJ(false);
        runTests(mexJ);
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
    public static void runTests(MexJ mexJ) {
        mexJ.processWithExceptions(	"A", 	"2018-05-30 11:47:03", 	"Alice", 	"SIGN-UP"		);
        mexJ.processWithExceptions(	"A", 	"2018-05-30 11:47:05", 	"Mario", 	"SIGN-UP"		);
        mexJ.processWithExceptions(	"A", 	"2018-05-30 11:47:07", 	"Luca", 	"SIGN-UP"		);
        mexJ.processWithExceptions(	"A", 	"2018-05-30 11:47:09", 	"Carlo", 	"SIGN-UP"		);
      /*mexJ.processWithExceptions(	"A", 	"2018-05-30 11:47:11", 	"Carlo", 	"RENAME", 	"Luca"	);
        mexJ.processWithExceptions(	"A", 	"2018-05-30 11:47:13", 	"Carlo", 	"RENAME", 	"Alfonso"	);
        mexJ.processWithExceptions(	"A", 	"2018-05-30 11:47:15", 	"Alice", 	"FOLLOW", 	"Alfonso"	);
        mexJ.processWithExceptions(	"A", 	"2018-05-30 11:47:17", 	"Mario", 	"FOLLOW", 	"Alfonso"	);
        mexJ.processWithExceptions(	"A", 	"2018-05-30 11:47:19", 	"Luca", 	"FOLLOW", 	"Alfonso"	);
        mexJ.processWithExceptions(	"A", 	"2018-05-30 11:47:21", 	"Carlo", 	"FOLLOW", 	"Alfonso"	);
        mexJ.processWithExceptions(	"A", 	"2018-05-30 11:47:23", 	"Alfonso", 	"FOLLOW", 	"Alice"	);
*/      mexJ.processWithExceptions(	"A", 	"2018-05-30 11:47:25", 	"Luca", 	"FOLLOW", 	"Alice"	);
        //mexJ.processWithExceptions(	"A", 	"2018-05-30 11:47:27", 	"Mario", 	"FOLLOW", 	"Alfonso"	);
        mexJ.processWithExceptions(	"A", 	"2018-05-30 11:47:29", 	"Luca", 	"FOLLOW", 	"Carlo"	);
        mexJ.processWithExceptions(	"A", 	"2018-05-30 11:47:31", 	"Carlo", 	"FOLLOW", 	"Mario"	);
        mexJ.processWithExceptions(	"A", 	"2018-05-30 11:47:31", 	"Carlo", 	"FOLLOW", 	"Alice"	);
        mexJ.processWithExceptions(	"A", 	"2018-05-30 11:47:31", 	"Carlo", 	"FOLLOW", 	"Luca"	);
        //mexJ.processWithExceptions(	"A", 	"2018-05-30 11:47:33", 	"Alfonso", 	"FOLLOW", 	"Luca"	);
 /**/
        mexJ.processWithExceptions(	"A", 	"2018-05-30 11:47:31", 	"Carlo", 	"MUTE", 	"Alice"	);
        //mexJ.processWithExceptions(	"A", 	"2018-05-30 11:47:31", 	"Carlo", 	"UNMUTE", 	"Alice"	);

        console.log();
        console.log();

        mexJ.processWithExceptions(	"M", 	"2018-05-30 12:26:12", 	"Alice", 	"Messaggio 00001"	);
        mexJ.processWithExceptions(	"M", 	"2018-05-30 12:26:14", 	"Mario", 	"Messaggio 00002"	);
        mexJ.processWithExceptions(	"M", 	"2018-05-30 12:26:16", 	"Luca", 	"Messaggio 00003"	);
        mexJ.processWithExceptions(	"M", 	"2018-05-30 12:26:18", 	"Carlo", 	"Messaggio 00004"	);
        mexJ.processWithExceptions(	"M", 	"2018-05-30 12:26:20", 	"Carlo", 	"Messaggio 00005"	);
        mexJ.processWithExceptions(	"M", 	"2018-05-30 12:26:22", 	"Carlo", 	"Messaggio 00006"	);
        mexJ.processWithExceptions(	"M", 	"2018-05-30 12:26:24", 	"Alice", 	"Messaggio 00007"	);
        mexJ.processWithExceptions(	"M", 	"2018-05-30 12:26:26", 	"Mario", 	"Messaggio 00008"	);
        mexJ.processWithExceptions(	"M", 	"2018-05-30 12:26:28", 	"Luca", 	"Messaggio 00009"	);
        mexJ.processWithExceptions(	"M", 	"2018-05-30 12:26:30", 	"Carlo", 	"Messaggio 00010"	);
       //mexJ.processWithExceptions(	"M", 	"2018-05-30 12:26:32", 	"Alfonso", 	"Messaggio 00011"	);
        mexJ.processWithExceptions(	"M", 	"2018-05-30 12:26:34", 	"Luca", 	"Messaggio 00012"	);
        mexJ.processWithExceptions(	"M", 	"2018-05-30 12:26:36", 	"Mario", 	"Messaggio 00013"	);
        mexJ.processWithExceptions(	"M", 	"2018-05-30 12:26:38", 	"Luca", 	"Messaggio 00014"	);
        mexJ.processWithExceptions(	"M", 	"2018-05-30 12:26:40", 	"Carlo", 	"Messaggio 00015"	);
        //mexJ.processWithExceptions(	"M", 	"2018-05-30 12:26:42", 	"Alfonso", 	"Messaggio 00016"	);
        mexJ.processWithExceptions(	"A", 	"2018-05-30 11:47:31", 	"Carlo", 	"UNMUTE", 	"Alice"	);
        console.log();
        console.log();
        mexJ.processWithExceptions(	"M", 	"2018-05-30 12:26:12", 	"Alice", 	"Messaggio 00001"	);
        mexJ.processWithExceptions(	"M", 	"2018-05-30 12:26:14", 	"Mario", 	"Messaggio 00002"	);
        mexJ.processWithExceptions(	"M", 	"2018-05-30 12:26:16", 	"Luca", 	"Messaggio 00003"	);
        mexJ.processWithExceptions(	"M", 	"2018-05-30 12:26:18", 	"Carlo", 	"Messaggio 00004"	);
        mexJ.processWithExceptions(	"M", 	"2018-05-30 12:26:20", 	"Carlo", 	"Messaggio 00005"	);
        mexJ.processWithExceptions(	"M", 	"2018-05-30 12:26:22", 	"Carlo", 	"Messaggio 00006"	);
        mexJ.processWithExceptions(	"M", 	"2018-05-30 12:26:24", 	"Alice", 	"Messaggio 00007"	);
        mexJ.processWithExceptions(	"M", 	"2018-05-30 12:26:26", 	"Mario", 	"Messaggio 00008"	);
        mexJ.processWithExceptions(	"M", 	"2018-05-30 12:26:28", 	"Luca", 	"Messaggio 00009"	);
        mexJ.processWithExceptions(	"M", 	"2018-05-30 12:26:30", 	"Carlo", 	"Messaggio 00010"	);
        //mexJ.processWithExceptions(	"M", 	"2018-05-30 12:26:32", 	"Alfonso", 	"Messaggio 00011"	);
        mexJ.processWithExceptions(	"M", 	"2018-05-30 12:26:34", 	"Luca", 	"Messaggio 00012"	);
        mexJ.processWithExceptions(	"M", 	"2018-05-30 12:26:36", 	"Mario", 	"Messaggio 00013"	);
        mexJ.processWithExceptions(	"M", 	"2018-05-30 12:26:38", 	"Luca", 	"Messaggio 00014"	);
        mexJ.processWithExceptions(	"M", 	"2018-05-30 12:26:40", 	"Carlo", 	"Messaggio 00015"	);


    }
}