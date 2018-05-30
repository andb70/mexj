package it.biasutti.mexj;

public class ActionTester extends ActionExecutor {
    public ActionTester(IUsers server) {
        super(server);
    }

    @Override
    public boolean signUp(Action action) {
        console.log("Sign up dell'utente %s, utenti: %d", action.getUserName(), _server.size());
        /*
        risultati attesi
            utente esiste
                get(userName) = valid user
                u.signed = true
            utente non esiste
                size++
                get(userName) = valid user
                u.signed = true
         */
        int size = _server.size();
        boolean signed = (_server.findByName(action.getUserName()) > -1);

        IUser u = _server.signUp(action.getUserName());

        if (signed && size != _server.size()) {
            return false;
        }
        if (!signed && size != _server.size() - 1) {
            return false;
        }
        if (_server.findByName(action.getUserName()) == -1) {
            return false;
        }
        if (!u.signed()) {
            return false;
        }

        console.log("Sign up completo");
        return true;
    }

    @Override
    public boolean rename(Action action) {
        console.log("Rename dell'utente %s in %s, utenti: %d", action.getUserName(), action.getDest(), _server.size());
        /*
         risultati attesi
            utente esiste
                dest non esiste
                    u = get(userName)
                    rename
                    d = u
                    u.getName = dest
                dest esiste
                    get(userName) = valid user
                    get(dest) = valid user
                    d != u
                    rename
                    d = u
            utente non esiste

        */
        boolean userExixt = (_server.findByName(action.getUserName()) > -1);
        boolean destExist = (_server.findByName(action.getDest()) > -1);

        IUser u = _server.getUser(action.getUserName())
                .renameAs(action.getDest());

        boolean userAfter = (_server.findByName(action.getUserName()) > -1);
        boolean destAfter = (_server.findByName(action.getDest()) > -1);

        if (!userExixt) {
            // rename non possibile
            // u presente
            if (userAfter)
                return false;
            if (destAfter != destExist)
                return false;
        }

        if (!destExist) {
            // rename possibile
            // u non ha cambiato nome
            if (u.getName().compareTo(action.getDest()) != 0)
                return false;
            // u ancora presente
            if (userAfter)
                return false;
        }

        if (destExist) {
            // dest già nel sistema
            // u ha cambiato nome
            if (u.getName().compareTo(action.getDest()) == 0)
                return false;
            // u non presente
            if (!userAfter)
                return false;
        }
        // d non presente
        if (!destAfter)
            return false;
        console.log("Rename completo");
        return true;
    }

    @Override
    public boolean follow(Action action) {
        console.log("%s follow %s", action.getUserName(), action.getDest());
         /*
        risultati attesi
            utente esiste
                dest esiste
                    d.followers ++
                    d.followers.getItem(action.getDest) >> true
                dest non esiste
                    d = fake
            utente non esiste

        */

        IUser u = _server.getUser(action.getUserName());
        IUser d = _server.getUser(action.getDest());
        int preFollowers = d.getFollowers().size();

        int uID = u.getId();
        int dID = d.getId();

        IUser r = u.follow(d);

        int postFollowers = d.getFollowers().size();
        int postuID = u.getId();
        int postdID = d.getId();

        // utente esiste
        // dest esiste
        if (uID>-1 && dID>-1) {
           if (!(postFollowers==preFollowers+1))
               return false;

           IUser p = (IUser) d.getFollowers()._items.get(postFollowers - 1);
           if (p != u) {
               return false;
           }

        }
        console.log("Follow completo");
        return true;

    }


    @Override
    public boolean unfollow(Action action) {
         /* TODO: controllare che tra i followers
            di    action.getDest()   NON sia presente   action.getUserName()
        */
        console.log("%s unfollow %s", action.getUserName(), action.getDest());
        IUser u = _server.getUser(action.getUserName()).unfollow(action.getDest());
        if (u.signed()) {
            console.log("UNFollow completo");
            return true;
        }
        console.log("UNFollow fallito");
        return false;
    }

    @Override
    public boolean mute(Action action) {
         /* TODO: controllare che tra i followers
            di    action.getDest()   sia presente   action.getUserName()
            e che abbia il mute = true
        */
        console.log("%s mute %s", action.getUserName(), action.getDest());
        IUser u = _server.getUser(action.getUserName()).mute(action.getDest());
        if (u.signed()) {
            console.log("Mute completo");
            return true;
        }
        console.log("Mute fallito");
        return false;
    }

    @Override
    public boolean unmute(Action action) {
         /* TODO: controllare che tra i followers
            di    action.getDest()   sia presente   action.getUserName()
            e che abbia il mute = false
        */
        console.log("%s unmute %s", action.getUserName(), action.getDest());
        IUser u = _server.getUser(action.getUserName()).unmute(action.getDest());
        if (u.signed()) {
            console.log("UNMute completo");
            return true;
        }
        console.log("UNMute fallito");
        return false;
    }

    @Override
    public boolean publish(Action action) {
         /* TODO: controllare che il messagggio sia tra i SENT di action.getUserName()
            e tra i received dei followers
         */
        console.log("l'utente %s pubblica %s", action.getUserName(), action.getMessage());
        IUser u = _server.getUser(action.getUserName()).publish(action.getMessage());
        if (u.signed()) {
            console.log("Messaggio completo");
            return true;
        }
        console.log("Messaggio fallito");
        return false;
    }

    @Override
    public boolean querySent(Action action) {
        console.log("Richiesta di %d messaggi di %s", action.getCount(), action.getUserName());
        IUser u = _server.getUser(action.getUserName());
        String s = u.querySent(action.getCount());
        if (u.signed()) {
            console.log("Richiesta messaggi completo");
            return true;
        }
        console.log("Richiesta messaggi fallita");
        return true;
    }

    @Override
    public boolean queryReceived(Action action) {
        console.log("Richiesta di %d messaggi destinati %s", action.getCount(), action.getUserName());
        IUser u = _server.getUser(action.getUserName());
        String s = u.queryReceived(action.getCount());
        if (u.signed()) {
            console.log("Richiesta messaggi completo");
            return true;
        }
        console.log("Richiesta messaggi fallita");
        return true;
    }
}
