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
public class ApplicationParametersCollection extends ArrayList<ApplicationParameter>
{
    private static long LastIdentifierKey;
    
    public ApplicationParametersCollection()
    {
        LastIdentifierKey = 0;
    }
    
    @Override
    public boolean add(ApplicationParameter e) throws RuntimeException
    {
        if (LastIdentifierKey < Long.MAX_VALUE)
        {
            LastIdentifierKey++;
            e.setIdentiferKey(LastIdentifierKey);
            return (super.add(e));
        }
        else
        {
            throw new RuntimeException("Maximum parameters count reached !");
        }   
    }
    
    public long getParameterIdentifierKey(String ParameterName)
    {
        for(ApplicationParameter parameter: this)
        {
            if(parameter.getName().equals(ParameterName))
            {
                return(parameter.getIdentiferKey());
            }
        }
        
        return(-1);
    }
    
    public ApplicationParameter getApplicationParameterByIdentifierKey(long KeyId)
    {
        for(ApplicationParameter parameter: this)
        {
            if(parameter.getIdentiferKey()==KeyId)
            {
                return (parameter);
            }
        }
        
        return (null);
    }
}
