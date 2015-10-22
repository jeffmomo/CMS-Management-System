package com.seveneleven.servers;

import java.util.function.Consumer;

/**
 * Created by mdl94 on 12/10/2015.
 */
public interface IAPIServer {

    /**
     * Defines a response when API message received
     * @param key The key of the API message
     * @param func The function to execute
     */
    public void registerAction(String key, Consumer<String> func);

    /**
     * Broadcasts a message to all connected clients
     * @param tag The tag of the message (it can only be received by sockets listening to the same tag)
     * @param message The message, which should be a JSON object in string format
     */
    public void broadcast(String tag, String message);

    /**
     * Sends a specific target a message
     * @param identifier The identifier of the target
     * @param message The message which should be a JSON object in string format
     */
    public void sendTargeted(String identifier, String message);
}
