package com.seveneleven.publishers;

import com.seveneleven.Manager;

/**
 * Created by mdl94 on 22/09/2015.
 */
public class Publisher
{
    // Constants for the strings indicating publications
    public static final String INCIDENT_INFO = "iinfo";
    public static final String HAZARD_INFO = "hinfo";
    public static final String TEST_ECHO = "echo";
    public static final String TEST_WRITEOUT = "dank";

    // Declares the associated manager and publication
    private Manager _manager;
    private String _publication = "default";

    /**
     * Creates the publisher object which invokes all subscribers listening on the given publication string
     * @param manager The associated central manager
     * @param publication The publication to broadcast on
     */
    public Publisher(Manager manager, String publication)
    {
        _manager = manager;
        _publication = publication;
    }

    /**
     * Crates the publisher without associating it with a manager
     * @param publication The publication to broadcast on
     */
    public Publisher(String publication)
    {
        _publication = publication;
    }

    /**
     * Connects to the given manager
     * @param manager
     */
    public void connect(Manager manager)
    {
        _manager = manager;
    }

    /**
     * Publishes data
     * @param data The data to be published
     */
    protected void publish(Object data)
    {
        if(_manager != null)
            _manager.callSubscribers(_publication, data);
        else
            System.err.println("No manager connected to yet");
    }

    /**
     * Publishes data through alternative publication
     * @param publication A different publication to trigger subscribers on
     * @param data The data published
     */
    protected void publish(String publication, Object data)
    {
        _manager.callSubscribers(publication, data);
    }

    /**
     * Gets the publication string
     * @return
     */
    public String getPublication()
    {
        return _publication;
    }


}
