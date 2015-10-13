package com.seveneleven.publishers;

import com.seveneleven.Manager;

/**
 * Created by mdl94 on 22/09/2015.
 */
public class Publisher
{
    public static final String INCIDENT_INFO = "iinfo";
    public static final String HAZARD_INFO = "hinfo";
    public static final String TEST_ECHO = "echo";
    public static final String TEST_WRITEOUT = "dank";

    private Manager _manager;
    private String _publication;

    public Publisher(Manager manager, String publication)
    {
        _manager = manager;
        _publication = publication;
    }
    public Publisher(String publication)
    {
        _publication = publication;
    }

    public void connect(Manager manager)
    {
        _manager = manager;
    }

    protected void publish(Object data)
    {
        _manager.callSubscribers(_publication, data);
    }

    protected void publish(String publication, Object data)
    {
        _manager.callSubscribers(publication, data);
    }

    public String getPublication()
    {
        return _publication;
    }


}
