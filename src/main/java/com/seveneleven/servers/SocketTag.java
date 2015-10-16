package com.seveneleven.servers;

/**
 * Created by mdl94 on 13/10/2015.
 */
public class SocketTag
{
    // The socket's tag
    public String tag;
    // Socket privileges, for listen-only sockets it is 'r'
    public String privileges;
    // The unique identifier given to this socket. Used for sendMessage method in APIServer
    public String identifier;


    public SocketTag(String tag, String privileges, String identifier)
    {
        this.tag = tag;
        this.privileges = privileges;
        this.identifier = identifier;
    }
}
