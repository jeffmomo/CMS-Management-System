package com.seveneleven.servers;

/**
 * Created by mdl94 on 13/10/2015.
 */
public class SocketTag
{
    public String tag;
    public String privileges;
    public String identifier;

    public SocketTag(String tag, String privileges, String identifier)
    {
        this.tag = tag;
        this.privileges = privileges;
        this.identifier = identifier;
    }
}
