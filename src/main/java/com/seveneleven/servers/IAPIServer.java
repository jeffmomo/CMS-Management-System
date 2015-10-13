package com.seveneleven.servers;

import java.util.function.Consumer;

/**
 * Created by mdl94 on 12/10/2015.
 */
public interface IAPIServer {

    public void registerAction(String key, Consumer<String> func);

    public void broadcast(String apiType, String message);

    public void sendTargeted(String target, String message);
}
