/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SimulinkApplicationProject.ApplicationData;

import java.util.ArrayList;

/**
 *
 * @author BRAUL
 */
public class ApplicationSignalsCollection extends ArrayList<ApplicationSignal>
{
    private static long LastIdentifierKey;
    
    public ApplicationSignalsCollection()
    {
        LastIdentifierKey = 0;
    }
    
    @Override
    public boolean add(ApplicationSignal e) throws RuntimeException
    {
        if(LastIdentifierKey < Long.MAX_VALUE)
        {
            LastIdentifierKey++;
            e.setIdentiferKey(LastIdentifierKey);
            return (super.add(e));
        }
        else
        {
            throw new RuntimeException("Maximum signals count reached !");
        }
    }
    
    public long getSignalIdentifierKey(String SignalName)
    {
        for(ApplicationSignal Signal: this)
        {
           if(Signal.getName().equals(SignalName))
           {
               return(Signal.getIdentiferKey());
           }
        }
        
        return(-1);
    }
    
    public ApplicationSignal getSignalByIdentifierKey(long KeyId)
    {
        for (ApplicationSignal signal : this)
        {
            if (signal.getIdentiferKey() == KeyId)
            {
                return (signal);
            }
        }

        return (null);
    }
}
