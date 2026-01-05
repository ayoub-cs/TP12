package org.example.security;

import org.apache.wss4j.common.ext.WSPasswordCallback;

import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.UnsupportedCallbackException;
import java.io.IOException;
import java.util.Map;

/**
 * CallbackHandler utilisé par WS-Security pour la validation
 * des identifiants (UsernameToken).
 */
public class UsernameTokenCallbackHandler implements CallbackHandler {

    private final Map<String, String> credentials;

    public UsernameTokenCallbackHandler(Map<String, String> credentials) {
        this.credentials = credentials;
    }

    @Override
    public void handle(Callback[] callbacks)
            throws IOException, UnsupportedCallbackException {

        for (Callback callback : callbacks) {
            if (callback instanceof WSPasswordCallback passwordCallback) {

                String username = passwordCallback.getIdentifier();
                String expectedPassword = credentials.get(username);

                if (expectedPassword == null) {
                    throw new UnsupportedCallbackException(
                            callback,
                            "Utilisateur inconnu : " + username
                    );
                }

                passwordCallback.setPassword(expectedPassword);
            }
        }
    }
}
